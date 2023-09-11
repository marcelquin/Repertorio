package baseAPI.API.Sistema.Controller;


import baseAPI.API.Sistema.DTO.MusicaDTO;
import baseAPI.API.Sistema.Service.MusicasService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/musica")
@Tag(name = "Musica", description = "Manipula dados de tabela Musica")
public class MusicaController {

    @Autowired
    private final MusicasService service;

    @Operation(summary = "Listar", description = "Realiza Busca de todos os dados da tabela Musica",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping("/listar")
    public ResponseEntity<List<MusicaDTO>> listar() {
        return new ResponseEntity<List<MusicaDTO>>((List<MusicaDTO>) service.listar(), HttpStatus.OK);
    }

    @Operation(summary = "buscarporid", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/buscarporid", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicaDTO> buscarPorId(@RequestParam Long id){
        return new ResponseEntity<MusicaDTO>(service.buscarPorId(id).getBody(), HttpStatus.OK);
    }
    @Operation(summary = "BuscarPorNome", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/buscarpornome", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicaDTO> BuscarPorNome(@RequestParam String nome){
        return new ResponseEntity<MusicaDTO>(service.BuscarPorNome(nome).getBody(), HttpStatus.OK);
    }

    @Operation(summary = "salvar", description = "Salva nova musica no banco de dados",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PostMapping(value = "/salvar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicaDTO> Salvar(@RequestParam("MusicaDTO") MusicaDTO musicaDTO){
        return new ResponseEntity<MusicaDTO>(service.Salvar(musicaDTO).getBody(), HttpStatus.CREATED);
    }

    @Operation(summary = "editar", description = "Edita musica do banco de dados",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PutMapping(value = "/editar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicaDTO> Editar(@RequestParam Long id, @RequestParam("MusicaDTO") MusicaDTO musicaDTO){
        return new ResponseEntity<MusicaDTO>(service.Editar(id, musicaDTO).getBody() ,HttpStatus.OK);
    }

    @Operation(summary = "deletar", description = "Deleta musica do banco de dados",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @DeleteMapping(value = "/deletar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MusicaDTO> Excluir(@RequestParam Long id){
        return new ResponseEntity<MusicaDTO>(service.Excluir(id).getBody(), HttpStatus.OK);
    }



}
