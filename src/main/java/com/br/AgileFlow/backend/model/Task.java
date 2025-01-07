package com.br.AgileFlow.backend.model;

import java.util.Date;
import java.util.List;

import com.br.AgileFlow.backend.model.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long task_id;
	
	@Column(nullable = true)
	private String name;
	
	@Column
	private Date created_at;
	
	@Column
	private Date initialDate;
	
	@Column
	private Date finalDate;
	
	@Column
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User responsibleMember;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
	private List<Activity> activities;

}
