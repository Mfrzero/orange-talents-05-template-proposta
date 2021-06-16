package com.zup.matheusfernandes.cartao;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.matheusfernandes.biometria.Biometria;
import com.zup.matheusfernandes.biometria.BiometriaRequest;
import com.zup.matheusfernandes.bloqueio.Bloqueio;

@RestController
@RequestMapping("api/cartoes")
public class CartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CartaoApi cartaoApi;
	
	@PostMapping("/biometrias/{id}")
	public ResponseEntity<?> criar(@PathVariable Long id, @RequestBody @Valid BiometriaRequest biometriaRequest,
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
	
	@PostMapping("/bloqueios/{id}")
	public ResponseEntity<?> bloquearCartao(@PathVariable Long id, HttpServletRequest request){
		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
		if(possivelCartao.isPresent()) {
			
			HttpStatus bloqueioResponse = possivelCartao.get().realizarBloqueioNaApi(cartaoApi);
			if (bloqueioResponse.value( )== HttpStatus.OK.value()) {
				String ipCliente = request.getLocalAddr();
				String userAgentCliente = request.getHeader("User-Agent");
				possivelCartao.get().adicionarBloqueio(new Bloqueio(ipCliente,userAgentCliente));
				cartaoRepository.save(possivelCartao.get());
			}
			return ResponseEntity.status(bloqueioResponse).build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
