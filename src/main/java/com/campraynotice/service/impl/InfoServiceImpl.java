package com.campraynotice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campraynotice.dao.InfoDao;
import com.campraynotice.dto.Info;
import com.campraynotice.model.DataTableParamter;
import com.campraynotice.model.PagingData;
import com.campraynotice.service.InfoService;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月9日 下午3:04:49
 * 
 */
@Service
public class InfoServiceImpl implements InfoService{
    
	@Autowired
	private InfoDao infoDao;
	
	public PagingData loadPagingData(DataTableParamter dtp) {
		// TODO Auto-generated method stub
		return infoDao.findPage(dtp.getiDisplayStart(), dtp.getiDisplayLength());
	}

	public void createInfo(Info info) {
		// TODO Auto-generated method stub
		infoDao.create(info);
	}

	public void deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		infoDao.deleteAll(ids);
	}

	public Info getInfoById(int id) {
		// TODO Auto-generated method stub
		return infoDao.get(id);
	}

	public void updateInfo(Info info) {
		// TODO Auto-generated method stub
		infoDao.update(info);
	}

	public List<Info> getInfoList() {
		// TODO Auto-generated method stub
		return infoDao.LoadAll();
	}

}
