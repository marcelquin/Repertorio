package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Model.Musica;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventosDTO {

    private Long id;
    private String local;
    private String contratante;
    private String evento;
    private Date data;
    private Double valorCache;
    private String contrato;
    private List<Musica> musicas;

}
