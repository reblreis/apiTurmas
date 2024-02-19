package br.com.reginareis.entities;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Professor {

	private UUID id;
	private String nome;
	private String telefone;
	private Matricula matricula;
	private List<Aluno> alunos;
	private List<Turma> turmas;

}