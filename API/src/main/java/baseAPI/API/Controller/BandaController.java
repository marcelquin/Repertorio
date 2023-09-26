package baseAPI.API.Controller;


import baseAPI.API.DTO.BandaDTO;
import baseAPI.API.Model.Banda;
import baseAPI.API.Service.BandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banda")
@Tag(name = "Banda", description = "Manipula dados de tabela Banda")
@CrossOrigin(origins = "*")
public class BandaController {

    @Autowired
    private final BandaService service;

    @Operation(summary = "Lista Bandas cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping()
    public List<Banda> listarBandas() throws IOException, SQLException
    {
        return service.listar();
    }

    @Operation(summary = "Busca Registro por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping("/id")
    public Banda BuscaBandaPorId(@RequestParam Long id)
    {
        return  service.buscarPorId(id);
    }


    @Operation(summary = "Ver imagem por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping("/verImagemPorid")
    public ResponseEntity<byte[]> verImagemLogoPorId(@RequestParam Long id) throws SQLException, IOException {
        return ok().contentType(MediaType.IMAGE_JPEG).body(service.verImagemPorId(id).getBody());
    }

    @Operation(summary = "Salva Novo registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    public String novaBanda(BandaDTO entidade, @RequestPart MultipartFile file) throws SQLException, IOException{
        service.salvar(entidade, file);
        return "ok";
    }


    @Operation(summary = "Edita Registro da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PutMapping(value = "/id", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BandaDTO EditarBanda(@RequestParam Long id, BandaDTO entidade, @RequestPart MultipartFile file) throws SQLException, IOException { return service.editar(id, entidade,file);  }


    @Operation(summary = "Deleta Registro da tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @DeleteMapping("/id")
    public BandaDTO deletarBanda(@RequestParam Long id){return service.deletar(id);}
}
