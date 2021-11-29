<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<style type="text/css">
	.resetPassword-container{
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
	.resetPassword-container>h3{
		margin-top: 30px;
		text-align: center;
	}
	.resetPassword-container>p{
		color: #aaa;
		font-size: 14px;
		text-align: center;
		margin-top: 10px;
	}
	.resetPassword-container form{
		padding-top: 30px;
		width: 300px;
		margin: 0 auto;
		color: #777;
	}
	.resetPassword-container input{
		margin-bottom: 20px;
		padding: 5px 10px;
		width: 100%;
		margin-top: 5px;
		border: 1px solid #999;
		border-radius: 2px;
		padding: 5px 10px;
		font-size: 16px;
	}
	.resetPassword-container input:focus{
		outline: 1px solid #5FA3F5;
		border: 1px solid #5FA3F5;
		border-radius: 2px;
		padding: 5px 10px;
	}
	.resetPassword-container input::placeholder{
		font-size: 14px;
	}
	.resetPassword-container #matchWhether{
		color: red;
		font-size: 14px;
		margin-top: -15px;
	}
	.resetPassword-container button{
		border: none;
		background-color: #0C63CE;
		color: #fff;
		border-radius: 5px;
		width: 100%;
		padding: 10px;
		margin-top: 30px;
	}
	.resetPassword-container button:hover{
		background-color: #5FA3F5;		
		cursor: pointer;
	}
</style>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#newPassword").keyup(function(){
			if($("#passwordCheck").val() != null){
				if($("#newPassword").val() != $("#passwordCheck").val()){
					$("#matchWhether").text("비밀번호가 일치하지 않습니다.");
				}else{
					$("#matchWhether").text("");
				}
			}
		});
		
		$("#passwordCheck").keyup(function(){
			if($("#newPassword").val() != null){
				if($("#passwordCheck").val() != $("#newPassword").val()){
					$("#matchWhether").text("비밀번호가 일치하지 않습니다.");
				}else{
					$("#matchWhether").text("");
				}
			}
		});
		
		$("form").submit(function(){
			if($("#matchWhether").text() == "비밀번호가 일치하지 않습니다."){
				return false;
			}else{
				alert("비밀번호가 변경되었습니다.\n다시 로그인해주세요.");
			}
		});
	});
</script>
</head>
<body>
<div class="resetPassword-container">
	<h3>비밀번호를 재설정해주세요.</h3>
	<p>기존 비밀번호와 다른 안전한 비밀번호로 변경해주세요.</p>
	<form action="${pageContext.request.contextPath}/changePassword" method="post">
		<input type="hidden" name="memberId" value="${memberId}">
		<p>새 비밀번호</p>
		<input type="password" id="newPassword" name="password" placeholder="비밀번호를 입력해주세요." required autofocus>
		<p>새 비밀번호 확인</p>
		<input type="password" id="passwordCheck" placeholder="비밀번호를 입력해주세요." required>
		<p id="matchWhether"><p>
		<button type="submit">비밀번호 변경</button>
	</form>
</div>
</body>
</html>