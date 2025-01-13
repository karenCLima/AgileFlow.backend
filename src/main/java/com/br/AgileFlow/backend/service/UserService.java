package com.br.AgileFlow.backend.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.AgileFlow.backend.controller.exceptions.PasswordValidationError;
import com.br.AgileFlow.backend.dto.request.UserRequest;
import com.br.AgileFlow.backend.dto.response.UserResponse;
import com.br.AgileFlow.backend.model.User;
import com.br.AgileFlow.backend.repository.UserRepository;
import com.br.AgileFlow.backend.util.Validator;
import com.br.AgileFlow.backend.util.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserMapper mapper;
	
	public UserResponse createUser(UserRequest userRequest) throws PasswordValidationError {
		User user = mapper.toEntity(userRequest);
		
		if (!Validator.passwordValidate(user.getPassword())) {
			throw new PasswordValidationError("Senha deve seguir o padrão:\n -1 Letra maiuscula,"
					+ " \n -1 Letra minuscula, \n -1 caractere especial, \n -tamanho mínimo de 8 caracteres");
		}
		
		//TODO: Acrescentar password enconder que deve estar no Security config
		
		
		user.setActive(true);
		
		return mapper.toResponse(repository.save(user));
	}
	
	public List<UserResponse> getActiveAllUsers(){
		List<User> users = repository.findAllActiveUsers();
		
		return mapper.toResponseList(users);
	}
	
	public List<UserResponse> getAllUsers(){
		List<User> users = repository.findAll();
		
		return mapper.toResponseList(users);
	}
	
	public UserResponse updateUser(UserRequest userRequest, Long user_id) throws PasswordValidationError {
		User user = mapper.toEntity(userRequest);
		
		if (!Validator.passwordValidate(user.getPassword())) {
			throw new PasswordValidationError("Senha deve seguir o padrão:\n -1 Letra maiuscula,"
					+ " \n -1 Letra minuscula, \n -1 caractere especial, \n -tamanho mínimo de 8 caracteres");
		}
		
		//TODO: Acrescentar password enconder que deve estar no Security config
		
		user.setActive(true);
		user.setUser_id(user_id);
		
		return mapper.toResponse(repository.save(user));
		
		
	}
	
	public void deleteUser(Long user_id) throws UserPrincipalNotFoundException {
		User user = repository.findById(user_id).get();
		
		if(user == null) throw new UserPrincipalNotFoundException("O Usuário não foi encontrado!");
		
		user.setActive(false);
	}
	
	public UserResponse getUserById(Long user_id) throws UserPrincipalNotFoundException {
		User user = repository.findActiveUser(user_id);
		
		if(user == null) throw new UserPrincipalNotFoundException("O Usuário não foi encontrado!");
		
		return mapper.toResponse(user);
	}
	
	public UserResponse getUserByEmail(String email) throws UserPrincipalNotFoundException {
		User user = repository.findActiveUserByEmail(email);
		
		if(user == null) throw new UserPrincipalNotFoundException("O Usuário não foi encontrado!");
		
		return mapper.toResponse(user);
	}
	
	public UserResponse getUserByUsername(String username) throws UserPrincipalNotFoundException {
		User user = repository.findActiveUserByUsername(username);
		
		if(user == null) throw new UserPrincipalNotFoundException("O Usuário não foi encontrado!");
		
		return mapper.toResponse(user);
	}
	
	

}
