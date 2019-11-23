package br.com.projeto.projetobanco.service;


import br.com.projeto.projetobanco.entity.Cliente;
import br.com.projeto.projetobanco.entity.dto.ClienteDTO;
import br.com.projeto.projetobanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO findClienteCpf(String cpf){

        ClienteDTO clienteDTO = new ClienteDTO();
        Cliente cliente = clienteRepository.findByCpf(cpf);

        if(cliente == null){
            throw new RuntimeException("Cliente nao encontrado! ");
        }

        clienteDTO.setId(cliente.getId());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setDataNascimento(cliente.getDataNascimento());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setRg(cliente.getRg());

        return clienteDTO;

    }

    public Boolean findCpfBoolean(String cpf){

        ClienteDTO clienteDTO = new ClienteDTO();
        Cliente cliente = clienteRepository.findByCpf(cpf);

        if(cliente == null){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    public void salvarCliente(Cliente cliente){

        clienteRepository.save(cliente);
    }



}
