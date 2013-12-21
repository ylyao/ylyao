package com.ylyao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ylyao.dao.SystemDao;
import com.ylyao.model.EmailInfo;
import com.ylyao.model.MsgInfo;
import com.ylyao.model.SystemBean;
import com.ylyao.service.SystemService;

public class SystemServiceImpl implements SystemService{

	private Map<String,SystemBean> map;
	
	private Map<String,EmailInfo> emailMap;
	
	private Map<String,Object> sysMap = new HashMap<String,Object>();

	private SystemDao systemDao;
	
	public SystemDao getSystemDao() {
		return systemDao;
	}

	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}

	@Override
	public List<MsgInfo> findMessageByDate(String type) {
		// TODO Auto-generated method stub
		return systemDao.findMessageByDate(type);
	}

	@Override
	public Long insertMessage(MsgInfo megInfo) {
		// TODO Auto-generated method stub
		return systemDao.insertMessage(megInfo);
	}

	@Override
	public void msgDealSuccess(Long id) {
		// TODO Auto-generated method stub
		systemDao.msgDealSuccess(id);
	}

	@Override
	public SystemBean findSystemInfo(String name) {
		// TODO Auto-generated method stub
		if (map == null){
			map = new HashMap<String,SystemBean>();
			List<SystemBean> sysBeans = systemDao.findSystemInfo();
			for (SystemBean sys : sysBeans){
				map.put(sys.getName(), sys);
			}
		}
		return map.get(name);
	}

	@Override
	public void msgDealFail(Long id) {
		// TODO Auto-generated method stub
		systemDao.msgDealFail(id);
	}

	@Override
	public EmailInfo findEmailInfo(String name) {
		// TODO Auto-generated method stub
		if (emailMap == null){
			emailMap = new HashMap<String,EmailInfo>();
			List<EmailInfo> emailBeans = systemDao.findEmailInfo();
			for (EmailInfo sys : emailBeans){
				emailMap.put(sys.getEmailKey(), sys);
			}
		}
		return emailMap.get(name);
	}
	
	public void refreshSysMap(Map<String,Object> map){
		Set<String> set = map.keySet();
		for (String key : set){
			sysMap.put(key, map.get(key));
		}
	}
	
	public Object getSysMap(String name){
		return sysMap.get(name);
	}
	
}
