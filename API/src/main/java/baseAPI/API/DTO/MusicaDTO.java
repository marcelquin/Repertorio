package baseAPI.API.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MusicaDTO {

    private Long id;
    private String nome;
    private String banda;
    private String album;
    private String tom;
    private String linkCifra;
}
