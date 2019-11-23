package br.com.projeto.projetobanco;

import java.time.LocalDate;

import br.com.projeto.projetobanco.entity.Cliente;
import br.com.projeto.projetobanco.entity.ContaCorrente;
import br.com.projeto.projetobanco.repository.ClienteRepository;
import br.com.projeto.projetobanco.repository.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoBancoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBancoApplication.class, args);
	}

	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {

		Cliente cliente = new Cliente(null, "teste", "123", "123", LocalDate.now());

		clienteRepository.save(cliente);


		Cliente cliente1Banco = clienteRepository.findById(1).get();
		ContaCorrente conta = new ContaCorrente(null, 250.0, cliente1Banco);

		contaCorrenteRepository.save(conta);
	}
}
