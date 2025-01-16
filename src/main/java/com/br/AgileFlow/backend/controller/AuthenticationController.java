package com.br.AgileFlow.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.AgileFlow.backend.dto.request.LoginRequest;
import com.br.AgileFlow.backend.dto.response.TokenResponse;
import com.br.AgileFlow.backend.security.TokenGenerator;
import com.br.AgileFlow.backend.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	TokenGenerator tokenGenerator;
	
	@Autowired
	DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Autowired
	@Qualifier("jwtRefreshTokenAuthProvider")
	JwtAuthenticationProvider refreshTokenAuthProvider;
	
	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
		 Authentication authentication = refreshTokenAuthProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword()));
		 return ResponseEntity.ok(tokenGenerator.createToken(authentication));
	 }
	 
	 @PostMapping("/token")
	 public ResponseEntity<?> token(@RequestBody TokenResponse tokenResponse){
		 Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenResponse.getRefreshToken()));
		 Jwt jwt = (Jwt) authentication.getCredentials();
		 return ResponseEntity.ok(tokenGenerator.createToken(authentication));
	 }

}
