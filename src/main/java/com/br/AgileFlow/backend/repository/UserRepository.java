package com.br.AgileFlow.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.AgileFlow.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.active = true")
	User findActiveUser(Long user_id);
	
	@Query("SELECT u FROM User u WHERE u.active = true")
	List<User> findAllActiveUsers();

}
