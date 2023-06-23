package com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria;

import java.util.List;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTO;
import com.univesp.pi.pizzariacomparator.DTO.Pizzaria.PizzariaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaPizzariaDTOCriar {

    private PizzariaDTO pizzaria;
    private List<PizzaDTO> pizza;
    
}
