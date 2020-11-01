package com.gestao.gestao_alunos.dto;

public class MensagemDTO {
    private String mensagem;

    public MensagemDTO(){ }

    public MensagemDTO(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

