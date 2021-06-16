package com.zup.matheusfernandes.cartao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.http.HttpStatus;

import com.zup.matheusfernandes.avisoviagem.AvisoViagem;
import com.zup.matheusfernandes.biometria.Biometria;
import com.zup.matheusfernandes.bloqueio.Bloqueio;
import com.zup.matheusfernandes.bloqueio.BloqueioRequest;
import com.zup.matheusfernandes.carteiraDigital.CarteiraDigital;

import feign.FeignException.FeignClientException;

@Entity
public class Cartao {

	@Id 
	private String id;
	private String numeroCartao;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Biometria> biometrias;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Bloqueio> bloqueios;
	@Enumerated(EnumType.STRING)
	private StatusCartao statusCartao;
	@OneToMany(mappedBy="cartao", cascade=CascadeType.MERGE)
	private List<AvisoViagem> avisoVagem;
	@OneToMany(mappedBy="cartao", cascade = CascadeType.MERGE)
	private List<CarteiraDigital> carteiras;
	
	@Deprecated
	public Cartao() {
	}

	public Cartao(String id) {
		this.id = id;
		this.biometrias = new ArrayList<>();
		this.avisoVagem = new ArrayList<>();
		this.carteiras = new ArrayList<>();
	}

	public String getId() {
		return id;
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
	
	public void adicionaAvisoViagem(AvisoViagem avisoViagem) {
		this.avisoVagem.add(avisoViagem);
	}
	
	public void adicionaCarteiraDigital(CarteiraDigital carteiraDigital) {
		this.carteiras.add(carteiraDigital);
	}
	
}
