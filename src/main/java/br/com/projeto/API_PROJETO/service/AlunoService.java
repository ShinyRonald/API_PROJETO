package br.com.projeto.API_PROJETO.service;

import br.com.projeto.API_PROJETO.entidade.Aluno;
import br.com.projeto.API_PROJETO.repositorio.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    private final List<Aluno> alunos = new ArrayList<>();

    public Aluno inserir(Aluno aluno) {
        alunoRepository.insert(aluno);
        return aluno;
    }
}
