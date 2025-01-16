package com.br.AgileFlow.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TokenResponse {
	
	private String username;
	private String  accessToken;
	private String refreshToken;

}
