package com.zup.matheusfernandes.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analiseSolicitacao", url="http://localhost:9999/api")
public interface AnalisePropostaApi {
	
	@PostMapping("solicitacao")
	AnalisePropostaResponse analisar(AnalisePropostaRequest request);

}
