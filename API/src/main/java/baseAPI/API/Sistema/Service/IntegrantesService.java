package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.EventosDTO;
import baseAPI.API.Sistema.DTO.IntegranteDTO;
import baseAPI.API.Sistema.DTO.MusicaDTO;
import baseAPI.API.Sistema.Model.Integrante;
import baseAPI.API.Sistema.Repository.IntegranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegrantesService {

    @Autowired
    private IntegranteRepository repository;

    public ResponseEntity<List<IntegranteDTO>> listar() {
        try{
            repository.findAll();
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<List<IntegranteDTO>>) ResponseEntity.ok();
    }

    public ResponseEntity<IntegranteDTO> buscarPorId(Long id){
        try{
            if(id != null) repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<IntegranteDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<IntegranteDTO> BuscarPorNome(String nome){
        try{
            if(nome.length() > 0) repository.findByName(nome);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<IntegranteDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<IntegranteDTO> Salvar(IntegranteDTO integranteDTO, MultipartFile file)  throws IOException {
        try{
            Integrante integrante = new Integrante();
            if (file != null){
                integranteDTO.setFoto(file.getBytes());
            }
            BeanUtils.copyProperties(integranteDTO, integrante);
            repository.save(integrante);

        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<IntegranteDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<IntegranteDTO> Editar(Long id,IntegranteDTO integranteDTO,MultipartFile file){
        try{
            if(id != null){
            if(repository.existsById(id)){
                Integrante integrante = new Integrante();
                if (file != null){
                    integranteDTO.setFoto(file.getBytes());
                }
                BeanUtils.copyProperties(integranteDTO, integrante);
                integrante.setId(id);
                repository.save(integrante);
            }}
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<IntegranteDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<IntegranteDTO> Excluir(Long id){
        try{
            if (id != null){
                if(repository.existsById(id)){
                    repository.deleteById(id);
                }
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<IntegranteDTO>) ResponseEntity.ok();
    }
}
