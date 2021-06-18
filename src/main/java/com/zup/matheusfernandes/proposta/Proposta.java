package com.zup.matheusfernandes.proposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zup.matheusfernandes.analise.AnalisePropostaApi;
import com.zup.matheusfernandes.analise.AnalisePropostaRequest;
import com.zup.matheusfernandes.analise.AnalisePropostaResponse;
import com.zup.matheusfernandes.cartao.Cartao;
import com.zup.matheusfernandes.compartilhado.CPFOrCNPJ;
import com.zup.matheusfernandes.compartilhado.Criptografa;

import feign.FeignException;

@Entity
public class Proposta {

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
	@Enumerated(EnumType.STRING)
	private StatusProposta status;
	@OneToOne(cascade = CascadeType.ALL)
	private Cartao cartao;
	
	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String documento,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.nome = nome;
		this.email = email;
		this.documento = Criptografa.encrypt(documento);
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	public void realizarAnalise(AnalisePropostaApi api) throws JsonMappingException, JsonProcessingException {
		try {
			AnalisePropostaRequest request = new AnalisePropostaRequest(documento, nome, id.toString());
			AnalisePropostaResponse response = api.analisar(request);
			if (response.getResultadoSolicitacao().equals("SEM_RESTRICAO")) {
				status = StatusProposta.ELEGIVEL;
			}
		} catch (FeignException e) {
			status = StatusProposta.NAO_ELEGIVEL;
		}
		
	}

}
