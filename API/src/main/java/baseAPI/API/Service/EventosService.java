package baseAPI.API.Service;

import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Eventos;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Model.Musica;
import baseAPI.API.Repository.EventosRepository;
import baseAPI.API.Repository.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventosService {

    @Autowired
    private EventosRepository repository;

    @Autowired
    private MusicaRepository musicaRepository;

    public List<Eventos> listar() {
        try{
            List<Eventos> result = new ArrayList<>();
            result = repository.findAll();
            return result;
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Eventos buscarPorId(Long id){
        try{
            if(id != null){
                repository.findById(id);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Eventos BuscarPorNome(String nome){
        try{
            if (nome.length() > 0){
                repository.findByName(nome);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public EventosDTO Salvar(EventosDTO eventosDTO, MusicaDTO musicaDTO, MultipartFile contrato, MultipartFile banner){
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
        return null;
    }

    public EventosDTO Editar(Long id,EventosDTO eventosDTO,MusicaDTO musicaDTO, MultipartFile contrato, MultipartFile banner){
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
        return null;
    }

    public EventosDTO Excluir(Long id) {
        try {
            if (id != null) {
                Eventos entidade = new Eventos();
                entidade.setId(id);
                if (repository.existsById(entidade.getId()))
                    repository.deleteById(entidade.getId());
            }
        } catch (Exception e) {
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }
}
