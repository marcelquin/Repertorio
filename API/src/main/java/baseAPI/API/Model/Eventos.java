package baseAPI.API.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    @Lob
    private Blob banner;
    @Column(name = "Valor_Cache")
    private Double valorCache;
    @Lob
    private Blob contrato;
    @OneToMany
    private List<Musica> musicas;
}

