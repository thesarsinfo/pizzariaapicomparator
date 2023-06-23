package com.univesp.pi.pizzariacomparator.Controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univesp.pi.pizzariacomparator.DTO.Usuario.UsuarioDTOCriar;
import com.univesp.pi.pizzariacomparator.Model.Usuario;
import com.univesp.pi.pizzariacomparator.Service.UsuarioService;

@PreAuthorize("hasRole('ROLE_MASTER')")
@RestController
@RequestMapping(value = "/v1/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodasUsuarios(){
        List<Usuario> listaUsuarios = usuarioService.buscarTodos();
        return ResponseEntity.status(200).body(listaUsuarios);        
    }

    @GetMapping("/pagina")
    public ResponseEntity<Page<Usuario>> buscarTodosPaginados(@RequestParam(defaultValue = "0") Integer pagina){

        Page<Usuario> usuarioPaginada = usuarioService.buscarTodosPaginados(pagina);
        return ResponseEntity.ok(usuarioPaginada);        
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable UUID id){        
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid UsuarioDTOCriar usuarioDTOCriar) {
        Usuario usuario = usuarioService.salvarUsuario(usuarioDTOCriar);        
        return ResponseEntity.status(201).body(usuario);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioDTOCriar usuarioDTOAtualizar) {
        Usuario usuario = usuarioService.atualizarUsuario(id,usuarioDTOAtualizar);
        return ResponseEntity.status(200).body(usuario);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> atualizarParcialmenteUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioDTOCriar usuarioDTOAtualizar) {
        
            Usuario usuario = usuarioService.atualizarUsuarioPatch(id,usuarioDTOAtualizar);
            return ResponseEntity.status(200).body(usuario);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable UUID id) {
        
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok("Usuario deletada com sucesso");
    }
}
