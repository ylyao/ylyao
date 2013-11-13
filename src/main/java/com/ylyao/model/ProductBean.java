package com.ylyao.model;

public class ProductBean {
	
	private Long id;
	
	private String name;
	
	private String descript;
	
	private String attachment;
	
	private String detailPage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(String detailPage) {
		this.detailPage = detailPage;
	}
	
}
