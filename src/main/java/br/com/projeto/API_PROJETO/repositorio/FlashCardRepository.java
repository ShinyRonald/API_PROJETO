package br.com.projeto.API_PROJETO.repositorio;

import br.com.projeto.API_PROJETO.entidade.FlashCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlashCardRepository extends MongoRepository<FlashCard, String> {
}
