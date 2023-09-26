package baseAPI.API.Service;

import baseAPI.API.DTO.BandaDTO;
import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.DTO.MusicaDTO;
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


    public BandaDTO salvar(BandaDTO bandaDTO, MultipartFile file) throws SQLException, IOException
    {
        try {
            Banda entidade = new Banda();
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                bandaDTO.setLogo(blob);
            }
            BeanUtils.copyProperties(bandaDTO, entidade);
            repository.save(entidade);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }


    public BandaDTO editar(Long id, BandaDTO bandaDTO, MultipartFile file) throws SQLException, IOException
    {
        try {
            if(repository.existsById(id))
            {
                Banda entidade = new Banda();
                if(!file.isEmpty()){
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    bandaDTO.setLogo(blob);
                }
                BeanUtils.copyProperties(bandaDTO, entidade);
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
