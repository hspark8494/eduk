<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nav</title>
<style type="text/css">
	.nav-container{
		width: 250px;
		height: 100vh;
		border-right: 1.5px solid #ddd;
		padding-top: 58px;
		background-color: #F8F8FA;
		position: fixed;
		z-index: 0;
	}
	.nav-container ul li:first-child{
		margin: 20px 0px;
		padding-left: 30px;
	}
	.nav-container li{
		margin-bottom: 15px;
	}
	.nav-container p{
		color: #7B8084;
		font-size: 18px;
	}
	.nav-container p span{
		font-size: 20px;
	}
	.nav-container a{
		color: #7B8084;
		font-size: 17px;
		width: 100%;
		padding: 5px 121.54px 5px 30px;
	}
	.nav-container a:hover{
		color: #fff;
		background-color: #5CB3E7;
	}
	.nav-container .current{
		color: #fff;
		background-color: #2D9EE0;
	}
</style>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
	    $(".myInfo").click(function(){
	        $(".myInfo").addClass("current");
	        $(".classList,.setting,.withdrawal").removeClass("current");
	    });
	    $(".classList").click(function(){
	        $(".classList").addClass("current");
	        $(".myInfo,.setting,.withdrawal").removeClass("current");
	    });
	    $(".setting").click(function(){
	        $(".setting").addClass("current");
	        $(".myInfo,.classList,.withdrawal").removeClass("current");
	    });
	    $(".withdrawal").click(function(){
	        $(".withdrawal").addClass("current");
	        $(".myInfo,.classList,.setting").removeClass("current");
	    });
	});
</script>
</head>
<body>
<div class="nav-container">
	<ul>
		<li>
			<p><span>${user.name}</span> 님</p>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/myInfo" class="myInfo current">▶ 정보 수정</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/classList" class="classList">▶ 강의 목록</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/setting" class="setting">▶ 환경 설정</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/withdrawal" class="withdrawal">▶ 회원 탈퇴</a>
		</li>
	</ul>
</div>
</body>
</html>