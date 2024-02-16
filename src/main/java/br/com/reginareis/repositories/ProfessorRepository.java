package br.com.reginareis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.reginareis.entities.Professor;
import br.com.reginareis.factories.ConnectionFactory;

public class ProfessorRepository {

	public void insert(Professor professor) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into professor(id, nome, telefone, matricula) values(?,?,?,?)");

		statement.setObject(1, professor.getId());
		statement.setString(2, professor.getNome());
		statement.setString(3, professor.getTelefone());
		statement.execute();

		connection.close();
	}

	public void update(Professor professor) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("update professor set nome=?, telefone=? where id=?");

		statement.setString(1, professor.getNome());
		statement.setString(2, professor.getTelefone());
		statement.setObject(3, professor.getId());
		statement.execute();

		connection.close();
	}

	public void delete(Professor professor) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from professor where id=?");

		statement.setObject(1, professor.getId());
		statement.execute();

		connection.close();
	}

	public List<Professor> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from professor order by nome");

		ResultSet resultSet = statement.executeQuery();

		List<Professor> lista = new ArrayList<Professor>();

		while (resultSet.next()) {

			Professor professor = new Professor();

			professor.setId(UUID.fromString(resultSet.getString("id")));
			professor.setNome(resultSet.getString("nome"));
			professor.setTelefone(resultSet.getString("telefone"));

			lista.add(professor);
		}

		connection.close();
		return lista;
	}
}