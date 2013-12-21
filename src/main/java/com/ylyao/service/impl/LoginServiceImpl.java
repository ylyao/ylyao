package com.ylyao.service.impl;

import com.ylyao.dao.LoginDao;
import com.ylyao.model.User;
import com.ylyao.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	private LoginDao loginDao;
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return loginDao.login(user);
	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public Long insertUserInfo(User user) {
		// TODO Auto-generated method stub
		return loginDao.insertUserInfo(user);
	}

	@Override
	public void activate(String key) {
		// TODO Auto-generated method stub
		loginDao.activate(key);
	}

}
