package com.br.AgileFlow.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.AgileFlow.backend.dto.request.LoginRequest;
import com.br.AgileFlow.backend.dto.response.TokenResponse;
import com.br.AgileFlow.backend.security.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.experimental.var;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
		 
		 Authentication authentication = authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				 );
		 
		 String token = jwtService.generateJwtToken(authentication);
		 
		 Cookie tokenCookie = new Cookie("token", token);
		 tokenCookie.setHttpOnly(true);
		 tokenCookie.setSecure(true);
		 tokenCookie.setPath("/");
		 response.addCookie(tokenCookie);
		 
		 return ResponseEntity.ok().body(new TokenResponse(token));
		 
	 }

}
