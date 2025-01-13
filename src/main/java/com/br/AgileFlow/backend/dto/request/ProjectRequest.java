package com.br.AgileFlow.backend.dto.request;

import java.util.Date;
import java.util.List;

import com.br.AgileFlow.backend.model.ProjectMember;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
	
	@NotBlank(message = "O nome é obrigatório.")
	@Size(max = 200, message = "O nome pode ter no máximo 200 caracteres.")
	private String name;
	
	@Size(max = 255, message = "O nome pode ter no máximo 255 caracteres.")
	private String description;
	
	
	private Date closingDate;
	
	
	private List<ProjectMember> members;

}
