package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.MusicaDTO;
import baseAPI.API.Sistema.Model.Musica;
import baseAPI.API.Sistema.Repository.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicasService {

    @Autowired
    private MusicaRepository repository;

    public ResponseEntity<List<MusicaDTO>> listar() {
        try{
            repository.findAll();
        }catch (Exception e){
        new RuntimeException("ops, algo deu errado");
        e.getMessage();
    }
        return (ResponseEntity<List<MusicaDTO>>) ResponseEntity.ok();
    }

    public ResponseEntity<MusicaDTO> buscarPorId(Long id){
        try{
            if (id != null){repository.findById(id);}
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<MusicaDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<MusicaDTO> BuscarPorNome(String nome){
        try{
            if(nome.length() > 0){repository.findByName(nome);}
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<MusicaDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<MusicaDTO> Salvar(MusicaDTO musicaDTO){
        try{
            if(musicaDTO != null){
                Musica musica = new Musica();
                BeanUtils.copyProperties(musicaDTO, musica);
                repository.save(musica);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<MusicaDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<MusicaDTO> Editar(Long id,MusicaDTO musicaDTO){
        try{
            if(repository.existsById(id)){
                if(musicaDTO != null){
                    Musica musica = new Musica();
                    BeanUtils.copyProperties(musicaDTO, musica);
                    repository.save(musica);
                }
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<MusicaDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<MusicaDTO> Excluir(Long id){
        try{
            if (id != null){
                repository.deleteById(id);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<MusicaDTO>) ResponseEntity.ok();
    }




}
