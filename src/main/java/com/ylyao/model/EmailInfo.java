package com.ylyao.model;

import java.util.Date;

public class EmailInfo {
	
	private Long id;
	
	private String emailKey;
	
	private String emailValue;
	
	private Date updateTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailKey() {
		return emailKey;
	}

	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}

	public String getEmailValue() {
		return emailValue;
	}

	public void setEmailValue(String emailValue) {
		this.emailValue = emailValue;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
