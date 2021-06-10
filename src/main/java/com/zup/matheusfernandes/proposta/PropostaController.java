package com.zup.matheusfernandes.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zup.matheusfernandes.analise.AnalisePropostaApi;

@RestController
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private AnalisePropostaApi AnalisePropostaApi;

	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	@PostMapping("proposta")
	public ResponseEntity<PropostaResponse> criaProposta(@RequestBody @Valid PropostaResponse response,
			UriComponentsBuilder builder) throws JsonMappingException, JsonProcessingException {

		if (propostaRepository.findByDocumento(response.getDocumento()).isEmpty()) {
			Proposta proposta = propostaRepository.save(response.converter());
			proposta.realizarAnalise(AnalisePropostaApi);
			propostaRepository.save(proposta);
			URI uri = builder.path("proposta/{id}").build(response.getId());
			logger.info("Proposta Criada com Sucesso!", proposta.getDocumento());
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.unprocessableEntity().build();
	}
}
