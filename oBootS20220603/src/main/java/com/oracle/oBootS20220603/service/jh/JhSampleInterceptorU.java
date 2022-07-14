package com.oracle.oBootS20220603.service.jh;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.oBootS20220603.model.Member;


public class JhSampleInterceptorU implements HandlerInterceptor {
	
	public JhSampleInterceptorU() {
		
	}
	
	// 3번
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
    	// 넘어온 파라미터의 인코딩 설정을 UTF-8로 설정
    	request.setCharacterEncoding("utf-8");
    
    	// HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
    	response.setContentType("text/html; charset=utf-8");
    	
		System.out.println("post handle-----------------------------------");
		String id=(String)modelAndView.getModel().get("id");
		int memCnt=(Integer)modelAndView.getModel().get("memCnt");
		int subs=(Integer)modelAndView.getModel().get("subs");
		String name=(String)modelAndView.getModel().get("name");
		String view_direct = (String)modelAndView.getModel().get("view_direct");
		System.out.println("view_direct->"+view_direct);
		
		System.out.println("JhSampleInterceptorU post handle memCnt: "+memCnt);

		// 아이디 체크 
		if(memCnt < 1) {
			System.out.println("id Not Exists..");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원전용 페이지 입니다');");
			out.println("location.href='jhLoginFormU';"); // 로그인 페이지 *****************************************************************
			out.println("</script>");
			out.flush();
			out.close();
			//User 가 존재하지 않으면 User Interceptor Page(회원등록) 이동 
		}else {
			System.out.println("id Exists");
			
			// 이렇게 2개 세션에 담아주어야 함 
			request.getSession().setAttribute("subs", subs); // subs 에 0이나 1이 담겨있음 (구독전후)
			request.getSession().setAttribute("name", name);
			response.sendRedirect(view_direct);
//			response.sendRedirect("jhMypageU");
		}
	}
	
	//1번 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		System.out.println("pre Handle-----------------------------------");
		HandlerMethod method=(HandlerMethod) handler;
		Method methodObj=method.getMethod();
		System.out.println("Bean: "+method.getBean());
		System.out.println("Method: "+methodObj);
		return true;
		
	}

}