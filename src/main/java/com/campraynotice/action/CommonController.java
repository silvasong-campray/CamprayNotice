/**   
 * @Title: UserController.java 
 * @Package com.uswop.action 
 * @Description: TODO
 * @author Phills Li    
 * @date Sep 2, 2014 7:25:22 PM 
 * @version V1.0   
 */
package com.campraynotice.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.campraynotice.dto.Info;
import com.campraynotice.service.InfoService;



/**
 * @ClassName: UserController
 * @Description: TODO
 * @author Phills Li
 * @date Sep 2, 2014 7:25:22 PM
 * 
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

	private Logger logger = Logger.getLogger(CommonController.class);
	
	@Autowired
	private InfoService infoService;
	
	@RequestMapping(value="header",method=RequestMethod.GET)
	public ModelAndView header(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("common/header");
		return mav;
	}
	
	@RequestMapping(value="left",method=RequestMethod.GET)
	public ModelAndView left(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();		
		mav.setViewName("common/left");
		return mav;
	}
	
	
	@RequestMapping(value="footer",method=RequestMethod.GET)
	public ModelAndView footer(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("common/footer");
		return mav;
	}
	
	@RequestMapping(value="noRights",method=RequestMethod.GET)
	public ModelAndView noRights(HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("error/errpage");
		return mav;
	}
	
	@RequestMapping(value="index_content",method=RequestMethod.GET)
	public ModelAndView indexContent(HttpServletRequest request){
		Map <Integer,List<String>> infoMap = new LinkedHashMap<Integer, List<String>>();
		List<String> l = null;
		ModelAndView mav=new ModelAndView();
		List<Info> list = infoService.getInfoList();
		for(Info info : list){
			l=new ArrayList<String>();
			l.add(info.getTitle());
			l.add(info.getContent());
			infoMap.put(info.getId(), l);
		}
		mav.addObject("infoMap", infoMap);
		mav.setViewName("common/indexContent");
		return mav;
	}
}
