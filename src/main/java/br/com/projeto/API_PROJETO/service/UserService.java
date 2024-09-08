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

    public boolean updateField(String userId, String fieldName, String newValue) {
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
                    user.setCurso(newValue);
                    break;
                case "respostasSimuladoUmCorretas":
                    user.setCurso(newValue);
                    break;
                case "respostasSimuladoUmIncorretas":
                    user.setCurso(newValue);
                    break;
                case "flashcardsRealizados":
                    user.setCurso(newValue);
                    break;
                case "flashcardLembrei":
                    user.setCurso(newValue);
                    break;
                case "flashcardQuaseNaoLembrei":
                    user.setCurso(newValue);
                    break;
                case "flashcardNaoLembrei":
                    user.setCurso(newValue);
                    break;
                case "simuladosDoisRealizado":
                    user.setCurso(newValue);
                    break;
                case "respostasSimuladoDoisCorretas":
                    user.setCurso(newValue);
                    break;
                case "respostasSimuladoDoisIncorretas":
                    user.setCurso(newValue);
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
