package com.campraynotice.service;

import java.util.List;

import com.campraynotice.dto.Setting;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月25日 下午4:35:20
 * 
 */
public interface SettingService {
	
	List<Setting> loadAllSettings();
	
	Setting getSettingByPropority(String name);
	
	void updateSetting(Setting setting);

}
