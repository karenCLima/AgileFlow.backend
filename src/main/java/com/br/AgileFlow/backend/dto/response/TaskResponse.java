package com.br.AgileFlow.backend.dto.response;

import java.util.Date;
import java.util.List;

import com.br.AgileFlow.backend.model.Activity;
import com.br.AgileFlow.backend.model.Project;
import com.br.AgileFlow.backend.model.User;
import com.br.AgileFlow.backend.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

	private Long task_id;
	
	
	private String name;
	
	
	private Date created_at;
	
	
	private Date initialDate;
	
	
	private Date finalDate;
	
	
	private Status status;
	
	
	private User responsibleMember;
	
	
	private Project project;
	

	private List<Activity> activities;
}
