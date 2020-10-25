package com.gestao.gestao_alunos.repository;


import com.gestao.gestao_alunos.model.Aluno;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findAllByNomeIgnoreCaseContaining(String nome, Pageable pageable);
}
