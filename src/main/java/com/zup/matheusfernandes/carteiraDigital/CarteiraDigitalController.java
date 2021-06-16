package com.zup.matheusfernandes.carteiraDigital;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.matheusfernandes.cartao.Cartao;
import com.zup.matheusfernandes.cartao.CartaoApi;
import com.zup.matheusfernandes.cartao.CartaoRepository;

import feign.FeignException;

@RestController
@RequestMapping("/api/cartoes")
public class CarteiraDigitalController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	@Autowired
	private CartaoApi cartaoApi;

	@PostMapping("/{id}/carteiras")
	public ResponseEntity<?> associaCarteira(@PathVariable("id") Long id, @RequestBody @Valid CarteiraDigitalRequest request,
			UriComponentsBuilder builder){
		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
		if (possivelCartao.isPresent()) {
			try {
				request.setCarteira(request.getCarteira());
				CarteiraDigitalResponse response = cartaoApi.adicionarCarteira(id, request);
				possivelCartao.get().adicionaCarteiraDigital(new CarteiraDigital(response.getId(), request.getEmail(), request.getCarteira(),possivelCartao.get()));
				cartaoRepository.save(possivelCartao.get());
				URI uri = builder.path("/{idCartao}/carteras/{idCarteira}").build(possivelCartao.get().getId(), response.getId());
				return ResponseEntity.created(uri).build();
			} catch (FeignException e) {
				return ResponseEntity.status(e.status()).build();
			}
		}
		return ResponseEntity.notFound().build();
	}
}
