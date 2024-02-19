package br.com.reginareis.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Turma {

	private UUID id;
	private String nome;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private List<Professor> professores;
	private Matricula matricula;
	
}