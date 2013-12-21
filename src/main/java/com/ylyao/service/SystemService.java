package com.ylyao.service;

import java.util.List;
import java.util.Map;

import com.ylyao.model.EmailInfo;
import com.ylyao.model.MsgInfo;
import com.ylyao.model.SystemBean;

public interface SystemService {
	
	public List<MsgInfo> findMessageByDate(String type);
	
	public Long insertMessage(MsgInfo megInfo);

	public void msgDealSuccess(Long id);
	
	public SystemBean findSystemInfo(String name);

	public void msgDealFail(Long id);
	
	public EmailInfo findEmailInfo(String name);
	
	public void refreshSysMap(Map<String,Object> map);
	
	public Object getSysMap(String name);

}
