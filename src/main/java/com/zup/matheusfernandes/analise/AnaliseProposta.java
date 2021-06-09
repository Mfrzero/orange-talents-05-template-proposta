package com.zup.matheusfernandes.analise;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zup.matheusfernandes.CPFOrCNPJ;

public class AnaliseProposta {

	@NotBlank @CPFOrCNPJ
	private String documento;
	@NotBlank
	private String nome;
	@NotNull
	private Long idProposta;

	public AnaliseProposta(String documento, String nome, @NotNull String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = Long.parseLong(idProposta);
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
}
