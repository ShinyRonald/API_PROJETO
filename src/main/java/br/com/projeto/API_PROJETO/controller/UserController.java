package br.com.projeto.API_PROJETO.controller;

import br.com.projeto.API_PROJETO.entidade.Question;
import br.com.projeto.API_PROJETO.entidade.User;
import br.com.projeto.API_PROJETO.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping
    public ResponseEntity<User> inserir(@RequestBody User user) {
        userService.inserir(user);

        return ResponseEntity.created(null).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateField(
            @PathVariable String id,
            @RequestBody Map<String, String> updateFields) {
        try {
            ObjectId objectId = new ObjectId(id);
            String chave = updateFields.get("chave");
            String valor = updateFields.get("valor");

            if (chave == null || valor == null) {
                return ResponseEntity.badRequest().body("Missing required fields: 'chave' or 'valor'");
            }

            boolean success = userService.updateField(objectId.toString(), chave, valor);
            if (success) {
                return ResponseEntity.ok("Updated successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to update field");
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