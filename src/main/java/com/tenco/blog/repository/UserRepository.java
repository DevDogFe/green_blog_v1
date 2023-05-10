package com.tenco.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenco.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// select, selectAll, insert, update, delete 자동 생성
	
}
