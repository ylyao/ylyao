package com.ylyao.model;

import java.util.Date;

public class JSpictureBean {
	
	private Long id;
	
	private String type;
	
	private String url;
	
	private String realUrl;
	
	private String remark;
	
	private Date updateTime;
	
	private String status;
	
	private Long bigId;
	
	private String miniUrl;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRealUrl() {
		return realUrl;
	}

	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBigId() {
		return bigId;
	}

	public void setBigId(Long bigId) {
		this.bigId = bigId;
	}

	public String getMiniUrl() {
		return miniUrl;
	}

	public void setMiniUrl(String miniUrl) {
		this.miniUrl = miniUrl;
	}
	
}
