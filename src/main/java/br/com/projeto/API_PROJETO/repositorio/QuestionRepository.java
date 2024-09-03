package br.com.projeto.API_PROJETO.repositorio;

import br.com.projeto.API_PROJETO.entidade.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
