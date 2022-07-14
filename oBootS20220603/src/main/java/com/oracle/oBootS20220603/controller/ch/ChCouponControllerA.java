package com.oracle.oBootS20220603.controller.ch;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.service.ch.ChCouponService;

@Controller
public class ChCouponControllerA {

	private static final Logger logger = LoggerFactory.getLogger(ChCouponControllerA.class);
	
	@Autowired
	private ChCouponService chcs;
	
	@RequestMapping(value = "/coupMasList")
	public String coupMasList(CouponMaster couponMaster, Model model, HttpServletRequest request) {
		
		logger.info("ChCouponControllerA coupMasList start...");
		
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			List<CouponMaster> listCoupMaster = chcs.listCoupMaster(couponMaster);
			System.out.println("listCoupMaster.size()->" + listCoupMaster.size());
			model.addAttribute("listCoupMaster", listCoupMaster);
			model.addAttribute("menu_num", 7);
			
			return "aChCouponList";
		}
		
	}
	
	@RequestMapping(value = "/coupMasDetail")
	public String coupMasDetail(int coupno, Model model) {
		System.out.println("ChCouponControllerA coupMasDetail start..");
		
		CouponMaster couponMaster = chcs.detailCoupMaster(coupno);
		model.addAttribute("couponMaster", couponMaster);
		
		return "aChCouponDetail";
	}
	
	@GetMapping(value = "/coupMasUpdateForm")
	public String coupMasUpdateForm(int coupno, Model model) {
		System.out.println("ChCouponControllerA coupMasUpdateForm start..");
		
		CouponMaster couponMaster = chcs.detailCoupMaster(coupno);
		model.addAttribute("couponMaster", couponMaster);
		
		return "aChCouponDetailUpdateForm";
	}
	
	@PostMapping(value = "/updateCoupMaster")
	public String coupMasterUpdate(CouponMaster couponMaster, Model model) {
		
		System.out.println("ChCouponControllerA coupMasterUpdate start..");
		chcs.updateCoupMaster(couponMaster);
		
		return "forward:coupMasList";
	}
	
	@RequestMapping(value = "/newMasCoupon")
	public String newMasCoupon(Model model) {
		
		return "newMasCoupon";
	}
	
	@PostMapping(value = "/newMasCoupInsert")
	public String newMasCoupInsert(CouponMaster couponMaster, Model model) {
		
		System.out.println("ChCouponControllerA newMasCoupInsert start..");
		chcs.insertCoupMaster(couponMaster);
		
		return "forward:coupMasList";
	}
	
	@RequestMapping(value = "/coupMasDelete")
	public String deleteCoupMaster(int coupno, Model model) {
		
		
		System.out.println("ChCouponControllerA deleteCoupMaster start..");
		chcs.deleteCoupMaster(coupno);
		
		return "forward:coupMasList";
		
	}
	
	@RequestMapping(value = "/userCoupList")
	public String userCoupList(HttpServletRequest request, Coupon coupon, Model model) {
		
		System.out.println("ChCouponControllerA userCoupList start..");
		
		String id = (String) request.getSession().getAttribute("id");
		List<Coupon> listUserCoupon = chcs.userCoupList(id);
		System.out.println("listUserCoupon.size()->" + listUserCoupon.size());
		
		int totCnt = listUserCoupon.size();
		
		model.addAttribute("totCnt", totCnt);
		model.addAttribute("listUserCoupon", listUserCoupon);
		
		return "uUserCouponList";
	}
}
