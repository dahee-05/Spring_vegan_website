package com.oracle.oBootS20220603.service.jh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.jh.JhPaymentDetailDaoA;
import com.oracle.oBootS20220603.model.Payment;

@Service
public class JhPaymentDetailServiceImplA implements JhPaymentDetailServiceA {
	
	@Autowired
	private JhPaymentDetailDaoA pd;
	
	// 어드민 대시보드 시작----------------------------------------------------------
	// 오늘의 매출 현황 (금액의 총합)
	@Override
	public int selMoney() {
		System.out.println("JhPaymentDetailServiceImplA selMoney Starts..."); 
		int selMoney=pd.selMoney();
		System.out.println("JhPaymentDetailServiceImplA selMoney -> "+selMoney);
		return selMoney;
	}

	// 오늘의 주문 완료 (개수)
	@Override
	public int selCount() {
		System.out.println("JhPaymentDetailServiceImplA selCount Starts..."); 
		int selCount=pd.selCount();
		System.out.println("JhPaymentDetailServiceImplA selCount -> "+selCount);
		return selCount;
	}

	// 오늘의 취소 개수 
	@Override
	public int canCount() {
		System.out.println("JhPaymentDetailServiceImplA canCount Starts..."); 
		int canCount=pd.canCount();
		System.out.println("JhPaymentDetailServiceImplA canCount -> "+canCount);
		return canCount;
	}

	// 오늘의 환불 개수 
	@Override
	public int refCount() {
		System.out.println("JhPaymentDetailServiceImplA refCount Starts..."); 
		int refCount=pd.refCount();
		System.out.println("JhPaymentDetailServiceImplA refCount -> "+refCount);
		return refCount;
	}

	// 오늘의 신규 구독회원
	@Override
	public int subsMemTot() {
		System.out.println("JhPaymentDetailServiceImplA subsMemTot Starts..."); 
		int subsMemTot=pd.subsMemTot();
		System.out.println("JhPaymentDetailServiceImplA subsMemTot -> "+subsMemTot);
		return subsMemTot;
	}

	// 오늘의 신규 리뷰 
	@Override
	public int reviewTot() {
		System.out.println("JhPaymentDetailServiceImplA reviewTot Starts..."); 
		int reviewTot=pd.reviewTot();
		System.out.println("JhPaymentDetailServiceImplA reviewTot->"+reviewTot);
		return reviewTot;
	}
	// 어드민 대시보드 끝----------------------------------------------------
	
	
	
	//어드민 배송처리 시작------------------------------------------------------------
		// 구매완료 총합
		@Override
		public int totalPayed() {
			System.out.println("JhPaymentDetailServiceImplA totalPayed Starts..."); 
			int totCnt=pd.totalPayed();
			System.out.println("JhPaymentDetailServiceImplA totCnt->"+totCnt);
			return totCnt;
		}

		// 구매완료 리스트 
		@Override
		public List<Payment> listPayed(Payment payment) {
			List<Payment> listPayed=null; 
			System.out.println("JhPaymentDetailServiceImplA listPayed Starts...");
			listPayed=pd.listPayed(payment);
			System.out.println("JhPaymentDetailServiceImplA listPayed.size()->"+listPayed.size());
			return listPayed;
		}

		// 구매완료 검색 적용 후 총합 
		@Override
		public int totalPayedKey(Payment payment) { 
			System.out.println("JhPaymentDetailServiceImplA totalPayedKey Starts...");
			int totCnt=pd.totalPayedKey(payment);
			System.out.println("JhPaymentDetailServiceImplA totalPayedKey totCnt->"+totCnt);
			return totCnt;
		}
		
		// 구매완료 검색 적용 후 
		@Override
		public List<Payment> listPayedKey(Payment payment) {
			List<Payment> listPayedKey=null; 
			System.out.println("JhPaymentDetailServiceImplA listPayedKey Starts...");
			listPayedKey=pd.listPayedKey(payment);
			System.out.println("JhPaymentDetailServiceImplA listPayedKey.size()->"+listPayedKey.size());
			return listPayedKey;
		}

		// 송장번호 불러오기 아작스
		@Override
		public int delSeq(int payno) {
			System.out.println("JhPaymentDetailServiceImplA delSeq Starts..."); 
			int delivno=pd.delSeq(payno);
			System.out.println("After JhPaymentDetailServiceImplA deliveryno.getDeliveryno()->"+delivno);
			return delivno;
		}

		// 송장번호 입력 업데이트 
		@Override
		public void updateDel(Payment payment) {
			System.out.println("JhPaymentDetailServiceImplA updateDel Starts..."); 
			pd.updateDel(payment);
			
		}
		//어드민 배송처리 끝------------------------------------------------------------


		
		//어드민 환불 시작---------------------------------------------------------------
		//환불완료, 환불요청 개수 
		@Override
		public int totalref() {
			System.out.println("JhPaymentDetailServiceImplA totalref Starts..."); 
			int totCnt=pd.totalref();
			System.out.println("JhPaymentDetailServiceImplA totCnt->"+totCnt);
			return totCnt;
		}
		
		//환불완료, 환불요청 전체 리스트 
		@Override
		public List<Payment> listRef(Payment payment) {  
			List<Payment> listRef=null;
			System.out.println("JhPaymentDetailServiceImplA listRef Starts...");
			listRef=pd.listRef(payment);
			System.out.println("JhPaymentDetailServiceImplA listRef.size()->"+listRef.size());
			return listRef;
		}

		//환불완료, 환불요청 검색 후 총 개수
		@Override
		public int totalRefKey(Payment payment) { 
			System.out.println("JhPaymentDetailServiceImplA totalRefKey Starts...");
			int totCnt=pd.totalRefKey(payment);
			System.out.println("JhPaymentDetailServiceImplA totalRefKey totCnt->"+totCnt);
			return totCnt;
		}

		//환불완료, 환불요청 검색 후 리스트 
		@Override
		public List<Payment> listRefKey(Payment payment) { 
			List<Payment> listRefKey=null; 
			System.out.println("JhPaymentDetailServiceImplA listRefKey Starts...");
			listRefKey=pd.listRefKey(payment);
			System.out.println("JhPaymentDetailServiceImplA listRefKey.size()->"+listRefKey.size());
			return listRefKey;
		}

		// 환불요청->환불완료 아작스 
		@Override
		public void refStsChg(Payment payment) { 
			System.out.println("JhPaymentDetailServiceImplA refStsChg Starts...");
			pd.refStsChg(payment);
			System.out.println("JhPaymentDetailServiceImplA refStsChg ->"+payment.getPay_sts());
		}
		//어드민 환불 끝---------------------------------------------------------------
		
		
		
		//어드민 판매목록 시작------------------------------------------------------------
		// 판매목록 총 개수 
		@Override
		public int totalSel() { 
			System.out.println("JhPaymentDetailServiceImplA totalSel Starts...");
			int totSel=pd.totSel();
			System.out.println("JhPaymentDetailServiceImplA totSel->"+totSel);
			return totSel;
		}

		// 판매목록 메인 화면 전체 리스트 
		@Override
		public List<Payment> listSel(Payment payment) { 
			List<Payment> listSel=null;
			System.out.println("JhPaymentDetailServiceImplA listSel Starts...");
			listSel=pd.listSel(payment);
			System.out.println("JhPaymentDetailServiceImplA listSel.size()->"+listSel.size());
			return listSel;
		}

		// 판매목록 검색 후 총합 
		@Override
		public int totalSelKey(Payment payment) { 
			System.out.println("JhPaymentDetailServiceImplA totalSelKey Starts...");
			int totSelKey=pd.totSelKey(payment);
			System.out.println("JhPaymentDetailServiceImplA totSelKey->"+totSelKey);
			return totSelKey;
		}

		// 판매목록 검색 후 리스트 
		@Override
		public List<Payment> listSelKey(Payment payment) { 
			List<Payment> listSelKey=null;
			System.out.println("JhPaymentDetailServiceImplA listSelKey Starts...");
			listSelKey=pd.listSelKey(payment);
			System.out.println("JhPaymentDetailServiceImplA listSelKey.size()->"+listSelKey.size());
			return listSelKey;
		}
		//어드민 판매목록 끝--------------------------------------------------------------

		
		
		//어드민 취소 시작---------------------------------------------------------------
		// 취소 총 개수 
		@Override
		public int totalCan() {  
			System.out.println("JhPaymentDetailServiceImplA totalCan Starts...");
			int totalCan=pd.totalCan();
			System.out.println("JhPaymentDetailServiceImplA totalCan->"+totalCan);
			return totalCan;
		}

		// 취소 메인 화면 전체 리스트 
		@Override
		public List<Payment> listCan(Payment payment) { 
			List<Payment> listCan=null;
			System.out.println("JhPaymentDetailServiceImplA listCan Starts...");
			listCan=pd.listCan(payment);
			System.out.println("JhPaymentDetailServiceImplA listCan.size()->"+listCan.size());
			return listCan;
		}

		// 취소 검색 후 총합
		@Override
		public int totalCanKey(Payment payment) {  
			System.out.println("JhPaymentDetailServiceImplA totalCanKey Starts...");
			int totCanKey=pd.totCanKey(payment);
			System.out.println("JhPaymentDetailServiceImplA totCanKey->"+totCanKey);
			return totCanKey;
		}
		
		// 취소 검색 후 리스트 
		@Override
		public List<Payment> listCanKey(Payment payment) { 
			List<Payment> listCanKey=null;
			System.out.println("JhPaymentDetailServiceImplA listCanKey Starts...");
			listCanKey=pd.listCanKey(payment);
			System.out.println("JhPaymentDetailServiceImplA listCanKey.size()->"+listCanKey.size());
			return listCanKey;
		}
		//어드민 취소 끝---------------------------------------------------------------
	
}
