/**   
* @Title: BaseController.java 
* @Package com.uswop.action 
*
* @Description: 积分管理系统
* 
* @date Sep 10, 2014 3:27:05 PM
* @version V1.0   
*/ 
package com.campraynotice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.campraynotice.commons.SystemConstants;
import com.campraynotice.dto.TadminUser;





/** 
 * <p>Spring控制器的基类</p>
 * <此类实现了一些控制器处理类通用的方法，实际的业务控制器可以根据需要继承此类>
 * @ClassName: BaseController 
 * @author Phills Li 
 *  
 */ 
public class BaseController {
	
	public void setAdminSession(HttpServletRequest request , TadminUser user){
		request.getSession().setAttribute(SystemConstants.LOGINED, user);
	}
	
	public void removeAdminSession(HttpSession session){
		
		session.removeAttribute(SystemConstants.LOGINED);
		
	}
	
	
}
