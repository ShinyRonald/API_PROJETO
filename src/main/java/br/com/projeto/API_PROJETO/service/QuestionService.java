package br.com.projeto.API_PROJETO.service;

import br.com.projeto.API_PROJETO.entidade.Aluno;
import br.com.projeto.API_PROJETO.entidade.Question;
import br.com.projeto.API_PROJETO.repositorio.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    private final List<Question> questions = new ArrayList<>();

    public List<Question> listAll() {
        return questionRepository.findAll();
    }

    public Question inserir(Question question) {
        questionRepository.insert(question);
        return question;
    }
}
