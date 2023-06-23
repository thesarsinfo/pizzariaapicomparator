package com.univesp.pi.pizzariacomparator.DTO.Pizzaria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzariaDTOCriar {
   
    private String nome;
    private String endereco;
    private String cep;
    private String cidade;
    private String site;
    private String telefone;
    private Double avaliacao;
}
