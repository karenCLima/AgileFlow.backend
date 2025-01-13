package com.br.AgileFlow.backend.util.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.AgileFlow.backend.dto.request.UserRequest;
import com.br.AgileFlow.backend.dto.response.UserResponse;
import com.br.AgileFlow.backend.model.User;

@Component
public class UserMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User toEntity(UserRequest userRequest) {
		return modelMapper.map(userRequest, User.class);
	}
	
	public UserResponse toResponse(User user) {
		return modelMapper.map(user, UserResponse.class);
	}
	
	public List<UserResponse> toResponseList(List<User> users){
		return users.stream()
				.map(this::toResponse)
				.collect(Collectors.toList());
	}

}
