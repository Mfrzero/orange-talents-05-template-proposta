package com.zup.matheusfernandes.proposta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long>{

	Optional<Proposta> findByDocumento(String documento);

}