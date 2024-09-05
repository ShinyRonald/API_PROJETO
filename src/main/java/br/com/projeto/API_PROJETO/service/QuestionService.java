package br.com.projeto.API_PROJETO.service;

import br.com.projeto.API_PROJETO.entidade.Question;
import br.com.projeto.API_PROJETO.repositorio.QuestionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public boolean updateField(String userId, String fieldName, String newValue) {
        Optional<Question> optionalQuestion = questionRepository.findById(userId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            switch (fieldName) {
                case "disciplina":
                    question.setDisciplina(newValue);
                    break;
                case "enunciadoUm":
                    question.setEnunciadoUm(newValue);
                    break;
                case "enunciadoDois":
                    question.setEnunciadoDois(newValue);
                    break;
                case "enunciadoTres":
                    question.setEnunciadoTres(newValue);
                    break;
                case "enunciadoQuatro":
                    question.setEnunciadoQuatro(newValue);
                    break;
                case "enunciadoCinco":
                    question.setEnunciadoCinco(newValue);
                    break;
                case "enunciadoSeis":
                    question.setEnunciadoSeis(newValue);
                    break;
                case "enunciadoOito":
                    question.setEnunciadoOito(newValue);
                    break;
                case "enunciadoNove":
                    question.setEnunciadoNove(newValue);
                    break;
                case "alternativas":
                    question.setAlternativas(Collections.singletonList(newValue));
                    break;
                case "resposta":
                    question.setResposta(newValue);
                    break;
                default:
                    return false; // Campo não encontrado
            }
            questionRepository.save(question);
            return true; // Atualização bem-sucedida
        }
        return false; // Usuário não encontrado
    }

    public boolean deleteQuestion(String questionId) {
        try {
            ObjectId objectId = new ObjectId(questionId);
            questionRepository.deleteById(objectId.toString());
            return true; // Deleção bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Falha ao deletar
        }
    }

}
