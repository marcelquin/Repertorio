package baseAPI.API.Model;

import baseAPI.API.Enum.Ritmo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
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

    @Column(name = "CNPJ")
    private Long cnpj;

    @Lob
    private Blob logo;

    @Enumerated
    private Ritmo ritmo;
    @OneToMany
    private List<Integrante> integrantes;
    @OneToMany
    private List<Eventos> eventos;

}
