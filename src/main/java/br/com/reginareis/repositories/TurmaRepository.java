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
		statement.setDate(3, new java.sql.Date(turma.getDataInicio().getTime()));
		statement.setDate(4, new java.sql.Date(turma.getDataTermino().getTime()));
		statement.execute();

		statement.close();
	}

	public void update(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update turma set nome=?, dataInicio=?, dataTermino=? where id=?");

		statement.setString(1, turma.getNome());
		statement.setDate(2, new java.sql.Date(turma.getDataInicio().getTime()));
		statement.setDate(3, new java.sql.Date(turma.getDataTermino().getTime()));
		statement.setObject(4, turma.getId());
		statement.execute();

		connection.close();
	}

	public void delete(Turma turma) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from turma where id=?");

		statement.setObject(1, turma.getId());
		statement.execute();

		connection.close();
	}

	public List<Turma> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from turma order by nome");

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
}