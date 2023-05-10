package com.tenco.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, length = 30)
	private String username;
	@Column(nullable = false, length = 30)
	@NotBlank
	private String password;
	@NotBlank
	@Column(nullable = false, length = 100)
	private String email;
	@ColumnDefault("'user'") // 문자열 "'str'" 숫자는 "int"
	private String role; // user, admin, manager
	@CreationTimestamp // = now()
	private Timestamp createdDate;
}
