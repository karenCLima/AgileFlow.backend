//package com.br.AgileFlow.backend.api.controller.dto.request;
//
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import com.br.AgileFlow.backend.domain.model.User;
//import com.br.AgileFlow.backend.domain.model.enums.Status;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class TaskRequest {
//
//	@NotBlank(message = "O nome é obrigatório.")
//	@Size(max = 100, message = "O nome pode ter no máximo 100 caracteres.")
//	private String name;
//
//
//	private Date initialDate;
//
//
//	private Date finalDate;
//
//	@NotBlank(message = "O status é obrigatório")
//	private Status status;
//
//	@NotBlank(message = "O usuário é obrigatório")
//	private User user;
//
//	@NotBlank(message = "O numero do projeto é obrigatório")
//	private UUID projectNumber;
//
//
//	private List<String> activities;
//
//}
