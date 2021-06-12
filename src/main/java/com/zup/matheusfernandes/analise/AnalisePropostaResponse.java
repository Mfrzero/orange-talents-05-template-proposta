package com.zup.matheusfernandes.analise;

<<<<<<< HEAD
import javax.validation.constraints.NotNull;

=======
>>>>>>> 982ed91ecc3aa0ca55eb15eea272f0431f54c83f
public class AnalisePropostaResponse {

	private String documento;
	private String nome;
	private String resultadoSolicitacao;
<<<<<<< HEAD
	@NotNull
=======
>>>>>>> 982ed91ecc3aa0ca55eb15eea272f0431f54c83f
	private String idProposta;

	@Deprecated
	public AnalisePropostaResponse() {
	}

	public AnalisePropostaResponse(String documento, String nome, String resultadoSolicitacao, String idProposta) {
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
