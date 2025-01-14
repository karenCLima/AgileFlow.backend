package com.br.AgileFlow.backend.controller;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.AgileFlow.backend.controller.exceptions.PasswordValidationError;
import com.br.AgileFlow.backend.dto.request.UserRequest;
import com.br.AgileFlow.backend.dto.response.UserResponse;
import com.br.AgileFlow.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) throws PasswordValidationError{
		UserResponse userResponse = userService.createUser(userRequest);
		return ResponseEntity.created(URI.create("/user/" + userResponse.getUser_id())).body(userResponse);
	
	}
	
	@PutMapping("/{user_id}")
	public ResponseEntity<UserResponse> updateUser(
			@RequestBody @Valid UserRequest userRequest, 
			@PathVariable Long user_id) throws PasswordValidationError{
		return ResponseEntity.ok(userService.updateUser(userRequest, user_id));
	}
	
	@DeleteMapping("/{user_id}")
	public void deleteUser(@PathVariable Long user_id) throws UserPrincipalNotFoundException {
		userService.deleteUser(user_id);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<UserResponse>> listAllActiveUsers(){
		return ResponseEntity.ok(userService.getAllActiveUsers());
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> listAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/{user_id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long user_id) throws UserPrincipalNotFoundException{
		return ResponseEntity.ok(userService.getUserById(user_id));
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) throws UserPrincipalNotFoundException{
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) throws UserPrincipalNotFoundException{
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}

}
