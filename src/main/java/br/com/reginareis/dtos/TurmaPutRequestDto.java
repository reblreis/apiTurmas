package br.com.reginareis.dtos;

import java.sql.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.reginareis.entities.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TurmaPutRequestDto {

	@NotNull(message = "Por favor, informe o id da turma.")
	private UUID id;

	@Size(min = 8, max = 100, message = "Por favor, informe um nome de 8 a 100 caracteres.")
	@NotBlank(message = "Por favor, informe o nome da turma.")
	private String nome;

	@NotNull(message = "Por favor, informe a data de início.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date data_inicio;

	@NotNull(message = "Por favor, informe a data de término.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date data_termino;

	@NotEmpty(message = "A lista de professores não pode estar vazia.")
	@Size(min = 1, max = 10, message = "A lista de professores deve conter entre 1 e 10 professores.")
	private Professor professor;

}
