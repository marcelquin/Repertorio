package baseAPI.API.Controller;


import baseAPI.API.DTO.IntegranteDTO;
import baseAPI.API.Model.Integrante;
import baseAPI.API.Service.IntegrantesService;
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

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integrante")
@Tag(name = "Integrante", description = "Manipula dados de integrantes")
@CrossOrigin(origins = "*")
public class IntegranteController {

    @Autowired
    private final IntegrantesService service;


    @Operation(summary = "listar Integrante", description = "Realiza Busca de todos os dados da tabela Integrantes",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping()
    public List<Integrante> listarIntegrante() {
        return service.listar();
    }

    @Operation(summary = "buscar integrante por id", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/{id}")
    public Integrante buscarPorId(@RequestParam Long id){
        return service.buscarPorId(id);
    }


    @Operation(summary = "Buscar integrante Por Nome", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/{nome}")
    public Integrante BuscarIntegrantePorNome(@RequestParam String nome){
        return service.BuscarPorNome(nome);
    }


    @Operation(summary = "Novo Integrante", description = "Salva novo integrante no banco de dados",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PostMapping(MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public IntegranteDTO novoIntegrante(@RequestBody IntegranteDTO integranteDTO, @RequestPart MultipartFile file)  throws IOException {
        return service.Salvar(integranteDTO, file);
    }


    @Operation(summary = "editar integrante", description = "Edita as informações do banco de dados",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public IntegranteDTO EditarIntegrante(@RequestParam Long id, @RequestBody IntegranteDTO integranteDTO,@RequestPart MultipartFile file){
       return service.Editar(id, integranteDTO, file);
    }

    @Operation(summary = "deletar", description = "Deleta musica do banco de dados",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @DeleteMapping(value = "{id}")
    @Transactional
    public IntegranteDTO Excluir(@RequestParam  Long id){
        return service.Excluir(id);
    }
}
