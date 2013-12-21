package com.ylyao.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import com.ylyao.model.JSpictureBean;
import com.ylyao.model.OrderBean;
import com.ylyao.model.PageBean;
import com.ylyao.service.JSpictureService;
import com.ylyao.service.OrderService;
import com.ylyao.util.BaseAction;
import com.ylyao.util.DwindlePic;

public class OrderServiceImpl extends BaseAction implements OrderService {

	private static final long serialVersionUID = -3085460073502106293L;
	
	private JSpictureService jspictureService;
	
	@Override
	public Long doOrder(OrderBean order, String webPath) {
		// TODO Auto-generated method stub
		if ((order.getType().contains("增加图片") || order.getType().contains("addPic")) && order.getType().contains("[") && order.getType().contains("]")){
			addPic(order,webPath);
		}else if ((order.getType().contains("删除图片") || order.getType().contains("delPic"))&& order.getType().contains("[") && order.getType().contains("]")){
			delPic(order,webPath);
		}else{
			log.info("指令错误!");
		}
		return null;
	}
	
	private void addPic(OrderBean doOrder,String webPath){
		String bigUrl = doOrder.getType().trim();
		int start = bigUrl.indexOf("[");
		int end = bigUrl.indexOf("]");
		bigUrl = bigUrl.substring(start+1, end);
		String value = doOrder.getValue().trim();
		start = value.indexOf("[");
		end = value.indexOf("]");
		String remark = ""; 
		if (start >= 0 && end >= 0){
			remark =  value.substring(start+1, end);
		}
		JSpictureBean jsBean = new JSpictureBean();
		jsBean.setUrl(bigUrl);
		jsBean.setRemark(remark);
		jsBean.setType("PIC");
		jsBean.setUpdateTime(new Date());
		jsBean.setStatus("ACTIVE");
		String miniPath = this.dealMini(bigUrl,webPath);
		if (miniPath != null){
			DwindlePic mypic = new DwindlePic();
			mypic.s_pic(webPath+miniPath, webPath+miniPath.replace(".", "_mini."), "", "", 800,2400, true);
			jsBean.setMiniUrl(miniPath.replace(".", "_mini."));
		}
		Long infoId = jspictureService.saveJSpicture(jsBean);
		PageBean pb = new PageBean();
		pb.setInfoId(infoId);
		pb.setType("PIC");
		pb.setUpdateTime(new Date());
		jspictureService.savePageBean(pb);
	}
	
	private void delPic(OrderBean doOrder,String webPath){
		
	}

	public String dealMini(String urlPath,String webPath) {
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
			FileOutputStream fs = new FileOutputStream(webPath+miniPath);
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
	
	public JSpictureService getJspictureService() {
		return jspictureService;
	}

	public void setJspictureService(JSpictureService jspictureService) {
		this.jspictureService = jspictureService;
	}

}
