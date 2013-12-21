package com.ylyao.dao;

import java.util.List;

import com.ylyao.model.EmailInfo;
import com.ylyao.model.MsgInfo;
import com.ylyao.model.SystemBean;

public interface SystemDao {
	
	public List<MsgInfo> findMessageByDate(String type);
	
	public Long insertMessage(MsgInfo megInfo);

	public void msgDealSuccess(Long id);
	
	public List<SystemBean> findSystemInfo();

	public void msgDealFail(Long id);

	public List<EmailInfo> findEmailInfo();
}
