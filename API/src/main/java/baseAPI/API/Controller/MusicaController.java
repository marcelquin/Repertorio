package baseAPI.API.Controller;


import baseAPI.API.DTO.MusicaDTO;
import baseAPI.API.Model.Musica;
import baseAPI.API.Service.MusicasService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/musica")
@Tag(name = "Musica", description = "Manipula dados de tabela Musica")
@CrossOrigin(origins = "*")
public class MusicaController {

    @Autowired
    private final MusicasService service;

    @Operation(summary = "listar Musicas", description = "Realiza Busca de todos os dados da tabela Musica",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping()
    public List<Musica> listarMusicas() {
        return service.listar();
    }

    @Operation(summary = "/{id}", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/{id}")
    public Musica buscarMusicaPorId(@RequestParam Long id){
        return service.buscarPorId(id);
    }

    @Operation(summary = "/{nome}", description = "Realiza Busca de Um item por id",method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @GetMapping(value = "/{nome}")
    public Musica BuscarMusicaPorNome(@RequestParam String nome){
        return service.BuscarPorNome(nome);
    }

    @Operation(summary = "Nova Musica", description = "Salva nova musica no banco de dados",method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public MusicaDTO novaMusica( @RequestBody MusicaDTO musicaDTO){
        return service.Salvar(musicaDTO);
    }

    @Operation(summary = "editar", description = "Edita musica do banco de dados",method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public MusicaDTO Editar(@RequestParam Long id, @RequestBody MusicaDTO musicaDTO){
        return service.Editar(id, musicaDTO);
    }

    @Operation(summary = "{id}", description = "Deleta musica do banco de dados",method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algo deu errado"),
    })
    @DeleteMapping(value = "{id}")
    public MusicaDTO Excluir(@RequestParam Long id){
        return service.Excluir(id);
    }



}
