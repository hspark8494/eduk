<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style type="text/css">
	.findPassword-container{
		position: absolute;
		top: 58px;
		left: 0;
		right: 0;
		bottom: 0;
		margin: auto;
		width: 400px;
		height: 380px;
		border: 1px solid #aaa;
		border-radius: 5px;
		box-shadow: 2px 2px 5px 0px #aaa;
	}
	.findPassword-container>h3{
		margin-top: 30px;
		text-align: center;
	}
	.findPassword-container form{
		padding-top: 30px;
		width: 300px;
		margin: 0 auto;
		color: #777;
	}
	.findPassword-container input{
		margin-bottom: 20px;
		padding: 5px 10px;
		width: 100%;
		margin-top: 5px;
		border: 1px solid #999;
		border-radius: 2px;
		padding: 5px 10px;
		font-size: 16px;
	}
	.findPassword-container input:focus{
		outline: 1px solid #5FA3F5;
		border: 1px solid #5FA3F5;
		border-radius: 2px;
		padding: 5px 10px;
	}
	.findPassword-container input::placeholder{
		font-size: 14px;
	}
	.findPassword-container p:nth-child(3){
		display: inline-block;
	}
	.findPassword-container #time{
		color: red;
		font-size: 14px;
		margin-top: -15px;
	}
	.findPassword-container button{
		border: none;
		background-color: #0C63CE;
		color: #fff;
		border-radius: 5px;
	}
	.findPassword-container button:hover{
		background-color: #5FA3F5;		
		cursor: pointer;
	}
	.findPassword-container button[type=button]{
		display: inline-block;
		padding: 1px 10px;
		margin-left: 10px;
	}
	.findPassword-container button[type=submit]{
		width: 100%;
		padding: 10px;
		margin-top: 30px;
	}
</style>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#sendMail").click(function(){
			$.ajax({
 				url: "${pageContext.request.contextPath}/sendMail",
 				success: function(){
 					setTimer();
 				},
 				error: function(err){
 					alert(err + "발생");
 				}
 			});
		});
		
		function setTimer(){
			$("#time").text("남은 유효시간 04:59");
		}
		
	});
</script>
</head>
<body>
<div class="findPassword-container">
	<h3>비밀번호 찾기</h3>
	<form action="${pageContext.request.contextPath}/findPassword" method="post">
		<p>이메일</p>
		<input type="text" name="email" placeholder="이메일을 입력해주세요." required autofocus>
		<p>이메일 인증</p>
		<button type="button" id="sendMail">인증 코드 받기</button>
		<input type="text" name="code" placeholder="이메일로 발송된 인증 코드를 입력해주세요." required>
		<p id="time"><p>
		<button type="submit">비밀번호 찾기</button>
	</form>
</div>
</body>
</html>