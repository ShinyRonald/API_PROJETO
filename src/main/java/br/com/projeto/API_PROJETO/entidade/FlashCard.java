package br.com.projeto.API_PROJETO.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "flashcards")
public class FlashCard {

    private String id;
    private String enunciado;
    private String resposta;

}
