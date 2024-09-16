package br.com.projeto.API_PROJETO.controller;

import br.com.projeto.API_PROJETO.entidade.FlashCard;
import br.com.projeto.API_PROJETO.entidade.User;
import br.com.projeto.API_PROJETO.service.FlashCardService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/flashcards")
public class FlashCardController {
    
    @Autowired
    private FlashCardService flashCardService;

    @GetMapping
    public ResponseEntity<List<FlashCard>> listar() {
        List<FlashCard> flashCard = flashCardService.listAll();
        return ResponseEntity.ok(flashCard);
    }

    @GetMapping("/")
    public ResponseEntity<FlashCard> getFlashCardById(@RequestParam String id) {
        Optional<FlashCard> flashCardOptional = flashCardService.getFlashCardbyId(id);
        if (flashCardOptional.isPresent()) {
            return new ResponseEntity<>(flashCardOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<FlashCard> inserir(@RequestBody FlashCard FlashCard ) {
        flashCardService.inserir(FlashCard);

        return ResponseEntity.created(null).body(FlashCard);
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

            boolean success = flashCardService.updateField(objectId.toString(), chave, valor);
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
    public ResponseEntity<String> deleteFlashCard(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            boolean success = flashCardService.deleteFlashCard(objectId.toString());
            if (success) {
                return ResponseEntity.ok("FlashCard deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to delete FlashCard");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ObjectId format");
        }
    }
}
