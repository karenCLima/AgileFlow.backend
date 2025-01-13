package com.br.AgileFlow.backend.dto.response;

import com.br.AgileFlow.backend.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private Long user_id;
	
	private String name;
	
	private String email;
	
	private String position;
	
	private Boolean active;

}
