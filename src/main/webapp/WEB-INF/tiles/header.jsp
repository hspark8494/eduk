<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<style type="text/css">	
	.header-container{
		position: fixed;
		width: 100%;
		top: 0;
		padding: 5px 40px;
		box-shadow: 0px 1px 2px 0px #ddd;
		background-color: #fff;
		z-index: 1;
	}
	.header-logo{
		float: left;
	}
	.header-logo img{
		width: 200px;
		height: 44px;
	}
	.header-menu{
		float: right;
		padding: 13.2px 0px;
	}
	.header-menu li{
		float : left;
		margin-right: 30px;
	}
	.header-menu .last-child{
		margin-right: 0;
	}
	.header-menu a{
		padding: 5px 10px;
		font-size: 14px;
		font-weight: bold;
		color: #0C63CE;
	}
	.header-menu a:hover{
		color: #5FA3F5;
	}
	.header-menu .emphasize{
		padding: 5px 20px;
		font-size: 16px;
		background-color: #F26D21;
		color: #fff;
		border-radius: 8px;
	}
	.header-menu .emphasize:hover {
		color: #fff;
		background-color: #F48A4A;
	}
	.header-menu .logout{
		margin-right: 20px;
	}
	.logout a{
		color: #ccc;
	}
</style>
</head>
<body>
<div class="header-container">
	<ul>
		<li class="header-logo">
			<a href="${pageContext.request.contextPath}/">
				<img alt="로고" src="images/kosta.png">
			</a>
		</li>
		<li class="header-menu">
			<ul>
				<c:choose>
					<c:when test="${empty sessionScope.user}">
						<li>
							<a href="${pageContext.request.contextPath}/loginForm">로그인</a>
						</li>
						<li class="last-child">
							<a href="${pageContext.request.contextPath}/signup" class="emphasize">회원 가입</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="logout">
							<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/myInfo">마이 페이지</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/create" class="emphasize">강의실 생성</a>
						</li>
						<li class="last-child">
							<a href="${pageContext.request.contextPath}/enter" class="emphasize">강의실 입장</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</li>
	</ul>
</div>
</body>
</html>