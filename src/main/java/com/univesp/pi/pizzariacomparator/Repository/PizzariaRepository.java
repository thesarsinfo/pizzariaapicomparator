package com.univesp.pi.pizzariacomparator.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univesp.pi.pizzariacomparator.Model.Pizzaria;

public interface PizzariaRepository extends JpaRepository<Pizzaria ,UUID>{

    
}
