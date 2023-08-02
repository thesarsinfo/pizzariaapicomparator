package com.univesp.pi.pizzariacomparator.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.univesp.pi.pizzariacomparator.DTO.Usuario.UsuarioDTOCriar;
import com.univesp.pi.pizzariacomparator.Exception.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Role;
import com.univesp.pi.pizzariacomparator.Model.Usuario;
import com.univesp.pi.pizzariacomparator.Repository.UsuarioRepository;

@Service

public class UsuarioService implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;  
    @Autowired
    private RoleService roleService;  
    @Autowired
    private ModelMapper usuarioMapper;
    

    public Usuario salvarUsuario(UsuarioDTOCriar usuarioDTO) {
        Usuario usuarioSalvar = new Usuario();
        usuarioMapper.map(usuarioDTO, usuarioSalvar); 

        Role role = roleService.buscarRolePorNome(usuarioDTO.getRole());
        if (role != null) {
            usuarioSalvar.setRoles(Collections.singletonList(role));
        }
        return usuarioRepository.save(usuarioSalvar);
    }
    public Usuario buscarUsuarioPorId(UUID id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrada - id: " + id));
    }

    public List<Usuario> buscarTodos(){       
        return usuarioRepository.findAll();
    }
    public Page<Usuario> buscarTodosPaginados(Integer paginaPassada){      
        PageRequest pagina = PageRequest.of(paginaPassada,10); 
        return usuarioRepository.findAll(pagina);
    }
    public Usuario atualizarUsuario(UUID id, UsuarioDTOCriar atualizar) {
        Usuario usuarioAtualizar = buscarUsuarioPorId(id);
        usuarioMapper.map(atualizar, usuarioAtualizar);

        if (atualizar.getRole() != null) {
            Role role = roleService.buscarRolePorNome(atualizar.getRole());
            if (role != null) {
                List<Role> rolesList = new ArrayList<>();
                rolesList.add(role);
                usuarioAtualizar.setRoles(rolesList);
            } 
        }
    return usuarioRepository.save(usuarioAtualizar);
}
    public Usuario atualizarUsuarioPatch(UUID id, UsuarioDTOCriar atualizarPatch) {
        try {
            Usuario usuarioAtualizada = buscarUsuarioPorId(id);

            List<Field> fields = Arrays.asList(UsuarioDTOCriar.class.getDeclaredFields());
            for (Field field : fields) {
                field.setAccessible(true);
                Object usuarioTemporaria = field.get(atualizarPatch);
                if (Objects.nonNull(usuarioTemporaria)) {
                    if (field.getName().equals("role")) {
                        Role role = roleService.buscarRolePorNome(usuarioTemporaria.toString());
                        if (role != null) {
                            List<Role> rolesList = new ArrayList<>();
                            rolesList.add(role);
                            usuarioAtualizada.setRoles(rolesList);
                        }
                    } else {
                        Field usuarioField = Usuario.class.getDeclaredField(field.getName());
                        usuarioField.setAccessible(true);
                        usuarioField.set(usuarioAtualizada, usuarioTemporaria);
                    }
                }
            }
            return usuarioRepository.save(usuarioAtualizada);

        } catch (Exception e) {
            throw new ResourceNotFoundException("Ocorreu um erro durante a atualização dos dados do usuário.");
        }
    }

    public void excluirUsuario(UUID id) {
        Usuario usuarioAtual = buscarUsuarioPorId(id);
        usuarioRepository.delete(usuarioAtual);
    } 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByemail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + email));
        return usuario;
    }
 

    
}
