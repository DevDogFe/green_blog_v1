package com.tenco.blog.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

@RestController // Ioc 처리
public class DummyControllerTest {

	// DI
	private UserRepository userRepository;
	
	public DummyControllerTest(UserRepository userRepository) { // autowired 역할
		this.userRepository = userRepository;
	}
	
	// application/json
	// 회원 등록 - 샘플
	@PostMapping("/dummy/insert-user")
	public String insertUser(@Validated @RequestBody User user) {
		
		user.setRole("user");
		System.out.println(user.toString());
		System.out.println("1111111111111111");
		userRepository.save(user);
		
		return "회원 가입에 성공";
	}
	
	// http://localhost:8080/dummy/user/1
	@GetMapping("/dummy/user/{id}")
	public User getUser(@PathVariable Integer id) {
		// optional
		
		// Optional<User> userEntity = userRepository.findById(id);
		/**
		 * 1. get(): null일수 없을 때 사용
		 * 2. orElseGet(): 데이터가 있으면 그대로 반환, 없으면 직접 정의한 객체를 반환시킬 수 있다.
		 * 3. orElseThrow(): 있으면 반환, 없으면 예외
		 */
//		User userEntity = userRepository.findById(id).get();
//		User userEntity = userRepository.findById(id).orElseGet(()->{
//			return new User();
//		});
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			
			return new IllegalArgumentException("없는 id입니다.");
		});
		System.out.println(userEntity.toString());
		
		return userEntity;
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 3, sort = "id") Pageable pageable){
		
//		List<User> users = userRepository.findAll();
		Page<User> pageUser = userRepository.findAll(pageable);
		
		return pageUser.getContent();
		
	}
	
	// JSON 던질 예정
	// dirty checking 사용
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable Integer id, @Validated @RequestBody User reqUser, BindingResult bindingResult) {
		
		// 존재 여부 확인
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 없습니다.");
		});
		userEntity.setEmail(reqUser.getEmail());
		userEntity.setPassword(reqUser.getPassword());
		
		// 저장 처리
		// userRepository.save(userEntity);
		/**
		 * dirty checking: save를 호출하지 않았는데 변경처리 됨.
		 * 트랜잭션 내에서 트랜잭션이 끝나기 전에 영속성 컨텍스트의
		 * 1차 캐시에 들어가있는 데이터 상태를 변경 감지한다.
		 */
		
		
		
		return userEntity;
	}
	
	@Transactional
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable Integer id) {
		User userEntity = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 없습니다.");
		});
		userRepository.delete(userEntity);
		
		
		return id + "번 id user 정보를 삭제하였습니다.";
	}
}
