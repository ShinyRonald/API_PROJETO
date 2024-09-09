package br.com.projeto.API_PROJETO.controller;

import br.com.projeto.API_PROJETO.entidade.Question;
import br.com.projeto.API_PROJETO.entidade.User;
import br.com.projeto.API_PROJETO.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listar() {
        List<User> user = userService.listAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> userOptional = userService.getUserByEmail(email);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<User> inserir(@RequestBody User user) {
        userService.inserir(user);

        return ResponseEntity.created(null).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateField(
            @PathVariable String id,
            @RequestBody Map<String, Object> updateFields) {
        try {
            // Verifica se o id é válido
            ObjectId objectId = new ObjectId(id);

            // Extrai os campos do Map
            String chave = (String) updateFields.get("chave");
            Object valor = updateFields.get("valor");

            // Verifica se os campos obrigatórios estão presentes
            if (chave == null || valor == null) {
                return ResponseEntity.badRequest().body("Missing required fields: 'chave' or 'valor'");
            }

            // Converte o valor para o tipo adequado, se necessário
            boolean success;
            if (valor instanceof String) {
                success = userService.updateField(objectId.toString(), chave, (String) valor, null);
            } else if (valor instanceof Integer) {
                success = userService.updateField(objectId.toString(), chave, null, (Integer) valor);
            } else {
                return ResponseEntity.badRequest().body("Invalid value type. Must be String or Integer.");
            }

            // Verifica se a atualização foi bem-sucedida
            if (success) {
                return ResponseEntity.ok("Updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to update field");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ObjectId format");
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body("Invalid data type in request body");
        }
    }

    @PutMapping("/updateByEmail")
    public ResponseEntity<String> updateFieldByEmail(
            @RequestParam String email,
            @RequestBody Map<String, Object> updateFields) {
        try {
            // Verifica se o e-mail é válido
            if (email == null || email.isEmpty()) {
                return ResponseEntity.badRequest().body("Email must not be null or empty");
            }

            // Verifica se o corpo da requisição contém os campos necessários
            if (updateFields == null || updateFields.isEmpty()) {
                return ResponseEntity.badRequest().body("Update fields must not be null or empty");
            }

            // Extrai os campos do Map
            String chave = (String) updateFields.get("chave");
            Object valor = updateFields.get("valor");

            // Verifica se os campos obrigatórios estão presentes
            if (chave == null || chave.isEmpty()) {
                return ResponseEntity.badRequest().body("Field 'chave' must not be null or empty");
            }

            if (valor == null) {
                return ResponseEntity.badRequest().body("Field 'valor' must not be null");
            }

            // Realiza a atualização do campo com base no e-mail
            boolean success;
            if (valor instanceof String) {
                success = userService.updateFieldByEmail(email, chave, (String) valor, null);
            } else if (valor instanceof Integer) {
                success = userService.updateFieldByEmail(email, chave, null, (Integer) valor);
            } else {
                return ResponseEntity.badRequest().body("Invalid value type. Must be String or Integer.");
            }

            // Verifica se a atualização foi bem-sucedida
            if (success) {
                return ResponseEntity.ok("Updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update field");
            }
        } catch (Exception e) {
            // Log do erro para análise posterior
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            boolean success = userService.deleteUser(objectId.toString());
            if (success) {
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to delete User");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ObjectId format");
        }
    }
}