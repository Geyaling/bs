<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<title>Footer</title>
<style type="text/css">
	footer{width: 100%;height: 70px;background:#fff;}
	footer .copyright{height: 30px;line-height: 30px;font-size: 13px;word-spacing: 15px;text-align: center;}
	footer .copyright:first-of-type{margin-top: 40px;}
</style>
</head>

<body>
	<footer>
		<div class="copyright">简体 | 繁体 | English | 常见问题</div>
		<div class="copyright">当当商城版权所有-京ICP备10046444-<img src="<c:url value="/images/ghs.png"/>" alt="">京公网安备11010802020134号-京ICP证110507号</div>
	</footer>
</body>
</html>