package com.oracle.oBootS20220603.controller.jh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;
import com.oracle.oBootS20220603.service.jh.JhPaymentDetailServiceU;
import com.oracle.oBootS20220603.service.Paging;

@Controller
public class JhPaymentDetailControllerU {
	private static final Logger logger = LoggerFactory.getLogger(JhPaymentDetailControllerA.class);
	
	@Autowired
	private JhPaymentDetailServiceU ps;
	
	@Autowired
	private JavaMailSender mailSender;
	
	// 공통 프레임 -----------------------------------------------------------
	
	
	// 아이디 체크 시작 + 구독 정보-------------------------------------------------------
	@RequestMapping("jhInterCeptor") // [interceptor] 회원 아이디 존재 여부 검증 => 모델에 넣어서 Interceptor로 가져감 
	public String mypage(HttpServletRequest request, Model model ) { 
		 System.out.println("jhInterCeptor Test Starts...");
		 
		 String id=(String) request.getSession().getAttribute("id");  
		 System.out.println("jhInterCeptor id->"+id);

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
	     model.addAttribute("view_direct", "jhMypageU");
	     
	     System.out.println("jhInterCeptor Test End");
	     
		return "uJhInterCeptor";
	}
	
	
	 // [interceptor_회원인경우] 마이페이지 대시보드 화면 (아이디당 마일리지 정보 등 가져옴) 
	@RequestMapping("jhMypageU")
	public String mypageU(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String id=(String) request.getSession().getAttribute("id");
		int subs=(int) request.getSession().getAttribute("subs");
		String name=(String)request.getSession().getAttribute("name");
		
		System.out.println("jhMypageU Test Start id->"+id);
		System.out.println("jhMypageU Test Start subs->"+subs);
		System.out.println("jhMypageU Test Start name->"+name);
	
		// 1. 결제완료
		System.out.println("selCount Starts...");
		int selCount=ps.selCount(id);
		System.out.println("selCount->"+selCount);
		
		// 2. 취소완료
		System.out.println("canCount Starts...");
		int canCount=ps.canCount(id);
		System.out.println("canCount->"+canCount);
		
		// 3. 환불진행중
		System.out.println("refingCount Starts...");
		int refingCount=ps.refingCount(id);
		System.out.println("refingCount->"+refingCount);
		
		// 4. 환불완료
		System.out.println("refCount Starts...");
		int refCount=ps.refCount(id);
		System.out.println("refCount->"+refCount);
		
		// 5. 배송완료
		System.out.println("delCount Starts...");
		int delCount=ps.delCount(id);
		System.out.println("delCount->"+delCount);
		
		// 6. 구매확정 
		System.out.println("selFixCount Starts...");
		int selFixCount=ps.selFixCount(id);
		System.out.println("selFixCount->"+selFixCount);
		
		// 사용자 쿠폰 테이블 쿠폰 개수 
		System.out.println("coupCount Starts...");
		int coupCount=ps.coupCount(id);
		System.out.println("coupCount->"+coupCount);
		
		// 회원 테이블 마일리지 
		System.out.println("selCount Starts...");
		int mileTot=ps.mileTot(id);
		System.out.println("mileTot->"+mileTot);
		
		
		// session에 담긴 값들. mypage에 넘겨 두었고 -> 이게 mySnb에 적용되서 계속해서 보임  (세션에 담기면 없어져도 될듯)
		model.addAttribute("id", id); 
		model.addAttribute("name", name); // 마이페이지 ~님  myPageSnb
		model.addAttribute("subs", subs); // 마이페이지 구독 여부에 따라 다른 페이지 myPageSnb
		
		// 마이페이지 메인에 띄울 화면
		model.addAttribute("selCount", 		selCount);
		model.addAttribute("canCount", 		canCount);
		model.addAttribute("refingCount", 	refingCount);
		model.addAttribute("refCount", 		refCount);
		model.addAttribute("delCount", 		delCount);
		model.addAttribute("selFixCount", 	selFixCount);
		model.addAttribute("coupCount", 	coupCount);
		model.addAttribute("mileTot", 		mileTot);
		
		return "mypage";
	}
	
	// 아이디 체크 끝-----------------------------------------------------------
	
	
	
	
	
	
	// 마이페이지 구매내역 시작 ---------------------------------------------------
	// 1-1. 마이페이지 구매내역 (id를 세션에 담아옴_계속 쓸 수 있음 ) 테스트용 아이디 testuser 가지고 있음
	@RequestMapping("jhMyOrderListU") 
	public String myOrderListU(HttpServletRequest request, String currentPage, Model model) {
		String id=(String) request.getSession().getAttribute("id"); // (id를 세션에 담아옴_계속 쓸 수 있음 )
		System.out.println("jhMyOrderListU Test id->"+id);
		Payment payment=new Payment();
		
		// 아이디 별로 구매 내역(payno)에 따른 status (payment 테이블) 
		// 아이디 별로 구매 내역(payno)에 따른 product info (payment 테이블) 
		List<Payment> orderList=ps.orderList(id);
		int totCnt=orderList.size(); // 페이징 작업
		System.out.println("totCnt->"+totCnt);
		
		// 페이징
		Paging pg=new Paging(totCnt, currentPage);
		payment.setStart(pg.getStart());
		System.out.println("payment.getStart()->"+payment.getStart());
		payment.setEnd(pg.getEnd());
		System.out.println("payment.getEnd()->"+payment.getEnd());
		payment.setId(id);
		
		// 이름 가져오기 
		String name=ps.findName(id);
		System.out.println("jhMypageU Test Start name->"+name);
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("id", id); // 추후에 생략 가능 (session id 값 저장되었을 시)
		model.addAttribute("payment", payment);
		model.addAttribute("name", name);
		model.addAttribute("totCnt", totCnt);

		return "uJhMyOrderList";
	}
	
	
	// 1-2. 구매 상세 보기 
	@RequestMapping("jhMyOrderDetailU")
	public String myOrderDetailU(int payno, Model model) {
		logger.info("myOrderDetailU Starts...");
		
		System.out.println("myOrderDetailU payno->"+payno);
		
		Payment detailListC=ps.detailListC(payno); // 상품 상세 정보 (공통) _ 배송지 등 
		System.out.println("JhPaymentDetailControllerU detailListC After"+detailListC.getDel_address());
		
		CouponMaster dcAmount=ps.dcAmount(payno); // 상품 상세 정보 (공통) _ 쿠폰할인율
		System.out.println("JhPaymentDetailControllerU dcAmount After"+dcAmount.getCoup_dc_rate());
		
		List<Payment> detailList=ps.myOrderDetailU(payno); // 상품 상세 정보 
		System.out.println("JhPaymentDetailControllerU myOrderDetailU After "+detailList.get(0).getProd_name());
		
		model.addAttribute("dlistC", detailListC);
		model.addAttribute("dcAmount", dcAmount);
		model.addAttribute("dlist", detailList);
		model.addAttribute("payno", payno);
		
		return "uJhMyOrderDetail";
	}
	
	
	// 2. [아작스] 구매완료 -> 취소 신청 변경 (상태 들고 와야함) 
	@ResponseBody
	@RequestMapping(value = "jhChSelCanU", produces = "application/text;charset=UTF-8")
	public String chSelCanU(Payment payment) {
		logger.info("JhPaymentDetailControllerU chSelCanU ajax Starts...");
		System.out.println("JhPaymentDetailControllerU chSelCanU payment.getPayno()->"+payment.getPayno());
		
		ps.chSelCanU(payment);
		System.out.println("JhPaymentDetailControllerU chSelCanU payment.getPay_sts()->"+payment.getPay_sts());
		String pay_sts=Integer.toString(payment.getPay_sts());
		
		return pay_sts;
	}
	
	
	// 3. [아작스] 구매완료/배송완료 -> 구매 확정 변경 (상태 들고 와야함)
	@ResponseBody
	@RequestMapping(value = "jhChSelFixU", produces = "application/text;charset=UTF-8")
	public String chSelFixU(Payment payment) {
		logger.info("JhPaymentDetailControllerU chSelDelFixU ajax Starts...");
		System.out.println("JhPaymentDetailControllerU chSelDelFixU payment.getPayno()->"+payment.getPayno());
		
		ps.chSelFixU(payment);
		System.out.println("JhPaymentDetailControllerU chSelDelFixU payment.getPay_sts()->"+payment.getPay_sts());
		String pay_sts=Integer.toString(payment.getPay_sts());
		
		return pay_sts;
	}
	
	// 4. [아작스] 배송완료 -> 구매 확정 변경 (상태 들고 와야함)
	@ResponseBody
	@RequestMapping(value = "jhChDelFixU", produces = "application/text;charset=UTF-8")
	public String chDelFixU(Payment payment) {
		logger.info("JhPaymentDetailControllerU chDelFixU ajax Starts...");
		System.out.println("JhPaymentDetailControllerU chDelFixU payment.getPayno()->"+payment.getPayno());
		
		ps.chDelFixU(payment);
		System.out.println("JhPaymentDetailControllerU chSelDelFixU payment.getPay_sts()->"+payment.getPay_sts());
		String pay_sts=Integer.toString(payment.getPay_sts());
		
		return pay_sts;
	}
	
	
	
	// 5. [팝업] 배송완료 -> 환불 신청 변경 (상태 들고 와야함)
	@RequestMapping("jhChDelRefPopU") // 팝업 페이지 띄우기 
	public String chDelRefPopU(int payno , Model model) throws Exception{
		logger.info("chDelRefPopU Starts...");
		model.addAttribute("payno", payno);
		return "uJhDelRefPop";
	}
	
	@RequestMapping("jhChDelRefInfoU") //팝업창에서 입력한 정보 가지고 와서 수정 
	public void chDelRefInfoU(int payno, String account, int reason, HttpServletResponse response) throws IOException {
		logger.info("chDelRefInfoU Starts...");
		System.out.println("payno ->"+payno);
		System.out.println("account ->"+account);
		System.out.println("reason ->"+reason);
		
		Payment payment=new Payment();
		payment.setPayno(payno);
		payment.setRefund_account(account);
		payment.setRefund_reason(reason);
		
		ps.chDelRefInfoU(payment);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter alert=response.getWriter();
		alert.println("<script>alert('환불신청이 완료되었습니다'); location.href='jhMyOrderListU'; </script>;");
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 로그인 시작 
	@RequestMapping(value = "jhLoginFormU")
	public String loginForm() {
		logger.info("JhPaymentDetailControllerU loginForm Starts...");
		
		return "uYjLoginForm";
	}
	
	// 1-1. [아작스] 로그인 아이디 비번 체크 (결과값 1, 0)
	@ResponseBody
	@RequestMapping(value = "jhLoginCheckU" , produces = "application/text;charset=UTF-8")
	public String loginIdCheck(Member member) {
		logger.info("JhPaymentDetailControllerU jhLoginIdCheckU Starts...");
		System.out.println("JhPaymentDetailControllerU id->"+member.getId());
		System.out.println("JhPaymentDetailControllerU id->"+member.getPw());
		
		int result=ps.loginIdCheck(member);
		System.out.println("JhPaymentDetailControllerU result->"+result);

		String result1=Integer.toString(result);
		System.out.println("String result1=>"+result1);
		
		return result1;
	}
	
	// 1-2 로그인 성공시에 화면이동  
	@RequestMapping("jhLoginSucU")
	public String mainPageLogSuc(Member member ,HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("JhPaymentDetailControllerU mainPageLogSuc Start...");
		
		// 가져온 id를 세션에 담을 준비
		HttpSession session=request.getSession();
		String id=member.getId();
		
		System.out.println("JhPaymentDetailControllerU id->"+id);
		
		// 로그인 세션 정보 저장
		session.setAttribute("id", id);
		model.addAttribute("id", id);
		
		return "redirect:/";
	}
	
	
	// 1-3 로그인 비밀번호 찾기 폼이동
	@RequestMapping("jhFindPwFormU")
	public String findPw() {
		
		return "findPwU";
	}
	
	// 1-4 [아작스] 로그인 비밀번호 찾기 아이디 체크 (결과값 0, 1) 
	@ResponseBody
	@RequestMapping(value = "jhFindPwIdCheckU" , produces = "application/text;charset=UTF-8")
	public String findIdCheck(String id) {
		System.out.println("JhPaymentDetailControllerU findIdCheck Start...");
		System.out.println("JhPaymentDetailControllerU findIdCheck id->"+id);
		
		int result=ps.findPwIdCheck(id);
		System.out.println("JhPaymentDetailControllerU findIdCheck result->"+result);

		String result1=Integer.toString(result);
		System.out.println("String findIdCheck result1=>"+result1);
		
		return result1;
	}
	
	
	// 1-5 [아작스] 로그인 비밀번호 찾기 이메일 발송 
	@ResponseBody
	@RequestMapping(value= "jhFindPwEmailGetU", produces = "application/text;charset=UTF-8")
	public String findPwEmail(Member member,HttpServletRequest request ) {
		System.out.println("JhPaymentDetailControllerU jhFindPwEmailGetU Starts....");
		
		int result=0; 
		
		String id=member.getId(); //findPw.jsp에서 받아온 id를 Member 객체에 담았고 거기서 뽑아냄
		System.out.println("JhPaymentDetailControllerU jhFindPwEmailGetU id->"+id);
		
		// 1) id로 이메일 찾음 
		String email=ps.findEmail(id);
		member.setEmail(email); 
		System.out.println("JhPaymentDetailControllerU jhFindPwEmailGetU email->"+email);
		
		
		System.out.println("mailSending...");
		String tomail=email; // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom="rlawlgh3245@gmail.com"; // 관리자 이메일? 
		System.out.println(setfrom);
		String title="[비숲_비밀번호찾기] 임시 비밀번호 발송드립니다"; // 제목
		
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message=mailSender.createMimeMessage();
			MimeMessageHelper messageHelper=new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); // 보낸 사람 생략하면 정상작동 안됨 
			//messageHelper.setTo(tomail);	// 받는 사람 이메일************************************************************
			messageHelper.setTo("rlawlgh3245@gmail.com"); // test용***************************************************
			messageHelper.setSubject(title);// 메일 제목은 생략 가능 
			String tempPassword=(int)(Math.random()*999999)+1+"";
			messageHelper.setText("임시 비밀번호 입니다 : "+tempPassword+"\n링크로 접속하여 로그인을 진행해주세요. http://localhost:8380/jhLoginFormU"); // 메일 내용 
			System.out.println("임시 비밀번호 입니다 : "+tempPassword);
			
			// DataSource dataSource=new FileDataSource("c:\\log\\8.jpg");
			// messageHelper.addAttachment(MimeUtility.encodeText("sweetpotato.png", "UTF-8", "B"), dataSource); // B는 base64
			
			// 2) 이메일 전달 
			mailSender.send(message);
			
			// 3)  db에 비밀번호를 임시비밀번호로 업데이트 (성공 1, 실패0)
			// s.tempPw(u_id, tempPassword) ; db에 비밀번호를 임시비밀번호로 업데이트 
			member.setPw(tempPassword);
			result=ps.chgPwTemp(member);
			
			
			// 정상전달
		} catch (Exception e) {
			System.out.println(e);
			result=0;
		}
		
		String result1=Integer.toString(result);
		
		return result1;
		
	}
	
	// 로그인 끝 
	
	
	// 회원가입 시작 
	// 회원가입 포멧 이동 
	@RequestMapping("jhJoinFormU")
	public String joinForm() {
		return "joinU";
	}
	
	// 회원가입 성공 후 db insert
	@RequestMapping("jhJoinSucU")
	public String joinSuc(Member member, Model model) {
		System.out.println("JhPaymentDetailControllerU jhJoinSucU Starts....");
		
		System.out.println("jhJoinSucU member.getId()"+member.getId());
		
		int result=ps.joinSuc(member);
		System.out.println("JhPaymentDetailControllerU jhJoinSucU result -> "+result);
		
		model.addAttribute("result", result);
		
		return "joinResultU";
	}
	
	
	
	// 로그아웃 
	@RequestMapping("jhLogoutU")
	public String logoutU(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		session.removeAttribute("id");
		
		return "redirect:/";
	}

	// 리뷰 (참고)
	@RequestMapping("revWrite")
	public String revwrite(int prodno, Model model) {
		
		model.addAttribute("prodno", prodno);
		
		return "uYjReviewWriteForm";
	}
	
	// 개인정보조회 (마이페이지)
	@RequestMapping("myInfoCheckU")
	public String myinfocheck(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		String id=(String) request.getSession().getAttribute("id");
		System.out.println("myInfoCheckU id->"+id);
		
		Member member1=null;
		member1=ps.finduserinfo(id);
		System.out.println("myInfoCheckU "+member1.getEmail());
		
		model.addAttribute("member", member1);

		return "uMyInfoCheck";
	}
	
	// 개인정보수정(마이페이지)
	@RequestMapping("uMyInfoUpdate")
	public String myinfoupdate(Member member, Model model) {
		System.out.println("JhPaymentDetailControllerU uMyInfoUpdate Starts....");
		int result=0;
		
		result=ps.myinfoupdate(member);
		System.out.println("JhPaymentDetailControllerU myinfoupdate result->"+result);
		
		model.addAttribute("msg", "개인정보 변경이 완료되었습니다");
		model.addAttribute("url", "myInfoCheckU");
		
		return "uMyinfoupdatealert";
	}
}
