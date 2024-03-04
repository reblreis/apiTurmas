package br.com.reginareis.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reginareis.dtos.AlunoPostRequestDto;
import br.com.reginareis.dtos.AlunoPutRequestDto;
import br.com.reginareis.entities.Aluno;
import br.com.reginareis.repositories.AlunoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/alunos")
public class AlunoController {

	@PostMapping()
	public ResponseEntity<String> post(@RequestBody @Valid AlunoPostRequestDto dto) {

		try {
			Aluno aluno = new Aluno();

			aluno.setId_aluno(UUID.randomUUID());
			aluno.setNome(dto.getNome());
			aluno.setMatricula(dto.getMatricula());
			aluno.setCpf(dto.getCpf());

			AlunoRepository alunoRepository = new AlunoRepository();
			alunoRepository.insert(aluno);

			return ResponseEntity.status(201).body("Aluno(a) cadastrado(a) com sucesso.");

		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());

		}
	}

	@PutMapping()
	public ResponseEntity<String> put(@RequestBody @Valid AlunoPutRequestDto dto) {

		try {

			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(dto.getId());

			if (aluno == null)
				return ResponseEntity.status(400).body("Aluno(a) não encontrado(a). Verifique o ID informado.");

			aluno.setNome(dto.getNome());
			aluno.setMatricula(dto.getMatricula());
			aluno.setCpf(dto.getCpf());
			alunoRepository.update(aluno);

			return ResponseEntity.status(200).body("Aluno(a) atualizado(a) com sucesso.");

		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {

		try {

			// consultar os dados do cliente através do ID
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(id);

			// verificando se o cliente não foi encontrado
			if (aluno == null)
				return ResponseEntity.status(400).body("Aluno(a) não encontrado(a). Verifique o ID informado.");

			// excluindo o cliente
			alunoRepository.delete(aluno);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body("Aluno(a) excluído(a) com sucesso.");
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@GetMapping()
	public ResponseEntity<List<Aluno>> getAll() throws Exception {

		try {
			AlunoRepository alunoRepository = new AlunoRepository();
			List<Aluno> alunos = alunoRepository.findAll();
			
			if (alunos.size() == 0) // se a lista está vazia
				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(alunos);
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Aluno> getById(@PathVariable("id") UUID id) throws Exception {

		try {

			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(id);

			if (aluno == null)
				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(aluno);
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}
}