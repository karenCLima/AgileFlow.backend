package com.br.AgileFlow.backend.api.controller.dto.response;

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
	
	private String username;
	
	private String position;
	
	private Boolean active;

}
