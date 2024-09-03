package br.com.projeto.API_PROJETO.repositorio;

import br.com.projeto.API_PROJETO.entidade.Aluno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlunoRepository  extends MongoRepository<Aluno, String>{

}
