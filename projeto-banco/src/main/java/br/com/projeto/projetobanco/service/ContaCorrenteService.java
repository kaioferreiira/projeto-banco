package br.com.projeto.projetobanco.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.projeto.projetobanco.entity.Cliente;
import br.com.projeto.projetobanco.entity.ContaCorrente;
import br.com.projeto.projetobanco.entity.dto.ClienteDTO;
import br.com.projeto.projetobanco.entity.dto.ContaCorrenteDTO;
import br.com.projeto.projetobanco.repository.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteService {

    private ContaCorrenteRepository contaCorrenteRepository;
    private ClienteService clienteService;

    @Autowired
    public ContaCorrenteService(ContaCorrenteRepository contaCorrenteRepository, ClienteService clienteService) {
        this.contaCorrenteRepository = contaCorrenteRepository;
        this.clienteService = clienteService;
    }

    public  List<ContaCorrenteDTO> findAll(){

        List<ContaCorrenteDTO> contaCorrentesDTO = new ArrayList<>();
        List<ContaCorrente> contaCorrentes = contaCorrenteRepository.findAll();


        if(Objects.isNull(contaCorrentes)){
            throw new RuntimeException("Conta corrente nao encontrada! ");
        }

        for(ContaCorrente contaCorrente: contaCorrentes){

            ContaCorrenteDTO contaCorrenteDTO = new ContaCorrenteDTO(contaCorrente.getId(), contaCorrente.getSaldo(),
                    contaCorrente.getCliente());

            contaCorrentesDTO.add(contaCorrenteDTO);
        }

        return contaCorrentesDTO;

    }

    public ContaCorrenteDTO findById(Integer id){

        ContaCorrenteDTO contaCorrenteDTO = new ContaCorrenteDTO();
        Optional <ContaCorrente> contaCorrente = contaCorrenteRepository.findById(id);

        if(contaCorrente.get() == null){
            throw new RuntimeException("Conta corrente nao encontrada! ");
        }

        contaCorrenteDTO.setId(contaCorrente.get().getId());
        contaCorrenteDTO.setSaldo(contaCorrente.get().getSaldo());
        contaCorrenteDTO.setCliente(contaCorrente.get().getCliente());

        return contaCorrenteDTO;

    }

    public void salvarContaCorrente(ContaCorrenteDTO contaDTO){

        String cpfNovoCliente = contaDTO.getCliente().getCpf();
        Boolean clienteCadastrado = clienteService.findCpfBoolean(cpfNovoCliente);

        if (clienteCadastrado){
            throw new RuntimeException("Cliente ja cadastrado! ");
        }

        Cliente cliente = contaDTO.getCliente();
        clienteService.salvarCliente(cliente);

         ClienteDTO clienteDTO=  clienteService.findClienteCpf(contaDTO.getCliente().getCpf());
         Cliente clientaBanco = new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getCpf(),
                 clienteDTO.getRg(), clienteDTO.getDataNascimento());


        ContaCorrente contaCorrente = new ContaCorrente(null, contaDTO.getSaldo(), clientaBanco);

        contaCorrenteRepository.save(contaCorrente);
    }


}
