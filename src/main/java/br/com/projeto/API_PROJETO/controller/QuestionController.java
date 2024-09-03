package br.com.projeto.API_PROJETO.controller;

import br.com.projeto.API_PROJETO.entidade.Question;
import br.com.projeto.API_PROJETO.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> listar() {
        List<Question> question = questionService.listAll();
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<Question> inserir(@RequestBody Question question ) {
        questionService.inserir(question);

        return ResponseEntity.created(null).body(question);
    }
}
