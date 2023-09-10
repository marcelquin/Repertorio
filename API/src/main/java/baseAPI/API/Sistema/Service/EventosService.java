package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.EventosDTO;
import baseAPI.API.Sistema.DTO.MusicaDTO;
import baseAPI.API.Sistema.Model.Eventos;
import baseAPI.API.Sistema.Model.Musica;
import baseAPI.API.Sistema.Repository.EventosRepository;
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
public class EventosService {

    @Autowired
    private EventosRepository repository;

    @Autowired
    private MusicaRepository musicaRepository;

    public ResponseEntity<List<EventosDTO>> listar() {
        try{
            repository.findAll();
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<List<EventosDTO>>) ResponseEntity.ok();
    }

    public ResponseEntity<EventosDTO> buscarPorId(Long id){
        try{
            if(id != null){
                repository.findById(id);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<EventosDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<EventosDTO> BuscarPorNome(String nome){
        try{
            if (nome.length() > 0){
                repository.findByName(nome);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<EventosDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<EventosDTO> Salvar(EventosDTO eventosDTO, MusicaDTO musicaDTO, MultipartFile contrato, MultipartFile banner){
        try{
            if (eventosDTO != null){
                Eventos eventos = new Eventos();
                if(contrato != null){
                   eventosDTO.setContrato(contrato.getBytes());
                }
                if(banner != null){
                    eventosDTO.setBanner(banner.getBytes());
                }
                if(musicaDTO != null)
                {
                    Musica musica = new Musica();
                    BeanUtils.copyProperties(musicaDTO, musica);
                    musicaRepository.save(musica);
                }
                BeanUtils.copyProperties(eventosDTO, eventos);
                repository.save(eventos);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<EventosDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<EventosDTO> Editar(Long id,EventosDTO eventosDTO,MusicaDTO musicaDTO, MultipartFile contrato, MultipartFile banner){
        try{
            if(id != null){
                if(repository.existsById(id)){
                    Eventos eventos = new Eventos();
                    if(contrato != null){
                        eventosDTO.setContrato(contrato.getBytes());
                    }
                    if(banner != null){
                        eventosDTO.setBanner(banner.getBytes());
                    }
                    if(musicaDTO != null)
                    {
                        Musica musica = new Musica();
                        BeanUtils.copyProperties(musicaDTO, musica);
                        musica.setId(musicaDTO.getId());
                        musicaRepository.save(musica);
                    }
                    BeanUtils.copyProperties(eventosDTO, eventos);
                    eventos.setId(id);
                    repository.save(eventos);
                }
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<EventosDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<EventosDTO> Excluir(Long id){
        try{
            if(id != null)repository.deleteById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<EventosDTO>) ResponseEntity.ok();
    }
}
