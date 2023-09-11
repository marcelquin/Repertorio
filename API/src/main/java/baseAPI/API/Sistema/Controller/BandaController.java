package baseAPI.API.Sistema.Controller;


import baseAPI.API.Sistema.DTO.BandaDTO;
import baseAPI.API.Sistema.DTO.EventosDTO;
import baseAPI.API.Sistema.DTO.IntegranteDTO;
import baseAPI.API.Sistema.Service.BandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/banda")
@Tag(name = "Banda", description = "Manipula dados de tabela Banda")
public class BandaController {

    @Autowired
    private final BandaService service;



    @Operation(summary = "Listar", description = "Realiza Busca de todos os dados da tabela Banda",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<BandaDTO>> listar() {
        return new ResponseEntity<List<BandaDTO>>((List<BandaDTO>) service.listar(), HttpStatus.OK);
    }

    @Operation(summary = "buscarporid", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/buscarporid", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BandaDTO> buscarPorId(@RequestParam Long id){
        return new ResponseEntity<BandaDTO>(service.buscarPorId(id).getBody(), HttpStatus.OK);
    }

    @Operation(summary = "salvar", description = "Salva nova musica no banco de dados",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PostMapping(value = "/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BandaDTO> Salvar(@RequestParam("BandaDTO") BandaDTO bandaDTO, @RequestPart MultipartFile logo, @RequestParam("IntegranteDTO") IntegranteDTO integranteDTO, @RequestParam("EventosDTO") EventosDTO eventosDTO){
        return new ResponseEntity<BandaDTO>(service.Salvar(bandaDTO, logo, integranteDTO,eventosDTO).getBody(), HttpStatus.CREATED);
    }

    @Operation(summary = "editar", description = "Edita musica do banco de dados",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PutMapping(value = "/editar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BandaDTO> Editar(@RequestParam Long id,@RequestParam("BandaDTO") BandaDTO bandaDTO,  @RequestPart MultipartFile logo, @RequestParam("IntegranteDTO") IntegranteDTO integranteDTO, @RequestParam("EventosDTO") EventosDTO eventosDTO){
        return new ResponseEntity<BandaDTO>(service.Editar(id,bandaDTO,logo, integranteDTO, eventosDTO).getBody() ,HttpStatus.OK);
    }

    @Operation(summary = "deletar", description = "Deleta musica do banco de dados",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @DeleteMapping(value = "/deletar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BandaDTO> Excluir(@RequestParam Long id){
        return new ResponseEntity<BandaDTO>(service.Excluir(id).getBody(), HttpStatus.OK);
    }
}
