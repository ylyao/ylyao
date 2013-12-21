package com.ylyao.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ylyao.action.LoginAction;

/**
 * 拦截器 拦截没有登录的用户
 * 
 * @author lyvee AbstractInterceptor 抽象类
 */
public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6781900739868655482L;
	
	public static ActionContext ctx;

	public static Map<?, ?> sessionMap = null;
	
	String returnStr = "plaseLogin";
	String user_session_key = "user";// 默认的key

	@SuppressWarnings("unused")
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {

		ctx = actionInvocation.getInvocationContext();
		sessionMap = actionInvocation.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(ServletActionContext.HTTP_REQUEST);
		// not to intercepted on loginAction
		Object action = actionInvocation.getAction();
		if (action instanceof LoginAction) {
			actionInvocation.invoke();
			return null;
		} else {
			Object okk = sessionMap.get(user_session_key);
			if (okk != null) {
				actionInvocation.invoke();
			} else {// no login
				return returnStr;
			}
		}
		return null;
	}

}
