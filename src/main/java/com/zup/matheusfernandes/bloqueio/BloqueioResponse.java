package com.zup.matheusfernandes.bloqueio;

import java.time.LocalDateTime;

import com.zup.matheusfernandes.cartao.StatusCartao;

public class BloqueioResponse {

	private LocalDateTime dataBloqueio;
	private StatusCartao statusCartao;
	
	public LocalDateTime getDataBloqueio() {
		return dataBloqueio;
	}
	public StatusCartao getStatusCartao() {
		return statusCartao;
	}
	
	
}
