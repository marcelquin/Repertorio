package baseAPI.API.DTO;

import baseAPI.API.Enum.Cargo;
import baseAPI.API.Enum.Residencia;
import jakarta.persistence.Lob;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
public class IntegranteDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private Long cpf;
    private Date dataNascimento;
    private String logradouro;
    private String numero;
    private String bairro;
    private Residencia residencia;
    private Long cep;
    private String cidade;
    private String estado;
    private Cargo instrumentista;
    @Lob
    private Blob foto;
    private Long telefone;
    private Long telefone2;
    private String email;
}
