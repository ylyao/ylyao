package com.ylyao.service;

import com.ylyao.model.User;

public interface LoginService {

	public User login(User user);

	public Long insertUserInfo(User user);

	public void activate(String key);

}
