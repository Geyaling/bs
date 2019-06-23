<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>登录</title>
		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="content-type" content="text/html;charset=utf-8">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		-->
		<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/login.css'/>">
		<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
		<script src="<c:url value='/jsps/js/user/login.js'/>"></script>
		<script>
			$(function(){
				var loginname = window.decodeURI("${cookie.loginname.value}");
				if("${requestScope.user.loginname}"){
					loginname = "${requestScope.user.loginname}";
				}
				$("#loginname").val(loginname);
			});
		</script>
	</head>
	<body>
		<!-- login -->
		<div class="top center">
			<div class="logo center">
				<a href="<c:url value='/index.jsp'/>" target="_blank"><img src="<c:url value='/images/logo.png'/>" alt=""></a>
			</div>
		</div>
		<div class="loginbox center">
			<div class="login">
				<div class="login_center">
					<div class="login_top">
						<div class="left fl">会员登录</div>
						<div class="right fr">您还不是我们的会员？<a href="<c:url value='/jsps/user/regist.jsp'/>">立即注册</a></div>
						<div class="clear"></div>
					</div>
					<div class="xian center"></div>
					<div class="itemtop right fr"><label class="errorClass" id="msg">${msg}</label></div>
				    <div class="login_main center">
				      <form target="_top" action="<c:url value='/UserServlet'/>" method="post" id="loginForm">
				      <input type="hidden" name="method" value="login" />
			          <table>
			            <tr class="item">
			              <td width="50">用户名:&nbsp;</td>
			              <td><input class="input" type="text" name="loginname" id="loginname"/></td>
			            </tr>
			            <tr>
			              <td height="20">&nbsp;</td>
			              <td><label class="errorClass" id="loginnameError">${errors.loginname }</label></td>
			            </tr>
			            <tr class="item">
			              <td>密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</td>
			              <td><input class="input" type="password" name="loginpass" id="loginpass"  value="${user.loginpass }"/></td>
			            </tr>
			            <tr>
			              <td height="20">&nbsp;</td>
			              <td><label class="errorClass" id="loginpassError">${errors.loginpass }</label></td>
			            </tr>
			            <tr>
			              <td  class="item">验证码:&nbsp;</td>
			              <td>
			                <input class="input" type="text" name="verifyCode" id="verifyCode" value="${user.verifyCode }"/>
			                <img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/>"/>
			                <a href="javascript:_hyz()">换一张</a>
			              </td>
			            </tr>
			            <tr>
			              <td height="20px">&nbsp;</td>
			              <td><label class="errorClass" id="verifyCodeError">${errors.verifyCode }</label></td>
			            </tr>
						<tr class="login_submit">
							<td colspan="2"><input type="submit" name="submit" value="立即登录" id="submit"></td>
						</tr>																				
			         </table>
				     </form>
				    </div>
				</div>
			</div>
		</div>
		<footer>
			<div class="copyright">简体 | 繁体 | English | 常见问题</div>
			<div class="copyright">当当商城版权所有-京ICP备10046444-<img src="<c:url value="/images/ghs.png"/>" alt="">京公网安备11010802020134号-京ICP证110507号</div>

		</footer>
	</body>
</html>