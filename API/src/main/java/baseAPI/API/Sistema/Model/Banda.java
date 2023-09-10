package baseAPI.API.Sistema.Model;

import baseAPI.API.Sistema.Enum.Ritmo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Banda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(name = "razao_Social")
    private String razaoSocial;
    @Lob
    private byte[] logo;
    @Enumerated
    private Ritmo ritmo;
    @OneToMany
    private List<Integrante> integrantes;
    @OneToMany
    private List<Eventos> eventos;

}
