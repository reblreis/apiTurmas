package br.com.reginareis.entities;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class Matricula {

	private UUID id;
	private Turma turma;
	private Aluno aluno;
	private LocalDate dataMatricula;

}