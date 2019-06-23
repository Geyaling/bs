function _change() {
	$("#vCode").attr("src", "/bs/VerifyCodeServlet?" + new Date().getTime());
}