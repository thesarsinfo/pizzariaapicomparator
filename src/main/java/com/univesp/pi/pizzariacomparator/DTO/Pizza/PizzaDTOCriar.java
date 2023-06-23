package com.univesp.pi.pizzariacomparator.DTO.Pizza;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PizzaDTOCriar {
    
    private String nome;    
    private String categoria;
    private String descricao;
    private String urlimagem;
}
