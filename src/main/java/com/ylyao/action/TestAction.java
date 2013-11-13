package com.ylyao.action;

import com.ylyao.service.TestService;

public class TestAction {
	
	private TestService testService;
	
	public void test(){
		testService.test();
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
}
