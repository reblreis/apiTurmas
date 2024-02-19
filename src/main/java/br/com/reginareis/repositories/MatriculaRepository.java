package br.com.reginareis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.UUID;

import br.com.reginareis.entities.Matricula;
import br.com.reginareis.factories.ConnectionFactory;

public class MatriculaRepository {

	public void insert(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into matricula(id, turma_id, aluno_id, dataMatricula) values(?,?,?,?)");

		statement.setObject(1, matricula.getId());
		statement.setObject(2, matricula.getTurma().getId());
		statement.setObject(3, matricula.getAluno().getId());
		statement.setDate(4, java.sql.Date.valueOf(matricula.getDataMatricula()));

		statement.execute();

		connection.close();
	}

	public void delete(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from matricula where id=?");

		statement.setObject(1, matricula.getId());
		statement.execute();

		connection.close();
	}

	public Matricula findById(UUID id) throws Exception {
		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"SELECT m.id, m.turma_id, m.aluno_id, m.data_matricula " + "FROM matricula m " + "WHERE m.id = ?");
		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();

		Matricula matricula = null;

		if (resultSet.next()) {

			matricula = new Matricula();
			matricula.setId(UUID.fromString(resultSet.getString("id")));
			matricula.setDataMatricula(LocalDate.parse(resultSet.getString("dataMatricula")));
		}

		connection.close();
		return matricula;

	}

}