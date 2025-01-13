package com.br.AgileFlow.backend.dto.request;

import com.br.AgileFlow.backend.model.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotBlank(message = "O nome é obrigatório.")
	@Size(max = 100, message = "O nome pode ter no máximo 100 caracteres.")
	private String name;
	
	@Email
	private String email;
	
	@NotBlank(message = "O username é obrigatória")
	@Size(max = 20 , message = "O username deve conter no máximo 20 caracteres.")
	private String username;
	
	@NotBlank(message = "A senha é obrigatória")
	@Size(min = 4, message = "A senha deve conter no mínimo 4 caracteres.")
	private String password;
	
	
	@NotBlank(message = "O cargo é obrigatório")
	@Size(min = 3, message = "O cargo deve conter no mínimo 3 caracteres.")
	private String position;
}
