package com.tenco.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tenco.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// select, selectAll, insert, update, delete 자동 생성
	
	// 1. Spring JPA 네이밍 전략
	// 메서드 이름으로 JPA 쿼리를 만들어준다.(규칙을 지킨다면)
	Optional<User> findByUsernameAndPassword(String username, String password);
	
	// 2.
	@Query(value = " SELECT * FROM user "
			+ " WHERE username = ?1 "
			+ " AND password = ?2 ",
			 nativeQuery = true)
	Optional<User> login(String username, String password);
	
}
