package com.oracle.oBootS20220603.controller.hk;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.service.jh.JhPaymentDetailServiceU;

@Controller
public class HKInterceptorController {
	
	@Autowired
	private JhPaymentDetailServiceU ps;
	
	
	@RequestMapping("hkWishInterCeptor") // [interceptor] 회원 아이디 존재 여부 검증 => 모델에 넣어서 Interceptor로 가져감 
	public String wishInterceptor(HttpServletRequest request, Model model ) { 
		 System.out.println("wishInterceptor Test Starts...");
		 
		 String id=(String) request.getSession().getAttribute("id");  
		 System.out.println("wishInterceptor id->"+id);

		 // [회원인지] 결과 값은 있다면 1 , 없으면 0
	     int memCnt=ps.memCount(id);
	     System.out.println("memCnt->"+memCnt);
	     
	     Member member=new Member();
	     member.setId(id); // *****************id member에 넣어줘야 작동************************    (변경 가능 - 로그인 아이디 정보) 
	     
	     // [현재날짜>구독날짜]  procedure = 구독 여부 변경  + [그 후에 구독상태 추출] => 메뉴바에 구독상태에 따라 다른 페이지 (변경 가능 - 로그인 아이디 정보)
	     int subSts=0;
	     if(member.getId()==null) {
	    	 subSts=0;
	     } else {
	    	 ps.subSts(member); 
	    	 subSts=member.getSubs();
	     }
	     System.out.println("memCnt->"+memCnt);
	     System.out.println("subSts->"+subSts);
	     
	     // [마이페이지 메뉴에 이름 담기 위함]
	     String name=ps.findName(id);
	     System.out.println("jhMypageU Test Start name->"+name); 
			
	     
	     model.addAttribute("memCnt", memCnt);
	     // JhSampleInterceptorU 에 session에 담기 위한용도 
	     model.addAttribute("id", id); 
	     model.addAttribute("name", name);
	     model.addAttribute("subs", subSts);
	     model.addAttribute("view_direct", "wish");
	     
	     System.out.println("jhInterCeptor Test End");
	     
	     return "uJhInterCeptor";
	}
	
	
	
	@RequestMapping("hkCartInterCeptor") // [interceptor] 회원 아이디 존재 여부 검증 => 모델에 넣어서 Interceptor로 가져감 
	public String cartInterceptor(HttpServletRequest request, Model model ) { 
		 System.out.println("cartInterceptor Test Starts...");
		 
		 String id=(String) request.getSession().getAttribute("id");  
		 System.out.println("cartInterceptor id->"+id);

		 // [회원인지] 결과 값은 있다면 1 , 없으면 0
	     int memCnt=ps.memCount(id);
	     System.out.println("memCnt->"+memCnt);
	     
	     Member member=new Member();
	     member.setId(id); // *****************id member에 넣어줘야 작동************************    (변경 가능 - 로그인 아이디 정보) 
	     
	     // [현재날짜>구독날짜]  procedure = 구독 여부 변경  + [그 후에 구독상태 추출] => 메뉴바에 구독상태에 따라 다른 페이지 (변경 가능 - 로그인 아이디 정보)
	     int subSts=0;
	     if(member.getId()==null) {
	    	 subSts=0;
	     } else {
	    	 ps.subSts(member); 
	    	 subSts=member.getSubs();
	     }
	     System.out.println("memCnt->"+memCnt);
	     System.out.println("subSts->"+subSts);
	     
	     // [마이페이지 메뉴에 이름 담기 위함]
	     String name=ps.findName(id);
	     System.out.println("jhMypageU Test Start name->"+name); 
			
	     
	     model.addAttribute("memCnt", memCnt);
	     // JhSampleInterceptorU 에 session에 담기 위한용도 
	     model.addAttribute("id", id); 
	     model.addAttribute("name", name);
	     model.addAttribute("subs", subSts);
	     model.addAttribute("view_direct", "shoppingcartList");
	     
	     System.out.println("jhInterCeptor Test End");
	     
		 return "uJhInterCeptor";
	}
	
	
	
}
