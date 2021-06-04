package com.zup.matheusfernandes.proposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PropostaController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PropostaRepository propostaRepository;

	@Transactional
	@PostMapping("proposta")
	public ResponseEntity<PropostaForm> criaProposta(@RequestBody @Valid PropostaForm form, UriComponentsBuilder builder){
		
		if(propostaRepository.findByDocumento(form.getDocumento()).isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		Proposta proposta = form.converter(manager);
		manager.persist(proposta);
		URI uri = builder.path("proposta/{id}").build(form.getId());
		return ResponseEntity.created(uri).build();
	}
}
