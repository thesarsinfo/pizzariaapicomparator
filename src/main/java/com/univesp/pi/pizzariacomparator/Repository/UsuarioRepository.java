package com.univesp.pi.pizzariacomparator.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.univesp.pi.pizzariacomparator.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario ,UUID>{
    @EntityGraph(attributePaths = "roles")
    Optional<Usuario> findByemail(String email);
}
