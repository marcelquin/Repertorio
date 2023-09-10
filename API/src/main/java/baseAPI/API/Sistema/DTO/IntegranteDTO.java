package baseAPI.API.Sistema.DTO;

import baseAPI.API.Sistema.Enum.Cargo;
import baseAPI.API.Sistema.Enum.Residencia;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.Data;

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
    private byte[] foto;
    private Long telefone;
    private Long telefone2;
    private String email;
}
