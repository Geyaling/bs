<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/regist.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>
</head>
<body>
<div id="divMain">
	<div class="regist">
			<div class="regist_center">
				<div class="regist_top">
					<div class="left fl">新用户注册</div>
					<div class="right fr"><a href="/bs/index.jsp">回到首页</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div id="divBody">
				<form id="registForm" action="<c:url value='/UserServlet'/>" method="post">
				<input type="hidden" name="method" value="regist"/>
				<table id="tableForm">
					<tr>
						<td class="tdText">用户名：</td>
						<td class="tdInput">
							<input class="inputClass" type="text" name="loginname" id="loginname" value="${form.loginname }">
						</td>
						<td class="tdError">
							<label class="errorClass" id="loginnameError">${errors.loginname }</label>
						</td>
					</tr>
					<tr>
						<td class="tdText">登录密码：</td>
						<td>
							<input class="inputClass" type="password" name="loginpass" id="loginpass" value="${form.loginpass }">
						</td>
						<td>
							<label class="errorClass" id="loginpassError">${errors.loginpass }</label>
						</td>
					</tr>
					<tr>
						<td class="tdText">确认密码：</td>
						<td>
							<input class="inputClass" type="password" name="reloginpass" id="reloginpass" value="${form.reloginpass }">
						</td>
						<td>
							<label class="errorClass" id="reloginpassError">${errors.reloginpass }</label>
						</td>
					</tr>
					<tr>
						<td class="tdText">Email：</td>
						<td>
							<input class="inputClass" type="text" name="email" id="email" value="${form.email }">
						</td>
						<td>
							<label class="errorClass" id="emailError">${errors.email }</label>
						</td>
					</tr>
					<tr>
						<td class="tdText">验证码：</td>
						<td>
							<input class="inputClass" type="text" name="verifyCode" id="verifyCode" value="${form.verifyCode }">
						</td>
						<td>
							<label class="errorClass" id="verifyCodeError">${errors.verifyCode }</label>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div id="divVerifyCode"><img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/>"/></div>
						</td>
						<td>
							<label><a href="javascript:_hyz()">换一张</a></label>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" name="submit" value="立即注册" id="submitBtn">
						</td>
						<td>
							<label></label>
						</td>
					</tr>
				</table>
				</form>	
			</div>
			</div>
	</div>
	
</div>
</body>
</html>