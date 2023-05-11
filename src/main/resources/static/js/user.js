let index = {
	init: function(){
		$("#btn--save").on("click", () => {
			this.save();
		});
		
		$("#btn--login").on("click", () => {
			this.login();
		});
	},
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),   
			email: $("#email").val()
		};
		$.ajax({
			type: "POST",
			url: "/api/user",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			dataType: "json"
		}).done((res) => {
			console.log(res);
			if(res.status == "OK" && res.data == 1){
				alert("회원가입에 성공하였습니다.");
				location.href = "/login";
			}
		}).fail((e) => {
			console.log(e);
			alert("회원가입에 실패하였습니다.");
		});
	},
	login: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val()   
		}
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			dataType: "json"
		}).done((res) => {
			console.log(res);
			alert("로그인 성공");
			location.href="/";
		}).fail((e) => {
			alert("로그인 실패");
		});
	}
};

index.init();