$(function(){
	//1. 找到所有的错误信息，循环遍历
	$(".errorClass").each(function(){
		showError($(this));
	});
	//2.切换注册按钮的图片
	$("#submitBtn").hover(
			function(){
				$("#submitBtn").attr("src","/bs/images/regist2.jpg");
				
			},
			function(){
				$("#submitBtn").attr("src","/bs/images/regist1.jpg");
			}
	);
	//3.输入框得到焦点隐藏错误信息
	$(".inputClass").focus(function(){
		var labelId = $(this).attr("id")+"Error";
		$("#"+labelId).text("");
		showError($("#"+labelId));
	});
	//4.输入框失去焦点进行校验
	$(".inputClass").blur(function(){
		var id = $(this).attr("id");//获取当前输入框的id
		var funName = "validate" + id.substring(0,1).toUpperCase()+id.substring(1)+"()";
		eval(funName);//把字符串参数当做函数语句执行
	});
	//5.表单提交时进行校验
	$("#registForm").submit(function(){
		var bool = true;//表示校验通过
		if(!validateLoginname()){
			bool = false;
		}
		if(!validateLoginpass()){
			bool = false;
		}
		
		if(!validateReloginpass()){
			bool = false;
		}
		if(!validateEmail()){
			bool = false;
		}
		if(!validateVerifyCode()){
			bool = false;
		}
		return bool;
	});
});

//登录名校验
function validateLoginname(){
	var id = "loginname";
	var value = $("#"+id).val();//获取输入框内容
	//1.非空校验
	if (!value){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("用户名不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	//2.长度校验
	if(value.length < 3 || value.length > 20){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("用户名长度必须在3～20之间！");
		showError($("#"+id+"Error"));
		return false;
	}
	//3.是否注册
	$.ajax({
		url:"/bs/UserServlet",
		data:{method:"ajaxValidateLoginname",loginname:value},
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是，就会不等函数执行成功就向下执行
		cache:false,
		success:function(result){
			if(!result){//校验失败
				$("#"+id+"Error").text("用户名已被注册！");
				showError($("#"+id+"Error"));
				return false;
			}
		}
	});
	return true;
}

//登录密码校验
function validateLoginpass(){
	var id = "loginpass";
	var value = $("#"+id).val();//获取输入框内容
	//1.非空校验
	if (!value){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("密码不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	//2.长度校验
	if(value.length < 3 || value.length > 20){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("密码长度必须在3～20之间！");
		showError($("#"+id+"Error"));
		return false;
	}
	return true;	
}

//确认密码校验
function validateReloginpass(){
	var id = "reloginpass";
	var value = $("#"+id).val();//获取输入框内容
	//1.非空校验
	if (!value){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("确认密码不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	//2.两次输入是否一致校验
	if(value != $("#loginpass").val()){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("两次输入不一致");
		showError($("#"+id+"Error"));
		return false;
	}
	return true;	
}

//Email 校验
function validateEmail(){
	var id = "email";
	var value = $("#"+id).val();//获取输入框内容
	//1.非空校验
	if (!value){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("Email不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	//2.Email格式校验
	if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("Email格式错误");
		showError($("#"+id+"Error"));
		return false;
	}
	//3.是否注册
	$.ajax({
		url:"/bs/UserServlet",
		data:{method:"ajaxValidateEmail",email:value},
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是，就会不等函数执行成功就向下执行
		cache:false,
		success:function(result){
			if(!result){//校验失败
				$("#"+id+"Error").text("Email已被注册！");
				showError($("#"+id+"Error"));
				return false;
			}
		}
	});	
	return true;
}

//验证码校验
function validateVerifyCode(){
	var id = "verifyCode";
	var value = $("#"+id).val();//获取输入框内容
	//1.非空校验
	if (!value){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("验证码不能为空");
		showError($("#"+id+"Error"));
		return false;
	}
	//2.长度校验
	if(value.length !=4){
		//获取对应的label，添加错误信息，再显示
		$("#"+id+"Error").text("验证码错误");
		showError($("#"+id+"Error"));
		return false;
	}
	//3.是否正确
	$.ajax({
		url:"/bs/UserServlet",
		data:{method:"ajaxValidateVerifyCode",verifyCode:value},
		type:"POST",
		dataType:"json",
		async:false,//是否异步请求，如果是，就会不等函数执行成功就向下执行
		cache:false,
		success:function(result){
			if(!result){//校验失败
				$("#"+id+"Error").text("验证码错误！");
				showError($("#"+id+"Error"));
				return false;
			}
		}
	});		
	return true;	
}

//判断当前元素是否存在内容，存在显示
function showError(ele){
	var text = ele.text();//获取元素内容
	if(!text){//如果没有内容，隐藏
		ele.css("display","none");
	}else{//如果有，显示
		ele.css("display","")
	}
}

function _hyz(){
	//1.获取img元素
	//2.重新设置src
	//3.使用毫秒来添加参数
	$("#imgVerifyCode").attr("src","/bs/VerifyCodeServlet?a="+new Date().getTime())
}