package br.com.projeto.API_PROJETO.entidade;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

        private String id;
        private String nome;
        private String email;
        private String curso;
        private String faculdade;
        private String senha;

        //Isso aqui é pra manter a data no formato dd/MM/yyyy se não fica yyyy-MM-dd
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private Date data;

        private int simuladosUmRealizado;
        private int respostasSimuladoUmCorretas;
        private int respostasSimuladoUmIncorretas;
        private int flashcardsRealizados;
        private int flashcardLembrei;
        private int flashcardNaoLembrei;
        private int simuladosDoisRealizado;
        private int respostasSimuladoDoisCorretas;
        private int respostasSimuladoDoisIncorretas;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        private Date ultimaSaida;

}
