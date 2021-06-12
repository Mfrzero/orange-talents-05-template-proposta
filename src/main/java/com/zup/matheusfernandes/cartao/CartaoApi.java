package com.zup.matheusfernandes.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url="http://localhost:8888/api/cartoes")
public interface CartaoApi {

	@PostMapping
	CartaoResponse criarPropostaCartao(CartaoRequest request);
	
	@GetMapping
	CartaoResponse buscarPropostaCartao(@RequestParam("idProposta") String propostaId);
}
