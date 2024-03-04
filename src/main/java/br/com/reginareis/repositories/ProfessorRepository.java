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
				.prepareStatement("insert into professor(id_professor, nome, telefone) values(?,?,?)");

		statement.setObject(1, professor.getId_professor());
		statement.setString(2, professor.getNome());
		statement.setString(3, professor.getTelefone());
		statement.execute();

		connection.close();
	}

	public void update(Professor professor) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("UPDATE professor set nome=?, telefone=? where id_professor=?");

		statement.setString(1, professor.getNome());
		statement.setString(2, professor.getTelefone());
		statement.setObject(3, professor.getId_professor());
		statement.execute();

		connection.close();
	}

	public void delete(Professor professor) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("DELETE FROM professor where id_professor=?");

		statement.setObject(1, professor.getId_professor());
		statement.execute();

		connection.close();
	}

	public List<Professor> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("SELECT * FROM professor ORDER BY nome");

		ResultSet resultSet = statement.executeQuery();

		List<Professor> lista = new ArrayList<Professor>();

		while (resultSet.next()) {

			Professor professor = new Professor();

			professor.setId_professor(UUID.fromString(resultSet.getString("id_professor")));
			professor.setNome(resultSet.getString("nome"));
			professor.setTelefone(resultSet.getString("telefone"));

			lista.add(professor);
		}

		connection.close();
		return lista;
	}

	public Professor findById(UUID id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select from professor where id_professor = ?");
		statement.setObject(1, id);

		ResultSet resultSet = statement.executeQuery();

		Professor professor = null;

		if (resultSet.next()) {
			professor = new Professor();

			professor.setId_professor(UUID.fromString(resultSet.getString("id_professor")));
			professor.setNome(resultSet.getString("nome"));
			professor.setTelefone(resultSet.getString("telefone"));
		}

		connection.close();
		return professor;
	}
}