package com.ylyao.util;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseIbatis {

	private SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();

	public final void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
	}

	public final SqlMapClientTemplate getSqlMapClientTemplate() {
		return this.sqlMapClientTemplate;
	}
}
