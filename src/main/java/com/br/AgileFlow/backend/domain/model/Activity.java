//package com.br.AgileFlow.backend.domain.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "activities")
//public class Activity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	private Integer activity_id;
//
//	@Column(nullable = false)
//	private String name;
//
//	@ManyToOne
//	@JoinColumn(name = "task_id")
//	private Task task;
//
//}
