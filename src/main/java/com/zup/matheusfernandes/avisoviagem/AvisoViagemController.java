package com.zup.matheusfernandes.avisoviagem;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.matheusfernandes.cartao.Cartao;
import com.zup.matheusfernandes.cartao.CartaoRepository;

@RestController
@RequestMapping("/cartoes/{id}")
public class AvisoViagemController {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@PostMapping("/viagens")
	public ResponseEntity<?> criarAvisoViagem(@PathVariable Long id, @RequestBody @Valid AvisoViagemRequest request,
							HttpServletRequest httpRequest){

		Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		String ipCliente = httpRequest.getLocalAddr();
		String userAgentCliente = httpRequest.getHeader("User-Agent");
		AvisoViagem avisoViagem = new AvisoViagem(request.getDestinoViagem(),request.getTerminoViagem(), ipCliente, userAgentCliente, possivelCartao.get());
		possivelCartao.get().adicionaAvisoViagem(avisoViagem);
		cartaoRepository.save(possivelCartao.get());
		return ResponseEntity.ok().build();
	}
	
}
