package com.tenco.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	@Autowired
	private HttpSession session;
	
	/**
	 * 
	 * @return 로그인 페이지
	 */
	@GetMapping("/login")
	public String getLoginPage() {
		
		return "/user/login_form";
	}
	
	/**
	 * 
	 * @return 회원가입 페이지
	 */
	@GetMapping("/join")
	public String getJoinPage() {
		
		return "/user/join_form";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		session.invalidate();
		return "redirect:/";
	}

}
