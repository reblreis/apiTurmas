package br.com.reginareis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.reginareis.entities.Professor;
import br.com.reginareis.entities.Turma;
import br.com.reginareis.factories.ConnectionFactory;

public class TurmaRepository {

	public void insert(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO turma(id_turma, nome_turma, data_inicio, data_termino, professor_id) values(?,?,?,?,?)");

		statement.setObject(1, turma.getId_turma());
		statement.setString(2, turma.getNome_turma());
		statement.setDate(3, new java.sql.Date(turma.getData_inicio().getTime()));
		statement.setDate(4, new java.sql.Date(turma.getData_termino().getTime()));
		statement.setObject(5, turma.getProfessor().getId_professor());
		statement.execute();

		statement.close();
	}

	public void update(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"UPDATE turma SET nome_turma=?, data_inicio=?, data_termino=?, professor_id=? where id_turma=?");

		statement.setString(1, turma.getNome_turma());
		statement.setDate(2, new java.sql.Date(turma.getData_inicio().getTime()));
		statement.setDate(3, new java.sql.Date(turma.getData_termino().getTime()));
		statement.setObject(4, turma.getProfessor().getId_professor());
		statement.setObject(5, turma.getId_turma());
		statement.execute();

		connection.close();
	}

	public void delete(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("DELETE FROM turma WHERE id_turma=?");

		statement.setObject(1, turma.getId_turma());
		statement.execute();

		connection.close();
	}

	public List<Turma> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM turma ORDER BY nome_turma");

		ResultSet resultSet = statement.executeQuery();

		List<Turma> turmas = new ArrayList<Turma>();

		while (resultSet.next()) {

			Turma turma = new Turma();
			turma.setProfessor(new Professor());

			turma.setId_turma(UUID.fromString(resultSet.getString("id_turma")));
			turma.setNome_turma(resultSet.getString("nome_turma"));
			turma.setData_inicio(new java.sql.Date(
					new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data_inicio")).getTime()));
			turma.setData_termino(new java.sql.Date(
					new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data_termino")).getTime()));

			turma.getProfessor().setId_professor(UUID.fromString(resultSet.getString("professor_id")));
			turmas.add(turma);
		}

		connection.close();
		return turmas;
	}

	public Turma findById(UUID id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM turma WHERE id_turma=?");
		preparedStatement.setObject(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		Turma turma = null;

		if (resultSet.next()) {
			turma = new Turma();
			turma.setProfessor(new Professor());

			turma.setId_turma(UUID.fromString(resultSet.getString("id_turma")));
			turma.setNome_turma(resultSet.getString("nome_turma"));
			turma.setData_inicio(new java.sql.Date(
					new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data_inicio")).getTime()));
			turma.setData_termino(new java.sql.Date(
					new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString("data_termino")).getTime()));
			turma.getProfessor().setId_professor(UUID.fromString(resultSet.getString("professor_id")));

		}

		connection.close();
		return turma;
	}
}