package br.com.projeto.projetobanco.repository;

import br.com.projeto.projetobanco.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository <Transacao, Integer>{
}
