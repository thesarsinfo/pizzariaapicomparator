package com.univesp.pi.pizzariacomparator.DTO.Usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTOLogin {    
    private String email;
    private String senha;   

   
}

