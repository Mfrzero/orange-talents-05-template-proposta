package com.zup.matheusfernandes.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Bloqueio {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	LocalDateTime dataBloqueio;
	private String ipCliente;
	private String userAgentClienteString;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(String ipCliente, String userAgentClienteString) {
		this.dataBloqueio = dataBloqueio;
		this.ipCliente = ipCliente;
		this.userAgentClienteString = userAgentClienteString;
	}
}
