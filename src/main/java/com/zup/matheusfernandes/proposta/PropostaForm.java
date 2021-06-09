package com.zup.matheusfernandes.proposta;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zup.matheusfernandes.CPFOrCNPJ;

public class PropostaForm {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotBlank @Email
	private String email;
	@NotBlank 
	@CPFOrCNPJ 
	private String documento;
	@NotBlank
	private String endereco;
	@NotNull @Positive
	private BigDecimal salario;
	
	public PropostaForm(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = documento;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDocumento() {
		return documento;
	}

	public Proposta converter() {
		return new Proposta(nome,email,documento,endereco,salario);
	}
}
