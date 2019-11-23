package br.com.projeto.projetobanco.repository;

import br.com.projeto.projetobanco.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByCpf(String cpf);

}
