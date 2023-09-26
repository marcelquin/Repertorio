package baseAPI.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String local;
    private String contratante;
    private String evento;
    private Date data;
    @Lob
    private byte[] banner;
    @Column(name = "Valor_Cache")
    private Double valorCache;
    @Lob
    private Blob contrato;
    @OneToMany
    private List<Musica> musicas;
}

