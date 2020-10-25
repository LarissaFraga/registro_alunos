package com.gestao.gestao_alunos.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "resgistrado_em")
    private Date registradoEm;

    @Column(name = "situacao")
    private boolean situacao;

    @Column(name = "rga")
    private String rga;

    @Column(name = "nome")
    private String nome;

    @Column(name = "curso")
    private String curso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegistradoEm() {
        return registradoEm;
    }

    public void setRegistradoEm(Date registradoEm) {
        this.registradoEm = registradoEm;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
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
}
