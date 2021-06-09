package com.zup.matheusfernandes.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.matheusfernandes.CPFOrCNPJ;
import com.zup.matheusfernandes.analise.AnaliseProposta;
import com.zup.matheusfernandes.analise.AnalisePropostaApi;
import com.zup.matheusfernandes.analise.AnalisePropostaForm;

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
	
	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String nome, @NotBlank @Email String email, @NotBlank String documento,
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

	public void setStatus(StatusProposta status) {
		this.status = status;
	}

	public void realizarAnalise(AnalisePropostaApi api) throws JsonMappingException, JsonProcessingException {
		try {
			
			AnaliseProposta analiseProposta = new AnaliseProposta(documento, nome, id.toString());
			AnalisePropostaForm form = api.analisar(analiseProposta);
			
			if (form.getResultadoSolicitacao().equals("SEM_RESTRICAO")) {
				status = StatusProposta.ELEGIVEL;
			}
		} catch (FeignException e) {
			AnalisePropostaForm form = new ObjectMapper().readValue(e.contentUTF8(), AnalisePropostaForm.class);
			if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value() && form.getResultadoSolicitacao().equals("COM_RSTRICAO")) {
				status = StatusProposta.NAO_ELEGIVEL;
			}
		}
		
	}


}
