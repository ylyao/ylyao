package com.ylyao.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class DetailPageAction {
	
	private static String HTML_BEGIN = "<html><head></head><body>";
	
	private static String HTML_END = "</body>";
	
	public void detailPage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getSession().getServletContext().getRealPath("")+"";
		File file=new File(path);
	}
	
}
