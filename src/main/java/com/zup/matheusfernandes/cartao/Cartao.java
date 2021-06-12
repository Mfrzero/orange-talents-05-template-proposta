package com.zup.matheusfernandes.cartao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.zup.matheusfernandes.biometria.Biometria;

@Entity
public class Cartao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroCartao;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Biometria> biometrias;
	
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
	
}
