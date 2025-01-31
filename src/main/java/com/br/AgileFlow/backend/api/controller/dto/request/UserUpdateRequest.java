package com.br.AgileFlow.backend.api.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
	
	@NotBlank(message = "O nome é obrigatório.")
	@Size(max = 100, message = "O nome pode ter no máximo 100 caracteres.")
	private String name;
	
	@Email
	private String email;
	
	
	@NotBlank(message = "O cargo é obrigatório")
	@Size(min = 3, message = "O cargo deve conter no mínimo 3 caracteres.")
	private String position;

}
