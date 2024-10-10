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

    @PutMapping("/updateField")
    public ResponseEntity<String> updateFields(
            @RequestParam String email,
            @RequestBody Map<String, Object> requestBody) {
        try {
            if (email == null || email.isEmpty()) {
                return ResponseEntity.badRequest().body("Email must not be null or empty");
            }

            if (requestBody == null || requestBody.isEmpty()) {
                return ResponseEntity.badRequest().body("Request body must not be null or empty");
            }

            String field = (String) requestBody.get("chave");
            String value = (String) requestBody.get("valor");

            if (field == null || field.isEmpty() || value == null) {
                return ResponseEntity.badRequest().body("Field and value must not be null or empty");
            }

            boolean success = userService.updateFieldByEmail(email, field, value);

            if (success) {
                return ResponseEntity.ok("Updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update field");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/update-last-exit/{id}")
    public ResponseEntity<String> updateLastExit(@PathVariable String id) {
        try {
            if (userService.updateLastExit(id)) {
                return ResponseEntity.ok("Última saída atualizada com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ObjectId format");
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