package com.zup.matheusfernandes.avisoviagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import io.micrometer.core.lang.NonNull;

public class AvisoViagemRequest {

	@NotBlank
	private String destinoViagem;
	@NonNull @Future
	private LocalDate terminoViagem;

	public AvisoViagemRequest(@NotBlank String destinoViagem, @Future LocalDate terminoViagem) {
		this.destinoViagem = destinoViagem;
		this.terminoViagem = terminoViagem;
	}
	public String getDestinoViagem() {
		return destinoViagem;
	}
	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}
	
	
}
