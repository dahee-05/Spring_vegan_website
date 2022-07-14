package com.oracle.oBootS20220603.controller.hk;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.oracle.oBootS20220603.model.Banner;
import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.service.hk.HKBannerService;
import com.oracle.oBootS20220603.service.hk.HKBasketService;
import com.oracle.oBootS20220603.service.hk.HKCouponService;
import com.oracle.oBootS20220603.service.hk.HKEventService;
import com.oracle.oBootS20220603.service.hk.HKMemberService;
import com.oracle.oBootS20220603.service.hk.HKProductService;

@Controller
public class HKMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(HKPaymentController.class);
	
	@Autowired
	private HKProductService ps;
	
	@Autowired
	private HKBannerService bs;
	
	@Autowired
	private HKBasketService bas;
	
	@Autowired
	private HKMemberService ms;
	
	@Autowired
	private HKCouponService cs;
	
	@Autowired
	private HKEventService es;
	
	
	@RequestMapping("/")
	public String mainPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		System.out.println("HKMainController mainPage Start...");
		List<Product> p_list = ps.selectBestProduct();
		
		// 로그인 세션 정보 있는지 확인
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		int bas_cnt = 0;
		session.setAttribute("bas_cnt", bas_cnt);
		
		if(id != null && !id.equals("")) {
			System.out.println("HKMainController id->"+id);
			bas_cnt = bas.countItems(id);
			System.out.println("bas_cnt->"+bas_cnt);
			
			session.setAttribute("bas_cnt", bas_cnt);
		}
		
		// 배너 이미지 로드
		List<Banner> banner_list = bs.selectAll();
		System.out.println("banner_list.size()->"+banner_list.size());
		
		model.addAttribute("p_list", p_list);
		model.addAttribute("id", id);
		model.addAttribute("banner_list", banner_list);
		
		return "main";
	}
	
	
	//비밀번호 변경
	@GetMapping("/hkpasswdChange")
	public String pwChange() {
		System.out.println("HKMainController pwChange Start...");
		
		return "hkpwChangeFormU";
	}
	
	
	@PostMapping("/hkPwChangePro")
	public String pwChangePro(HttpServletRequest request, Member member, Model model) {
		System.out.println("HKMainController pwChangePro Start...");
		
		String id = (String) request.getSession().getAttribute("id");
		member.setId(id);
		
		int chk = pwCheck(member);
		if(chk == 0) {
			model.addAttribute("msg", "현재 비밀번호가 올바르지 않습니다.");
			return "hkpwChangeFormU";
		} else {
			int update_result = ms.changePw(member);
			model.addAttribute("msg2", "비밀번호가 변경되었습니다.");
			return "uYjLoginForm";
		}
		
	}
	
	
	private int pwCheck(Member member) {
		System.out.println("HKMainController pwCheck Start...");
		
		// 비밀번호가 맞는 경우 1, 아닌 경우 0
		int result = ms.pwCheck(member);
		
		return result;
	}
	
	
	@PostMapping("/coupGetChk")
	@ResponseBody
	public String coupGetChk(HttpServletRequest request, Coupon coupon, Model model) {
		System.out.println("HKMainController coupGetChk Start...");
		
		String id = (String) request.getSession().getAttribute("id");
		coupon.setId(id);
		
		int chk_result = cs.coupGetChk(coupon);
		String result = null;
		
		if(chk_result > 0) {
			result = "exist";
		} else {
			result = "not exist";
		}
		
		return result;
	}
	
	
	@GetMapping("/hkEventSelect")
	public String eventSelect(int evt_no) {
		System.out.println("HKMainController eventSelect Start...");
		
		int evt_type = es.getEvtType(evt_no);
		
		if(evt_type == 1) {
			return "redirect:/uSwEvtdetailp?evt_no="+evt_no;
		} else {
			return "redirect:/uSwEvtdetailc?evt_no="+evt_no;
		}
	}
	
}
