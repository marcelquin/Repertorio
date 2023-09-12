package baseAPI.API.Service;

import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Model.Musica;
import baseAPI.API.Repository.IntegranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegrantesService {

    @Autowired
    private IntegranteRepository repository;

    public List<Integrante> listar() {
        try{
            List<Integrante> result = new ArrayList<>();
            result = repository.findAll();
            return result;
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Integrante buscarPorId(Long id){
        try{
            if(id != null) repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Integrante BuscarPorNome(String nome){
        try{
            if(nome.length() > 0) repository.findByName(nome);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public IntegranteDTO Salvar(IntegranteDTO integranteDTO, MultipartFile file)  throws IOException {
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
        return null;
    }

    public IntegranteDTO Editar(Long id,IntegranteDTO integranteDTO,MultipartFile file){
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
        return null;
    }

    public IntegranteDTO Excluir(Long id){
        try{
            if (id != null){
                Integrante entidade = new Integrante();
                entidade.setId(id);
                if (repository.existsById(entidade.getId()))
                    repository.deleteById(entidade.getId());
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }
}
