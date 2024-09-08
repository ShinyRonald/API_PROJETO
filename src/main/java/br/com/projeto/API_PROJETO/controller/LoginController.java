package br.com.projeto.API_PROJETO.controller;

import br.com.projeto.API_PROJETO.entidade.LoginRequest;
import br.com.projeto.API_PROJETO.entidade.User;
import br.com.projeto.API_PROJETO.repositorio.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        boolean success = user.isPresent() && user.get().getSenha().equals(loginRequest.getSenha());

        return new LoginResponse(success);
    }

    @Setter
    @Getter
    public static class LoginResponse {
        private boolean success;

        public LoginResponse(boolean success) {
            this.success = success;
        }

    }
}
