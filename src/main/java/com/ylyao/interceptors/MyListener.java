package com.ylyao.interceptors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ylyao.action.InitAction;

public class MyListener implements ServletContextListener{
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		ApplicationContext context=new FileSystemXmlApplicationContext("classpath:/spring/spring-*.xml");
		
		InitAction demo=(InitAction)context.getBean("initAction");
		
		try {
			demo.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
