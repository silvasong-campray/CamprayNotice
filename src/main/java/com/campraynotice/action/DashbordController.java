package com.campraynotice.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.campraynotice.commons.CNException;
import com.campraynotice.commons.ConvertTools;
import com.campraynotice.dto.Info;
import com.campraynotice.model.DataTableParamter;
import com.campraynotice.model.PagingData;
import com.campraynotice.service.InfoService;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月9日 下午2:03:55
 * 
 */
@Controller
@RequestMapping(value="dashbord")
public class DashbordController extends BaseController{
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView dashbord(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashbord/dashbord");
		return mav;
	}
	
	@RequestMapping(value="info_list",method=RequestMethod.GET)
	@ResponseBody
	public String list(HttpServletRequest request , DataTableParamter dtp){
		PagingData pagingData = infoService.loadPagingData(dtp);
		Object [] obj = null;
		if(pagingData.getAaData()==null){
			obj = new Object[]{};
			pagingData.setAaData(obj);
		}
		
		return JSON.toJSONString(pagingData);
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("dashbord/add");
		return mav;
	}
	
	@RequestMapping(value="add_info",method=RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request,Info info){
		ModelAndView mav = new ModelAndView();
		info.setCreateTime(System.currentTimeMillis());
		infoService.createInfo(info);
		mav.setViewName("redirect:");
		return mav;
	}
	
	@RequestMapping(value="info_delete/{ids}",method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpServletRequest request,@PathVariable String ids){
		JSONObject resp = new JSONObject();
		try{
			Integer [] id = ConvertTools.stringArr2IntArr(ids.split(","));
			infoService.deleteByIds(id);
			resp.put("status", true);
		}catch(CNException e){
			resp.put("status", false);
		}
		return JSON.toJSONString(resp);
	}
    
	@RequestMapping(value="info_edit",method=RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request ,@RequestParam(value="id") int id){
		ModelAndView mav = new ModelAndView();
		Info info = infoService.getInfoById(id);
		mav.addObject("info",info);
		mav.setViewName("dashbord/edit");
		return mav;
	}
	
	@RequestMapping(value="info_edit",method=RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request,Info info){
		ModelAndView mav = new ModelAndView();
		info.setCreateTime(System.currentTimeMillis());
		infoService.updateInfo(info);
		mav.setViewName("redirect:");
		return mav;
	}
	
}
