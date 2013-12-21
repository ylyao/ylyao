package com.ylyao.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ylyao.model.JSpictureBean;
import com.ylyao.model.MsgInfo;
import com.ylyao.model.PageBean;
import com.ylyao.model.SystemBean;
import com.ylyao.service.JSpictureService;
import com.ylyao.service.SystemService;
import com.ylyao.util.BaseAction;
import com.ylyao.util.DwindlePic;
import com.ylyao.util.SystemContext;

public class JSpictureAction extends BaseAction{
	
	private static final long serialVersionUID = -9059235694531748056L;
	
	private JSpictureService jspictureService;
	
	private SystemService systemService;
	
	private String bigUrl;
	
	private String miniUrl;
	
	private String remark;
	
	private int pagesize;
	
	private int page;

	public void dealJSpicture(){
		JSpictureBean jsBean = new JSpictureBean();
		jsBean.setUrl(bigUrl);
		jsBean.setRemark(remark);
		jsBean.setType("PIC");
		jsBean.setUpdateTime(new Date());
		jsBean.setStatus("ACTIVE");
		String miniPath = this.dealMini(bigUrl);
		if (miniPath != null){
			DwindlePic mypic = new DwindlePic();
			mypic.s_pic(getWebPath()+miniPath, getWebPath()+miniPath.replace(".", "_mini."), "", "", 800,2400, true);
			jsBean.setMiniUrl(miniPath.replace(".", "_mini."));
		}
		Long infoId = jspictureService.saveJSpicture(jsBean);
		PageBean pb = new PageBean();
		pb.setInfoId(infoId);
		pb.setType("PIC");
		pb.setUpdateTime(new Date());
		jspictureService.savePageBean(pb);
		try {
			this.writeJson("true");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String dealMini(String urlPath) {
		int index = urlPath.lastIndexOf(".");
		if (index < 0){
			return null;
		}
		String fex = urlPath.substring(index);
		String miniPath = "pictures\\"+new Date().getTime()+fex;
		URL url;
		try {
			int byteread = 0;
			url = new URL(urlPath);
			URLConnection conn = url.openConnection();// 获得连接
			InputStream inStream = conn.getInputStream();// 生成输入流文件
			FileOutputStream fs = new FileOutputStream(getWebPath()+miniPath);
			byte[] buffer = new byte[30000];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return miniPath;
	}
	
	public void findBigPicture() throws Exception{
		int index = bigUrl.lastIndexOf(".");
		if (index < 0){
			this.writeJson("");
			return ;
		}
		String fex = bigUrl.substring(index);
		String miniPath = "pictures\\temp\\"+new Date().getTime()+fex;
		URL url;
		try {
			int byteread = 0;
			url = new URL(bigUrl);
			URLConnection conn = url.openConnection();// 获得连接
			InputStream inStream = conn.getInputStream();// 生成输入流文件
			FileOutputStream fs = new FileOutputStream(getWebPath()+miniPath);
			byte[] buffer = new byte[30000];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setMsgInfo(getWebPath()+miniPath);
		msgInfo.setUpdateTime(new Date());
		SystemBean sysBean = systemService.findSystemInfo(SystemContext.DEL_TEMP_PIC_TIME);
		Long time = null;
		if (sysBean != null && sysBean.getValue() != null){
			time = msgInfo.getUpdateTime().getTime() + Long.valueOf(sysBean.getValue());
		}else{
			time = msgInfo.getUpdateTime().getTime() + 6000;
		}
		Date passTime = new Date();
		passTime.setTime(time);
		msgInfo.setPassTime(passTime);
		msgInfo.setSendAlready(0);
		msgInfo.setType("tempPic");
		systemService.insertMessage(msgInfo);
		writeJson(miniPath);
	}
	
	public void findAllInfo() throws IOException{
		if (this.getSession().get("user") == null){
			writeJson(new ArrayList<PageBean>(), new String[]{"id","url","remark","miniUrl"}, null);
		}
		List<PageBean> list = jspictureService.findPageInfo(pagesize,page);
		List<Object> result = new ArrayList<Object>();
		List<Long> picList = new ArrayList<Long>();
		for (PageBean pg : list){
			if (pg.getType().equals("PIC")){
				picList.add(pg.getInfoId());
			}
		}
		result = jspictureService.findByIds(picList);
		
		Collections.shuffle(result);
		
		writeJson(result, new String[]{"id","url","remark","miniUrl"}, null);
	}
	
	public void findMini(){
		List<JSpictureBean> list = jspictureService.findMiniPicture();
		try {
			writeJson(list, new String[]{"id","url","remark","miniUrl"}, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findBig(){
		List<JSpictureBean> list = jspictureService.findBigPicture();
		try {
			writeJson(list, new String[]{"url","remark"}, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**   set and get **/
	public JSpictureService getJspictureService() {
		return jspictureService;
	}

	public void setJspictureService(JSpictureService jspictureService) {
		this.jspictureService = jspictureService;
	}

	public String getBigUrl() {
		return bigUrl;
	}

	public void setBigUrl(String bigUrl) {
		this.bigUrl = bigUrl;
	}

	public String getMiniUrl() {
		return miniUrl;
	}

	public void setMiniUrl(String miniUrl) {
		this.miniUrl = miniUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	
}
