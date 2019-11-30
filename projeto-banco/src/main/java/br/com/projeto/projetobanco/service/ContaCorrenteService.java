package br.com.projeto.projetobanco.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.projeto.projetobanco.entity.Cliente;
import br.com.projeto.projetobanco.entity.ContaCorrente;
import br.com.projeto.projetobanco.entity.Transacao;
import br.com.projeto.projetobanco.entity.dto.ClienteDTO;
import br.com.projeto.projetobanco.entity.dto.ContaCorrenteDTO;
import br.com.projeto.projetobanco.entity.enums.OperacaoEnum;
import br.com.projeto.projetobanco.repository.ContaCorrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteService {

    private ContaCorrenteRepository contaCorrenteRepository;
    private ClienteService clienteService;
    private TransacaoService transacaoService;

    @Autowired
    public ContaCorrenteService(ContaCorrenteRepository contaCorrenteRepository, ClienteService clienteService, TransacaoService transacaoService) {
        this.contaCorrenteRepository = contaCorrenteRepository;
        this.clienteService = clienteService;
        this.transacaoService = transacaoService;
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


    public void  realizaSaque(Integer id, Double  saque){

        ContaCorrenteDTO contaCorrenteDTO = findById(id);

        if (contaCorrenteDTO.getSaldo() < saque){
            throw new RuntimeException(" Saldo insuficiente! ");
        }

        double novoSaldo = contaCorrenteDTO.getSaldo() - saque;

        contaCorrenteDTO.setSaldo(novoSaldo);

        atualizarSaldoContaCorrente(contaCorrenteDTO);

        registraTransacao(contaCorrenteDTO, saque,  OperacaoEnum.DEBITO);


    }

    public void registraTransacao(ContaCorrenteDTO contaCorrenteDTO, Double valorOperacao, OperacaoEnum tipo){

        Transacao transacao = new Transacao();


        ContaCorrente contaCorrente = new ContaCorrente(contaCorrenteDTO.getId(), contaCorrenteDTO.getSaldo(),
                contaCorrenteDTO.getCliente());

        transacao.setData(LocalDateTime.now());
        transacao.setConta(contaCorrente);
        transacao.setTipoOperacao(tipo);
        transacao.setValor(valorOperacao);

        transacaoService.salvarTransacao(transacao);

    }



    public void  realizaDeposito(Integer id, Double valorReal, Double valorEnvelope ){

        if (valorReal != valorEnvelope){
            throw new RuntimeException(" Valor informado "+ valorReal + "é diferente do valor contido no envelope! " + valorEnvelope);
        }

        ContaCorrenteDTO contaCorrenteDTO = findById(id);

        double novoSaldo = contaCorrenteDTO.getSaldo() + valorReal;



        contaCorrenteDTO.setSaldo(novoSaldo);

        atualizarSaldoContaCorrente(contaCorrenteDTO);

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

    public void atualizarSaldoContaCorrente(ContaCorrenteDTO contaDTO){

        Cliente cliente = contaDTO.getCliente();
        clienteService.salvarCliente(cliente);

        ClienteDTO clienteDTO=  clienteService.findClienteCpf(contaDTO.getCliente().getCpf());
        Cliente clientaBanco = new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getCpf(),
                clienteDTO.getRg(), clienteDTO.getDataNascimento());


        ContaCorrente contaCorrente = new ContaCorrente(null, contaDTO.getSaldo(), clientaBanco);

        contaCorrenteRepository.save(contaCorrente);
    }


}
