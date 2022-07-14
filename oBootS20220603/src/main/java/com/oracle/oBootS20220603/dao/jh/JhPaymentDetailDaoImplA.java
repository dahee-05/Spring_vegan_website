package com.oracle.oBootS20220603.dao.jh;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Payment;

@Repository
public class JhPaymentDetailDaoImplA implements JhPaymentDetailDaoA {
	
	@Autowired
	private SqlSession session;

	
	// 어드민 대시보드 시작----------------------------------------------------------
	// 1 오늘의 매출 현황 (금액의 총합)
	@Override
	public int selMoney() {
		System.out.println("JhPaymentDetailDaoImplA selMoney Starts..."); 
		Integer selMoney=0;
		
		try {
			selMoney=session.selectOne("jhPaymentSelMoneyTodayA");
			System.out.println("selMoney->"+selMoney);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplA selMoney Exception->"+e.getMessage());
		}
		
		if(selMoney!=null) {
			return selMoney;
		} else {
			return 0;
		}
	}
	
	// 2 오늘의 주문 완료 (개수) _결제일
	@Override
	public int selCount() {
		System.out.println("JhPaymentDetailDaoImplA selCount Starts..."); 
		int selCount=0;
		
		try {
			selCount=session.selectOne("jhPaymentSelTodayTotA");
			System.out.println("selCount->"+selCount);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplA selCount Exception->"+e.getMessage());
		}
		
		return selCount;
	}

	// 3 오늘의 취소 개수 _취소일
	@Override
	public int canCount() {
		System.out.println("JhPaymentDetailDaoImplA canCount Starts..."); 
		int canCount=0;
		
		try {
			canCount=session.selectOne("jhPaymentCanTodayTotA");
			System.out.println("canCount->"+canCount);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplA canCount Exception->"+e.getMessage());
		}
		
		return canCount;
	}

	// 4 오늘의 환불 개수 _환불요청일
	@Override
	public int refCount() {
		System.out.println("JhPaymentDetailDaoImplA refCount Starts..."); 
		int refCount=0;
		
		try {
			refCount=session.selectOne("jhPaymentRefTodayTotA");
			System.out.println("refCount->"+refCount);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplA refCount Exception->"+e.getMessage());
		}
		
		return refCount;
	}

	// 5 오늘의 신규 구독회원 _구독시작일
	@Override
	public int subsMemTot() {
		System.out.println("JhPaymentDetailDaoImplA subsMemTot Starts..."); 
		int subsMemTot=0;

		try {
			subsMemTot=session.selectOne("jhMemberTodayTotA");
			System.out.println("subsMemTot->"+subsMemTot);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplA subsMemTot Exception->"+e.getMessage());
		}
		
		return subsMemTot;
	}
	

	// 6 오늘의 신규 리뷰 _리뷰등록일
	@Override
	public int reviewTot() {
		System.out.println("JhPaymentDetailDaoImplA reviewTot Starts..."); 
		int reviewTot=0;
		
		try {
			reviewTot=session.selectOne("jhReviewTodayTotA");
			System.out.println("reviewTot->"+reviewTot);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplA reviewTot Exception->"+e.getMessage());
		}
		
		return reviewTot;
	}
	// 어드민 대시보드 끝----------------------------------------------------
	
	
	
	
	
	
	//어드민 배송처리 시작------------------------------------------------------------
		// 구매완료 게시판 총 개수 
		@Override
		public int totalPayed() {
			int totCnt=0; 
			System.out.println("JhPaymentDetailDaoImplA totalPayed Starts...");
			try {
				totCnt=session.selectOne("jhPaymentPayedlistTotA");
				System.out.println("JhPaymentDetailDaoImplA totalPayed() totCnt "+totCnt);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totalPayed Exception->"+e.getMessage());
			}
			return totCnt;
		}
		
		// 구매완료 조회 
		@Override
		public List<Payment> listPayed(Payment payment) {
			List<Payment> listPayed=null; 
			System.out.println("JhPaymentDetailDaoImplA listPayed Starts...");
			try {
				listPayed=session.selectList("jhPaymentPayedlistA",payment);
				System.out.println("JhPaymentDetailDaoImplA listPayed.size()->"+listPayed.size());
				
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listPayed Exception->"+e.getMessage());
			}
			return listPayed;
		}

		// 배송주문 검색 적용 후 총합
		@Override
		public int totalPayedKey(Payment payment) { 
			int totCnt=0;
			System.out.println("JhPaymentDetailDaoImplA totalPayedKey Starts...");
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
			try {
				totCnt=session.selectOne("jhPaymentPayedlistKeyTotA", payment);
				System.out.println("JhPaymentDetailDaoImplA totalPayedKey() totCnt "+totCnt);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totalPayedKey Exception->"+e.getMessage());
			}
			return totCnt;
		}
		
		// 배송주문 검색 적용 후 리스트 
		@Override
		public List<Payment> listPayedKey(Payment payment) { 
			List<Payment> listPayedKey=null; // 
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
			try {
				listPayedKey=session.selectList("jhPaymentPayedlistKeyA", payment);
				System.out.println("JhPaymentDetailDaoImplA listPayedKey.size()->"+listPayedKey.size());
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listPayedKey Exception->"+e.getMessage());
			}
			return listPayedKey;
		}

		// 송장번호 불러오기 아작스
		@Override
		public int delSeq(int payno) { 
			int delivno=0;
			System.out.println("JhPaymentDetailDaoImplA delSeq Starts...");
			try {
				delivno=session.selectOne("jhPaymentDelSeqCallA",payno);
				System.out.println("JhPaymentDetailDaoImplA delSeq delivno->"+delivno);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA delSeq Exception->"+e.getMessage());
			}
			return delivno;
		}

		// 송장번호 입력 아작스 
		@Override
		public void updateDel(Payment payment) {
			System.out.println("JhPaymentDetailDaoImplA delSeq Starts...");
			
			try {
				session.update("jhPaymentUpdDelA", payment);
				System.out.println("After JhPaymentDetailDaoImplA updateDel payment.getDeliveryno()->"+payment.getDeliveryno());
				
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA delSeq Exception->"+e.getMessage());
			}
			
			}
		//어드민 배송처리 끝------------------------------------------------------------

		
		
		//어드민 환불 시작---------------------------------------------------------------
		//환불완료, 환불요청 개수 
		@Override
		public int totalref() {
			int totCnt=0;
			System.out.println("JhPaymentDetailDaoImplA totalref Starts...");
			try {
				totCnt=session.selectOne("jhPaymentReflistTotA");
				System.out.println("JhPaymentDetailDaoImplA totalref totCnt "+totCnt);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totalref Exception->"+e.getMessage());
			}
			return totCnt;
		}

		//환불완료, 환불요청 전체 리스트 
		@Override
		public List<Payment> listRef(Payment payment) {
			List<Payment> listRef=null; 
			System.out.println("JhPaymentDetailDaoImplA listRef Starts...");
			try {
				listRef=session.selectList("jhPaymentReflistA",payment);
				System.out.println("JhPaymentDetailDaoImplA listRef->"+listRef.size());
				
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listRef Exception->"+e.getMessage());
			}
			return listRef;
		}

		//환불완료, 환불요청 검색 후 총 개수
		@Override
		public int totalRefKey(Payment payment) {  
			int totCnt=0;
			System.out.println("JhPaymentDetailDaoImplA totalRefKey Starts...");
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
			try {
				totCnt=session.selectOne("jhPaymentReflistKeyTotA", payment);
				System.out.println("JhPaymentDetailDaoImplA totalRefKey totCnt "+totCnt);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totalRefKey Exception->"+e.getMessage());
			}
			return totCnt;
		}

		//환불완료, 환불요청 검색 후 리스트 
		@Override
		public List<Payment> listRefKey(Payment payment) { 
			List<Payment> listRefKey=null; 
			System.out.println("JhPaymentDetailDaoImplA listRefKey Starts...");
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
			try {
				listRefKey=session.selectList("jhPaymentReflistKeyA", payment);
				System.out.println("JhPaymentDetailDaoImplA listRefKey.size()->"+listRefKey.size());
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listRefKey Exception->"+e.getMessage());
			}
			return listRefKey;
		}

		// 환불요청->환불완료 아작스 
		@Override
		public  void refStsChg(Payment payment) {
			System.out.println("JhPaymentDetailDaoImplA refStsChg Starts...");
			try {
				session.selectOne("jhPaymentRefStsChgA", payment);
				System.out.println("JhPaymentDetailDaoImplA refStsChg payment.getPay_sts()->"+payment.getPay_sts());
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplArefStsChg Exception->"+e.getMessage());
			}
		}
		//어드민 환불 끝 ---------------------------------------------------------------
		
		
		
		
		//어드민 판매목록 시작------------------------------------------------------------
		// 판매목록 총 개수 
		@Override
		public int totSel() { 
			int totSel=0;
			System.out.println("JhPaymentDetailDaoImplA totalref Starts...");
			try {
				totSel=session.selectOne("jhPaymentSellistTotA");
				System.out.println("JhPaymentDetailDaoImplA totSel "+totSel);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totSel Exception->"+e.getMessage());
			}
			return totSel;
		}

		// 판매목록 메인 화면 전체 리스트 
		@Override
		public List<Payment> listSel(Payment payment) { 
			List<Payment> listSel=null;
			System.out.println("JhPaymentDetailDaoImplA listSel Starts...");
			try {
				listSel=session.selectList("jhPaymentSellistA",payment);
				System.out.println("JhPaymentDetailDaoImplA listSel->"+listSel.size());
				
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listSel Exception->"+e.getMessage());
			}
			return listSel;
		}

		 // 판매목록 검색 후 총합 
		@Override
		public int totSelKey(Payment payment) { 
			int totSelKey=0;
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
			System.out.println("JhPaymentDetailDaoImplA totSelKey Starts...");
			try {
				totSelKey=session.selectOne("jhPaymentSellistKeyTotA", payment);
				System.out.println("JhPaymentDetailDaoImplA totSelKey "+totSelKey);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totSelKey Exception->"+e.getMessage());
			}
			return totSelKey;
		}

		// 판매목록 검색 후 리스트 
		@Override
		public List<Payment> listSelKey(Payment payment) { 
			List<Payment> listSelKey=null; 
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
			System.out.println("JhPaymentDetailDaoImplA listSelKey Starts...");
			try {
				listSelKey=session.selectList("jhPaymentSellistKeyA",payment);
				System.out.println("JhPaymentDetailDaoImplA listSelKey->"+listSelKey.size());
				
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listSel Exception->"+e.getMessage());
			}
			return listSelKey;
		}
		//어드민 판매목록 끝--------------------------------------------------------------

		
		
		
		
		
		//어드민 취소 시작---------------------------------------------------------------
		// 취소 총 개수 
		@Override
		public int totalCan() {
			int totalCan=0;
			System.out.println("JhPaymentDetailDaoImplA totalCan Starts...");
			try {
				totalCan=session.selectOne("jhPaymentCanlistTotA");
				System.out.println("JhPaymentDetailDaoImplA totalCan "+totalCan);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totalCan Exception->"+e.getMessage());
			}
			return totalCan;
		}

		 // 취소 메인 화면 전체 리스트 
		@Override
		public List<Payment> listCan(Payment payment) { 
			List<Payment> listCan=null;
			System.out.println("JhPaymentDetailDaoImplA listCan Starts...");
			
			try {
				listCan=session.selectList("jhPaymentCanlistA",payment);
				System.out.println("JhPaymentDetailDaoImplA listCan->"+listCan.size());
				
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listCan Exception->"+e.getMessage());
			}
			return listCan;
		}
		
		// 취소 검색 후 총합 
		@Override
		public int totCanKey(Payment payment) { 
			int totCanKey=0;
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
//			
			System.out.println("JhPaymentDetailDaoImplA totCanKey Starts...");
			try {
				totCanKey=session.selectOne("jhPaymentCanlistKeyTotA", payment);
				System.out.println("JhPaymentDetailDaoImplA totCanKey "+totCanKey);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA totCanKey Exception->"+e.getMessage());
			}
			return totCanKey;
		}

		
		
		// 취소 검색 후 리스트 
		@Override
		public List<Payment> listCanKey(Payment payment) { 
			List<Payment> listCanKey=null; 
			if(payment.getKeyword1()==null) {
				payment.setKeyword1("");
			}
			if(payment.getSdate()==null || payment.getSdate().equals("")) {
				payment.setSdate("1900-01-01");
				System.out.println("sdate->"+payment.getSdate());
			}
			if(payment.getEdate()==null || payment.getEdate().equals("")) {
				payment.setEdate("2050-12-30");
				System.out.println("edate->"+payment.getEdate());
			}
//			if(payment.getName()==null) {
//				payment.setName("%");
//			}
//			if(payment.getPayno()==0) {
//				payment.setPayno('%');
//			}
			System.out.println("JhPaymentDetailDaoImplA listCanKey Starts...");
			try {
				listCanKey=session.selectList("jhPaymentCanlistKeyA",payment);
				System.out.println("JhPaymentDetailDaoImplA listCanKey->"+listCanKey.size());
				
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplA listCanKey Exception->"+e.getMessage());
			}
			return listCanKey;
		}
		//어드민 취소 끝---------------------------------------------------------------
	
	}
