package com.tenco.blog.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenco.blog.dto.ResponseDto;
import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpSession session;

	@Transactional
	public int createUser(User user) {

		try {
			user.setRole("user");
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;

	}

	@Transactional
	public ResponseDto<?> readUser(User user) {

//		User principal = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
//				.orElseThrow(() -> {
//					return new IllegalArgumentException("로그인에 실패하였습니다.");
//				});
		User principal = userRepository.login(user.getUsername(), user.getPassword())
				.orElseThrow(() -> {
					return new IllegalArgumentException("로그인에 실패하였습니다.");
				});
		
		session.setAttribute("principal", principal);

		return new ResponseDto<Integer>(HttpStatus.OK, 1);

	}

}
