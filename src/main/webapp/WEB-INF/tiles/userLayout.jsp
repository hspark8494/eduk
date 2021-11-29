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
		padding-top: 58px;
	}
</style>
</head>
<body>
<header>
	<tiles:insertAttribute name="header"/>
</header>
<section>
	<tiles:insertAttribute name="content"/>
</section>
</body>
</html>