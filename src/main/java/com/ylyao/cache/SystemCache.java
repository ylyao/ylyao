package com.ylyao.cache;

import java.util.HashMap;
import java.util.Map;

public class SystemCache {
	
	private static SystemCache	systemCache = null;
	
	private Map<String,Object> sysMap = new HashMap<String,Object>();
	
	private SystemCache(){

	}
	
	public static SystemCache getInstance(){
		if (systemCache == null){
			return new SystemCache(); 
		}
		return systemCache;
	}
	
	public void setSysMap(String key,Object value){
		sysMap.put(key, value);
	}
	
	public Object getSysMap(String key){
		return sysMap.get(key);
	}
}
