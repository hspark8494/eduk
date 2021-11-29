<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style type="text/css">
	.login-container{
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
	.login-container>a{
		float: right;
		padding: 5px 20px;
		background-color: #F26D21;
		color: #fff;
		border-radius: 15px;
		margin-top: 20px;
		margin-right: 20px;
	}
	.login-container>a:hover {
		color: #fff;
		background-color: #F48A4A;
	}
	.login-container form{
		clear: both;
		padding-top: 30px;
		width: 300px;
		margin: 0 auto;
		color: #777;
	}
	.login-container input{
		margin-bottom: 20px;
	}
	.login-container input::placeholder{
		font-size: 14px;
	}
	.login-container input[type=text], .login-container input[type=password]{
		padding: 5px 10px;
		width: 100%;
		margin-top: 5px;
		border: 1px solid #999;
		border-radius: 2px;
		padding: 5px 10px;
		font-size: 16px;
	}
	.login-container input[type=text]:focus, .login-container input[type=password]:focus{
		outline: 1px solid #5FA3F5;
		border: 1px solid #5FA3F5;
		border-radius: 2px;
		padding: 5px 10px;
	}
	.login-container .left{
		float: left;
	}
	.login-container .right{
		float: right;
		font-size: 14px;
		color: #0C63CE;
	}
	.login-container .right:hover{
		color: #5FA3F5;
	}
	.login-container input[type=password]{
		clear: both;
	}
	.login-container span{
		font-size: 14px;
		vertical-align: top;
	}
	.login-container button {
		display: block;
		border: none;
		background-color: #0C63CE;
		color: #fff;
		width: 100%;
		padding: 10px;
		border-radius: 5px;
		margin-top: 15px;
	}
	.login-container button:hover{
		background-color: #5FA3F5;		
		cursor: pointer;
	}
</style>
</head>
<body>
<div class="login-container">
	<a href="${pageContext.request.contextPath}/signup">회원 가입</a>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<p>이메일</p>
		<input type="text" name="email" placeholder="이메일을 입력해주세요." required autofocus>
		<p class="left">비밀번호</p>
		<a href="${pageContext.request.contextPath}/findPassword" class="right">비밀번호를 잊으셨나요?</a>
		<input type="password" name="password" placeholder="비밀번호를 입력해주세요." required>
		<input type="checkbox" name="stayLogin" value="stay"><span> 로그인 상태 유지</span>
		<button type="submit">로그인</button>
	</form>
</div>
</body>
</html>