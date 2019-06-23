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
	#searchtext {
		width: 400px;
		height: 35px;
		border-style:solid;
		border-color: #00008B;
		padding-left:5px;
	}
	a {
		text-transform:none;
		text-decoration:none;
		border-width: 0px;
	} 
	a:hover {
		font-weight: bold;
		border-width: 0px;
	}
	.topl{width: 100%;height: 80px;background: #fff;}
	.topl .logo{width: 300px;height: 80px;margin-left:80px;}
	.center{margin: 0 auto;}
	.fl{float: left;}
	.fr{float: right;}
	.clear{clear:both;}
	.topl .search{margin-right:250px;}
	.sl {padding-top:25px}
	.sr {padding-top:28px;margin-left:5px;}
	#submit{border:none;width: 100px;height: 30px;background: #00008B;color: #fff; font-size: 16px;letter-spacing: 2px;cursor:pointer;border-radius: 2px;}
	#submit:hover{border:1px solid #fff;}
</style>
  </head>
  
  <body>
 	<div class="topl center">
		<div class="logo fl">
			<a href="<c:url value='/index.jsp'/>" target="_blank">
				<img src="<c:url value='/images/logo.png'/>" alt="">
			</a>
		</div>
		<div class="search fr">
		    <form action="<c:url value='/BookServlet'/>" method="get" target="body" id="form1">
		    	<input type="hidden" name="method" value="findByBname"/>
		    	<div class="sl fl"><input type="text" name="bname" id="searchtext"/></div>
		    	<div class="sr fr">
		    		<input type="submit" name="submit" value="搜  索" id="submit">
		    		<a href="<c:url value='/jsps/gj.jsp'/>" style="font-size: 10pt; color: #404040;" target="body">高级搜索</a>
		    	</div>
		    </form>
	    </div>
  	</div>  
  </body>
</html>
