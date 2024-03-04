package br.com.reginareis.entities;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Turma {

	private UUID id_turma;
	private String nome_turma;
	private Date data_inicio;
	private Date data_termino;
	private Professor professor;
}