package com.ylyao.dao;

import com.ylyao.model.User;

public interface LoginDao {

	public User login(User user);

	public Long insertUserInfo(User user);

	public void activate(String key);

}
