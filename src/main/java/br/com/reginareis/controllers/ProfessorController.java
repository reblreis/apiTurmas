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

import br.com.reginareis.dtos.ProfessorPostRequestDto;
import br.com.reginareis.dtos.ProfessorPutRequestDto;
import br.com.reginareis.entities.Professor;
import br.com.reginareis.repositories.ProfessorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/professores")
public class ProfessorController {

	@PostMapping()
	public ResponseEntity<String> post(@RequestBody @Valid ProfessorPostRequestDto dto) {

		try {
			Professor professor = new Professor();

			professor.setId(UUID.randomUUID());
			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());

			ProfessorRepository professorRepository = new ProfessorRepository();
			professorRepository.insert(professor);

			return ResponseEntity.status(201).body("Professor(a) cadastrado(a) com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@PutMapping()
	public ResponseEntity<String> put(@RequestBody ProfessorPutRequestDto dto) {

		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(dto.getId());

			if (professor == null)
				return ResponseEntity.status(400)
						// HTTP 400 - BAD REQUEST
						.body("Professor(a) não encontrado(a). Verifique o ID informado.");

			professor.setId(UUID.randomUUID());
			professor.setNome(dto.getNome());
			professor.setTelefone(dto.getTelefone());

			professorRepository.update(professor);

			return ResponseEntity.status(200).body("Professor(a) atualizado(a) com sucesso.");

		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {

		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(id);

			if (professor == null)
				// HTTP 400 - BAD REQUEST
				return ResponseEntity.status(400).body("Professor(a) não encontrado(a). Verifique o ID informado.");

			professorRepository.delete(professor);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body("Professor(a) excluído(a) com sucesso.");
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@GetMapping()
	public ResponseEntity<List<Professor>> getAll() throws Exception {

		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			List<Professor> professores = professorRepository.findAll();

			if (professores.size() == 0)// se a lista está vazia
				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(professores);
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Professor> getById(@PathVariable("id") UUID id) throws Exception {

		try {
			ProfessorRepository professorRepository = new ProfessorRepository();
			Professor professor = professorRepository.findById(id);

			if (professor == null)

				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(professor);
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}
}