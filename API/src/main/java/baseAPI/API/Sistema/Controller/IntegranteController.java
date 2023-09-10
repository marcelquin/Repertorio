package baseAPI.API.Sistema.Controller;


import baseAPI.API.Sistema.DTO.IntegranteDTO;
import baseAPI.API.Sistema.Service.IntegrantesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/integrante")
@Tag(name = "Integrante", description = "Manipula dados de integrantes")
public class IntegranteController {

    @Autowired
    private final IntegrantesService service;


    @Operation(summary = "Listar", description = "Realiza Busca de todos os dados da tabela Integrantes",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<IntegranteDTO>> listar() {
        return new ResponseEntity<List<IntegranteDTO>>((List<IntegranteDTO>) service.listar().getBody(), HttpStatus.OK);
    }

    @Operation(summary = "buscarporid", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping("/buscarporid")
    public ResponseEntity<IntegranteDTO> buscarPorId(Long id){
        return new ResponseEntity<IntegranteDTO>(service.buscarPorId(id).getBody(), HttpStatus.OK);
    }


    @Operation(summary = "BuscarPorNome", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping("/buscarpornome")
    public ResponseEntity<IntegranteDTO> BuscarPorNome(String nome){
        return new ResponseEntity<IntegranteDTO>(service.BuscarPorNome(nome).getBody(), HttpStatus.OK);
    }


    @Operation(summary = "salvar", description = "Salva nova musica no banco de dados",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PostMapping("/salvar")
    public ResponseEntity<IntegranteDTO> Salvar(IntegranteDTO integranteDTO, MultipartFile file)  throws IOException {
        return new ResponseEntity<IntegranteDTO>(service.Salvar(integranteDTO, file).getBody(), (HttpStatusCode) HttpStatus.CREATED);
    }


    @Operation(summary = "editar", description = "Edita musica do banco de dados",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PutMapping("/editar")
    public ResponseEntity<IntegranteDTO> Editar(Long id,IntegranteDTO integranteDTO,MultipartFile file){
       return new ResponseEntity<IntegranteDTO>(service.Editar(id, integranteDTO, file).getBody(), HttpStatus.OK);
    }

    @Operation(summary = "deletar", description = "Deleta musica do banco de dados",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @DeleteMapping("/deletar")
    public ResponseEntity<IntegranteDTO> Excluir(Long id){
        return new ResponseEntity<IntegranteDTO>(service.Excluir(id).getBody(), HttpStatus.OK);
    }
}
