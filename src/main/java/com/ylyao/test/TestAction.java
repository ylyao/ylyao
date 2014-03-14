package com.ylyao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ylyao.model.TreeBean;
import com.ylyao.service.TestService;
import com.ylyao.util.BaseAction;

public class TestAction extends BaseAction{

	private static final long serialVersionUID = 1088018852190845254L;

	private TestService testService;
	
	private String params;
	
	public void loadTree(){
		String[] strs = params.split(",");
		TreeBean root = new TreeBean();
		getTreeChild(root,0,strs);
		try {
			this.writeJson("图片处理成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getTreeChild(TreeBean root,int i,String[] strs){
		Map<String,String> map = new HashMap<String,String>();
		if (root.getChildren() == null){
			if (root.getValue() == null){
				map.put("param", "1=1");
			}else{
				map.put("param", strs[i]+"='"+root.getValue()+"'");
			}
			map.put("key", strs[i]);
			List<TreeBean> tbs = testService.getTree(map);
			root.setChildren(tbs);
			getTreeChild(root,i+1,strs);
		}else{
			for (TreeBean tb : root.getChildren()){
				getTreeChild(tb,i+1,strs);
			}
		}
	}
	
	public static void test() {
		URL url = null;
		String str = "";
		StringBuffer result = new StringBuffer();
		BufferedReader r = null;
		String urlPath = "http://www.kuaipan.cn/file/id_50864958384111627.htm";
		try {
			url = new URL(urlPath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			URLConnection con = url.openConnection();
			r = new BufferedReader(new InputStreamReader(con.getInputStream(),
					"UTF-8"));
			do {
				try {
					result.append(str);
					str = r.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (str != null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String html = result.toString();
		int index = html.indexOf("查看原图");
		html = html.substring(0, index);
		int href = html.lastIndexOf("href=");
		html = html.substring(href);
		int f1 = html.indexOf("\"");
		html = html.substring(f1+1);
		int f2 = html.indexOf("\"");
		html = html.substring(0, f2);
		System.out.println(html);
	}

	public static void main(String[] args) {
		TestAction.test();
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
