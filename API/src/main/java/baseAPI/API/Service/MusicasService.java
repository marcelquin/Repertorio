package baseAPI.API.Service;

import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Musica;
import baseAPI.API.Repository.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicasService {

    @Autowired
    private MusicaRepository repository;

    public List<Musica> listar() {
        try{
            List<Musica> result = new ArrayList<>();
            result = repository.findAll();
            return result;
        }catch (Exception e){
        new RuntimeException("ops, algo deu errado");
        e.getMessage();
    }
        return null;
    }

    public Musica buscarPorId(Long id){
        try{
            if (id != null){repository.findById(id);}
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Musica BuscarPorNome(String nome){
        try{
            if(nome.length() > 0){repository.findByName(nome);}
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public MusicaDTO Salvar(MusicaDTO musicaDTO){
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
        return null;
    }

    public MusicaDTO Editar(Long id,MusicaDTO musicaDTO){
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
        return null;
    }

    public MusicaDTO Excluir(Long id){
        try{
            if (id != null){
                Musica entidade = new Musica();
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
