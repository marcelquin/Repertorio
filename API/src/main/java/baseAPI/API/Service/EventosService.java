package baseAPI.API.Service;

import baseAPI.API.DTO.EventosDTO;

import baseAPI.API.Model.Eventos;
import baseAPI.API.Model.Musica;
import baseAPI.API.Repository.EventosRepository;
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
public class EventosService {


    @Autowired
    private EventosRepository repository;
    @Autowired
    private MusicaRepository mRepository;

    public List<Eventos> listar(){
        try {
            repository.findAll();
        }catch (Exception e) {
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Eventos buscarPorId(Long id) {
        try {
            repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<byte[]> verImagemPorId(long id) throws IOException, SQLException {
        Eventos entidade = repository.findById(id).get();
        byte[] imageBytes = null;
        imageBytes = entidade.getBanner().getBytes(1, (int) entidade.getBanner().length());
        return ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


    public EventosDTO salvar(EventosDTO eventosDTO)
    {
        try {
            Eventos entidade = new Eventos();
            BeanUtils.copyProperties(eventosDTO, entidade);
            repository.save(entidade);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public  EventosDTO adicionarBanner(Long id, MultipartFile file) throws IOException, SQLException {
        try {
            if (repository.existsById(id)) {
                if (!file.isEmpty()) {
                    Eventos eventos = new Eventos();
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    if(repository.existsById(id))
                    {
                        eventos = repository.findById(id).get();
                    }
                    eventos.setBanner(blob);
                    repository.save(eventos);
                }}
            }catch(Exception e){
                new RuntimeException("ops, algo deu errado");
                e.getMessage();
            }
        return null;
    }

    public  EventosDTO adicionarContrato(Long id, MultipartFile file) throws IOException, SQLException
    {
        try{
            if(repository.existsById(id))
            {
                Eventos eventos = repository.findById(id).get();
                if(!file.isEmpty())
                {
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    if(repository.existsById(id))
                    {
                        eventos = repository.findById(id).get();
                    }
                    eventos.setContrato(blob);
                    repository.save(eventos);
                }
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

/*    public EventosDTO adicionarMusica(String nome, Long id)
    {
        try{
            if(mRepository.existsById(id)){
                Musica musica = mRepository.findById(id).get();
                List<Musica> lista = new ArrayList<>();
                lista.add(musica);
                Eventos eventos = new Eventos();
                if(repository.findByName(nome))
                {
                    eventos.setMusicas(lista);
                    eventos.setId(id);
                }
                repository.save(eventos);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }*/

    public void adicionarMusica(Long idMusica, Long idEvento)
    {
        try{
            if(mRepository.existsById(idMusica)){
                Musica musica = mRepository.findById(idMusica).get();
                List<Musica> lista = new ArrayList<>();
                lista.add(musica);
                Eventos eventos = new Eventos();
                if(repository.existsById(idEvento))
                {
                    eventos = repository.findById(idEvento).get();
                    eventos.setMusicas(lista);
                    eventos.setId(idEvento);
                }
                repository.save(eventos);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
  }

    public EventosDTO editar(Long id, EventosDTO eventosDTO)
    {
        try {
            if(repository.existsById(id))
            {
                Eventos entidade = new Eventos();
                BeanUtils.copyProperties(eventosDTO, entidade);
                entidade.setId(id);
                repository.save(entidade);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public EventosDTO deletar(Long id)
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
