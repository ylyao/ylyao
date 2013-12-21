package com.ylyao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestAction {

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

}
