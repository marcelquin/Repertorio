package baseAPI.API.Service;

import baseAPI.API.DTO.BandaDTO;
import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.Model.Banda;
import baseAPI.API.Model.Eventos;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Repository.BandaRepository;
import baseAPI.API.Repository.EventosRepository;
import baseAPI.API.Repository.IntegranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
public class BandaService {

    @Autowired
    private BandaRepository repository;
    @Autowired
    private IntegranteRepository iRepository;
    @Autowired
    private EventosRepository eRepositort;

    public List<Banda> listar(){
        try {
            repository.findAll();
        }catch (Exception e) {
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Banda buscarPorId(Long id) {
        try {
            repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<byte[]> verImagemPorId(long id) throws IOException, SQLException {
        Banda entidade = repository.findById(id).get();
        byte[] imageBytes = null;
        imageBytes = entidade.getLogo().getBytes(1, (int) entidade.getLogo().length());
        return ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


    public BandaDTO salvar(BandaDTO bandaDTO)
    {
        try {
            Banda entidade = new Banda();
            BeanUtils.copyProperties(bandaDTO, entidade);
            repository.save(entidade);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public  BandaDTO adicionarLogo(Long id, MultipartFile file) throws IOException, SQLException {
        try {
            if (repository.existsById(id)) {
                if (!file.isEmpty()) {
                    Banda banda = new Banda();
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    banda = repository.findById(id).get();
                    banda.setLogo(blob);
                    repository.save(banda);
                }}
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void adicionaEvento(Long idBanda, Long idEvento)
    {
        if(eRepositort.existsById(idEvento)){
            Eventos eventos = eRepositort.findById(idEvento).get();
            List<Eventos> lista = new ArrayList<>();
            lista.add(eventos);
            Banda banda = repository.findById(idBanda).get();
            if(repository.existsById(idBanda))
            {
                banda.setEventos(lista);
                banda.setId(idBanda);
            }
            repository.save(banda);
        }}

    public void adicionaIntegrante(Long idBanda, Long idIntegrante)
    {
        if(iRepository.existsById(idIntegrante)){
            Integrante integrante = iRepository.findById(idIntegrante).get();
            List<Integrante> lista = new ArrayList<>();
            lista.add(integrante);
            Banda banda = repository.findById(idBanda).get();
            if(repository.existsById(idBanda))
            {
                banda.setIntegrantes(lista);
                banda.setId(idBanda);
            }
            repository.save(banda);
        }}

    public BandaDTO editar(Long id, BandaDTO bandaDTO)
    {
        try {
            if(repository.existsById(id))
            {
                Banda entidade = new Banda();
                BeanUtils.copyProperties(bandaDTO, entidade);
                entidade.setId(id);
                repository.save(entidade);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public BandaDTO deletar(Long id)
    {
        try {
            if(repository.existsById(id))
            {
                repository.deleteById(id);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

}
