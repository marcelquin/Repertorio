package baseAPI.API.Sistema.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String banner;
    @Column(name = "Valor_Cache")
    private Double valorCache;
    private String contrato;
    @OneToMany
    private List<Musica> musicas;
}

