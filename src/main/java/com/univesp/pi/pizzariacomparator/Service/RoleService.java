package com.univesp.pi.pizzariacomparator.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.DTO.Role.RoleDTOCriar;
import com.univesp.pi.pizzariacomparator.Exception.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Model.Role;
import com.univesp.pi.pizzariacomparator.Repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ModelMapper roleMapper;

    public List<Role> buscarTodos(){       
        return roleRepository.findAll();
    }
    private Role buscarRolePorId(UUID id){
        Optional<Role> role = roleRepository.findById(id);
        return role.orElseThrow(() -> new ResourceNotFoundException("Role não encontrada - id: " + id));
    }
    public Role buscarRolePorNome(String roleName) {
        return roleRepository.findByroleName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada - nome: " + roleName));
    };
    
    public Role salvarRole(RoleDTOCriar roleDTOCriar) {   
        Role role = new Role();   
        roleMapper.map(roleDTOCriar, role);
        return roleRepository.save(role);        
    }
    public void excluirRole(UUID id) {
        Role roleAtual = buscarRolePorId(id);
        roleRepository.delete(roleAtual);
    } 
}
