package com.br.AgileFlow.backend.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.AgileFlow.backend.controller.exceptions.PasswordValidationError;
import com.br.AgileFlow.backend.controller.exceptions.UserNotFoundException;
import com.br.AgileFlow.backend.dto.request.ParamRequest;
import com.br.AgileFlow.backend.dto.request.UserRequest;
import com.br.AgileFlow.backend.dto.request.UserUpdateRequest;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserResponse createUser(UserRequest userRequest) throws PasswordValidationError {
		User user = mapper.toEntity(userRequest);
		
		if (!Validator.passwordValidate(user.getPassword())) {
			throw new PasswordValidationError("Senha deve seguir o padrão:\n -1 Letra maiuscula,"
					+ " \n -1 Letra minuscula, \n -1 caractere especial, \n -tamanho mínimo de 8 caracteres");
		}
		
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		user.setProjectOwnerCount(0);
		user.setActive(true);
		
		return mapper.toResponse(repository.save(user));
	}
	
	public List<UserResponse> getAllActiveUsers(){
		List<User> users = repository.findAllActiveUsers();
		
		return mapper.toResponseList(users);
	}
	
	public List<UserResponse> getAllUsers(){
		List<User> users = repository.findAll();
		
		return mapper.toResponseList(users);
	}
	
	public UserResponse updateUser(UserUpdateRequest userUpdateRequest, Long user_id) throws UserNotFoundException {
		User user = repository.findById(user_id).get();
		
		if(user == null) throw new UserNotFoundException("O Usuário não foi encontrado!");
		
		user.setName(userUpdateRequest.getName());
		user.setPosition(userUpdateRequest.getPosition());
		user.setEmail(userUpdateRequest.getEmail());
		user.setActive(true);
		
		return mapper.toResponse(repository.save(user));
		
		
	}
	
	public void deleteUser(Long user_id) throws UserNotFoundException {
		User user = repository.findById(user_id).get();
		
		if(user == null) throw new UserNotFoundException("O Usuário não foi encontrado!");
		
		user.setActive(false);
		
		repository.save(user);
	}
	
	public UserResponse getUserById(Long user_id) throws UserNotFoundException {
		User user = repository.findActiveUser(user_id);
		
		if(user == null) throw new UserNotFoundException("O Usuário não foi encontrado!");
		
		return mapper.toResponse(user);
	}
	
	public UserResponse getUserByEmail(String email) throws UserNotFoundException {
		User user = repository.findActiveUserByEmail(email);
		
		if(user == null) throw new UserNotFoundException("O Usuário não foi encontrado!");
		
		return mapper.toResponse(user);
	}
	
	public UserResponse getUserByUsername(String username) throws UserNotFoundException {
		User user = repository.findActiveUserByUsername(username);
		
		if(user == null) throw new UserNotFoundException("O Usuário não foi encontrado!");
		
		return mapper.toResponse(user);
	}
	
	public UserResponse updatePassword(ParamRequest password, Long user_id) throws UserNotFoundException, PasswordValidationError {
		User user = repository.findActiveUser(user_id);
		
		if(user == null) throw new UserNotFoundException("O Usuário não foi encontrado!");
		
		if (!Validator.passwordValidate(password.getParameter())) {
			throw new PasswordValidationError("Senha deve seguir o padrão:\n -1 Letra maiuscula,"
					+ " \n -1 Letra minuscula, \n -1 caractere especial, \n -tamanho mínimo de 8 caracteres");
		}
		
		String encodePassword = passwordEncoder.encode(password.getParameter());
		user.setPassword(encodePassword);
		
		return mapper.toResponse(repository.save(user));
	}
	
	public UserResponse updateUsername(ParamRequest username, Long user_id) throws UserNotFoundException, BadRequestException {
		User user = repository.findActiveUser(user_id);
		
		if(user == null) throw new UserNotFoundException("O Usuário não foi encontrado!");
		
		if(username.getParameter() == null || username.getParameter().isBlank() || username.getParameter().isEmpty()|| username.getParameter().length()>20) throw new BadRequestException("O username não pode ser branco, nulo ou ter mais de 20 caracteres!");
		
		user.setUsername(username.getParameter());
		
		return mapper.toResponse(repository.save(user));
	}
	

}
