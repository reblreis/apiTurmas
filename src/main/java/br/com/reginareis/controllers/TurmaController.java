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

import br.com.reginareis.dtos.TurmaPostRequestDto;
import br.com.reginareis.dtos.TurmaPutRequestDto;
import br.com.reginareis.entities.Turma;
import br.com.reginareis.repositories.TurmaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/turmas")
public class TurmaController {

	@PostMapping()
	public ResponseEntity<String> post(@RequestBody @Valid TurmaPostRequestDto dto) {

		try {
			Turma turma = new Turma();

			turma.setId(UUID.randomUUID());
			turma.setNome(dto.getNome());
			turma.setDataInicio(dto.getDataInicio());
			turma.setDataTermino(dto.getDataTermino());
			turma.setProfessores(dto.getProfessores());

			TurmaRepository turmaRepository = new TurmaRepository();
			turmaRepository.insert(turma);

			// HTTP 201 - CREATED
			return ResponseEntity.status(201).body("Turma cadastrada com sucesso.");
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@PutMapping()
	public ResponseEntity<String> put(@RequestBody @Valid TurmaPutRequestDto dto) {

		try {

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(dto.getId());

			if (turma == null)
				return ResponseEntity.status(400)
						// HTTP 400 - BAD REQUEST
						.body("Turma não encontrada. Verifique o ID informado.");

			turma.setId(UUID.randomUUID());
			turma.setNome(dto.getNome());
			turma.setDataInicio(dto.getDataInicio());
			turma.setDataTermino(dto.getDataTermino());
			turma.setProfessores(dto.getProfessores());

			turmaRepository.update(turma);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body("Turma atualizada com sucesso.");

		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {

		try {

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(id);

			if (turma == null)
				// HTTP 400 - BAD REQUEST
				return ResponseEntity.status(400).body("Turma não encontrada. Verifique o ID informado.");

			turmaRepository.delete(turma);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body("Turma excluída com sucesso.");
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}

	}

	@GetMapping()
	public ResponseEntity<List<Turma>> getAll() throws Exception {

		try {

			TurmaRepository turmaRepository = new TurmaRepository();
			List<Turma> turmas = turmaRepository.findAll();

			if (turmas.size() == 0)// se a lista está vazia
				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(turmas);
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Turma> getById(@PathVariable("id") UUID id) throws Exception {

		try {
			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(id);

			if (turma == null)// se a lista está vazia
				// HTTP 204 - NO CONTENT
				return ResponseEntity.status(204).body(null);

			// HTTP 200 - OK
			return ResponseEntity.status(200).body(turma);
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(null);
		}
	}
}