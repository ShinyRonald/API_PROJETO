package br.com.projeto.API_PROJETO.repositorio;

import br.com.projeto.API_PROJETO.entidade.FlashCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FlashCardRepository extends MongoRepository<FlashCard, String> {

    Optional<FlashCard> findById(String id);
}
