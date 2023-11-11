package com.univesp.pi.pizzariacomparator.Model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pizzaria")
public class Pizzaria {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varbinary(36)")
    private UUID id;
    //
    @Size(max = 25)
	@Column(nullable = false, unique = true)
	@NotBlank
    private String nome;
    //
    @Size(max = 100)
	@Column(nullable = false, unique = true)
	@NotBlank
    private String endereco;
    //
    @Size(max = 10)
	@Column(nullable = false)
	@NotBlank
    private String cep;
    //
    @Size(max = 25)
	@Column(nullable = false, unique = false)
	@NotBlank
    private String cidade;
    //
    @Size(max = 100)
	@Column(nullable = false, unique = true)
	@NotBlank
    private String site;
    //
    @Size(max = 15)
	@Column(nullable = false, unique = false)
	@NotBlank
    private String telefone;
    //
    @Max(5)
	@Column(nullable = false, unique = false)
	@NotBlank
    private BigDecimal avaliacao;    
}
