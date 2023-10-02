package baseAPI.API.Service;

import baseAPI.API.DTO.IntegranteDTO;

import baseAPI.API.Model.Integrante;

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


    public IntegranteDTO salvar(IntegranteDTO integranteDTO)
    {
        try {
            Integrante entidade = new Integrante();
            BeanUtils.copyProperties(integranteDTO, entidade);
            repository.save(entidade);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }
    //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    public IntegranteDTO adicionarFoto(Long id, MultipartFile file) throws IOException, SQLException
    {
        try {
            if (repository.existsById(id)) {
            }
                if (!file.isEmpty()) {
                    Integrante integrante = new Integrante();
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    if(repository.existsById(id))
                    {
                        integrante = repository.findById(id).get();
                    }
                    integrante.setFoto(blob);
                    repository.save(integrante);
                }
            }catch(Exception e){
                new RuntimeException("ops, algo deu errado");
                e.getMessage();
            }
            return null;
        }

        public IntegranteDTO editar(Long id, IntegranteDTO integranteDTO)
    {
        try {
            if(repository.existsById(id))
            {
                Integrante entidade = new Integrante();
                BeanUtils.copyProperties(integranteDTO, entidade);
                entidade.setId(id);
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
