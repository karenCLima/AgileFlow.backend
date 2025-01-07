package com.br.AgileFlow.backend.dto.response;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.br.AgileFlow.backend.model.User;
import com.br.AgileFlow.backend.model.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
	
	private Long project_id;
	
	
	private UUID projectNumber;
	

	private String name;
	
	
	private String description;
	
	
	private Date created_at;
	
	
	private Date closingDate;
	
	
	private Date realClosingDate;
	
	
	private Status status;
	
	
	private List<User> teamMembers;

}
