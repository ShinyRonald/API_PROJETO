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

    public boolean updateField(String userId, String fieldName, String newValue, Integer valueNew) {
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
                    if (valueNew != null) {
                        user.setSimuladosUmRealizado(valueNew);
                    }
                    break;
                case "respostasSimuladoUmCorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoUmCorretas(valueNew);
                    }
                    break;
                case "respostasSimuladoUmIncorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoUmIncorretas(valueNew);
                    }
                    break;
                case "flashcardsRealizados":
                    if (valueNew != null) {
                        user.setFlashcardsRealizados(valueNew);
                    }
                    break;
                case "flashcardLembrei":
                    if (valueNew != null) {
                        user.setFlashcardLembrei(valueNew);
                    }
                    break;
                case "flashcardQuaseNaoLembrei":
                    if (valueNew != null) {
                        user.setFlashcardQuaseNaoLembrei(valueNew);
                    }
                    break;
                case "flashcardNaoLembrei":
                    if (valueNew != null) {
                        user.setFlashcardNaoLembrei(valueNew);
                    }
                    break;
                case "simuladosDoisRealizado":
                    if (valueNew != null) {
                        user.setSimuladosDoisRealizado(valueNew);
                    }
                    break;
                case "respostasSimuladoDoisCorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoDoisCorretas(valueNew);
                    }
                    break;
                case "respostasSimuladoDoisIncorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoDoisIncorretas(valueNew);
                    }
                    break;
                default:
                    return false; // Campo não encontrado
            }
            userRepository.save(user);
            return true; // Atualização bem-sucedida
        }
        return false; // Usuário não encontrado
    }

    public boolean updateFieldByEmail(String email, String fieldName, String newValue, Integer valueNew) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
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
                    if (valueNew != null) {
                        user.setSimuladosUmRealizado(valueNew);
                    }
                    break;
                case "respostasSimuladoUmCorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoUmCorretas(valueNew);
                    }
                    break;
                case "respostasSimuladoUmIncorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoUmIncorretas(valueNew);
                    }
                    break;
                case "flashcardsRealizados":
                    if (valueNew != null) {
                        user.setFlashcardsRealizados(valueNew);
                    }
                    break;
                case "flashcardLembrei":
                    if (valueNew != null) {
                        user.setFlashcardLembrei(valueNew);
                    }
                    break;
                case "flashcardQuaseNaoLembrei":
                    if (valueNew != null) {
                        user.setFlashcardQuaseNaoLembrei(valueNew);
                    }
                    break;
                case "flashcardNaoLembrei":
                    if (valueNew != null) {
                        user.setFlashcardNaoLembrei(valueNew);
                    }
                    break;
                case "simuladosDoisRealizado":
                    if (valueNew != null) {
                        user.setSimuladosDoisRealizado(valueNew);
                    }
                    break;
                case "respostasSimuladoDoisCorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoDoisCorretas(valueNew);
                    }
                    break;
                case "respostasSimuladoDoisIncorretas":
                    if (valueNew != null) {
                        user.setRespostasSimuladoDoisIncorretas(valueNew);
                    }
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
