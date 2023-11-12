package com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria;

import java.math.BigDecimal;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTOPut;
import com.univesp.pi.pizzariacomparator.DTO.Pizzaria.PizzariaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaPizzariaDTOPut {

    private PizzariaDTO pizzaria;
    private PizzaDTOPut pizza;
    private BigDecimal preco;
    
}
