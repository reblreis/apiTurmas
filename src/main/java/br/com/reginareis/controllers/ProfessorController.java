package br.com.reginareis.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/professores")
public class ProfessorController {

	@PostMapping()
	public void post() {
		// TODO
	}

	@PutMapping()
	public void put() {
		// TODO
	}

	@DeleteMapping()
	public void delete() {
		// TODO
	}

	@GetMapping()
	public void getAll() {
		// TODO
	}

}