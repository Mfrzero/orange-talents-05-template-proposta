package com.zup.matheusfernandes.cartao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.http.HttpStatus;

import com.zup.matheusfernandes.biometria.Biometria;
import com.zup.matheusfernandes.bloqueio.Bloqueio;
import com.zup.matheusfernandes.bloqueio.BloqueioRequest;

import feign.FeignException.FeignClientException;

@Entity
public class Cartao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroCartao;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Biometria> biometrias;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Bloqueio> bloqueios;
	@Enumerated(EnumType.STRING)
	private StatusCartao statusCartao;
	
	@Deprecated
	public Cartao() {
	}

	public Cartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public void cadastrarBiometria(Biometria biometria) {
		biometria.setCartao(this);
		biometrias.add(biometria);
	}
	
	public HttpStatus realizarBloqueioNaApi(CartaoApi cartaoApi) {
		try {
			cartaoApi.bloquearCartao(numeroCartao, new BloqueioRequest("Sistema de Propostas"));
			return HttpStatus.OK;
		} catch (FeignClientException e) {
			return HttpStatus.UNPROCESSABLE_ENTITY;
		}
	}
	
	
	public void adicionarBloqueio(Bloqueio bloqueio) {
		statusCartao = StatusCartao.BLOQUEADO;
		bloqueios.add(bloqueio);
	}
	
}
