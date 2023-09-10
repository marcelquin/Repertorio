package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Enum.Ritmo;
import baseAPI.API.Sistema.Model.Eventos;
import baseAPI.API.Sistema.Model.Integrante;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class BandaDTO {

    private Long id;
    private String nome;
    private String razaoSocial;
    private Ritmo ritmo;
    private List<Integrante> integrantes;
    private List<Eventos> eventos;

}
