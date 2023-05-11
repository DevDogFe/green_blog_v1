package com.tenco.blog.dto;

import lombok.Data;

@Data
public class UpdateUser {

	private String username;
	private String password;
	private String newPassword;
	private String email;
}

