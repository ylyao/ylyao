package com.ylyao.action;

import java.security.MessageDigest;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ylyao.mail.JavaMail;
import com.ylyao.model.EmailInfo;
import com.ylyao.model.SystemBean;
import com.ylyao.model.User;
import com.ylyao.service.LoginService;
import com.ylyao.service.SystemService;
import com.ylyao.util.BaseAction;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 3640058673253395127L;

	private LoginService loginService;
	
	private SystemService systemService;

	private String username;

	private String password;
	
	private String password2;
	
	private String email;
	
	private String key;


	public void login() throws Exception {
		if (username == null || username.trim().equals("")) {
			writeJson("请输入用户名");
			return;
		}
		if (password == null || password.trim().equals("")) {
			writeJson("请输入密码");
			return;
		}
		User user = new User();
		user.setUsername(username);
		user = loginService.login(user);
		if (user == null){
			writeJson("用户名不存在");
			return ;
		}
		if (user.getInfo() != null && user.getInfo().contains("未激活")){
			writeJson("用户未激活");
			return ;
		}
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(password.getBytes());
		password = encodeHex(digest.digest());
		if (user.getPassword().equals(password)) {
			getSession().put("user", user);
			writeJson("true");
		}else{
			writeJson("密码错误！");
		}
	}

	public final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public void applyFor() throws Exception{
		if (username == null || username.trim().equals("")) {
			writeJson("请输入用户名");
			return;
		}
		if (password == null || password.trim().equals("")) {
			writeJson("请输入密码");
			return;
		}
		if (password2 == null || password2.trim().equals("")) {
			writeJson("请确认输入密码");
			return;
		}
		if (!password.equals(password2)) {
			writeJson("密码不一致,请重试！");
			return;
		}
		if (email == null || email.trim().equals("")){
			writeJson("邮箱不能为空！");
			return;
		}
		if (!emailFormat(email)){
			writeJson("邮箱格式有误！");
			return;
		}
		User user = new User();
		user.setUsername(username);
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(password.getBytes());
		password = encodeHex(digest.digest());
		user.setPassword(password);
		user.setUpdateTime(new Date());
		digest.update(String.valueOf(user.getUpdateTime().getTime()).getBytes());
		String key = encodeHex(digest.digest());
		user.setInfo("未激活"+key);
		if (loginService.login(user) != null){
			writeJson("用户名已存在");
			return;
		}
		loginService.insertUserInfo(user);
		JavaMail javaMail = JavaMail.getInstance();
		SystemBean sb = systemService.findSystemInfo("ylyaoUrl");
		String url = "";
		if (sb != null){
			url = sb.getValue();
		}
		url += "/activate.html?key="+key;
		javaMail.doSendHtmlEmail("YLYAO激活邮件", "点击<br/><a href='"+url+"'>激活帐号"+user.getUsername()+"<a><br/>如无法点击则复制一下链接在浏览器中打开<br>"+url, email);
		writeJson("true");
	}
	
	private boolean emailFormat(String email){ 
        boolean tag = true; 
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"; 						
        final Pattern pattern = Pattern.compile(pattern1); 
        final Matcher mat = pattern.matcher(email); 
        if (!mat.find()) { 
            tag = false; 
        } 
        return tag; 
    }
	
	public void getEmailInfo() throws Exception{
		email = email.split("@")[1];
		EmailInfo emailInfo = systemService.findEmailInfo(email);
		if (emailInfo != null){
			writeJson(emailInfo.getEmailValue());
			return ;
		}else{
			writeJson("false");
			return ;
		}
	}
	
	public void activate(){
		try {
			key = "未激活" + key;
			loginService.activate(key);
			writeJson("true");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				writeJson("激活失败，请重新注册！");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

}
