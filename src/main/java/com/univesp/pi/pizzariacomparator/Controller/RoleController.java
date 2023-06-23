package com.univesp.pi.pizzariacomparator.Controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.univesp.pi.pizzariacomparator.DTO.Role.RoleDTOCriar;
import com.univesp.pi.pizzariacomparator.Model.Role;
import com.univesp.pi.pizzariacomparator.Service.RoleService;

@RestController
@RequestMapping(value = "/v1/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    
    @GetMapping
    public ResponseEntity<List<Role>> buscarTodasRoles(){
        List<Role> listaRoles = roleService.buscarTodos();
        return ResponseEntity.status(200).body(listaRoles);        
    }
    
    @PostMapping
    public ResponseEntity<Role> cadastrarRole(@RequestBody @Valid RoleDTOCriar roleDTOCriar) {
        Role role = roleService.salvarRole(roleDTOCriar);
        return ResponseEntity.status(201).body(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirRole(@PathVariable UUID id) {        
        roleService.excluirRole(id);
        return ResponseEntity.ok("Role deletada com sucesso");
    }
}
