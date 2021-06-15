package com.zup.matheusfernandes.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zup.matheusfernandes.bloqueio.BloqueioRequest;
import com.zup.matheusfernandes.bloqueio.BloqueioResponse;

@FeignClient(name = "cartoes", url="http://localhost:8888/api/cartoes")
public interface CartaoApi {

	@PostMapping
	CartaoResponse criarPropostaCartao(CartaoRequest request);
	
	@GetMapping
	CartaoResponse buscarPropostaCartao(@RequestParam("idProposta") Long id);

	@PostMapping("/{numeroCartao}/bloqueios")
	BloqueioResponse bloquearCartao(@PathVariable("numeroCartao") String numeroCartao, BloqueioRequest request);
}
