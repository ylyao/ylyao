package com.ylyao.dao.impl;

import java.util.List;

import com.ylyao.dao.TestDao;
import com.ylyao.model.TestBean;
import com.ylyao.util.BaseIbatis;

public class TestDaoImpl extends BaseIbatis implements TestDao {

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void test() {
		// TODO Auto-generated method stub
		List<TestBean> list = getSqlMapClientTemplate()
				.queryForList("TestSQL.getAllTestBean");
	}

}
