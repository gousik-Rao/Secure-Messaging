package com.military.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.military.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
//	Native query if required
//	@Query(value = "SELECT password FROM users WHERE email = :email", nativeQuery = true)

//	Custom query for finding password using email
	@Query("SELECT u.password from User u where u.email = :email") // In JPQL we need to referencce the entity class name (User) and not the table name(users) in the database                  
	String findPasswordByEmail(@Param("email")String email);

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);
}
