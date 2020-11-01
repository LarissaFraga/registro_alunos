package com.gestao.gestao_alunos.dto;

import com.gestao.gestao_alunos.model.Aluno;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlunoDTO {
    private Integer id;
    private String rga;
    private String nome;
    private String curso;
    private String situacao;
    private String registradoEm;

    public AlunoDTO() { }

    public AlunoDTO(Aluno aluno) {
        this.id = aluno.getId();
        this.rga = aluno.getRga();
        this.nome = aluno.getNome();
        this.curso = aluno.getCurso();
        this.situacao = aluno.isSituacao() ? "ativo" : "inativo";
        this.registradoEm = formatarData(aluno.getRegistradoEm());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRga() {
        return rga;
    }

    public void setRga(String rga) {
        this.rga = rga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getRegistradoEm() {
        return registradoEm;
    }

    public void setRegistradoEm(String registradoEm) {
        this.registradoEm = registradoEm;
    }

    private String formatarData (Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return simpleDateFormat.format(data);
    }

}
