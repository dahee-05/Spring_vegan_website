package com.oracle.oBootS20220603.controller.jh;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootS20220603.model.Payment;
import com.oracle.oBootS20220603.service.jh.JhPaymentDetailServiceA;
import com.oracle.oBootS20220603.service.Paging;

@Controller		// 얘가 있어야 DispatherConfig가 컨트롤러를 찾는다
public class JhPaymentDetailControllerA {
	private static final Logger logger = LoggerFactory.getLogger(JhPaymentDetailControllerA.class);
	
	@Autowired
	private JhPaymentDetailServiceA ps;
	
	@RequestMapping("admin")
	public String admin(HttpServletRequest request, Model model) {
		
		logger.info("JhPaymentDetailControllerA admin 조건조회 Starts...");	
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			//어드민 대시보드 시작------------------------------------------------------------		
			// 오늘의 매출 현황 (금액의 총합)
			System.out.println("JhPaymentDetailControllerA selMoney Starts..."); 
			int selMoney=ps.selMoney();
			
			System.out.println("JhPaymentDetailControllerA selMoney -> "+selMoney);
			
			// 오늘의 주문 완료 (개수)
			System.out.println("JhPaymentDetailControllerA selCount Starts..."); 
			int selCount=ps.selCount();
			System.out.println("JhPaymentDetailControllerA selCount -> "+selCount);
			
			// 오늘의 취소 개수 
			System.out.println("JhPaymentDetailControllerA canCount Starts..."); 
			int canCount=ps.canCount();
			System.out.println("JhPaymentDetailControllerA canCount -> "+canCount);
			
			
			// 오늘의 환불 개수 
			System.out.println("JhPaymentDetailControllerA refCount Starts..."); 
			int refCount=ps.refCount();
			System.out.println("JhPaymentDetailControllerA refCount -> "+refCount);
			
			
			// 오늘의 신규 구독회원
			System.out.println("JhPaymentDetailControllerA subsMemTot Starts..."); 
			int subsMemTot=ps.subsMemTot();
			System.out.println("JhPaymentDetailControllerA subsMemTot -> "+subsMemTot);
			
			
			// 오늘의 신규 리뷰 
			System.out.println("JhPaymentDetailControllerA reviewTot Starts...");
			int reviewTot=ps.reviewTot();
			System.out.println("JhPaymentDetailControllerA reviewTot->"+reviewTot);
			
			model.addAttribute("menu_num", 1);
			model.addAttribute("selMoney", selMoney);
			model.addAttribute("selCount", selCount);
			model.addAttribute("canCount", canCount);
			model.addAttribute("refCount", refCount);
			model.addAttribute("subsMemTot", subsMemTot);
			model.addAttribute("reviewTot", reviewTot);
			
			return "Admin";
		}
		
	}
	//어드민 대시보드 끝------------------------------------------------------------
	
	
	
	
	//어드민 배송처리 시작------------------------------------------------------------
	// 구매완료 메인 화면 전체 리스트 
	@RequestMapping("jhDelivListA")
	public String delivlistA(Payment payment,String currentPage, Model model, HttpServletRequest request) {
		logger.info("JhPaymentDetailControllerA jhDelivListA 조건조회 Starts...");
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			int totalPayed=ps.totalPayed(); 
			System.out.println("JhPaymentDetailControllerA jhDelivListA totalPayed->"+totalPayed);
			
			Paging pg=new Paging(totalPayed, currentPage);
			payment.setStart(pg.getStart());
			System.out.println("payment.getStart()->"+payment.getStart());
			payment.setEnd(pg.getEnd());
			System.out.println("payment.getEnd()->"+payment.getEnd());
			
			// 주문정보 전체 
			List<Payment> listPayed=ps.listPayed(payment); // payment
			System.out.println("JhPaymentDetailControllerA list listPayed.size()->"+listPayed.size());

			model.addAttribute("total", totalPayed);
			model.addAttribute("pg", pg);
			model.addAttribute("payment", payment);
			model.addAttribute("listPayed", listPayed);
			model.addAttribute("menu_num", 3);
					
			return "aJhDelivList";
		}
	}
	
	 // 배송주문 검색 적용 후 
	@RequestMapping("jhDelivSearchA")
	public String delivSearchA(Payment payment,String currentPage, Model model) {
		 System.out.println("sdate->"+payment.getSdate());
		 System.out.println("edate->"+payment.getEdate());
		
		 logger.info("JhPaymentDetailControllerA jhDelivSearchA 조건조회 Starts...");
		 System.out.println("payment.getKeyword1()->"+payment.getKeyword1());
		 int totalPayedKey=ps.totalPayedKey(payment); 
		 System.out.println("JhPaymentDetailControllerA delivSearchA totalPayed->"+totalPayedKey);
		 
		 Paging pg=new Paging(totalPayedKey, currentPage);
		 payment.setStart(pg.getStart());
		 System.out.println("payment.getStart()->"+payment.getStart());
		 payment.setEnd(pg.getEnd());
		 System.out.println("payment.getEnd()->"+payment.getEnd());
	 
		 List<Payment> listPayedKey=ps.listPayedKey(payment);
		 System.out.println("JhPaymentDetailControllerA list listPayed.size()->"+listPayedKey.size());
		 
		 
		 
		 model.addAttribute("total", totalPayedKey);
		 model.addAttribute("pg", pg);
		 model.addAttribute("payment", payment);
		 model.addAttribute("keyword1", payment.getKeyword1());
		 model.addAttribute("listPayed", listPayedKey);
		 model.addAttribute("menu_num", 3);
		 
		 return "aJhDelivList";
	}
	
	// [아작스] 송장번호 불러오기 아작스
	@ResponseBody
	@RequestMapping(value = "jhGetDelSeqA", produces = "application/text;charset=UTF-8") 
	public String getDelSeq(int payno) { 
		System.out.println("JhPaymentDetailControllerA getDelSeq->"+payno);
		int delivno=ps.delSeq(payno);
		System.out.println("JhPaymentDetailControllerA delivno->"+delivno);
		String delivno1=Integer.toString(delivno);
		return delivno1;
	}
	
	// [아작스] 송장번호 입력 업데이트 
	@ResponseBody
	@RequestMapping(value ="jhPaymentUpdDelA", produces = "application/text;charset=UTF-8") 
	public String updateDel(Payment payment) {
		logger.info("JhPaymentDetailControllerA jhPaymentUpdDelA 운송장번호 입력 Starts...");
		System.out.println("JhPaymentDetailControllerA updateDel payment.getDeliveryno()->"+payment.getDeliveryno());
		System.out.println("JhPaymentDetailControllerA updateDel payment.getPayno()->"+payment.getPayno());
		ps.updateDel(payment);
		System.out.println("After JhPaymentDetailControllerA updateDel payment.getDeliveryno()->"+payment.getDeliveryno());
		String payno=Integer.toString(payment.getPayno());
		System.out.println("After JhPaymentDetailControllerA updateDel payno"+payno);
		return payno;
	}
	//어드민 배송처리 끝------------------------------------------------------------

	
	
	
	
	
	
	
	//어드민 판매목록 시작------------------------------------------------------------
	// 판매목록 메인 화면 전체 리스트 
	@RequestMapping("jhSelListA") 
	public String selListA(Payment payment,String currentPage, Model model, HttpServletRequest request) {
		logger.info("JhPaymentDetailControllerA jhSelListA 조건조회 Starts...");
		
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			 // 판매목록 총 개수 
			int totalSel=ps.totalSel();
			System.out.println("JhPaymentDetailControllerA jhDelivListA totalPayed->"+totalSel);
			
			Paging pg=new Paging(totalSel, currentPage);
			payment.setStart(pg.getStart());
			System.out.println("payment.getStart()->"+payment.getStart());
			payment.setEnd(pg.getEnd());
			System.out.println("payment.getEnd()->"+payment.getEnd());
			
			// 주문정보 전체 
			List<Payment> listSel=ps.listSel(payment); 
			System.out.println("JhPaymentDetailControllerA list listPayed.size()->"+listSel.size());

			model.addAttribute("total", totalSel);
			model.addAttribute("pg", pg);
			model.addAttribute("payment", payment);
			model.addAttribute("listSel", listSel);
			model.addAttribute("menu_num", 3);
					
			return "aJhSelList";
		}
		
	}
	
	// 판매목록 검색 후 
	@RequestMapping(value = "jhSelSearchListA") 
	public String selSearchA(Payment payment,String currentPage, Model model) {
		 logger.info("JhPaymentDetailControllerA selSearchA 조건조회 Starts...");
		 System.out.println("payment.getKeyword1()->"+payment.getKeyword1());
		// 판매목록 검색 후 총합 
		 int totalSelKey=ps.totalSelKey(payment); 
		 System.out.println("JhPaymentDetailControllerA selSearchA totalSelKey->"+totalSelKey);
		 
		 Paging pg=new Paging(totalSelKey, currentPage);
		 payment.setStart(pg.getStart());
		 System.out.println("payment.getStart()->"+payment.getStart());
		 payment.setEnd(pg.getEnd());
		 System.out.println("payment.getEnd()->"+payment.getEnd());
	 
		 // 판매목록 검색 후 리스트 
		 List<Payment> listSelKey=ps.listSelKey(payment);
		 System.out.println("JhPaymentDetailControllerA listSelKey.size()->"+listSelKey.size());
		 
		 model.addAttribute("total", totalSelKey);
		 model.addAttribute("pg", pg);
		 model.addAttribute("payment", payment);
		 model.addAttribute("keyword1", payment.getKeyword1());
		 model.addAttribute("listSel", listSelKey);
		 model.addAttribute("menu_num", 3);
		 
		 return "aJhSelList";
	}
	
	//어드민 판매목록 끝--------------------------------------------------------------

	
	
	
	
	
	//어드민 취소 시작---------------------------------------------------------------
	// 취소 메인 화면 전체 리스트 
	@RequestMapping("jhCanListA") 
	public String canListA(Payment payment,String currentPage, Model model, HttpServletRequest request) {
		logger.info("JhPaymentDetailControllerA jhCanListA 조건조회 Starts...");
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			// 취소 총 개수 
			int totalCan=ps.totalCan(); 
			System.out.println("JhPaymentDetailControllerA jhCanListA totalCan->"+totalCan);
			
			Paging pg=new Paging(totalCan, currentPage);
			payment.setStart(pg.getStart());
			System.out.println("payment.getStart()->"+payment.getStart());
			payment.setEnd(pg.getEnd());
			System.out.println("payment.getEnd()->"+payment.getEnd());
			
			 // 취소 전체 
			List<Payment> listCan=ps.listCan(payment);
			System.out.println("JhPaymentDetailControllerA list listCan.size()->"+listCan.size());

			model.addAttribute("total", totalCan);
			model.addAttribute("pg", pg);
			model.addAttribute("payment", payment);
			model.addAttribute("listCan", listCan);
			model.addAttribute("menu_num", 4);
					
			return "aJhCanList";
		}
		
	}
	 // 취소 검색 후 
	@RequestMapping(value = "jhCanSearchListA")
	public String canSearchA(Payment payment,String currentPage, Model model) {
		 logger.info("JhPaymentDetailControllerA canSearchA 조건조회 Starts...");
		 System.out.println("payment.getKeyword1()->"+payment.getKeyword1());
		 // 취소 검색 후 총합 
		 int totalCanKey=ps.totalCanKey(payment);
		 System.out.println("JhPaymentDetailControllerA canSearchA totalCanKey->"+totalCanKey);
		 
		 Paging pg=new Paging(totalCanKey, currentPage);
		 payment.setStart(pg.getStart());
		 System.out.println("payment.getStart()->"+payment.getStart());
		 payment.setEnd(pg.getEnd());
		 System.out.println("payment.getEnd()->"+payment.getEnd());
		 
		 // 취소 검색 후 리스트 
		 List<Payment> listCanKey=ps.listCanKey(payment); 
		 System.out.println("JhPaymentDetailControllerA listCanKey.size()->"+listCanKey.size());
		 
		 model.addAttribute("total", totalCanKey);
		 model.addAttribute("pg", pg);
		 model.addAttribute("payment", payment);
		 model.addAttribute("keyword1", payment.getKeyword1());
		 model.addAttribute("listCan", listCanKey);
		 model.addAttribute("menu_num", 4);
		 
		 return "aJhCanList";
	}
	//어드민 취소 끝---------------------------------------------------------------

	
	
	
	
	//어드민 환불 시작---------------------------------------------------------------
	@RequestMapping("jhRefListA")
	public String reflistA(Payment payment,String currentPage, Model model) { // 환불완료, 환불요청 전체 리스트
		logger.info("JhPaymentDetailControllerA jhDelivListA 조건조회 Starts...");
		
		//환불완료, 환불요청 개수 
		int totalref=ps.totalref(); 
		System.out.println("JhPaymentDetailControllerA jhDelivListA totalPayed->"+totalref);
		
		Paging pg=new Paging(totalref, currentPage);
		payment.setStart(pg.getStart());
		System.out.println("payment.getStart()->"+payment.getStart());
		payment.setEnd(pg.getEnd());
		System.out.println("payment.getEnd()->"+payment.getEnd());
		
		//환불완료, 환불요청 전체 리스트 
		List<Payment> listRef=ps.listRef(payment);
		System.out.println("JhPaymentDetailControllerA list listPayed.size()->"+listRef.size());

		model.addAttribute("total", totalref);
		model.addAttribute("pg", pg);
		model.addAttribute("payment", payment);
		model.addAttribute("listRef", listRef);
		model.addAttribute("menu_num", 4);
				
		return "aJhRefList";
	}
	
	// 환불완료, 환불요청 검색 적용 후 
	@RequestMapping("jhRefSearchA")
	public String refSearchA(Payment payment,String currentPage, Model model) {
		 logger.info("JhPaymentDetailControllerA jhRefSearchA 조건조회 Starts...");
		 System.out.println("payment.getKeyword1()->"+payment.getKeyword1());
		 
		 //환불완료, 환불요청 검색 후 총 개수
		 int totalRefKey=ps.totalRefKey(payment);
		 System.out.println("JhPaymentDetailControllerA jhRefSearchA totalRefKey->"+totalRefKey);
		 
		 Paging pg=new Paging(totalRefKey, currentPage);
		 payment.setStart(pg.getStart());
		 System.out.println("payment.getStart()->"+payment.getStart());
		 payment.setEnd(pg.getEnd());
		 System.out.println("payment.getEnd()->"+payment.getEnd());
	 
		 //환불완료, 환불요청 검색 후 리스트 
		 List<Payment> listRefKey=ps.listRefKey(payment); 
		 System.out.println("JhPaymentDetailControllerA jhRefSearchA listRefKey.size()->"+listRefKey.size());
		 
		 model.addAttribute("totalRefKey", totalRefKey);
		 model.addAttribute("pg", pg);
		 model.addAttribute("payment", payment);
		 model.addAttribute("keyword1", payment.getKeyword1());
		 model.addAttribute("listRef", listRefKey);
		 model.addAttribute("menu_num", 4);
		 
		 return "aJhRefList";
	}
	
	 // [아작스] 환불요청->환불완료 아작스 
	@ResponseBody
	@RequestMapping(value = "jhRefStsChgA", produces = "application/text;charset=UTF-8")
	public String refStsChg(Payment payment) {
		logger.info("JhPaymentDetailControllerA jhRefStsChgA 조건조회 Starts...");
		System.out.println("JhPaymentDetailControllerA jhRefStsChgA payment1.getPayno()->"+payment.getPayno());
		ps.refStsChg(payment);
		System.out.println("JhPaymentDetailControllerA jhRefStsChgA payment.getPay_sts()->"+payment.getPay_sts());
		String pay_sts=Integer.toString(payment.getPay_sts());
		return pay_sts;
	}
	//어드민 환불 끝---------------------------------------------------------------
	
	
}
