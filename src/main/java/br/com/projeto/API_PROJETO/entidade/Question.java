package br.com.projeto.API_PROJETO.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "questions")
public class Question {

    private String id;
    private String disciplina;
    private String enunciadoUm;
    private String enunciadoDois;
    private String enunciadoTres;
    private String enunciadoQuatro;
    private String enunciadoCinco;
    private String enunciadoSeis;
    private String enunciadoSete;
    private String enunciadoOito;
    private String enunciadoNove;
    private List<Integer> alternativas;
    private String resposta;

}
