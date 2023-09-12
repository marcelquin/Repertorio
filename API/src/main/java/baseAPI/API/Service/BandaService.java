package baseAPI.API.Service;

import baseAPI.API.DTO.BandaDTO;
import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Banda;
import baseAPI.API.Model.Eventos;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Repository.BandaRepository;
import baseAPI.API.Repository.EventosRepository;
import baseAPI.API.Repository.IntegranteRepository;
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
public class BandaService {

    @Autowired
    private BandaRepository repository;

    @Autowired
    private IntegranteRepository integranteRepository;

    @Autowired
    private EventosRepository eventosRepository;

    public List<Banda> listar() {
        try{
            List<Banda> result = new ArrayList<>();
            result = repository.findAll();
            return result;
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public Banda buscarPorId(Long id){
        try{
            if(id != null) repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }


    public BandaDTO Salvar(BandaDTO bandaDTO, MultipartFile logo, IntegranteDTO integranteDTO, EventosDTO eventosDTO){
        try{
            if(bandaDTO != null){
                Banda banda = new Banda();
                if (logo != null) bandaDTO.setLogo(logo.getBytes());
                if(integranteDTO != null){
                    Integrante integrante = new Integrante();
                    BeanUtils.copyProperties(integranteDTO, integrante);
                    integranteRepository.save(integrante);
                }
                if (eventosDTO != null){
                    Eventos eventos = new Eventos();
                    BeanUtils.copyProperties(eventosDTO, eventos);
                    eventosRepository.save(eventos);
                }
                BeanUtils.copyProperties(bandaDTO, banda);
                repository.save(banda);
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public BandaDTO Editar(Long id,BandaDTO bandaDTO, MultipartFile logo, IntegranteDTO integranteDTO, EventosDTO eventosDTO){
        try{
            if(id != null){
                if(repository.existsById(id)){
                    if(bandaDTO != null){
                        Banda banda = new Banda();
                        if (logo != null) bandaDTO.setLogo(logo.getBytes());
                        if(integranteDTO != null){
                            Integrante integrante = new Integrante();
                            BeanUtils.copyProperties(integranteDTO, integrante);
                            integrante.setId(integranteDTO.getId());
                            integranteRepository.save(integrante);
                        }
                        if (eventosDTO != null){
                            Eventos eventos = new Eventos();
                            BeanUtils.copyProperties(eventosDTO, eventos);
                            eventos.setId(eventosDTO.getId());
                            eventosRepository.save(eventos);
                        }
                        BeanUtils.copyProperties(bandaDTO, banda);
                        banda.setId(bandaDTO.getId());
                        repository.save(banda);
                    }
                }
            }
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }

    public BandaDTO Excluir(Long id){
        try{
            if (id != null){
                Banda entidade = new Banda();
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
