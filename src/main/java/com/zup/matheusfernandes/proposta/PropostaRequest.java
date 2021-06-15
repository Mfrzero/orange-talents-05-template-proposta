package com.zup.matheusfernandes.proposta;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zup.matheusfernandes.compartilhado.CPFOrCNPJ;

public class PropostaRequest {
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
	
	
	public Long getId() {
		return id;
	}
	
	public String getDocumento() {
		return documento;
	}
	

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Proposta converter() {
		return new Proposta(nome,email,documento,endereco,salario);
	}

}
