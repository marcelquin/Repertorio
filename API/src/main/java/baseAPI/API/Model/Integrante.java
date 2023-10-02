package baseAPI.API.Model;

import baseAPI.API.Enum.Cargo;
import baseAPI.API.Enum.Residencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Integrante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String sobrenome;
    @Column(length = 11)
    private Long cpf;
    @Column(name = "Data_Nascimento")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String logradouro;
    private String numero;
    private String bairro;
    @Enumerated
    private Residencia residencia;
    @Column(length = 8)
    private Long cep;
    private String cidade;
    @Column(length = 2)
    private String estado;
    @Enumerated
    private Cargo instrumentista;
    @Lob
    private Blob foto;
    @Column(length = 11)
    private Long telefone;
    @Column(length = 11)
    private Long telefone2;
    private String email;
}
