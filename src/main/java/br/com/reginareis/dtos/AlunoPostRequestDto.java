package br.com.reginareis.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoPostRequestDto {

	@Size(min = 8, max = 100, message = "Por favor, informe um nome de 8 a 100 caracteres.")
	@NotBlank(message = "Por favor, informe o nome do aluno.")
	private String nome;

	@Size(min = 8, max = 15, message = "A matrícula deve ter entre 8 e 15 caracteres.")
	@NotBlank(message = "Por favor, informe a matrícula.")
	private String matricula;

	@NotBlank(message = "Por favor, informe o CPF.")
	@Pattern(regexp = "\\d{11}", message = "O CPF deve ter exatamente 11 dígitos numéricos.")
	private String cpf;

}
