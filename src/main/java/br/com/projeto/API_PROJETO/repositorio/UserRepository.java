package br.com.projeto.API_PROJETO.repositorio;

import br.com.projeto.API_PROJETO.entidade.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{

}
