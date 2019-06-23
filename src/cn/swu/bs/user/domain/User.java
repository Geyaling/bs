package cn.swu.bs.user.domain;
//实体类
/*
 * *
 * 1.t_user表
 * 2.该模块所有表单
 * 
 * 1将表中查询出的封装到类user中。
 * 2验证码数据库中没有，
 * 3确认密码
 * 4新密码
 */
public class User {
	//对应数据库
	private String uid;//主键
	private String loginname;//登录名
	private String loginpass;//登陆密码
	private String email;//邮箱
	private boolean status;//状态是否激活
	private String activationCode;//激活码
	//对应表单
	private String reloginpass;
	private String verifyCode;
	
	//修改密码表单
	private String newpass;
	public String getReloginpass() {
		return reloginpass;
	}
	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", loginpass=" + loginpass + ", email=" + email
				+ ", status=" + status + ", activationCode=" + activationCode + ", reloginpass=" + reloginpass
				+ ", verifyCode=" + verifyCode + ", newpass=" + newpass + "]";
	}

}
