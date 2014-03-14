package com.ylyao.model;

import java.util.List;

public class TreeBean {
	
	private String value;
	
	private Integer count;
	
	private List<TreeBean> children;
	
	private TreeBean parent;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<TreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<TreeBean> children) {
		this.children = children;
	}

	public TreeBean getParent() {
		return parent;
	}

	public void setParent(TreeBean parent) {
		this.parent = parent;
	}
	
}
