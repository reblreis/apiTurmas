package br.com.reginareis.dtos;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MatriculaPostRequestDto {

	@NotNull(message = "Por favor, informe o ID da turma.")
	@Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}", message = "O ID da turma deve estar no formato UUID válido.")
	private UUID turmaId;

	@NotNull(message = "Por favor, informe o ID do aluno.")
	@Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}", message = "O ID do aluno deve estar no formato UUID válido.")
	private UUID alunoId;

	@NotNull(message = "Por favor, informe a data de matrícula.")
	@PastOrPresent(message = "A data de matrícula deve ser no presente ou no passado.")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataMatricula;

}
