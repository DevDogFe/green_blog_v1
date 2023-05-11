<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<h1>회원가입</h1>
<div class="container">
	<form action="#" method="post">
		<div class="form-group">
			<label for="username">username: </label> <input type="text" name="username" id="username" value="atom" class="form-control">
		</div>
		<div class="form-group">
			<label for="password">password: </label> <input type="password" name="password" id="password" value="1234" class="form-control">
		</div>
		<div class="form-group">
			<label for="email">email: </label> <input type="text" name="email" id="email" value="a@naver.com" class="form-control">
		</div>
		<button type="button" class="btn btn-primary" id="btn--save">회원가입</button>
	</form>
</div>
<%@ include file="../layout/footer.jsp"%>
<script src="/js/user.js"></script>
