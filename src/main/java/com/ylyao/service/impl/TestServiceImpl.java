package com.ylyao.service.impl;

import com.ylyao.dao.TestDao;
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
	
}
