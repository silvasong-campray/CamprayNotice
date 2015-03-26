package com.campraynotice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campraynotice.commons.SystemConfig;
import com.campraynotice.dao.SettingDao;
import com.campraynotice.dto.Setting;
import com.campraynotice.service.SettingService;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月25日 下午4:35:39
 * 
 */
@Service
public class SettingServiceImpl implements SettingService{
    
	@Autowired
	private SettingDao settingDao;
	
	public List<Setting> loadAllSettings() {
		// TODO Auto-generated method stub
		return settingDao.LoadAll();
	}

	public Setting getSettingByPropority(String name) {
		// TODO Auto-generated method stub
		return settingDao.findUnique("name",name);
	}

	public void updateSetting(Setting setting) {
		// TODO Auto-generated method stub
		settingDao.update(setting);
	}

	public void cachedData() {
		// TODO Auto-generated method stub
		SystemConfig.setting.clear();
		List<Setting> list = settingDao.LoadAll();
		for(Setting setting : list){
			SystemConfig.setting.put(setting.getName(),setting.getValue());
		}
	}

}
