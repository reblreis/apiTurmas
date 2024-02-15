package br.com.reginareis.entities;

import java.util.Date;
import java.util.UUID;

public class Matricula {

	private UUID id;
	private UUID turma_id;
	private UUID aluno_id;
	private Date dataMatricula;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getTurma_id() {
		return turma_id;
	}

	public void setTurma_id(UUID turma_id) {
		this.turma_id = turma_id;
	}

	public UUID getAluno_id() {
		return aluno_id;
	}

	public void setAluno_id(UUID aluno_id) {
		this.aluno_id = aluno_id;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

}