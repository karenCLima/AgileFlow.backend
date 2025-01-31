package com.br.AgileFlow.backend.api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamRequest {
	
	@NotBlank(message = "O parâmetro é obrigatória")
	@Size(max = 20, min = 4 , message = "O parâmetro deve conter no mínimo 4 e no máximo 20 caracteres.")
	private String parameter;

}
