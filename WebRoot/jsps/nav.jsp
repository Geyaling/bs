<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>按图名查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
			text-decoration: none;
			list-style-type: none;
			font-family: Times New Roman;
		}
		.center{margin: 0 auto;}
		.fl{float: left;}
		.fr{float: right;}
		.clear{clear:both;}
		.nav ul li{float: left;height: 25px;line-height: 25px;}
		.nav ul li a{display:inline-block;color: #000;font-size: 16px;padding: 0 10px;}
		.nav ul li a:hover{color: rgb(255,103,0); }
	</style>	
  </head>
<body>
	<div class="nav fl">
		<ul>
			<li>热卖</li>
			<li><a href="<c:url value='/BookServlet?method=load&bid=0EE8A0AE69154287A378FB110FF2C780'/>" target="body">Java核心技术：卷1</a></li>
			<li><a href="<c:url value='/BookServlet?method=load&bid=3AE5C8B976B6448A9D3A155C1BDE12BC'/>" target="body">深入理解Java虚拟机</a></li>
			<li><a href="<c:url value='/BookServlet?method=load&bid=4A9574F03A6B40C1B2A437237C17DEEC'/>" target="body">Spring实战</a></li>
			<li><a href="<c:url value='/BookServlet?method=load&bid=4D20D2450B084113A331D909FF4975EB'/>" target="body">jQuery实战</a></li>
			<li><a href="<c:url value='/BookServlet?method=load&bid=9D257176A6934CB79427CEC37E69249F'/>" target="body">疯狂Ajax讲义</a></li>
			<li><a href="<c:url value='/BookServlet?method=load&bid=95AACC68D64D4D67B1E33E9EAC22B885'/>" target="body">Head First Java</a></li>
		</ul>
	</div>
</body>
</html>