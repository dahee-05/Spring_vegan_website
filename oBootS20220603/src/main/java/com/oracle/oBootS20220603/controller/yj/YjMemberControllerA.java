package com.oracle.oBootS20220603.controller.yj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootS20220603.model.Admin;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Paging;
import com.oracle.oBootS20220603.service.yj.YjMemberServiceA;

//관리자 컨트롤러
@Controller
public class YjMemberControllerA {
	private static final Logger logger = LoggerFactory.getLogger(YjMemberControllerU.class);
	
	@Autowired
	private YjMemberServiceA msa;
	
	
	// 로그인 페이지 
	@RequestMapping(value="aYjLoginForm")
	public String aYjLoginForm() {
		System.out.println("YjMemberControllerA aYjLoginForm Start...");
		return "aYjLoginForm";
	}
	
	//로그인페이지-> 아이디 비번 가지고 이동 
	@PostMapping(value="aYjLogin")
	public String aYjLogin(Admin admin, Model model, HttpServletRequest request) {
		System.out.println("YjMemberControllerA aYjLogin Start...");
		System.out.println("YjMemberControllerA aYjLogin id->"+admin.getAd_id());
		System.out.println("YjMemberControllerA aYjLogin pw->"+admin.getAd_pw());
		//존재 : 객체 , 비존재 : null
		
		Admin user2 = msa.getUser(admin);
		
		
		//로그인 실패
		if(user2 == null) {
			model.addAttribute("msg","아이디와 비밀번호를 확인해주세요.");
			return "aYjLoginForm";
			
		//로그인 성공
		} else {
			//로그인된 유저 객체를 세션에 저장 
			HttpSession session = request.getSession();
			session.setAttribute("admin_id", user2.getAd_id());
			
			return "redirect:/admin";
			
		}
		
	}
	
	//로그아웃
 	@GetMapping(value="yjLogoutA")
 	public String yjLogoutA(HttpServletRequest request) {
 		
 		logger.info("yjLogoutA Start...");
 		
 		HttpSession session = request.getSession();
 		session.invalidate();
 		
 		return "redirect:/aYjLoginForm";
 		
 	}
	

	
	//회원관리 목록(+마일리지 수정 가능하도록 ?)
	@RequestMapping(value="aYjMemberList")
	public String memberList(Member member, String currentPage, Model model, HttpServletRequest request) {
		System.out.println("YjMemberControllerU Start...");

		String admin_id = (String) request.getSession().getAttribute("admin_id");
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			//paging
			int total = msa.total(); //글 전체 개수 가져오기
			System.out.println("YjReviewControllerA total=>"+total);
			
			Paging pg = new Paging(total, currentPage);
			System.out.println("total->"+pg.getTotal());
			System.out.println("currentPage->"+pg.getCurrentPage());
			System.out.println("endPage->"+pg.getEndPage());
			
			member.setStart(pg.getStart());
			System.out.println("member.getStart()->"+member.getStart());
			member.setEnd(pg.getEnd());
			System.out.println("member.getEnd()->"+member.getEnd());
			
			//회원목록 
			List<Member> listMember = msa.listMember(member);
			System.out.println("YjMemberControllerA list listMember.size()->"+ listMember.size());
			model.addAttribute("listMember", listMember);
			model.addAttribute("pg",pg);// 이걸 빼먹어서 오류...ㅠㅠ
			model.addAttribute("total", total);
			model.addAttribute("menu_num", 5);
			
			
			return "aYjMemberList";
		}
		
	}
	
	//회원 마일리지 수정
	@ResponseBody
	@GetMapping(value="aYjmilEdit")
	public int aYjmilEdit (Member member, Model model) {
		System.out.println("YjMemberControllerU aYjmilEdit Start...");
		
		int result = msa.aYjmilEdit(member);
		
//		System.out.println("YjMemberControllerU result->"+result);
//		//수정성공
//		if(result>0) 
//			return "redirect:/aYjMemberList";
//		else {
//			model.addAttribute("msg", "수정실패");
//			return "redirect:/aYjMemberList";
//		}
		
		return result;
		
	
	}
	
	
	
	
}

