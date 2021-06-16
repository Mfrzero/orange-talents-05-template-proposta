package com.zup.matheusfernandes.carteiraDigital;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraDigitalRequest {

	@Email @NotBlank
	private String email;
	private CarteiraEnum carteira;

	public CarteiraDigitalRequest(@Email @NotBlank String email, CarteiraEnum carteira) {
		this.email = email;
		this.carteira = carteira;
	}
	
	public String getEmail() {
		return email;
	}
	public CarteiraEnum getCarteira() {
		return carteira;
	}

	public void setCarteira(CarteiraEnum carteiraEnum) {
		this.carteira = carteiraEnum;
	}

}
