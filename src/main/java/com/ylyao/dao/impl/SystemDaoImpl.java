package com.ylyao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ylyao.dao.SystemDao;
import com.ylyao.model.EmailInfo;
import com.ylyao.model.MsgInfo;
import com.ylyao.model.SystemBean;
import com.ylyao.util.BaseIbatis;

public class SystemDaoImpl extends BaseIbatis implements SystemDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MsgInfo> findMessageByDate(String type) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("type", "tempPic");
		return getSqlMapClientTemplate().queryForList("sysSql.findSendMsgInfo", map);
	}

	@Override
	public Long insertMessage(MsgInfo megInfo) {
		// TODO Auto-generated method stub
		return (Long) getSqlMapClientTemplate().insert("sysSql.insertMsgInfo", megInfo);
	}

	@Override
	public void msgDealSuccess(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("id", id);
		getSqlMapClientTemplate().insert("sysSql.updateSuccess", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemBean> findSystemInfo() {
		// TODO Auto-generated method stub
		return (List<SystemBean>) getSqlMapClientTemplate().queryForList("sysSql.findSystemInfo");
	}

	@Override
	public void msgDealFail(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("id", id);
		getSqlMapClientTemplate().insert("sysSql.updateFail", map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmailInfo> findEmailInfo() {
		// TODO Auto-generated method stub
		return (List<EmailInfo>)getSqlMapClientTemplate().queryForList("sysSql.findEmailInfo");
	}

}
