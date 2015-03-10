package com.campraynotice.service;

import java.util.List;

import com.campraynotice.dto.Info;
import com.campraynotice.model.DataTableParamter;
import com.campraynotice.model.PagingData;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月9日 下午3:05:05
 * 
 */
public interface InfoService {
	
	PagingData loadPagingData (DataTableParamter dtp);
	
	void createInfo(Info info);
	
	void deleteByIds(Integer [] ids);
	
	Info getInfoById(int id);
	
	void updateInfo(Info info);
    
	List<Info> getInfoList();
}
