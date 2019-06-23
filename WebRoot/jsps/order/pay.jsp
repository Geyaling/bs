<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>pay.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/pay.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

<script type="text/javascript">
$(function() {
	$("img").click(function() {
		$("#" + $(this).attr("name")).attr("checked", true);
	});
});
</script>
  </head>
  
  <body>
<div class="divContent">
	<span class="spanPrice">支付金额：</span><span class="price_t">&yen;${order.total }</span>
	<span class="spanOid">编号：${order.oid }</span>
</div>
<form action="<c:url value='/OrderServlet'/>" method="post" id="form1" target="_top">
<input type="hidden" name="method" value="payment"/>
<input type="hidden" name="oid" value="${order.oid }"/>
<div class="divBank">
	<div class="divText">选择网上银行</div>
	<div style="margin-left: 20px;">
	  <div style="margin-bottom: 20px;">
		<input id="ICBC-NET-B2C" type="radio" name="yh" value="ICBC-NET" checked="checked"/>
		<img name="ICBC-NET-B2C" align="middle" src="<c:url value='/bank_img/icbc.bmp'/>"/>
		
		<input id="CMBCHINA-NET-B2C" type="radio" name="yh" value="CMBCHINA-NET"/>
		<img name="CMBCHINA-NET-B2C" align="middle" src="<c:url value='/bank_img/cmb.bmp'/>"/>

		<input id="ABC-NET-B2C" type="radio" name="yh" value="ABC-NET"/>
		<img name="ABC-NET-B2C" align="middle" src="<c:url value='/bank_img/abc.bmp'/>"/>
		
		<input id="CCB-NET-B2C" type="radio" name="yh" value="CCB-NET"/>
		<img name="CCB-NET-B2C" align="middle" src="<c:url value='/bank_img/ccb.bmp'/>"/>
	  </div>	
	  <div style="margin-bottom: 20px;">
		<input id="BCCB-NET-B2C" type="radio" name="yh" value="BCCB-NET"/>
		<img name="BCCB-NET-B2C" align="middle" src="<c:url value='/bank_img/bj.bmp'/>"/>

		<input id="BOCO-NET-B2C" type="radio" name="yh" value="BOCO-NET"/>
		<img name="BOCO-NET-B2C" align="middle" src="<c:url value='/bank_img/bcc.bmp'/>"/>

		<input id="CIB-NET-B2C" type="radio" name="yh" value="CIB-NET"/>
		<img name="CIB-NET-B2C" align="middle" src="<c:url value='/bank_img/cib.bmp'/>"/>

		<input id="NJCB-NET-B2C" type="radio" name="yh" value="NJCB-NET"/>
		<img name="NJCB-NET-B2C" align="middle" src="<c:url value='/bank_img/nanjing.bmp'/>"/>
	  </div>
	  <div style="margin-bottom: 20px;">
		<input id="CMBC-NET-B2C" type="radio" name="yh" value="CMBC-NET"/>
		<img name="CMBC-NET-B2C" align="middle" src="<c:url value='/bank_img/cmbc.bmp'/>"/>

		<input id="CEB-NET-B2C" type="radio" name="yh" value="CEB-NET"/>
		<img name="CEB-NET-B2C" align="middle" src="<c:url value='/bank_img/guangda.bmp'/>"/>

		<input id="BOC-NET-B2C" type="radio" name="yh" value="BOC-NET"/>
		<img name="BOC-NET-B2C" align="middle" src="<c:url value='/bank_img/bc.bmp'/>"/>

		<input id="PINGANBANK-NET" type="radio" name="yh" value="PINGANBANK-NET"/>
		<img name="PINGANBANK-NET" align="middle" src="<c:url value='/bank_img/pingan.bmp'/>"/>
	  </div>
	  <div style="margin-bottom: 20px;">
		<input id="CBHB-NET-B2C" type="radio" name="yh" value="CBHB-NET"/>
		<img name="CBHB-NET-B2C" align="middle" src="<c:url value='/bank_img/bh.bmp'/>"/>

		<input id="HKBEA-NET-B2C" type="radio" name="yh" value="HKBEA-NET"/>
		<img name="HKBEA-NET-B2C" align="middle" src="<c:url value='/bank_img/dy.bmp'/>"/>

		<input id="NBCB-NET-B2C" type="radio" name="yh" value="NBCB-NET"/>
		<img name="NBCB-NET-B2C" align="middle" src="<c:url value='/bank_img/ningbo.bmp'/>"/>

		<input id="ECITIC-NET-B2C" type="radio" name="yh" value="ECITIC-NET"/>
		<img name="ECITIC-NET-B2C" align="middle" src="<c:url value='/bank_img/zx.bmp'/>"/>
	  </div>
	  <div style="margin-bottom: 20px;">
		<input id="SDB-NET" type="radio" name="yh" value="SDB-NET"/>
		<img name="SDB-NET" align="middle" src="<c:url value='/bank_img/sfz.bmp'/>"/>

		<input id="GDB-NET" type="radio" name="yh" value="GDB-NET"/>
		<img name="GDB-NET" align="middle" src="<c:url value='/bank_img/gf.bmp'/>"/>

		<input id="SHB-NET" type="radio" name="yh" value="SHB-NET"/>
		<img name="SHB-NET" align="middle" src="<c:url value='/bank_img/sh.bmp'/>"/>

		<input id="SPDB-NET" type="radio" name="yh" value="SPDB-NET"/>
		<img name="SPDB-NET" align="middle" src="<c:url value='/bank_img/shpd.bmp'/>"/>
	  </div>
	  <div style="margin-bottom: 20px;">
		<input id="POST-NET" type="radio" name="yh" value="POST-NET"/>
		<img name="POST-NET" align="middle" src="<c:url value='/bank_img/post.bmp'/>"/>

		<input id="BJRCB-NET" type="radio" name="yh" value="BJRCB-NET"/>
		<img name="BJRCB-NET" align="middle" src="<c:url value='/bank_img/beijingnongshang.bmp'/>"/>

		<input id="HXB-NET" type="radio" name="yh" value="HXB-NET"/>
		<img name="HXB-NET" align="middle" src="<c:url value='/bank_img/hx.bmp'/>"/>

		<input id="CZ-NET" type="radio" name="yh" value="CZ-NET"/>
		<img name="CZ-NET" align="middle" src="<c:url value='/bank_img/zheshang.bmp'/>"/>
	  </div>
	</div>
	<div style="margin: 40px;">
		<a href="javascript:void $('#form1').submit();" class="linkNext">下一步</a>
	</div>
</div>
</form>
  </body>
</html>
