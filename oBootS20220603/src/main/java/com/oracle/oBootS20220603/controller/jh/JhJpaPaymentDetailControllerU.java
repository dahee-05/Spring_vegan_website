package com.oracle.oBootS20220603.controller.jh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootS20220603.domain.jh.Member;
import com.oracle.oBootS20220603.service.jh.JhJpaPaymentDetailServiceU;

@Controller
public class JhJpaPaymentDetailControllerU {
	private final JhJpaPaymentDetailServiceU js;
	
	@Autowired
	public JhJpaPaymentDetailControllerU(JhJpaPaymentDetailServiceU js) {
		this.js=js;
	}
	// JPA 사용 -----------------------------------------------------------	
	
	// 구독페이지 시작
	
	// 구독전 -> 구독 정보 입력 (구독전0) 
	@RequestMapping(value = "jhSubFormU")
	public String subFormU (HttpServletRequest request,Model model) {
		System.out.println("JhJpaPaymentDetailControllerU subFormU Starts... ");
		String id=(String)request.getSession().getAttribute("ID");
		model.addAttribute("id", id);
		
		return "uJhSubForm";
	}
	
	
	// 구독전-> 입력후 성공 후 
		@RequestMapping(value = "jhSubSucU" )
		public String subSucU(HttpServletRequest request, Member member, Model model) {
			// id, bank, account 담아옴 
			System.out.println("JhJpaPaymentDetailControllerU subSucU Starts...");

			System.out.println("member.getBank() ->"+member.getBank());
			System.out.println("member.getAccount() ->"+member.getAccount());
			System.out.println("member.getid()->"+member.getId());
			
			Member member1=js.updSubInfo(member);
			// 변화된 객체 
			
			System.out.println("JhJpaPaymentDetailControllerU subFormU After member1.getSubs() ->"+member1.getSubs());
			System.out.println("JhJpaPaymentDetailControllerU subFormU After member1.getSub_start() ->"+member1.getSub_start());
			System.out.println("JhJpaPaymentDetailControllerU subFormU After member1.getSub_exp()->"+member1.getSub_exp());
			
			model.addAttribute("member", member1);
			
			return "uJhSubSuc";
		}
		
	// 구독 중-> 구독 정보 열람
		@RequestMapping(value = "jhSubOldU")
		public String subOldU(Member member, HttpServletRequest request, Model model) {
			System.out.println("JhJpaPaymentDetailControllerU subOldU Starts...");
			
			String id=(String)request.getSession().getAttribute("id");
			System.out.println("subOldU id->"+id);
			
			member.setId(id);
			
			member=js.readSubInfo(id);
			
			model.addAttribute("member", member);
			
			return "uJhSubSuc";
		}
		
	 // 구독 중 -> 구독해지 
		@RequestMapping(value = "jhSubCanU")
		public String subCanU(String id, Model model) throws IOException {
			System.out.println("JhJpaPaymentDetailControllerU subCanU Starts...");
			System.out.println("id ->"+id);
			
			int result=js.subCanU(id);
			System.out.println("After JhJpaPaymentDetailControllerU result-> "+result);
			
			model.addAttribute("msg", "구독신청이 해지되었습니다");
			model.addAttribute("url", "jhSubFormU");
			
			return "uMyinfoupdatealert"; // 마이페이지 메인으로 redirect
		}
		

}
