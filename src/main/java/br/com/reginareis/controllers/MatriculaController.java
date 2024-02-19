package br.com.reginareis.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reginareis.dtos.MatriculaPostRequestDto;
import br.com.reginareis.entities.Aluno;
import br.com.reginareis.entities.Matricula;
import br.com.reginareis.entities.Turma;
import br.com.reginareis.repositories.AlunoRepository;
import br.com.reginareis.repositories.MatriculaRepository;
import br.com.reginareis.repositories.TurmaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/matriculas")
public class MatriculaController {

	@PostMapping()
	public ResponseEntity<String> post(@RequestBody @Valid MatriculaPostRequestDto dto) {

		try {

			Matricula matricula = new Matricula();

			matricula.setId(UUID.randomUUID());

			TurmaRepository turmaRepository = new TurmaRepository();
			Turma turma = turmaRepository.findById(dto.getTurmaId());

			if (turma == null)
				// HTTP 400 - BAD REQUEST
				return ResponseEntity.status(400).body("Turma não encontrada. Verifique o ID informado.");

			matricula.setTurma(turma);

			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(dto.getAlunoId());

			if (aluno == null)
				// HTTP 400 - BAD REQUEST
				return ResponseEntity.status(400).body("Aluno(a) não encontrado(a). Verifique o ID informado.");

			matricula.setAluno(aluno);

			matricula.setDataMatricula(dto.getDataMatricula());

			MatriculaRepository matriculaRepository = new MatriculaRepository();
			matriculaRepository.insert(matricula);

			// HTTP 201 - CREATED
			return ResponseEntity.status(201).body("Matricula cadastrada com sucesso.");
		} catch (Exception e) {
			// HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") UUID id) {

		try {
			MatriculaRepository matriculaRepository = new MatriculaRepository();
			Matricula matricula = matriculaRepository.findById(id);

			if (matricula == null)
				// HTTP 400 - BAD REQUEST
				return ResponseEntity.status(400).body("Matricula não encontrada. Verifique o ID informado.");

			matriculaRepository.delete(matricula);

			//HTTP 200 - OK
			return ResponseEntity.status(200).body("Matricula excluída com sucesso.");
		} catch (Exception e) {
			//HTTP 500 - INTERNAL SERVER ERROR
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}
}