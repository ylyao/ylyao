package com.ylyao.service;

import java.util.List;
import java.util.Map;

import com.ylyao.model.TreeBean;

public interface TestService {

	public void test();

	public List<TreeBean> getTree(Map<String,String> map);

	public int getCount(Map<String, String> map);

}
