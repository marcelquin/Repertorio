package baseAPI.API.Sistema.Service;

import baseAPI.API.Sistema.DTO.BandaDTO;
import baseAPI.API.Sistema.DTO.EventosDTO;
import baseAPI.API.Sistema.DTO.IntegranteDTO;
import baseAPI.API.Sistema.Model.Banda;
import baseAPI.API.Sistema.Model.Eventos;
import baseAPI.API.Sistema.Model.Integrante;
import baseAPI.API.Sistema.Repository.BandaRepository;
import baseAPI.API.Sistema.Repository.EventosRepository;
import baseAPI.API.Sistema.Repository.IntegranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseEntity<List<BandaDTO>> listar() {
        try{
            repository.findAll();
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<List<BandaDTO>>) ResponseEntity.ok();
    }

    public ResponseEntity<BandaDTO> buscarPorId(Long id){
        try{
            if(id != null) repository.findById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<BandaDTO>) ResponseEntity.ok();
    }


    public ResponseEntity<BandaDTO> Salvar(BandaDTO bandaDTO, MultipartFile logo, IntegranteDTO integranteDTO, EventosDTO eventosDTO){
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
        return (ResponseEntity<BandaDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<BandaDTO> Editar(Long id,BandaDTO bandaDTO, MultipartFile logo, IntegranteDTO integranteDTO, EventosDTO eventosDTO){
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
        return (ResponseEntity<BandaDTO>) ResponseEntity.ok();
    }

    public ResponseEntity<BandaDTO> Excluir(Long id){
        try{
            if(id != null) repository.deleteById(id);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return (ResponseEntity<BandaDTO>) ResponseEntity.ok();
    }
}
