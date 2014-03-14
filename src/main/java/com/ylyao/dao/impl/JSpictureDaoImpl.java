package com.ylyao.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ylyao.dao.JSpictureDao;
import com.ylyao.model.JSpictureBean;
import com.ylyao.model.PageBean;
import com.ylyao.util.BaseIbatis;
import com.ylyao.util.DateUtil;

public class JSpictureDaoImpl extends BaseIbatis implements JSpictureDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<JSpictureBean> findMiniPicture() {
		// TODO Auto-generated method stub
		List<JSpictureBean> list = getSqlMapClientTemplate()
				.queryForList("JSpictureSQL.getMINI");
		return list;
	}

	@Override
	public Long saveJSpicture(JSpictureBean jsBean) {
		// TODO Auto-generated method stub
		Long id = (Long) getSqlMapClientTemplate().insert("JSpictureSQL.insertJSpicture",jsBean);
		return id;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JSpictureBean> findBigPicture() {
		// TODO Auto-generated method stub
		List<JSpictureBean> list = getSqlMapClientTemplate()
				.queryForList("JSpictureSQL.getBIG");
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageBean> findPageInfo(int pagesize, int page,String user,String level) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		map.put("pagesize", String.valueOf(pagesize));
		map.put("sizes", String.valueOf((page-1)*pagesize));
		map.put("page", String.valueOf(page));
		map.put("user",user);
		map.put("level", level);
		return getSqlMapClientTemplate()
				.queryForList("pageSQL.getPage",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		map.put("ids", DateUtil.getIds(ids));
		if (ids.isEmpty()){
			return new ArrayList<Object>();
		}
		return getSqlMapClientTemplate()
				.queryForList("pageSQL.getIds",map);
	}

	@Override
	public Long savePageBean(PageBean pb) {
		// TODO Auto-generated method stub
		Long id = (Long) getSqlMapClientTemplate().insert("pageSQL.insertPageBean",pb);
		return id;
	}

	@Override
	public void updateJSpicture(JSpictureBean jb) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("JSpictureSQL.updateJSpicture", jb);
	}

	@Override
	public void deleteISpictures(List<Long> delIds) {
		// TODO Auto-generated method stub
		if (delIds == null || delIds.isEmpty()){
			return ;
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("ids",DateUtil.getIds(delIds));
		getSqlMapClientTemplate().delete("JSpictureSQL.delJSpicture", map);
		getSqlMapClientTemplate().delete("JSpictureSQL.deletePageBean", map);
	}
	
}
