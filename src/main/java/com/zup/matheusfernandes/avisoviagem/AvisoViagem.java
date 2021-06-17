package com.zup.matheusfernandes.avisoviagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.zup.matheusfernandes.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String destinoViagem;
	private LocalDate terminoViagem;
	@CreationTimestamp
	private LocalDateTime instanteAvisoViagem;
	private String ipCliente;
	private String userAgentCliente;
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {
		super();
	}
	
	public AvisoViagem(String destinoViagem, LocalDate terminoViagem, 
			String ipCliente, String userAgentCliente, Cartao cartao) {
		this.destinoViagem = destinoViagem;
		this.terminoViagem = terminoViagem;
		this.instanteAvisoViagem = instanteAvisoViagem;
		this.ipCliente = ipCliente;
		this.userAgentCliente = userAgentCliente;
		this.cartao = cartao;
	}



	
	
}
