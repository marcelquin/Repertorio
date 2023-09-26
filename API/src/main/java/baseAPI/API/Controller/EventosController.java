package baseAPI.API.Controller;


import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.Model.Eventos;
import baseAPI.API.Service.EventosService;
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
@RequestMapping("/api/evento")
@Tag(name = "Evento", description = "Manipula dados de tabela Eventos")
@CrossOrigin(origins = "*")
public class EventosController {

    @Autowired
    private final EventosService service;

    @Operation(summary = "Lista Bandas cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping()
    public List<Eventos> listarEventos() throws IOException, SQLException
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
    public Eventos BuscaEventoPorId(@RequestParam Long id)
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
    public ResponseEntity<byte[]> verImagemPerfilPorId(@RequestParam Long id) throws SQLException, IOException {
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
    public String novoEvento(EventosDTO entidade, @RequestPart MultipartFile file, @RequestPart MultipartFile file2 ) throws SQLException, IOException{
        service.salvar(entidade, file, file2);
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
    public EventosDTO EditarEvento(@RequestParam Long id, EventosDTO entidade, @RequestPart MultipartFile file, @RequestPart MultipartFile file2) throws SQLException, IOException { return service.editar(id, entidade,file, file2);  }


    @Operation(summary = "Deleta Registro da tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @DeleteMapping("/id")
    public EventosDTO deletarBanda(@RequestParam Long id){return service.deletar(id);}
}
