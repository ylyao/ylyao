package com.ylyao.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ylyao.dao.TestDao;
import com.ylyao.model.TestBean;
import com.ylyao.model.TreeBean;
import com.ylyao.util.BaseIbatis;

public class TestDaoImpl extends BaseIbatis implements TestDao {

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void test() {
		// TODO Auto-generated method stub
		List<TestBean> list = getSqlMapClientTemplate()
				.queryForList("TestSQL.getAllTestBean");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TreeBean> getTree(Map<String,String> map) {
		// TODO Auto-generated method stub
		List<String> result = getSqlMapClientTemplate().queryForList("TestSQL."+map.get("key"),map);
		List<TreeBean> tbs = new ArrayList<TreeBean>();
		TreeBean tb ;
		for (String str : result){
			tb = new TreeBean();
			tb.setValue(str);
			tbs.add(tb);
		}
		return tbs;
	}

	@Override
	public int getCount(Map<String,String> map) {
		// TODO Auto-generated method stub
		System.out.println("SELECT count(*) from TEST_BEAN WHERE "+map.get("param")+"---------");
		return (Integer) getSqlMapClientTemplate().queryForObject("TestSQL.count", map);
	}

}
