package br.com.projeto.API_PROJETO.controller;

import br.com.projeto.API_PROJETO.entidade.Aluno;
import br.com.projeto.API_PROJETO.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> inserir(@RequestBody Aluno aluno) {
        alunoService.inserir(aluno);

        return ResponseEntity.created(null).body(aluno);
    }

}