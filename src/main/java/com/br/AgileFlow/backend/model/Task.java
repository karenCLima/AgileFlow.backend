package com.br.AgileFlow.backend.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
	
	private Long task_id;
	
	private String name;
	
	private Date created_at;
	
	private Date initialDate;
	
	private Date finalDate;
	
	private User responsibleMember;
	
	private List<Activity> activities;

}
