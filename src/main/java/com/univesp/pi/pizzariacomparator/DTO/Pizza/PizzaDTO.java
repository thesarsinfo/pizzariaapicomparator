package com.univesp.pi.pizzariacomparator.DTO.Pizza;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {
    
    private UUID id;   
    private BigDecimal preco;
}
