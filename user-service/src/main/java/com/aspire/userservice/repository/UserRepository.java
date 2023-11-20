package com.aspire.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aspire.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value= "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	public Optional<User> findByUsername(@Param("username") String username);
}
