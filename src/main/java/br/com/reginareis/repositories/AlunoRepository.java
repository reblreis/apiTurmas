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
				.prepareStatement("insert into aluno(id_aluno, nome, matricula, cpf) values(?,?,?,?)");

		statement.setObject(1, aluno.getId_aluno());
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

		statement.setString(1, aluno.getNome());
		statement.setString(2, aluno.getMatricula());
		statement.setString(3, aluno.getCpf());
		statement.setObject(4, aluno.getId_aluno());
		statement.execute();

		connection.close();
	}

	public void delete(Aluno aluno) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from aluno where id=?");

		statement.setObject(1, aluno.getId_aluno());
		statement.execute();

		connection.close();
	}

	// método para retornar todos os alunos cadastrados no banco de dados
	// TRABALHA COM MUITOS = LISTA DE ALUNOS

	public List<Aluno> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from aluno order by nome");
		ResultSet resultSet = statement.executeQuery();

		List<Aluno> lista = new ArrayList<Aluno>();

		while (resultSet.next()) {

			Aluno aluno = new Aluno();

			aluno.setId_aluno(UUID.fromString(resultSet.getString("id_aluno")));
			aluno.setNome(resultSet.getString("nome"));
			aluno.setMatricula(resultSet.getString("matricula"));
			aluno.setCpf(resultSet.getString("cpf"));

			lista.add(aluno);
		}

		connection.close();
		return lista;
	}

	public Aluno findById(UUID id) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// de dados para consultar 1 aluno através do ID
		PreparedStatement statement = connection.prepareStatement("select * from aluno where id=?");
		statement.setObject(1, id);

		// ler e armazenar os registros obtidos do banco de dados
		ResultSet resultSet = statement.executeQuery();

		// criando um objeto Aluno vazio
		Aluno aluno = null;

		// verificando se algum aluno foi encontrado no banco de dados
		if (resultSet.next()) {
			aluno = new Aluno();

			aluno.setId_aluno(UUID.fromString(resultSet.getString("id")));
			aluno.setNome(resultSet.getString("nome"));
			aluno.setMatricula(resultSet.getString("matricula"));
			aluno.setCpf(resultSet.getString("cpf"));
		}

		// fechando a conexão com o banco de dados
		connection.close();

		// retornando o aluno
		return aluno;
	}
}