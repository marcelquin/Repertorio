package baseAPI.API.DTO;

import baseAPI.API.Enum.Ritmo;
import baseAPI.API.Model.Eventos;
import baseAPI.API.Model.Integrante;
import jakarta.persistence.Lob;
import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
public class BandaDTO {

    private Long id;
    private String nome;
    private String razaoSocial;
    @Lob
    private Blob logo;
    private Ritmo ritmo;
    private List<Integrante> integrantes;
    private List<Eventos> eventos;

}
