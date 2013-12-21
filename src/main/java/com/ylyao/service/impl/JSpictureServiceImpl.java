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
	public List<PageBean> findPageInfo(int pagesize, int page) {
		// TODO Auto-generated method stub
		return jspictureDao.findPageInfo(pagesize,page);
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

}
