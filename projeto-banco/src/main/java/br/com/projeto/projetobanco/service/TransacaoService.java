package br.com.projeto.projetobanco.service;

import br.com.projeto.projetobanco.entity.Transacao;
import br.com.projeto.projetobanco.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void salvarTransacao(Transacao transacao){
        transacaoRepository.save(transacao);
    }


}
