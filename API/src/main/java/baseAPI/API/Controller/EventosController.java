package baseAPI.API.Controller;


import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Eventos;
import baseAPI.API.Service.EventosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/evento")
@Tag(name = "Evento", description = "Manipula dados de tabela Eventos")
@CrossOrigin(origins = "*")
public class EventosController {

    @Autowired
    private final EventosService service;


    @Operation(summary = "Listar Eventos", description = "Realiza Busca de todos os dados da tabela Evento",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping()
    public List<Eventos> listarEventos() {
        return service.listar();
    }

    @Operation(summary = "Buscar Eventos por id", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/{id}")
    public Eventos buscarEventosPorId(@RequestParam Long id){
        return service.buscarPorId(id);
    }

    @Operation(summary = "Buscar Evento Por Nome", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/{nome}")
    public Eventos BuscarEventoPorNome(@RequestParam String nome){
        return service.BuscarPorNome(nome);
    }

    @Operation(summary = "Novo Evento", description = "Salva novo evento no banco de dados",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public EventosDTO novoEvento(@RequestBody EventosDTO eventosDTO, @RequestBody MusicaDTO musicaDTO,@RequestPart MultipartFile contrato, @RequestPart MultipartFile banner){
        return service.Salvar(eventosDTO, musicaDTO, contrato, banner);
    }

    @Operation(summary = "Editar Evento ", description = "Edita as informações do banco de dados",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public EventosDTO EditaEvento(@RequestParam Long id, @RequestBody EventosDTO eventosDTO, @RequestBody MusicaDTO musicaDTO, @RequestPart MultipartFile contrato, @RequestPart MultipartFile banner){
        return service.Editar(id, eventosDTO, musicaDTO, contrato, banner);
    }

    @Operation(summary = "Deletar Evento", description = "Deleta musica do banco de dados",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @DeleteMapping(value = "/{id}")
    @Transactional
    public EventosDTO ExcluirEvento(@RequestParam Long id){
        return service.Excluir(id);
    }
}
