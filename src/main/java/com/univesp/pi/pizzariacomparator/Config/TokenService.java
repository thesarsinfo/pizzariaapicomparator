package com.univesp.pi.pizzariacomparator.Config;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.univesp.pi.pizzariacomparator.Model.Usuario;

@Service
public class TokenService {


    public String gerarToken(Usuario usuario) {
     
        return JWT.create()
                .withIssuer("Produtos")
                .withSubject(usuario.getUsername())
                .withClaim("id",  usuario.getId().toString())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(20)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secreta"));
    }


    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("Produtos")
                .build().verify(token).getSubject();
    }

}
