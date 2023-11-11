package com.univesp.pi.pizzariacomparator.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

import com.univesp.pi.pizzariacomparator.Config.TokenService;
import com.univesp.pi.pizzariacomparator.DTO.Usuario.LoginRetornoDTO;
import com.univesp.pi.pizzariacomparator.DTO.Usuario.UsuarioDTOCriar;
import com.univesp.pi.pizzariacomparator.DTO.Usuario.UsuarioDTOLogin;
import com.univesp.pi.pizzariacomparator.Model.Role;
import com.univesp.pi.pizzariacomparator.Model.Usuario;
import com.univesp.pi.pizzariacomparator.Service.UsuarioService;


@RestController
@RequestMapping(value = "/v1/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginRetornoDTO login(@RequestBody @Valid UsuarioDTOLogin usuarioDTOLogin) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
        new UsernamePasswordAuthenticationToken(usuarioDTOLogin.getEmail(), usuarioDTOLogin.getSenha());
        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Usuario usuario = (Usuario) usuarioService.loadUserByUsername(usuarioDTOLogin.getEmail());
        String token = tokenService.gerarToken(usuario);
        List<String> listaRoles = new ArrayList<>();
        for ( Role role : usuario.getRoles()) {
            listaRoles.add(role.getRoleName());
        }
        return new LoginRetornoDTO(usuario.getId().toString(),usuario.getNome(),token, listaRoles);
        
        
    }
    //@PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodasUsuarios(){
        List<Usuario> listaUsuarios = usuarioService.buscarTodos();
        return ResponseEntity.status(200).body(listaUsuarios);        
    }
    //@PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/pagina")
    public ResponseEntity<Page<Usuario>> buscarTodosPaginados(@RequestParam(defaultValue = "0") Integer pagina){

        Page<Usuario> usuarioPaginada = usuarioService.buscarTodosPaginados(pagina);
        return ResponseEntity.ok(usuarioPaginada);        
    }
    //@PreAuthorize("hasRole('ROLE_MASTER')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable UUID id){        
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }
    //@PreAuthorize("hasRole('ROLE_MASTER')")
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid UsuarioDTOCriar usuarioDTOCriar) {
        Usuario usuario = usuarioService.salvarUsuario(usuarioDTOCriar);        
        return ResponseEntity.status(201).body(usuario);
    }
    //@PreAuthorize("hasRole('ROLE_MASTER')")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioDTOCriar usuarioDTOAtualizar) {
        Usuario usuario = usuarioService.atualizarUsuario(id,usuarioDTOAtualizar);
        return ResponseEntity.status(200).body(usuario);
    }
    //@PreAuthorize("hasRole('ROLE_MASTER')")
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> atualizarParcialmenteUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioDTOCriar usuarioDTOAtualizar) {
        
            Usuario usuario = usuarioService.atualizarUsuarioPatch(id,usuarioDTOAtualizar);
            return ResponseEntity.status(200).body(usuario);
    }
    //@PreAuthorize("hasRole('ROLE_MASTER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable UUID id) {
        
        usuarioService.excluirUsuario(id);
        return ResponseEntity.ok("Usuario deletada com sucesso");
    }
}
