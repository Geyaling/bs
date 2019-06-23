<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	*{margin: 0;padding: 0;text-decoration: none;list-style-type: none;font-family: Times New Roman;}
	
	img{border:none;}
	a{border:none;text-decoration: none;color:#000;}
	.border{border:1px solid red;}
	.center{margin: 0 auto;}
	.fl{float: left;}
	.fr{float: right;}
	.clear{clear:both;}
	
	
	/*header*/
	.header{width: 100%;height: 40px;background: #fff}
	.header .top{height: 40px;line-height: 40px;}
	.header .top .right ul li{float: left;font-size: 12px;color: #666666;}
	.header .top .right ul li a{display:block;padding:0 8px;font-size: 12px;font-family: Times New Roman;color: #666666;}
	.header .top .right ul li a:hover{color:#111;}
	.header .topd{width: 100%;height: 80px;background: #fff;}
	.header .topd .logo{width: 1130px;height: 80px;}

</style>
  </head>
  
  <body>
	<div class="header">
		<div class="top center">
			<div class="right fr">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.sessionUser }">
							  <li><a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">当当会员登录</a> </li>
							  <li>|&nbsp; </li>
							  <li><a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册当当会员</a></li>	
						</c:when>
						<c:otherwise>
							  <li>当当会员：${sessionScope.sessionUser.loginname}</li>
							  <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							  <li><a href="<c:url value='/CartItemServlet?method=myCart'/>" target="body">我的购物车</a></li>
							  <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							  <li><a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a></li>
							  <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							  <li><a href="<c:url value='/jsps/user/pwd.jsp'/>" target="body">修改密码</a></li>
							  <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							  <li><a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a></li>
							  <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							  <li><a href="#" target="_top">联系我们</a></li>		
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
	</div>
  </body>
</html>
