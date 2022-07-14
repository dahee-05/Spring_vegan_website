package com.oracle.oBootS20220603.controller.yj;

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

import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.service.yj.YjMemberServiceU;

//유저 컨트롤러
@Controller
public class YjMemberControllerU {
	private static final Logger logger = LoggerFactory.getLogger(YjMemberControllerU.class);
	
	@Autowired
	private YjMemberServiceU ms;
	
	// 로그인 페이지 
	@RequestMapping(value="uYjLoginForm")
	public String uYujinLoginForm(Model model, HttpServletRequest request) {
		System.out.println("uYjLoginForm Start...");
		HttpSession session = request.getSession();
		
		//세션에 로그인된 객체가 저장되어있으면 로그인사용자
		if( session.getAttribute("user") != null) {
		Member user = (Member) session.getAttribute("user");
		
		return "redirect:/";
		}
		// 없으면 로그인 할 사용자 
		else {
			
			return "uYjLoginForm";
		}
		
	}
	//로그인페이지-> 아이디 비번 가지고 이동 
	@PostMapping(value="uYjLogin")
	public String uYjLogin(Member member, Model model, HttpServletRequest request) {
		System.out.println("uYjLogin Start...");
		System.out.println("uYjLogin id->"+member.getId());
		System.out.println("uYjLogin pw->"+member.getPw());
		//존재 : 객체 , 비존재 : null
		
		Member user = ms.getUser(member);
		
		
		//로그인 실패
		if(user == null) {
			model.addAttribute("msg","아이디와 비밀번호를 확인해주세요.");
			return "uYjLoginForm";
			
		//로그인 성공
		} else {
			model.addAttribute("id", user.getId());
			
			//로그인된 유저 객체를 세션에 저장 
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			return "redirect:/";
			
			}
		}
		//로그아웃
	 	@GetMapping(value="yjLogoutU")
	 	public String yjLogoutU(HttpServletRequest request) {
	 		
	 		logger.info("yjLogoutU Start...");
	 		
	 		HttpSession session = request.getSession();
	 		session.invalidate();
	 		
	 		return "redirect:/";
	 		
	 	}
	
	
		//회원가입 폼
		@RequestMapping(value="uYjJoinForm")
		public String uYjJoinForm(Member member, Model model) {
			System.out.println("uYjJoinForm Start...");
			
			
			return "uYjJoinForm";
		}
		
		//id check
		@ResponseBody
		@GetMapping(value="yjIdCheckU")
		public int yjIdCheckU(Member member) {
			System.out.println("YjMemberCotrollerU yjIdCheckU Start...");
			System.out.println("member.getId->"+member.getId());
			int result = ms.idCheck(member); 
			
			return result;
		}
		
		//회원가입
		@PostMapping(value="yjJoinSucU")
		public String uYjJoin(Member member, Model model) {
			System.out.println("yjJoinSucU Start...");
			int result = ms.yjJoinSucU(member);
			
			return "redirect:/uYjLoginForm";
		}
		
		//ajax 호출시 객체로 반환
		@ResponseBody
		@RequestMapping(value="uYjIdFind")
		//화면으로부터 넘어온 파라메터(id)가 담겨있는 멤버
		public String uYjIdFind (Member member, Model model) {
		//디비로 부터 조회해온 사용자 정보(이메일)
		return ms.getEmail(member);
			
		
		}
		
	

	}


