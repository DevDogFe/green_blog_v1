package com.tenco.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenco.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// select, selectAll, insert, update, delete 자동 생성
	
	Optional<User> findByUsernameAndPassword(String username, String password);
	
}
