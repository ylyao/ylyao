package com.ylyao.service.impl;

import java.util.List;
import java.util.Map;

import com.ylyao.dao.TestDao;
import com.ylyao.model.TreeBean;
import com.ylyao.service.TestService;

public class TestServiceImpl implements TestService {

	private TestDao testDao;
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		testDao.test();
	}

	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public List<TreeBean> getTree(Map<String,String> map) {
		// TODO Auto-generated method stub
		return testDao.getTree(map);
	}

	@Override
	public int getCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return testDao.getCount(map);
	}
	
}
