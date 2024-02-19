package br.com.reginareis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.reginareis.entities.Turma;
import br.com.reginareis.factories.ConnectionFactory;

public class TurmaRepository {

	public void insert(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into turma(id, nome, dataInicio, dataTermino) values(?,?,?,?)");

		statement.setObject(1, turma.getId());
		statement.setString(2, turma.getNome());
		statement.setDate(3, java.sql.Date.valueOf(turma.getDataInicio()));
		statement.setDate(4, java.sql.Date.valueOf(turma.getDataTermino()));
		statement.execute();

		statement.close();
	}

	public void update(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update turma set nome=?, dataInicio=?, dataTermino=? where id=?");

		statement.setString(1, turma.getNome());
		statement.setDate(2, java.sql.Date.valueOf(turma.getDataInicio()));
		statement.setDate(3, java.sql.Date.valueOf(turma.getDataTermino()));
		statement.setObject(4, turma.getId());
		statement.execute();

		connection.close();
	}

	public void delete(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"SELECT t.id, t.nome, t.data_inicio, t.data_termino, m.id as matricula_id, m.descricao as matricula_descricao "
						+ "FROM turma t " + "LEFT JOIN matricula m ON t.matricula_id = m.id " + "WHERE t.id = ?");

		statement.setObject(1, turma.getId());
		statement.execute();

		connection.close();
	}

	public List<Turma> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement(
				"SELECT t.id, t.nome, t.data_inicio, t.data_termino, m.id as matricula_id, m.descricao as matricula_descricao "
						+ "FROM turma t " + "LEFT JOIN matricula m ON t.matricula_id = m.id " + "WHERE t.id = ?");

		ResultSet resultSet = statement.executeQuery();

		List<Turma> lista = new ArrayList<Turma>();

		while (resultSet.next()) {

			Turma turma = new Turma();

			turma.setId(UUID.fromString(resultSet.getString("id")));
			turma.setNome(resultSet.getString("nome"));
			turma.setDataInicio(LocalDate.parse(resultSet.getString("dataInicio")));
			turma.setDataTermino(LocalDate.parse(resultSet.getString("dataTermino")));
			lista.add(turma);
		}

		connection.close();
		return lista;
	}

	public Turma findById(UUID id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("select * from turma where id=?");
		preparedStatement.setObject(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		Turma turma = null;

		if (resultSet.next()) {
			turma = new Turma();

			turma.setId(UUID.fromString(resultSet.getString("id")));
			turma.setNome(resultSet.getString("nome"));
			turma.setDataInicio(LocalDate.parse(resultSet.getString("dataInicio")));
			turma.setDataTermino(LocalDate.parse(resultSet.getString("dataTermino")));
		}

		connection.close();
		return turma;
	}
}