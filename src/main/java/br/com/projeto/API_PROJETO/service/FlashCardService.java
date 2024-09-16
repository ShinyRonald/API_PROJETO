package br.com.projeto.API_PROJETO.service;

import br.com.projeto.API_PROJETO.entidade.FlashCard;
import br.com.projeto.API_PROJETO.entidade.User;
import br.com.projeto.API_PROJETO.repositorio.FlashCardRepository;
import br.com.projeto.API_PROJETO.repositorio.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlashCardService {

    @Autowired
    FlashCardRepository flashCardRepository;

    private final List<FlashCard> users = new ArrayList<>();

    public List<FlashCard> listAll() {
        return flashCardRepository.findAll();
    }

    public Optional<FlashCard> getFlashCardbyId(String email) {
        return flashCardRepository.findById(email);
    }

    public FlashCard inserir(FlashCard flashCard) {
        flashCardRepository.insert(flashCard);
        return flashCard;
    }

    public boolean updateField(String userId, String fieldName, String newValue) {
        Optional<FlashCard> optionalFlashCard = flashCardRepository.findById(userId);
        if (optionalFlashCard.isPresent()) {
            FlashCard user = optionalFlashCard.get();
            switch (fieldName) {
                case "enunciado":
                    user.setEnunciado(newValue);
                    break;
                case "resposta":
                    user.setResposta(newValue);
                    break;
                default:
                    return false; // Campo não encontrado
            }
            flashCardRepository.save(user);
            return true; // Atualização bem-sucedida
        }
        return false; // Usuário não encontrado
    }

    public boolean deleteFlashCard(String questionId) {
        try {
            ObjectId objectId = new ObjectId(questionId);
            flashCardRepository.deleteById(objectId.toString());
            return true; // Deleção bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Falha ao deletar
        }
    }
}
