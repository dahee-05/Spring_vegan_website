package com.oracle.oBootS20220603.service.hk;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HKLoginInterceptorA implements HandlerInterceptor{
	
	
	//1번 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		System.out.println("pre Handle-----------------------------------");
		
		
		return true;
	}
	
	// 3번
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
    	// 넘어온 파라미터의 인코딩 설정을 UTF-8로 설정
    	request.setCharacterEncoding("utf-8");
    
    	// HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
    	response.setContentType("text/html; charset=utf-8");
    	
		System.out.println("post handle-----------------------------------");
		
		String admin_id = (String) request.getSession().getAttribute("admin_id");

		// 아이디 체크 
		if(admin_id==null || admin_id.equals("")) {
			System.out.println("id Not Exists..");
			response.sendRedirect("aYjLoginForm");
		//User 가 존재하지 않으면 User Interceptor Page(회원등록) 이동 
		}else {
			System.out.println("id Exists");
		}
		
		
	}
		
		

}
