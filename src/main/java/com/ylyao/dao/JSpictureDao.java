package com.ylyao.dao;

import java.util.List;

import com.ylyao.model.JSpictureBean;
import com.ylyao.model.PageBean;

public interface JSpictureDao {
	
	public List<JSpictureBean> findMiniPicture();

	public Long saveJSpicture(JSpictureBean jsBean);

	public List<JSpictureBean> findBigPicture();

	public List<PageBean> findPageInfo(int pagesize, int page, String user, String level);

	public List<Object> findByIds(List<Long> ids);

	public Long savePageBean(PageBean pb);

	public void updateJSpicture(JSpictureBean jb);

	public void deleteISpictures(List<Long> delIds);
}
