package com.zup.matheusfernandes.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.zup.matheusfernandes.cartao.Cartao;

@Entity
public class Biometria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fingerprint;
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public Biometria() {
	}

	public Biometria(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public Long getId() {
		return id;
	}
	
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
}
