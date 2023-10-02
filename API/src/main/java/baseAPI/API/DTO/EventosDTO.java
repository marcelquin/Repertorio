package baseAPI.API.DTO;

import baseAPI.API.Model.Musica;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class EventosDTO {

    private Long id;
    private String local;
    private String contratante;
    private String evento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    @Lob
    private Blob banner;
    private Double valorCache;
    @Lob
    private Blob contrato;
    private List<Long> musicas;

}
