package com.gestao.gestao_alunos.controller;

import com.gestao.gestao_alunos.dto.AlunoDTO;
import com.gestao.gestao_alunos.model.Aluno;
import com.gestao.gestao_alunos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity salvarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

//        if (verificarParametros(alunoDTO)){
//            return new ResponseEntity("Erro de parametros ",BAD_REQUEST);
//        }

        aluno.setCurso(alunoDTO.getCurso());
        aluno.setNome(alunoDTO.getNome());
        aluno.setRga(alunoDTO.getRga());
        aluno.setRegistradoEm(new Date());
        aluno.setSituacao(Boolean.TRUE);

        try {
            alunoRepository.save(aluno);
            return new ResponseEntity(HttpStatus.CREATED); // status 200
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity("Erro ao salvar usuario", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    @GetMapping("/{limite}/{pagina}/{nome}")
    public ResponseEntity buscarTodosAlunos(@PathVariable int limite, @PathVariable int pagina, @PathVariable String nome) {
        // comeca sempre na pagina 0, por isso faço a verificacao
        pagina = pagina > 0 ? pagina - 1 : pagina;
        Pageable pageable = PageRequest.of(pagina, limite);
        try {
            // pegando valores do nome com ignore case e LIKE '%nome%'
            List<Aluno> listaAlunos = alunoRepository.findAllByNomeIgnoreCaseContaining(nome, pageable);
            return new ResponseEntity(listaAlunos, HttpStatus.OK); // 200
        } catch(Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity("Erro ao buscar usuarios", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

}
