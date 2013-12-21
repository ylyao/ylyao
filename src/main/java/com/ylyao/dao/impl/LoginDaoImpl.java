package com.ylyao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ylyao.dao.LoginDao;
import com.ylyao.model.User;
import com.ylyao.util.BaseIbatis;

public class LoginDaoImpl extends BaseIbatis implements LoginDao {

	@SuppressWarnings("unchecked")
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		List<User> list = getSqlMapClientTemplate()
				.queryForList("LoginSql.login",user);
		if (list == null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public Long insertUserInfo(User user) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate()
				.insert("LoginSql.insertUser",user);
	}

	@Override
	public void activate(String key) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		map.put("key",key);
		getSqlMapClientTemplate().update("LoginSql.activate", map);
	}

}
