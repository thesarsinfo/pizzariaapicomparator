package com.univesp.pi.pizzariacomparator.DTO.Usuario;

import java.util.List;
import java.util.UUID;

import com.univesp.pi.pizzariacomparator.Model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRetornoDTO {
    private String usuarioId;
    private String nome;
    private String token;
    List<String> listaRoles;
    
}