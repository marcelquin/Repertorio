package baseAPI.API.DTO;

import baseAPI.API.Model.Musica;
import jakarta.persistence.Lob;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Data
public class EventosDTO {

    private Long id;
    private String local;
    private String contratante;
    private String evento;
    private Date data;
    @Lob
    private Blob banner;
    private Double valorCache;
    @Lob
    private Blob contrato;
    private List<Musica> musicas;

}
