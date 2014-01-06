package com.ylyao.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import com.ylyao.model.JSpictureBean;
import com.ylyao.model.MsgInfo;
import com.ylyao.model.PageBean;
import com.ylyao.model.SystemBean;
import com.ylyao.model.User;
import com.ylyao.service.JSpictureService;
import com.ylyao.service.SystemService;
import com.ylyao.util.BaseAction;
import com.ylyao.util.DwindlePic;
import com.ylyao.util.SystemContext;

public class JSpictureAction extends BaseAction{
	
	private static Integer REMARK_LENGTH = 15;
	
	private static String ERROR_IMG = "img/hasdel.png";

	private static final long serialVersionUID = -9059235694531748056L;
	
	private JSpictureService jspictureService;
	
	private SystemService systemService;
	
	private String bigUrl;
	
	private String miniUrl;
	
	private String remark;
	
	private int pagesize;
	
	private int page;
	
	private Long infoid;
	
	public void dealJSpicture() throws Exception{
		if (this.getSession().get("user") == null){
			writeJson("请先登录！");
			return ;
		}
		User user = (User) getSession().get("user");
		bigUrl.replace("\\n", "");
		String[] urls = bigUrl.split(";");
		JSpictureBean jsBean = null;
		DwindlePic mypic = new DwindlePic();

		int i = 0;
		for (String url : urls){
			if (!url.contains("http:")){
				continue;
			}
			if (!url.contains(".jpg") && !url.contains(".png")){
				continue;
			}
			i++;
			jsBean = new JSpictureBean();
			jsBean.setUrl(url.trim());
			jsBean.setRemark(remark);
			jsBean.setType("PIC");
			jsBean.setUser(user.getUsername());
			jsBean.setUpdateTime(new Date());
			jsBean.setStatus("ACTIVE");
			String miniPath = this.dealMini(url.trim());
			if (miniPath != null){
				mypic.s_pic(getWebPath()+miniPath, getWebPath()+miniPath.replace(".", "_mini."), "", "", 300,8000, true);
				jsBean.setMiniUrl(miniPath.replace(".", "_mini."));
				mypic = null;
			}else{
				try {
					this.writeJson("图片获取失败！");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ;
			}
			File file = new File(getWebPath()+jsBean.getMiniUrl());
	        try {
				BufferedImage sourceImg =ImageIO.read(new FileInputStream(file));
				jsBean.setMiniWidth(String.valueOf(sourceImg.getWidth()));
				jsBean.setMiniHeight(String.valueOf(sourceImg.getHeight()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   

			Long infoId = jspictureService.saveJSpicture(jsBean);
			PageBean pb = new PageBean();
			pb.setInfoId(infoId);
			pb.setType("PIC");
			pb.setUpdateTime(new Date());
			Long id = jspictureService.savePageBean(pb);
			log.info("add "+i+" picture success!"+id);
		}
		try {
			this.writeJson("图片处理成功！"+i);
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
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(60000);
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
			log.info(urlPath+"远程读取失败！");
		}
		return miniPath;
	}
	
	public void findBigPicture() throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		List<Long> ids = new ArrayList<Long>();
		ids.add(infoid);
		List<Object> list = jspictureService.findByIds(ids);
		JSpictureBean js = null;
		if (list != null && !list.isEmpty()){
			js = (JSpictureBean)list.get(0);
			resultMap.put("remark", js.getRemark());
		}else{
			writeJson("未找到图片！");
			return ;
		}
		bigUrl = js.getUrl();
		int index = bigUrl.lastIndexOf(".");
		if (index < 0){
			this.writeJson("未找到图片！");
			return ;
		}
		String fex = bigUrl.substring(index);
		String miniPath = "pictures\\temp\\"+infoid+fex;
		File file = new File(getWebPath()+miniPath);
		if (!file.exists()){
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
		}
		resultMap.put("miniPath", miniPath);
		writeJson(resultMap,null,null);
	}
	
	public void findAllInfo() throws IOException{
		if (this.getSession().get("user") == null){
			writeJson(new ArrayList<PageBean>(), new String[]{"id","url","remark","miniUrl","miniWidth","miniHeight","user"}, null);
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
		
		cutRemark(result);
		
		writeJson(result, new String[]{"id","url","remark","miniUrl","miniWidth","miniHeight","user"}, null);
	}
	
	private void cutRemark(List<Object> result){
		JSpictureBean js = null;
		String temp = null;
		for (Object obj : result){
			js = (JSpictureBean)obj;
			temp = js.getRemark();
			if (temp != null && temp.length() > REMARK_LENGTH){
				temp = temp.substring(0, REMARK_LENGTH-3)+"...";
				js.setRemark(temp);
			}
		}
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
	
	public void resetThis() throws Exception {
		Map<String,String> resultMap = new HashMap<String,String>();
		List<Long> picList = new ArrayList<Long>();
		List<Long> delIds = new ArrayList<Long>();
		JSpictureBean jb = null;
		picList.add(infoid);
		List<Object> result = jspictureService.findByIds(picList);
		if (result == null || result.isEmpty()){
			delIds.add(infoid);
			jspictureService.deleteISpictures(delIds);
			resultMap.put("info", "图片不存在，已删除！");
			resultMap.put("url", ERROR_IMG);
			this.writeJson(resultMap,null,null);
			return ;
		}else{
			jb = (JSpictureBean)result.get(0);
			if (jb.getUrl() == null){
				delIds.add(infoid);
				jspictureService.deleteISpictures(delIds);
				resultMap.put("info", "图片不存在，已删除！");
				resultMap.put("url", ERROR_IMG);
				this.writeJson(resultMap,null,null);
				return ;
			}
			String miniPath = jb.getMiniUrl();
			if (miniPath != null){
				new File(getWebPath()+miniPath).delete();
			}
			miniPath = this.dealMini(jb.getUrl().trim());
			if (miniPath != null) {
				DwindlePic mypic = new DwindlePic();
				mypic = new DwindlePic();
				mypic.s_pic(getWebPath() + miniPath,
						getWebPath() + miniPath.replace(".", "_mini."), "", "",
						300, 800, true);
				jb.setMiniUrl(miniPath.replace(".", "_mini."));
				File file = new File(getWebPath()+jb.getMiniUrl());
		        try {
					BufferedImage sourceImg =ImageIO.read(new FileInputStream(file));
					jb.setMiniWidth(String.valueOf(sourceImg.getWidth()));
					jb.setMiniHeight(String.valueOf(sourceImg.getHeight()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				jspictureService.updateJSpicture(jb);
				log.info("文件重新加载成功："+jb.getMiniUrl());
				resultMap.put("info", "图片加载成功!");
				resultMap.put("url", jb.getMiniUrl());
				this.writeJson(resultMap,null,null);
				mypic = null;
			} else {
				delIds.add(jb.getId());
				jspictureService.deleteISpictures(delIds);
				resultMap.put("info", "图片不存在，已删除！");
				resultMap.put("url", ERROR_IMG);
				this.writeJson(resultMap,null,null);
				return ;
			}
		}

	}
	
	public void resetAllPic() throws Exception{
		File file = null;
		List<PageBean> list = jspictureService.findPageInfo(10000, 1);
		List<Object> result = new ArrayList<Object>();
		List<Long> picList = new ArrayList<Long>();
		List<Long> delIds = new ArrayList<Long>();
		Map<Long,Long> picMap = new HashMap<Long,Long>();
		for (PageBean pg : list){
			if (pg.getType().equals("PIC")){
				picList.add(pg.getInfoId());
				picMap.put(pg.getInfoId(), pg.getInfoId());
			}
		}
		result = jspictureService.findByIds(picList);
		JSpictureBean jb = null;
		String miniPath = "";
		DwindlePic mypic = null;
		Map<String,String> map = new HashMap<String,String>();
		for (Object obj : result){
			jb = (JSpictureBean)obj;
			picMap.remove(jb.getId());
			if (jb.getUrl() == null){
				delIds.add(jb.getId());
				continue;
			}
			miniUrl = jb.getMiniUrl();
			file = new File(getWebPath() + miniUrl);
			if (!file.exists()) {
				log.info("文件："+miniUrl+"不存在，重新加载！");
				miniPath = this.dealMini(jb.getUrl().trim());
				if (miniPath != null) {
					mypic = new DwindlePic();
					mypic.s_pic(getWebPath() + miniPath, getWebPath()
							+ miniPath.replace(".", "_mini."), "", "", 400,
							1200, true);
					jb.setMiniUrl(miniPath.replace(".", "_mini."));
				} else {
					delIds.add(jb.getId());
					log.info("文件重新加载失败："+jb.getMiniUrl());
					continue;
				}
				mypic = null;
				jspictureService.updateJSpicture(jb);
				log.info("文件重新加载成功："+jb.getMiniUrl());
				map.put(new File(getWebPath()+jb.getMiniUrl()).getName(), jb.getMiniUrl());
			}else{
				map.put(file.getName(),file.getName());
			}
		}
		if (picMap.size() > 0){
			Set<Long> keySets = picMap.keySet();
			for (long key : keySets) {
				delIds.add(key);
				log.info("清楚数据："+key);
			}
		}
		jspictureService.deleteISpictures(delIds);
		file = new File(getWebPath()+"pictures");
		String[] files = file.list();
		boolean suc = false;
		for (String fileName : files){
			if (fileName.equals("temp")){
				continue;
			}
			if (map.get(fileName) == null){
				suc = (new File(getWebPath()+"pictures\\"+fileName)).delete();
				log.info("删除文件"+fileName+" "+suc);
			}
		}
		writeJson("重置成功！");
	}
	
	public void delThis() throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		List<Long> delIds = new ArrayList<Long>();
		delIds.add(infoid);
		jspictureService.deleteISpictures(delIds);
		jspictureService.deleteISpictures(delIds);
		resultMap.put("info", "删除成功!");
		resultMap.put("url", ERROR_IMG);
		this.writeJson(resultMap,null,null);
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

	public Long getInfoid() {
		return infoid;
	}

	public void setInfoid(Long infoid) {
		this.infoid = infoid;
	}
	
}
