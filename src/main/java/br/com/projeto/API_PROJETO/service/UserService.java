package br.com.projeto.API_PROJETO.service;

import br.com.projeto.API_PROJETO.entidade.User;
import br.com.projeto.API_PROJETO.repositorio.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public boolean updateFieldByEmail(String email, String field, String value) {
        try {
            // Encontra o usuário pelo e-mail
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (!optionalUser.isPresent()) {
                return false; // Usuário não encontrado
            }

            User user = optionalUser.get();

            // Atualiza o campo específico com base na chave
            switch (field) {
                case "nome":
                    user.setNome(value);
                    break;
                case "email":
                    user.setEmail(value);
                    break;
                case "senha":
                    user.setSenha(value);
                    break;
                case "faculdade":
                    user.setFaculdade(value);
                    break;
                case "curso":
                    user.setCurso(value);
                    break;
                case "data":
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        user.setData(formatter.parse(value));
                    } catch (ParseException e) {
                        return false; // Formato de data inválido
                    }
                    break;
                case "flashcardLembrei":
                case "flashcardsRealizados":
                case "flashcardQuaseNaoLembrei":
                case "flashcardNaoLembrei":
                case "simuladosUmRealizado":
                case "respostasSimuladoUmCorretas":
                case "respostasSimuladoUmIncorretas":
                case "simuladosDoisRealizado":
                case "respostasSimuladoDoisCorretas":
                case "respostasSimuladoDoisIncorretas":
                    try {
                        int intValue = Integer.parseInt(value);
                        // Atualiza o campo diretamente
                        switch (field) {
                            case "flashcardLembrei":
                                user.setFlashcardLembrei(intValue);
                                break;
                            case "flashcardsRealizados":
                                user.setFlashcardsRealizados(intValue);
                                break;
                            case "flashcardQuaseNaoLembrei":
                                user.setFlashcardQuaseNaoLembrei(intValue);
                                break;
                            case "flashcardNaoLembrei":
                                user.setFlashcardNaoLembrei(intValue);
                                break;
                            case "simuladosUmRealizado":
                                user.setSimuladosUmRealizado(intValue);
                                break;
                            case "respostasSimuladoUmCorretas":
                                user.setRespostasSimuladoUmCorretas(intValue);
                                break;
                            case "respostasSimuladoUmIncorretas":
                                user.setRespostasSimuladoUmIncorretas(intValue);
                                break;
                            case "simuladosDoisRealizado":
                                user.setSimuladosDoisRealizado(intValue);
                                break;
                            case "respostasSimuladoDoisCorretas":
                                user.setRespostasSimuladoDoisCorretas(intValue);
                                break;
                            case "respostasSimuladoDoisIncorretas":
                                user.setRespostasSimuladoDoisIncorretas(intValue);
                                break;
                            default:
                                return false; // Campo não encontrado
                        }
                    } catch (NumberFormatException e) {
                        return false; // Valor inválido para campo numérico
                    }
                    break;
                default:
                    return false; // Campo não encontrado
            }

            // Salva o usuário atualizado
            userRepository.save(user);
            return true; // Atualização bem-sucedida
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
