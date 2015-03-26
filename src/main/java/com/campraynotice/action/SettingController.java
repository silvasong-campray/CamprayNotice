package com.campraynotice.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.campraynotice.dto.Setting;
import com.campraynotice.model.SettingModel;
import com.campraynotice.service.SettingService;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月25日 下午4:42:33
 * 
 */
@Controller
@RequestMapping(value="setting")
public class SettingController {
	
	@Autowired
	private SettingService settingService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView setting(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<Setting> list = settingService.loadAllSettings();
		for(Setting setting : list){
			map.put(setting.getName(), setting.getValue());
		}
		mav.addObject("settings", map);
		mav.setViewName("setting/setting");
		return mav;
	   }
	
	@RequestMapping(value="editsetting",method=RequestMethod.POST)
	@ResponseBody
	public String editSetting(HttpServletRequest request,SettingModel settingModel){
		JSONObject resp = new JSONObject();
		try {
			Setting setting = settingService.getSettingByPropority(settingModel.getName());
			if(setting != null){
				setting.setValue(settingModel.getValue());
				settingService.updateSetting(setting);
				resp.put("info", "编辑成功");
				resp.put("status", true);
				settingService.cachedData();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resp.put("info", "编辑失败");
			resp.put("status", false);
		}
		return JSON.toJSONString(resp);
     }

}
