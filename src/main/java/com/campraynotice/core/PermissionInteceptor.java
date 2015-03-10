package com.campraynotice.core;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.campraynotice.commons.SystemConstants;
import com.campraynotice.dto.TadminUser;

public class PermissionInteceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		TadminUser loginUser= (TadminUser)request.getSession().getAttribute(SystemConstants.LOGINED);
		//If not login
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}else{
			return true;
		}
			
	}

}
