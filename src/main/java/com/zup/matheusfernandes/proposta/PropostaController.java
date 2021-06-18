package com.zup.matheusfernandes.proposta;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zup.matheusfernandes.analise.AnalisePropostaApi;
import com.zup.matheusfernandes.compartilhado.Criptografa;

import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("api/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private AnalisePropostaApi AnalisePropostaApi;
	@Autowired
	private Tracer tracer;
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder)
			throws JsonMappingException, JsonProcessingException {
		Span activeSpan = tracer.activeSpan();
		String userEmail = activeSpan.getBaggageItem("user.email");
		activeSpan.setBaggageItem("user.email", userEmail);
		
		String documentoCriptografado = Criptografa.encrypt(request.getDocumento());
		if (propostaRepository.findByDocumento(documentoCriptografado).isEmpty()) {
			Proposta proposta = propostaRepository.save(request.converter());
			proposta.realizarAnalise(AnalisePropostaApi);
			propostaRepository.save(proposta);
			URI uri = builder.path("proposta/{id}").build(request.getId());
			logger.info("Proposta Criada com Sucesso!", proposta.getDocumento());
			return ResponseEntity.created(uri).build();
		}
		return ResponseEntity.unprocessableEntity().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultarProposta(@PathVariable("id") Long id) {
		Span activeSpan = tracer.activeSpan();
		String userEmail = activeSpan.getBaggageItem("user.email");
		activeSpan.setBaggageItem("user.email", userEmail);
		Optional<Proposta> proposta = propostaRepository.findById(id);
		if (proposta.isPresent()) {
			return ResponseEntity.ok().body(new PropostaResponse(proposta.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
