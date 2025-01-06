package com.br.AgileFlow.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.AgileFlow.backend.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
