package com.campraynotice.model;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月25日 下午5:41:49
 * 
 */
public class SettingModel {
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private String name;
    
    private String value;
    
}
