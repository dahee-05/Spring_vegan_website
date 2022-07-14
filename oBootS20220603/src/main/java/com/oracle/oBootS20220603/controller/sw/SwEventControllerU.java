package com.oracle.oBootS20220603.controller.sw;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.service.sw.SwEventServiceU;

@Controller
public class SwEventControllerU {
	private static final Logger logger = LoggerFactory.getLogger(SwEventControllerU.class);
	
	@Autowired
	private SwEventServiceU es;

	//유저 이벤트 리스트
	@RequestMapping(value = "uSwEventList")
	public String uSwEventList(Event event, Model model) {
		
		List<Event> listEventU = es.listEventU(event);
		model.addAttribute("listEventU",listEventU);
			
		return "uSwEventList";
	}
	
	//유저 할인 이벤트 상세 p(product 줄임말)
	@GetMapping(value = "uSwEvtdetailp")
	public String uSwEvtdetailp(int evt_no, Model model) {
			System.out.println("SwEventControllerU Start uSwEventdetailp...");
			
		Event eventp = es.uSwEvtdetailp(evt_no);
		List<Product> p_list = es.p_list(evt_no);
		
		model.addAttribute("event", eventp);
		model.addAttribute("p_list",p_list);
		
		return "uSwEvtdetailp";
		
	}
	
	//쿠폰 할인 이벤트 상세c(coupon 줄임말)
	@GetMapping(value = "uSwEvtdetailc")
	public String uSwEvtdetailc(int evt_no, Model model) {
		System.out.println("SwEventControllerU start uSwEventdetailc...");
		if(evt_no == 0) {
			evt_no = (int) model.getAttribute("evt_no");
			
		}
		
		Event eventc = es.uSwEvtdetailc(evt_no);
		List<CouponMaster> c_list = es.c_list(evt_no);
		
		model.addAttribute("event", eventc);
		model.addAttribute("c_list",c_list);
		
		return "uSwEvtdetailc";
		
	}
	
	/*
	 * model.addAttribute("msg", "발급되었습니다");  model.addAttribute("url",
	 * "uSwCoupRedirect.jsp"); 
	 * 쿠폰get에서 model .on url 메시지 요청
	 * 할인 이벤트 상세에서 if 안에 요청
	 */	
	
	//쿠폰 마이페이지로 다운
	@GetMapping(value = "coupget")
	public String coupget(CouponMaster couponmaster, Model model, HttpServletRequest request, int evt_no) {
			System.out.println("SwEventControllerU coupget Start...");
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
			System.out.println("SwEventControllerU coupget id->"+id);
			System.out.println("SwEventControllerU coupget getCoup_start->"+couponmaster.getCoup_start());
			System.out.println("SwEventControllerU coupget getCoup_end->"+couponmaster.getCoup_end());
			System.out.println("SwEventControllerU coupget getCoup_status->"+couponmaster.getCoup_status());
		int result = 0;
		Coupon coupon = new Coupon();
		
		if(id !=null) {
			coupon.setId(id);
			coupon.setCoupno(couponmaster.getCoupno());
			coupon.setCoup_start(couponmaster.getCoup_start());
			coupon.setCoup_end(couponmaster.getCoup_end());
			coupon.setCoup_status(couponmaster.getCoup_status());
			result = es.coup_insert(coupon);
			
			
			model.addAttribute("msg", "발급되었습니다");
			model.addAttribute("evt_no", evt_no);
			
			return "forward:uSwEvtdetailc";
			
		}else {
			result = 0;
			return "uYjLoginForm";
		}
		
		
		
	}
	
}
