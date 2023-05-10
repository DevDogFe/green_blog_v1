package com.tenco.blog.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenco.blog.dto.ApiErrorResponse;
import com.tenco.blog.dto.ExceptionFieldMessage;

@RestControllerAdvice // IOC
public class RestExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {

		System.out.println("====================");
		System.out.println(e.getClass().getName());
		System.out.println(e.getMessage());
		System.out.println("====================");
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {

		return "<h1>" + e.getMessage() + "</h1>";
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {

		List<ExceptionFieldMessage> fieldList = new ArrayList<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			FieldError fieldError = (FieldError) error;
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();

			fieldList.add(ExceptionFieldMessage.builder().field(fieldName).message(message).build());

		});
		System.out.println("MethodArgumentNotValidException 예외 발생");
		return ApiErrorResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value()).code("-1").resultCode("fail")
				.message("잘못된 요청입니다.").exceptionFieldMessages(fieldList).build();
	}
}
