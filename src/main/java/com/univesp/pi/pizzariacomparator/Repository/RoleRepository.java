package com.univesp.pi.pizzariacomparator.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univesp.pi.pizzariacomparator.Model.Role;

public interface RoleRepository extends JpaRepository<Role ,UUID>{
    Optional<Role> findByroleName(String roleName);
}
