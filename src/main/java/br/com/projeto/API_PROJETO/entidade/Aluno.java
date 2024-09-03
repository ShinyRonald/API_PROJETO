package br.com.projeto.API_PROJETO.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alunos")
public class Aluno {

    private int matricula;
    private String nome;
    private String sobrenome;
    private String cpf;

    public static String obterNome(){
        return "Eai, blz?";
    }
}
