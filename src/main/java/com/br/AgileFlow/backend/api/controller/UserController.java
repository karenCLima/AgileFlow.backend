//package com.br.AgileFlow.backend.api.controller;
//
//import java.net.URI;
//import java.util.List;
//
//import org.apache.coyote.BadRequestException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.br.AgileFlow.backend.api.controller.exceptions.PasswordValidationError;
//import com.br.AgileFlow.backend.api.controller.exceptions.UserNotFoundException;
//import com.br.AgileFlow.backend.api.controller.dto.request.ParamRequest;
//import com.br.AgileFlow.backend.api.controller.dto.request.UserRequest;
//import com.br.AgileFlow.backend.api.controller.dto.request.UserUpdateRequest;
//import com.br.AgileFlow.backend.api.controller.dto.response.UserResponse;
//import com.br.AgileFlow.backend.domain.service.UserService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//	@Autowired
//	UserService userService;
//
////	@PostMapping("/register")
////	public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) throws PasswordValidationError{
////		UserResponse userResponse = userService.createUser(userRequest);
////		return ResponseEntity.created(URI.create("/user/" + userResponse.getUser_id())).body(userResponse);
////
////	}
////
////	@PutMapping("/password/{user_id}")
////	public ResponseEntity<UserResponse> updatePassword(@RequestBody ParamRequest password, @PathVariable Long user_id) throws UserNotFoundException, PasswordValidationError{
////		return ResponseEntity.ok(userService.updatePassword(password, user_id));
////	}
////
////	@PutMapping("/change/username/{user_id}")
////	public ResponseEntity<UserResponse> updateUsername(@RequestBody ParamRequest username, @PathVariable Long user_id) throws UserNotFoundException, BadRequestException{
////		return ResponseEntity.ok(userService.updateUsername(username, user_id));
////	}
////
////	@PutMapping("/{user_id}")
////	public ResponseEntity<UserResponse> updateUser(
////			@RequestBody @Valid UserUpdateRequest userRequest,
////			@PathVariable Long user_id) throws UserNotFoundException{
////		return ResponseEntity.ok(userService.updateUser(userRequest, user_id));
////	}
////
////	@DeleteMapping("/{user_id}")
////	public void deleteUser(@PathVariable Long user_id) throws UserNotFoundException {
////		userService.deleteUser(user_id);
////	}
////
////	@GetMapping("/active")
////	public ResponseEntity<List<UserResponse>> listAllActiveUsers(){
////		return ResponseEntity.ok(userService.getAllActiveUsers());
////	}
////
////	@GetMapping
////	public ResponseEntity<List<UserResponse>> listAllUsers(){
////		return ResponseEntity.ok(userService.getAllUsers());
////	}
////
////	@GetMapping("/{user_id}")
////	public ResponseEntity<UserResponse> getUserById(@PathVariable Long user_id) throws UserNotFoundException{
////		return ResponseEntity.ok(userService.getUserById(user_id));
////	}
////
////	@GetMapping("/username/{username}")
////	public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) throws UserNotFoundException{
////		return ResponseEntity.ok(userService.getUserByUsername(username));
////	}
////
////	@GetMapping("/email/{email}")
////	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) throws UserNotFoundException{
////		return ResponseEntity.ok(userService.getUserByEmail(email));
////	}
//
//}
