<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eduKation</title>
<style type="text/css">
	*{
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	ul{
		list-style: none;
	}
	a{
		text-decoration: none;
	}
	section{
		padding-top: 70px;
		padding-left: 250px;
		height: 800px;
	}
	footer{
		padding-left: 250px;
	}
</style>
</head>
<body>
<header>
	<tiles:insertAttribute name="header"/>
</header>
<nav>
	<tiles:insertAttribute name="nav"/>
</nav>
<section>
	<tiles:insertAttribute name="content"/>
</section>
<footer>
	<tiles:insertAttribute name="footer"/>
</footer>
</body>
</html>