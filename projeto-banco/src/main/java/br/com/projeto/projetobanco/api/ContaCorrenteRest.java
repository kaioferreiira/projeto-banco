package br.com.projeto.projetobanco.api;


import java.util.List;

import br.com.projeto.projetobanco.entity.dto.ContaCorrenteDTO;
import br.com.projeto.projetobanco.service.ContaCorrenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contacorrentes"})
public class ContaCorrenteRest {


    private ContaCorrenteService contaCorrenteService;

    @Autowired
    public ContaCorrenteRest(ContaCorrenteService contaCorrenteService) {
        this.contaCorrenteService = contaCorrenteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ContaCorrenteDTO>> findAll(){

        List<ContaCorrenteDTO> contaCorrentesDTO = contaCorrenteService.findAll();

        return ResponseEntity.ok().body(contaCorrentesDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContaCorrenteDTO> findById(@PathVariable Integer id){

        ContaCorrenteDTO contaCorrenteDTO = contaCorrenteService.findById(id);

        return ResponseEntity.ok().body(contaCorrenteDTO);
    }

    @PostMapping
    public void salvarContaCorrente(@RequestBody ContaCorrenteDTO contaCorrenteDTO){

        contaCorrenteService.salvarContaCorrente(contaCorrenteDTO);


    }

    @PostMapping("/realiza-saque/{id}")
    public ResponseEntity<Void> realizaSaque(@PathVariable Integer id, @RequestParam Double saque){

        contaCorrenteService.realizaSaque(id, saque);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/realiza-deposito/{id}")
    public ResponseEntity<Void> realizaDeposito(@PathVariable Integer id, @RequestParam Double valorReal,
            Double valorEnvelope){

            contaCorrenteService.findById(id);

        return ResponseEntity.ok().build();
    }



//    Extrato das últimas transações
//    Realizar depósito (Irá receber um envelope com valor informado e valor real, se valor real for diferente do informado, não deve realizar depósito)


}
