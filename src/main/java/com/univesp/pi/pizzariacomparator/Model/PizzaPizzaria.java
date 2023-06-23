package com.univesp.pi.pizzariacomparator.Model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pizza_pizzaria")
public class PizzaPizzaria implements Comparable<PizzaPizzaria>{
    //
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varbinary(36)")
    private UUID id;
    //
    @ManyToOne
    @JoinColumn(name = "pizzaria_id")	
	@NotNull
    private Pizzaria pizzaria;
    //
    @ManyToOne
    @JoinColumn(name = "pizza_id")	
	@NotNull
    private Pizza pizza;
    //
	@Column(nullable = false)
	@NotBlank
    private BigDecimal preco;

    public PizzaPizzaria(@NotBlank Pizzaria pizzaria, @NotBlank Pizza pizza, @NotBlank BigDecimal preco) {
        this.pizzaria = pizzaria;
        this.pizza = pizza;
        this.preco = preco;
    }

    @Override
    public int compareTo(PizzaPizzaria outraPizza) {
        return preco.compareTo(outraPizza.preco);
    }

}
