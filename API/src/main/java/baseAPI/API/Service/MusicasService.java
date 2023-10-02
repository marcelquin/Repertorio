package baseAPI.API.Service;

import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Model.Musica;
import baseAPI.API.Repository.IntegranteRepository;
import baseAPI.API.Repository.MusicaRepository;
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
public class MusicasService {


    @Autowired
    private MusicaRepository repository;

    public List<Musica> listar(){
        try {
            repository.findAll();
        }catch (Exception e) {
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Musica buscarPorId(Long id) {
        try {
            repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }


    public MusicaDTO salvar(MusicaDTO musicaDTO)
    {
        try {
            Musica entidade = new Musica();
            BeanUtils.copyProperties(musicaDTO, entidade);
            repository.save(entidade);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }


    public MusicaDTO editar(Long id, MusicaDTO musicaDTO) throws SQLException, IOException
    {
        try {
            if(repository.existsById(id))
            {
                Musica entidade = new Musica();
                BeanUtils.copyProperties(musicaDTO, entidade);
                entidade.setId(id);
                repository.save(entidade);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public MusicaDTO deletar(Long id)
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
