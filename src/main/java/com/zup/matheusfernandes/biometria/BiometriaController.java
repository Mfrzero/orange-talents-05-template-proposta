package com.zup.matheusfernandes.biometria;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.matheusfernandes.cartao.Cartao;
import com.zup.matheusfernandes.cartao.CartaoRepository;

@RestController
@RequestMapping("api/cartoes")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@PostMapping("/biometrias/{id}")
	public ResponseEntity<?> criar(@PathVariable Long id, @RequestBody BiometriaRequest biometriaRequest,
							UriComponentsBuilder uriBuilder){
		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Cartao cartao = possivelCartao.get();
		Biometria biometria = biometriaRequest.converter();
		cartao.cadastrarBiometria(biometria);
		cartaoRepository.save(cartao);
		
		URI uri = uriBuilder.path("{id}").build(biometria.getId());
		return ResponseEntity.created(uri).build();
				
	}
}
