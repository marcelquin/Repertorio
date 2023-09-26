package baseAPI.API.Service;

import baseAPI.API.DTO.BandaDTO;
import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Eventos;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Model.Musica;
import baseAPI.API.Repository.EventosRepository;
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
public class EventosService {


    @Autowired
    private EventosRepository repository;

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


    public EventosDTO salvar(EventosDTO eventosDTO, MultipartFile file, MultipartFile file2) throws SQLException, IOException
    {
        try {
            Eventos entidade = new Eventos();
                if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                eventosDTO.setBanner(blob);
            }
            if(!file2.isEmpty()){
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                eventosDTO.setContrato(blob);
            }
            BeanUtils.copyProperties(eventosDTO, entidade);
            repository.save(entidade);
        }catch (Exception e){
            new RuntimeException("ops, algo deu errado");
            e.getMessage();
        }
        return null;
    }


    public EventosDTO editar(Long id, EventosDTO eventosDTO, MultipartFile file, MultipartFile file2) throws SQLException, IOException
    {
        try {
            if(repository.existsById(id))
            {
                Eventos entidade = new Eventos();
                if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                eventosDTO.setBanner(blob);
            }
                if(!file2.isEmpty()){
                    byte[] bytes = file.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                    eventosDTO.setContrato(blob);
                }
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
