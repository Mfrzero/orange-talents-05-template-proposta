package com.zup.matheusfernandes.proposta;

public class PropostaResponse {

	private Long id;
	private StatusProposta status;

	public PropostaResponse(Proposta proposta) {
		this.id = proposta.getId();
		this.status = proposta.getStatus();
	}

	public Long getId() {
		return id;
	}

	public StatusProposta getEstado() {
		return status;
	}
}
