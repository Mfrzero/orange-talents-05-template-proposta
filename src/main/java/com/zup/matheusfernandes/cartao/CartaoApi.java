package com.zup.matheusfernandes.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zup.matheusfernandes.avisoviagem.AvisoViagemRequest;
import com.zup.matheusfernandes.avisoviagem.AvisoViagemResponse;
import com.zup.matheusfernandes.bloqueio.BloqueioRequest;
import com.zup.matheusfernandes.bloqueio.BloqueioResponse;
import com.zup.matheusfernandes.carteiraDigital.CarteiraDigitalRequest;
import com.zup.matheusfernandes.carteiraDigital.CarteiraDigitalResponse;

@FeignClient(name = "cartoes", url="http://localhost:8888/api/cartoes")
public interface CartaoApi {

	@PostMapping
	CartaoResponse criarPropostaCartao(CartaoRequest request);
	
	@GetMapping
	CartaoResponse buscarPropostaCartao(@RequestParam("idProposta") Long id);

	@PostMapping("/{id}/bloqueios")
	BloqueioResponse bloquearCartao(@PathVariable("id") String id, BloqueioRequest request);

	@PostMapping("/{id}/avisos")
	AvisoViagemResponse avisoViagem(@PathVariable("id") String id, AvisoViagemRequest request);
	
	@PostMapping("{id}/carteiras")
	CarteiraDigitalResponse adicionarCarteira(@PathVariable("id") Long id, CarteiraDigitalRequest request);
}
