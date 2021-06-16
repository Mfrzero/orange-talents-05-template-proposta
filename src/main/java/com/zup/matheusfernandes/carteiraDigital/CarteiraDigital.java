package com.zup.matheusfernandes.carteiraDigital;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.zup.matheusfernandes.cartao.Cartao;

import io.micrometer.core.lang.NonNull;

@Entity
public class CarteiraDigital {

	@Id 
	private String id;
	@Email @NotBlank
	private String email;
	@Enumerated(EnumType.STRING) @NonNull
	private CarteiraEnum carteira;
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public CarteiraDigital() {
	}

	public CarteiraDigital(String id, @Email @NotBlank String email, CarteiraEnum carteira, Cartao cartao) {
		this.id = id;
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}
	 
}
