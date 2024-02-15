package br.com.reginareis.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Turma {

	private UUID id;
	private String nome;
	private Date dataInicio;
	private Date dataTermino;
	private UUID professor_id;
	private List<Aluno> alunos;
	private List<Professor> professores;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public UUID getProfessor_id() {
		return professor_id;
	}

	public void setProfessor_id(UUID professor_id) {
		this.professor_id = professor_id;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

}