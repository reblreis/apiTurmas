package br.com.reginareis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.reginareis.entities.Aluno;
import br.com.reginareis.entities.Matricula;
import br.com.reginareis.entities.Turma;
import br.com.reginareis.factories.ConnectionFactory;

public class MatriculaRepository {

	public void insert(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO matricula(id_matricula, turma_id, aluno_id, data_matricula) values(?,?,?,?)");

		statement.setObject(1, matricula.getId_matricula());
		statement.setObject(2, matricula.getTurma().getId_turma());
		statement.setObject(3, matricula.getAluno().getId_aluno());
		statement.setObject(4, matricula.getData_matricula());
		statement.execute();

		connection.close();
	}

	public void delete(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from matricula where id=?");

		statement.setObject(1, matricula.getId_matricula());
		statement.execute();

		connection.close();
	}

	public List<Matricula> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from matricula order by nome");

		ResultSet resultSet = statement.executeQuery();

		List<Matricula> lista = new ArrayList<Matricula>();

		while (resultSet.next()) {

			Matricula matricula = new Matricula();
			matricula.setTurma(new Turma());
			matricula.setAluno(new Aluno());

			matricula.setId_matricula(UUID.fromString(resultSet.getString("id_matricula")));
			matricula.getTurma().setId_turma(UUID.fromString(resultSet.getString("turma_id")));
			matricula.getAluno().setId_aluno(UUID.fromString(resultSet.getString("aluno_id")));

			lista.add(matricula);
		}

		connection.close();
		return lista;
	}

	public Matricula findById(UUID id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from matricula where matricula=id");

		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();

		Matricula matricula = null;

		if (resultSet.next()) {

			matricula = new Matricula();
			matricula.setTurma(new Turma());
			matricula.setAluno(new Aluno());

			matricula.setId_matricula(UUID.fromString(resultSet.getString("id")));
			matricula.getTurma().setId_turma(UUID.fromString(resultSet.getString("turma_id")));
			matricula.getAluno().setId_aluno(UUID.fromString(resultSet.getString("aluno_id")));
		}

		connection.close();
		return matricula;
	}
}