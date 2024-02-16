package br.com.reginareis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.reginareis.entities.Matricula;
import br.com.reginareis.factories.ConnectionFactory;

public class MatriculaRepository {

	public void insert(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into matricula(id, turma_id, aluno_id, dataMatricula) values(?,?,?,?)");

		statement.setObject(1, matricula.getId());
		statement.setObject(2, matricula.getTurma_id());
		statement.setObject(3, matricula.getAluno_id());
		statement.setDate(4, new java.sql.Date(matricula.getDataMatricula().getTime()));

		statement.execute();

		connection.close();
	}

	public void update(Matricula matricula) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update matricula set turma_id=?, aluno_id=?, dataMatricula=? where id=?");

		statement.setObject(1, matricula.getId());
		statement.setObject(2, matricula.getTurma_id());
		statement.setObject(3, matricula.getAluno_id());
		statement.setDate(4, new java.sql.Date(matricula.getDataMatricula().getTime()));

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

	public List<Matricula> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from matricula order by nome");

		ResultSet resultSet = statement.executeQuery();

		List<Matricula> lista = new ArrayList<Matricula>();

		while (resultSet.next()) {

			Matricula matricula = new Matricula();

			matricula.setId(UUID.fromString(resultSet.getString("id")));
			matricula.setAluno_id(UUID.fromString(resultSet.getString("aluno_id")));
			matricula.setTurma_id(UUID.fromString(resultSet.getString("turma_id")));
			matricula.setDataMatricula(LocalDate.parse(resultSet.getString("dataMatricula")));

			lista.add(matricula);
		}

		connection.close();
		return lista;
	}

}
