package baseAPI.API.Controller;


import baseAPI.API.DTO.BandaDTO;
import baseAPI.API.DTO.EventosDTO;
import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.Model.Banda;
import baseAPI.API.Service.BandaService;
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
@RequestMapping("/api/banda")
@Tag(name = "Banda", description = "Manipula dados de tabela Banda")
@CrossOrigin(origins = "*")
public class BandaController {

    @Autowired
    private final BandaService service;



    @Operation(summary = "Listar Bandas", description = "Realiza Busca de todos os dados da tabela Banda",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping()
    public List<Banda> listarBandas() {
        return service.listar();
    }

    @Operation(summary = "buscar Banda por id", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/{id}")
    public Banda buscarBandaPorId(@RequestParam Long id){
        return service.buscarPorId(id);
    }

    @Operation(summary = "Nova Banda", description = "Salva nova banda no banco de dados",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public BandaDTO novaBanda(@RequestBody BandaDTO bandaDTO, @RequestPart MultipartFile logo, @RequestBody IntegranteDTO integranteDTO, @RequestBody EventosDTO eventosDTO){
        return service.Salvar(bandaDTO, logo, integranteDTO, eventosDTO);
    }

    @Operation(summary = "editar Banda", description = "Edita as informações do banco de dados",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public BandaDTO EditarBanda(@RequestParam Long id,@RequestBody BandaDTO bandaDTO,  @RequestPart MultipartFile logo, @RequestBody IntegranteDTO integranteDTO, @RequestBody EventosDTO eventosDTO){
        return service.Editar(id, bandaDTO, logo, integranteDTO, eventosDTO);
    }

    @Operation(summary = "deletar Banda", description = "Deleta informações do banco de dados",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @DeleteMapping(value = "/{id}")
    @Transactional
    public BandaDTO ExcluirBanda(@RequestParam Long id){
        return service.Excluir(id);
    }
}
