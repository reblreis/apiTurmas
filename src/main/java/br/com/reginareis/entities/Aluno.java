package br.com.reginareis.entities;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Aluno {

	private UUID id;
	private String nome;
	private String matricula;
	private String cpf;
	private Turma turma;
	private List<Professor> professores;

}