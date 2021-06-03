package com.zup.matheusfernandes.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zup.matheusfernandes.CPFOrCNPJ;

@Entity
public class Proposta {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank @Email
	private String email;
	@NotBlank @CPFOrCNPJ
	private String documento;
	@NotBlank
	private String endereco;
	@NotNull @Positive
	private BigDecimal salario;
	
	public Proposta(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
	}

}
