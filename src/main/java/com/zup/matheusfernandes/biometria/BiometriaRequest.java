package com.zup.matheusfernandes.biometria;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BiometriaRequest {

	@NotBlank @JsonProperty
	private String fingerprint;
	
	@JsonCreator
	public BiometriaRequest(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	
	public Biometria converter() {
		try {
			Base64.getDecoder().decode(fingerprint);
			return new Biometria(fingerprint);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
