package br.com.reginareis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.reginareis.entities.Aluno;
import br.com.reginareis.factories.ConnectionFactory;

public class AlunoRepository {

	public void insert(Aluno aluno) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into aluno(id, nome, matricula, cpf) values(?,?,?,?)");

		statement.setObject(1, aluno.getId());
		statement.setString(2, aluno.getNome());
		statement.setString(3, aluno.getMatricula());
		statement.setString(4, aluno.getCpf());
		statement.execute();

		connection.close();
	}

	public void update(Aluno aluno) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update aluno set nome=?, matricula=?, cpf=? where id=?");

		statement.setObject(1, aluno.getId());
		statement.setString(2, aluno.getNome());
		statement.setString(3, aluno.getMatricula());
		statement.setString(4, aluno.getCpf());
		statement.execute();

		connection.close();
	}

	public void delete(Aluno aluno) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from aluno where id=?");

		statement.setObject(1, aluno.getId());
		statement.execute();

		connection.close();
	}

	public List<Aluno> findAll() throws Exception {
			
			Connection connection = ConnectionFactory.getConnection();
			
			PreparedStatement statement = connection.prepareStatement
					("select * from aluno order by nome");

			ResultSet resultSet = statement.executeQuery();
			
			List<Aluno> lista = new ArrayList<Aluno>();
			
			while(resultSet.next()) {
				
				Aluno aluno = new Aluno();
								
				aluno.setId(UUID.fromString(resultSet.getString("id")));
				aluno.setNome(resultSet.getString("nome"));
				aluno.setMatricula(resultSet.getString("matricula"));
				aluno.setCpf(resultSet.getString("cpf"));
				
				lista.add(aluno);
			}
			
			connection.close();
			return lista;
		}
}