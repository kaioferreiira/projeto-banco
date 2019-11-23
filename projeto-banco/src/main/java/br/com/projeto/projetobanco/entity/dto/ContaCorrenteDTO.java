package br.com.projeto.projetobanco.entity.dto;

import br.com.projeto.projetobanco.entity.Cliente;

public class ContaCorrenteDTO {

    private Integer id;
    private Double saldo;
    private Cliente cliente;


    public ContaCorrenteDTO() {
    }

    public ContaCorrenteDTO(Integer id, Double saldo, Cliente cliente) {
        this.id = id;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
