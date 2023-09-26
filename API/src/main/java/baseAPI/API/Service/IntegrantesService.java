package baseAPI.API.Service;

import baseAPI.API.DTO.BandaDTO;
import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Banda;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Model.Musica;
import baseAPI.API.Repository.BandaRepository;
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
public class IntegrantesService {


    @Autowired
    private IntegranteRepository repository;

    public List<Integrante> listar(){
        try {
            repository.findAll();
        }catch (Exception e) {
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Integrante buscarPorId(Long id) {
        try {
            repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<byte[]> verImagemPorId(long id) throws IOException, SQLException {
        Integrante entidade = repository.findById(id).get();
        byte[] imageBytes = null;
        imageBytes = entidade.getFoto().getBytes(1, (int) entidade.getFoto().length());
        return ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


    public IntegranteDTO salvar(IntegranteDTO integranteDTO, MultipartFile file) throws SQLException, IOException
    {
        try {
            Integrante entidade = new Integrante();
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                integranteDTO.setFoto(blob);
            }
            BeanUtils.copyProperties(integranteDTO, entidade);
            repository.save(entidade);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }


    public IntegranteDTO editar(Long id, IntegranteDTO integranteDTO, MultipartFile file) throws SQLException, IOException
    {
        try {
            if(repository.existsById(id))
            {
                Integrante entidade = new Integrante();
                if(!file.isEmpty()){
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    integranteDTO.setFoto(blob);
                }
                BeanUtils.copyProperties(integranteDTO, entidade);
                repository.save(entidade);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public IntegranteDTO deletar(Long id)
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
