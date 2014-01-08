package com.ylyao.service.impl;

import java.util.List;

import com.ylyao.dao.JSpictureDao;
import com.ylyao.model.JSpictureBean;
import com.ylyao.model.PageBean;
import com.ylyao.service.JSpictureService;

public class JSpictureServiceImpl implements JSpictureService {

	private JSpictureDao jspictureDao;
	
	public JSpictureDao getJspictureDao() {
		return jspictureDao;
	}

	public void setJspictureDao(JSpictureDao jspictureDao) {
		this.jspictureDao = jspictureDao;
	}

	@Override
	public List<JSpictureBean> findMiniPicture() {
		// TODO Auto-generated method stub
		return jspictureDao.findMiniPicture();
	}

	@Override
	public Long saveJSpicture(JSpictureBean jsBean) {
		// TODO Auto-generated method stub
		return jspictureDao.saveJSpicture(jsBean);
	}

	@Override
	public List<JSpictureBean> findBigPicture() {
		// TODO Auto-generated method stub
		return jspictureDao.findBigPicture();
	}

	@Override
	public List<PageBean> findPageInfo(int pagesize, int page,String user,String level) {
		// TODO Auto-generated method stub
		return jspictureDao.findPageInfo(pagesize,page,user,level);
	}

	@Override
	public List<Object> findByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return jspictureDao.findByIds(ids);
	}

	@Override
	public Long savePageBean(PageBean pb) {
		// TODO Auto-generated method stub
		return jspictureDao.savePageBean(pb);
	}

	@Override
	public void updateJSpicture(JSpictureBean jb) {
		// TODO Auto-generated method stub
		jspictureDao.updateJSpicture(jb);
	}

	@Override
	public void deleteISpictures(List<Long> delIds) {
		// TODO Auto-generated method stub
		jspictureDao.deleteISpictures(delIds);
	}

}
