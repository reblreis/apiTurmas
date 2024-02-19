package br.com.reginareis.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProfessorPutRequestDto {

	@NotNull(message = "Por favor, informe o id do professor.")
	private UUID id;

	@Size(min = 8, max = 100, message = "Por favor, informe um nome de 8 a 100 caracteres.")
	@NotBlank(message = "Por favor, informe o nome do professor.")
	private String nome;

	@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{5}-\\d{4}", message = "Por favor, informe o telefone no formato:'(99) 99999-9999'.")
	@NotBlank(message = "Por favor, informe o telefone do professor.")
	private String telefone;

}
