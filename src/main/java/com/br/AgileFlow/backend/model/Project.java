package com.br.AgileFlow.backend.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.br.AgileFlow.backend.model.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long project_id;
	
	@Column(nullable = false)
	private UUID projectNumber;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Date created_at;
	
	@Column
	private Date closingDate;
	
	@Column
	private Date realClosingDate;
	
	@Column
	private Status status;
	
	@ManyToMany
	private List<User> teamMembers;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Task> tasks;

}
