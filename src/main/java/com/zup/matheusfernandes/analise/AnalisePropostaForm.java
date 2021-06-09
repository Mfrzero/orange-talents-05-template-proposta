package com.zup.matheusfernandes.analise;

public class AnalisePropostaForm {

	private String documento;
	private String nome;
	private String resultadoSolicitacao;
	private String idProposta;

	@Deprecated
	public AnalisePropostaForm() {
	}

	public AnalisePropostaForm(String documento, String nome, String resultadoSolicitacao, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
}
