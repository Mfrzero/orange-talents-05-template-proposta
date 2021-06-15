package com.zup.matheusfernandes.proposta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.zup.matheusfernandes.cartao.Cartao;
import com.zup.matheusfernandes.cartao.CartaoApi;
import com.zup.matheusfernandes.cartao.CartaoRepository;
import com.zup.matheusfernandes.cartao.CartaoRequest;
import com.zup.matheusfernandes.cartao.CartaoResponse;

import feign.FeignException;

@Service
public class DisponibilizaCartaoParaPropostasElegiveis {

    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoApi cartaoApi;

    @Scheduled(initialDelay = 60000, fixedRate = 60000)
    public void disponibilizarCartao() {

        List<Proposta> propostas = propostaRepository.findByStatusEqualsAndCartaoIsNull(StatusProposta.ELEGIVEL);
        for (Proposta proposta : propostas) {
            try {
            	CartaoResponse propostaCadastradaEmProvedora = cartaoApi
                        .buscarPropostaCartao(proposta.getId().toString());
                Cartao cartao = cartaoRepository.save(new Cartao(propostaCadastradaEmProvedora.getId()));
                proposta.setCartao(cartao);
                propostaRepository.save(proposta);
            } catch (FeignException propostaNaoEncontrada) {
                CartaoRequest requestCartao = new CartaoRequest(proposta.getDocumento(),
                        proposta.getNome(), proposta.getId().toString());
                try {
                    cartaoApi.criarPropostaCartao(requestCartao);
                } catch (FeignException erroNaoFoiPossivelCadastrarProposta) {
                }
            }
        }
    }
}
