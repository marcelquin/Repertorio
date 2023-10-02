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

    @Operation(summary = "Lista Eventos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping()
    public List<Eventos> listarEvetos() throws IOException, SQLException
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

    @Operation(summary = "Busca imagem por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @GetMapping("/verimagemporid")
    public ResponseEntity<byte[]> verImagemPorId(long id) throws IOException, SQLException
    {
        return service.verImagemPorId(id);
    }

    @Operation(summary = "Salva Novo registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping()
    public EventosDTO novoevento(EventosDTO eventosDTO)
    {
        return service.salvar(eventosDTO);
    }

    @Operation(summary = "Salva Nova Musica ao registro", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping("/novamusica")
    public void adicionarMusica(Long idMusica, Long idEvento)
    {
        service.adicionarMusica(idMusica, idEvento);
    }

    @Operation(summary = "Adiciona banner", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/adicionarbanner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EventosDTO adicionarBanner(@RequestParam Long id,@RequestPart MultipartFile file)
    {
        try{
            service.adicionarBanner(id, file);
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Operation(summary = "Adiciona contrato", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/adicionarcontrato", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  EventosDTO adicionarContrato(@RequestParam Long id, @RequestPart MultipartFile file)
    {
        try{
            service.adicionarContrato(id, file);
        }catch (Exception e){
            e.getMessage();
        }
        return null;
    }

    @Operation(summary = "Edita Registro da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PutMapping("/id")
    public EventosDTO editarevento(Long id, EventosDTO eventosDTO)
    {
        return  service.editar(id, eventosDTO);
    }

    @Operation(summary = "Deleta Registro da tabela por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @DeleteMapping("/id")
    public EventosDTO deletareveno(Long id)
    {
        return service.deletar(id);
    }


}
