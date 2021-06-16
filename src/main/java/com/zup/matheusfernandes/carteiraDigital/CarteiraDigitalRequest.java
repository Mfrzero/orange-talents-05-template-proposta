package com.zup.matheusfernandes.carteiraDigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraDigitalRequest {

	@Email @NotBlank
	private String email;
	private String carteira;

	public CarteiraDigitalRequest(@Email @NotBlank String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}
	
	public String getEmail() {
		return email;
	}
	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public CarteiraEnum getCarteiraEnum() {
		return CarteiraEnum.valueOf(carteira);
	}
}
