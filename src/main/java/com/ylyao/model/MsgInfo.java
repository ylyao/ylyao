package com.ylyao.model;

import java.util.Date;

public class MsgInfo {
	
	private Long id;
	
	private String msgInfo;
	
	private String type;
	
	private Date updateTime;
	
	private Date passTime;
	
	private int sendAlready;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public int getSendAlready() {
		return sendAlready;
	}

	public void setSendAlready(int sendAlready) {
		this.sendAlready = sendAlready;
	}
	
}
