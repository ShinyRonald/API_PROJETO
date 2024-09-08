package br.com.projeto.API_PROJETO.service;

import br.com.projeto.API_PROJETO.entidade.User;
import br.com.projeto.API_PROJETO.repositorio.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final List<User> users = new ArrayList<>();

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User inserir(User user) {
        userRepository.insert(user);
        return user;
    }

    public boolean updateField(String userId, String fieldName, String newValue, int ValueNew) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            switch (fieldName) {
                case "nome":
                    user.setNome(newValue);
                    break;
                case "senha":
                    user.setSenha(newValue);
                    break;
                case "email":
                    user.setEmail(newValue);
                    break;
                case "faculdade":
                    user.setFaculdade(newValue);
                    break;
                case "curso":
                    user.setCurso(newValue);
                    break;
                case "simuladosUmRealizado":
                    user.setSimuladosUmRealizado(ValueNew);
                    break;
                case "respostasSimuladoUmCorretas":
                    user.setRespostasSimuladoUmCorretas(ValueNew);
                    break;
                case "respostasSimuladoUmIncorretas":
                    user.setRespostasSimuladoUmIncorretas(ValueNew);
                    break;
                case "flashcardsRealizados":
                    user.setFlashcardsRealizados(ValueNew);
                    break;
                case "flashcardLembrei":
                    user.setFlashcardLembrei(ValueNew);
                    break;
                case "flashcardQuaseNaoLembrei":
                    user.setFlashcardQuaseNaoLembrei(ValueNew);
                    break;
                case "flashcardNaoLembrei":
                    user.setFlashcardNaoLembrei(ValueNew);
                    break;
                case "simuladosDoisRealizado":
                    user.setSimuladosDoisRealizado(ValueNew);
                    break;
                case "respostasSimuladoDoisCorretas":
                    user.setSimuladosDoisRealizado(ValueNew);
                    break;
                case "respostasSimuladoDoisIncorretas":
                    user.setRespostasSimuladoDoisIncorretas(ValueNew);
                    break;

                default:
                    return false; // Campo não encontrado
            }
            userRepository.save(user);
            return true; // Atualização bem-sucedida
        }
        return false; // Usuário não encontrado
    }

    public boolean deleteUser(String questionId) {
        try {
            ObjectId objectId = new ObjectId(questionId);
            userRepository.deleteById(objectId.toString());
            return true; // Deleção bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Falha ao deletar
        }
    }
}
