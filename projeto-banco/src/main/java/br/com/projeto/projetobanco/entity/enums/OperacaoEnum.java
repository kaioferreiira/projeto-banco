package br.com.projeto.projetobanco.entity.enums;

public enum OperacaoEnum {


    CREDITO("CREDITO"),
    DEBITO("DEBITO");

    private String descricao;

    OperacaoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }


}




