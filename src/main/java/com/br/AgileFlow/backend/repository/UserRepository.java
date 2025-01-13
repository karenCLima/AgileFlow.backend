package com.br.AgileFlow.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.AgileFlow.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.active = true AND u.id = :userId")
    User findActiveUser(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.active = true")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.active = true AND u.email = :email")
    User findActiveUserByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM User u WHERE u.active = true AND u.username = :username")
    User findActiveUserByUsername(@Param("username") String username);
    
    Optional<User> findByUsername(String username);

}
