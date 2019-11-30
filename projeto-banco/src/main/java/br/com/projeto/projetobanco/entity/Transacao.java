package br.com.projeto.projetobanco.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.projeto.projetobanco.entity.enums.OperacaoEnum;


@Entity
public class Transacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @ManyToOne
    private ContaCorrente conta;

    private LocalDateTime data;

     @Enumerated
    private OperacaoEnum tipoOperacao;

    private Double valor;


    public Transacao() {
    }

    public Transacao(Integer id, ContaCorrente conta, LocalDateTime data, OperacaoEnum tipoOperacao, Double valor) {
        Id = id;
        this.conta = conta;
        this.data = data;
        this.tipoOperacao = tipoOperacao;
        this.valor = valor;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public OperacaoEnum getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(OperacaoEnum tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
