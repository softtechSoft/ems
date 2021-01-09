package com.softtech.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class EmsInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception
	{
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		return true;
		HttpSession session=request.getSession();
		if(session.getAttribute("userLoginInfo")!=null)
		{
			response.sendRedirect("/main");
			return true;
		}
		else
		{
			response.sendRedirect("/");
			return true;
		}
	}
}