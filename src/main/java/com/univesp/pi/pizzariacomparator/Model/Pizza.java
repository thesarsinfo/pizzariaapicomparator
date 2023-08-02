package com.univesp.pi.pizzariacomparator.Model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pizza")
public class Pizza implements Serializable{
    


    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varbinary(36)")
    private UUID id;
    
    
	@Column(columnDefinition = "varchar(25)",nullable = false, unique = true)
	@NotBlank(message = "O nome da pizza é obrigatorio")
    private String nome; 

    
	@Column(columnDefinition = "varchar(25)",nullable = false)
	@NotBlank   
    private String categoria;

    @Column(columnDefinition = "varchar(100)")
    private String descricao;
    
	@Column(nullable = false, unique = true)
	@NotBlank
    private String urlimagem;
}
