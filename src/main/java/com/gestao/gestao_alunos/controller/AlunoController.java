package com.gestao.gestao_alunos.controller;

import com.gestao.gestao_alunos.dto.AlunoDTO;
import com.gestao.gestao_alunos.dto.MensagemDTO;
import com.gestao.gestao_alunos.model.Aluno;
import com.gestao.gestao_alunos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    //post
    @PostMapping
    public ResponseEntity salvarAluno(@RequestBody AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();

        //if (verificarParametros(alunoDTO)){
        //    return new ResponseEntity("Erro de parametros ",BAD_REQUEST);
        //}

        aluno.setCurso(alunoDTO.getCurso());
        aluno.setNome(alunoDTO.getNome());
        aluno.setRga(alunoDTO.getRga());
        aluno.setRegistradoEm(new Date());
        aluno.setSituacao(Boolean.TRUE);

        try {
            alunoRepository.save(aluno);
            return new ResponseEntity(HttpStatus.CREATED); // status 201
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity("Erro ao salvar aluno", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    //post
    @PostMapping("/{id}")
    public ResponseEntity responderErro(@PathVariable int id){
        MensagemDTO mensagem = new MensagemDTO("Metodo nao permitido");
        return new ResponseEntity(mensagem, HttpStatus.METHOD_NOT_ALLOWED); // 405
    }


    //get
    @GetMapping("/{limite}/{pagina}/{nome}")
    public ResponseEntity buscarTodosAlunos(@PathVariable int limite, @PathVariable int pagina, @PathVariable String nome) {

        try {
            if (pagina <= 0) {
                MensagemDTO mensagem = new MensagemDTO("O numero da pagina deve ser maior ou igual a 1");
                return new ResponseEntity(mensagem, HttpStatus.BAD_REQUEST); // 400
            }

            if (limite <= 0) {
                MensagemDTO mensagem = new MensagemDTO("O limite deve ser maior ou igual a 1");
                return new ResponseEntity(mensagem, HttpStatus.BAD_REQUEST); // 400
            }

            // A pagina do spring boot comeca sempre na pagina 0, por isso faço a verificacao
            pagina = pagina > 0 ? pagina - 1 : pagina;
            Pageable pageable = PageRequest.of(pagina, limite);
            List<AlunoDTO> listaAlunosDTO = new ArrayList<>();

            // pegando valores do nome com ignore case e LIKE '%nome%'
            List<Aluno> listaAlunos = alunoRepository.findAllByNomeIgnoreCaseContaining(nome, pageable);

            for (Aluno aluno: listaAlunos) {
                listaAlunosDTO.add(new AlunoDTO(aluno));
            }

            return new ResponseEntity(listaAlunosDTO, HttpStatus.OK); // 200
        } catch(Exception ex) {
            ex.printStackTrace();
            MensagemDTO mensagem = new MensagemDTO("Erro ao buscar alunos");
            return new ResponseEntity(mensagem, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    //get
    @GetMapping("/{id}")
    public ResponseEntity buscarAluno(@PathVariable int id) {
        try {
            Aluno aluno = alunoRepository.findById(id);

            if (aluno == null) {
                MensagemDTO mensagem = new MensagemDTO("Aluno nao encontrado");
                return new ResponseEntity(mensagem, HttpStatus.NOT_FOUND); //404
            }

            AlunoDTO alunoDTO = new AlunoDTO(aluno);

            return new ResponseEntity(alunoDTO, HttpStatus.OK); // 200

        } catch(Exception ex) {
            ex.printStackTrace();
            MensagemDTO mensagem = new MensagemDTO("Erro ao buscar aluno");
            return new ResponseEntity(mensagem, HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
    }

    //delete
    @DeleteMapping
    public ResponseEntity deletarAlunos(){
        MensagemDTO mensagem = new MensagemDTO("Metodo nao permitido");
        return new ResponseEntity(mensagem, HttpStatus.METHOD_NOT_ALLOWED); // 405
    }


    //delete
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletarAluno(@PathVariable Integer id) {
        try {
            Aluno aluno = alunoRepository.findById(id);

            if (aluno == null) {
                MensagemDTO mensagem = new MensagemDTO("Aluno nao encontrado");
                return new ResponseEntity(mensagem, HttpStatus.NOT_FOUND); //404
            }

            AlunoDTO alunoDeletado = new AlunoDTO(aluno);
            alunoRepository.delete(aluno);
            return new ResponseEntity(alunoDeletado, HttpStatus.OK); //200

        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemDTO mensagem = new MensagemDTO("Erro ao deletar aluno");
            return new ResponseEntity(mensagem, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }

    }

    @PutMapping
    public ResponseEntity alterarAluno() {
        MensagemDTO mensagem = new MensagemDTO("Metodo nao permitido");
        return new ResponseEntity(mensagem , HttpStatus.METHOD_NOT_ALLOWED); // 405
    }

    //put
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT})
    public ResponseEntity atualizarAluno(@PathVariable Integer id, @RequestBody AlunoDTO alunoDTO) {
        try {
            Aluno aluno = alunoRepository.findById(id);

            if (aluno == null) {
                MensagemDTO mensagem = new MensagemDTO("Aluno nao encontrado");
                return new ResponseEntity(mensagem, HttpStatus.NOT_FOUND);
            }

            aluno.setCurso(alunoDTO.getCurso());
            aluno.setNome(alunoDTO.getNome());
            aluno.setRga(alunoDTO.getRga());
            aluno.setRegistradoEm(aluno.getRegistradoEm());
            aluno.setSituacao(Boolean.TRUE);

            Aluno alunoAtualizado = aluno;
            alunoRepository.save(alunoAtualizado);

            return new ResponseEntity(alunoAtualizado, HttpStatus.OK); //200

        } catch (Exception ex) {
            ex.printStackTrace();
            MensagemDTO mensagem = new MensagemDTO("Erro ao atualizar aluno");
            return new ResponseEntity(mensagem, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }

    }

}
