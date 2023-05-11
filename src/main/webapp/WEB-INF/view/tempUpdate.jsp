<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>form test</h1>
			<div class="form-group">
				<label for="username">username: </label> <input type="text" name="username" id="username" value="항1" class="form-control">
			</div>
			<div class="form-group">
				<label for="password">password: </label> <input type="password" name="password" id="password" value="1234" class="form-control">
			</div>
			<div class="form-group">
				<label for="password">new password: </label> <input type="password" name="newPassword" id="newPassword" value="1111" class="form-control">
			</div>
			<div class="form-group">
				<label for="email">new email: </label> <input type="text" name="email" id="email" value="aqwe@naver.com" class="form-control">
			</div>

		<button id="join--submit" class="btn btn-primary">정보수정</button>
	</div>
	<script type="text/javascript">
		$(document).ready(() => {
			$("#join--submit").on("click", () =>{
				// MIME TYPE => application/json
				// js -> json 문자열로 변경하는 방법
				// object -> json 문자열로 변경
				let data = {
						username: $("#username").val(),
						password: $("#password").val(),
						newPassword: $("#newPassword").val(),
						email: $("#email").val()
						};
				console.log(JSON.stringify(data));
				
				$.ajax({
					type: "PUT",
					url: "/temp/update",
					contentType:"application/json; charset=utf-8",
					data: JSON.stringify(data),
					dataType:"json"
				}).done((response) => {
					console.log(response);
					console.log(typeof response);
					alert("성공")
					location.href='/temp/index';
				}).fail((error) => {
					alert("서버오류")
				});
			});
		});
	</script>
</body>
</html>