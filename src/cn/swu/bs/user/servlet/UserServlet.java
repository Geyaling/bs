package cn.swu.bs.user.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.swu.bs.user.domain.User;
import cn.swu.bs.user.service.UserService;
import cn.swu.bs.user.service.exception.UserException;

/**
 * 用户web层
 */
public class UserServlet extends BaseServlet {
	private UserService userService =new UserService();
	public String updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.封装表单数据到user 中
		//2.从session中获取uid
		//3.使用uid和表单中的oldPass和newPass来调用service方法
		//如果异常，保存异常信息到request中，转发到pwd.jsp\
		//保存成功信息到request中
		//转发到msg.jsp
		User formUser = CommonUtils.toBean(req.getParameterMap(),User.class);
		User user = (User)req.getSession().getAttribute("sessionUser");
		if(user == null) {
			req.setAttribute("msg", "您还没有登录");
			return "f:/jsps/user/login.jsp";
		}
		try {
			userService.updatePassword(user.getUid(),formUser.getNewpass() , formUser.getLoginpass());
			req.setAttribute("msg", "修改密码成功");
			req.setAttribute("code","success");
			return "f:/jsps/msg.jsp";
		} catch (UserException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("user", formUser);//回现
			return "f:/jsps/user/pwd.jsp";
		}
	}
	/*
	 * 注册功能
	 */
	//ajax用户名是否注册
	public String ajaxValidateLoginname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取用户名
		String loginname = req.getParameter("loginname");
		//2.通过service得到校验结果
		boolean b = userService.ajaxValidateLoginname(loginname);
		//3.发给客户端
		resp.getWriter().print(b);
		return null;
	}
	//ajax Email是否注册
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取用户名
		String email = req.getParameter("email");
		//2.通过service得到校验结果
		boolean b = userService.ajaxValidateEmail(email);
		//3.发给客户端
		resp.getWriter().print(b);
		return null;
	}
	//ajax验证码是否正确
	public String ajaxValidateVerifyCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取输入框中验证码
		String verifyCode = req.getParameter("verifyCode");
		//2.获取图片上真实的验证码
		String vcode = (String)req.getSession().getAttribute("vCode");
		//3.进行忽略大小写比较得到结果
		boolean b = verifyCode.equalsIgnoreCase(vcode);
		//4.发给客户端
		resp.getWriter().print(b);
		return null;
	}
	public String regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 封装表单数据到User对象
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		//2. 校验,如果校验失败，保存错误信息，返回到regist.jsp
		Map<String,String> errors = validateRegist(formUser,req.getSession());
		if(errors.size()>0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/jsps/user/regist.jsp";
		}
		
		//3. 使用service完成业务
		userService.regist(formUser);
		//4. 保存成功信息，转发到msg.jsp
		req.setAttribute("code", "success");
		req.setAttribute("msg", "注册成功，请马上到邮箱激活！");
		return "f:/jsps/msg.jsp";
	}
	//注册校验 对表单字段逐个校验 
	//如果有错误，使用当前字段名称为key,错误信息为value,保存在map中，返回map
	private Map<String,String> validateRegist(User formUser,HttpSession session) {
		//错误消息Map
		Map<String,String> errors = new HashMap<String,String>();
		//1.校验登录名
		String loginname = formUser.getLoginname();
		if(loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空");
		}else if(loginname.length()<3||loginname.length()>20) {
			errors.put("loginname","用户名长度必须在3-20之间");
		}else if(!userService.ajaxValidateLoginname(loginname)) {
			errors.put("loginname","用户名已被注册");
		}
		//2.校验密码
		String loginpass = formUser.getLoginpass();
		if(loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空");
		}else if(loginpass.length()<3||loginpass.length()>20) {
			errors.put("loginpass","密码长度必须在3-20之间");
		}
		//3.确认密码
		String reloginpass = formUser.getReloginpass();
		if(reloginpass == null || reloginpass.trim().isEmpty()) {
			errors.put("reloginpass", "确认密码不能为空");
		}else if(!reloginpass.equals(loginpass)) {
			errors.put("reloginpass","两次输入不一致");
		}
		//4.校验Email
		String email = formUser.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空");
		}else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email","Email格式错误");
		}else if(!userService.ajaxValidateEmail(email)) {
			errors.put("email","Email已被注册");
		}
		//5.验证码校验
		String verifyCode = formUser.getVerifyCode();
		String vcode = (String)session.getAttribute("vCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "确认密码不能为空");
		}else if(!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode","验证码错误");
		}	
		return errors;
	}
	public String activation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取激活码
		//2.用激活码调用service的方法完成激活
		//若service方法抛出异常，把异常信息保存到request中，转发到msg.jsp
		//3.保存成功信息到request中，转发到msg.jsp
		String code = req.getParameter("activationCode");
		try {
			userService.activation(code);
			req.setAttribute("code","success");
			req.setAttribute("msg","激活成功，请马上登录！");
		}catch(UserException e) {
			req.setAttribute("msg",e.getMessage());
			req.setAttribute("code","error");
		}
		return "f:/jsps/msg.jsp";
	}
	
	public String quit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		return "r:/jsps/user/login.jsp";
	}
	
	public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.封装表单数据到user
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		
		//2.校验表单数据
		Map<String,String> errors = validateLogin(formUser,req.getSession());
		if(errors.size()>0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/jsps/user/login.jsp";
		}		
		//3.使用service查询，得到user
		User user = userService.login(formUser);
		//4.查看用户是否存在，
		//如果不存在，
			//保存错误信息，用户名或密码错误
			//保存用户数据，回县
			//转发到login.jsp
		//如果存在，
			//查看状态，状态false，
			//保存错误信息，没有激活，
			//保存用户数据，回县
			//转发到login.jsp
		//状态true，登录成功
		//保存当前查询的user到session中
		//查询当前用户名称到cookie中		
		if(user==null) {
			req.setAttribute("msg", "用户名或密码错误");
			req.setAttribute("user", formUser);
			return "f:/jsps/user/login.jsp";
		}else {
			if(!user.isStatus()) {
				req.setAttribute("msg", "您还没有激活");
				req.setAttribute("user", formUser);
				return "f:/jsps/user/login.jsp";
			}else {
				req.getSession().setAttribute("sessionUser", user);
				String loginname = user.getLoginname();
				loginname = URLEncoder.encode(loginname,"utf-8");
				Cookie cookie = new Cookie("loginname",loginname);
				cookie.setMaxAge(60*60*24*10);//10天
				resp.addCookie(cookie);
				return "r:/index.jsp";
			}
		}		
	}
	//登录校验 对表单字段逐个校验 
	//如果有错误，使用当前字段名称为key,错误信息为value,保存在map中，返回map
	private Map<String,String> validateLogin(User formUser,HttpSession session) {
		//错误消息Map
		Map<String,String> errors = new HashMap<String,String>();
		//1.校验登录名
		String loginname = formUser.getLoginname();
		if(loginname == null || loginname.trim().isEmpty()) {
			errors.put("loginname", "用户名不能为空");
		}else if(loginname.length()<3||loginname.length()>20) {
			errors.put("loginname","用户名长度必须在3-20之间");
		}
		//2.校验密码
		String loginpass = formUser.getLoginpass();
		if(loginpass == null || loginpass.trim().isEmpty()) {
			errors.put("loginpass", "密码不能为空");
		}else if(loginpass.length()<3||loginpass.length()>20) {
			errors.put("loginpass","密码长度必须在3-20之间");
		}
		//3.验证码校验
		String verifyCode = formUser.getVerifyCode();
		String vcode = (String)session.getAttribute("vCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "确认密码不能为空");
		}else if(!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode","验证码错误");
		}	
		return errors;
	}	

}
