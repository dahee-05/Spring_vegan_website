package com.oracle.oBootS20220603.service.jh;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootS20220603.dao.jh.JhPaymentDetailDaoU;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;


@Service
public class JhPaymentDetailServiceImplU implements JhPaymentDetailServiceU {
	
	@Autowired
	private JhPaymentDetailDaoU pd;
	
	
	// 마이페이지 인터셉터 아이디 체크 
	// [회원인지] 결과 값은 있다면 1 , 없으면 0
	@Override
	public int memCount(String id) { 
		 System.out.println("JhPaymentDetailServiceImplU memCount id->"+id);
	     return pd.memCount(id);
	}

	// 마이페이지 구독정보 시작
	// [현재날짜>구독날짜] [그 후에 구독상태 추출] 아이디에서 현재날짜>구독날짜 구독상태 변경 후  구독 여부 추출 구매내역 페이지 다르게 하기 위함********** (변경 가능 - 로그인 아이디 정보)
	@Override
	public void subSts(Member member) {
		System.out.println("JhPaymentDetailServiceImplU subSts Starts...");
		pd.subSts(member); 
		System.out.println("JhPaymentDetailServiceImplU member.getSubs()->"+member.getSubs());
	}
	
	 //[마이페이지 메뉴에 이름 담기 위함] 
	@Override
	public String findName(String id) {
		String name=pd.findName(id);
		return name;
	}
	
	// 마이페이지 메인화면 시작
	// 1. 결제완료
	@Override
	public int selCount(String id) {
		System.out.println("JhPaymentDetailServiceImplU selCount Starts...");
		int selCount=pd.selCount(id);
		System.out.println("selCount->"+selCount);
		return selCount;
	}
	// 2. 취소완료
	@Override
	public int canCount(String id) {
		System.out.println("JhPaymentDetailServiceImplU canCount Starts...");
		int canCount=pd.canCount(id);
		System.out.println("canCount->"+canCount);
		return canCount;
	}
	// 3. 환불진행중
	@Override
	public int refingCount(String id) {
		System.out.println("JhPaymentDetailServiceImplU refingCount Starts...");
		int refingCount=pd.refingCount(id);
		System.out.println("refingCount->"+refingCount);
		return refingCount;
	}
	// 4. 환불완료
	@Override
	public int refCount(String id) {
		System.out.println("JhPaymentDetailServiceImplU refCount Starts...");
		int refCount=pd.refCount(id);
		System.out.println("refCount->"+refCount);
		return refCount;
	}
	// 5. 배송완료
	@Override
	public int delCount(String id) {
		System.out.println("JhPaymentDetailServiceImplU delCount Starts...");
		int delCount=pd.delCount(id);
		System.out.println("delCount->"+delCount);
		return delCount;
	}
	// 6. 구매확정 
	@Override
	public int selFixCount(String id) {
		System.out.println("JhPaymentDetailServiceImplU selFixCount Starts...");
		int selFixCount=pd.selFixCount(id);
		System.out.println("selFixCount->"+selFixCount);
		return selFixCount;
	}
	// 사용자 쿠폰 테이블 쿠폰 개수 
	@Override
	public int coupCount(String id) {
		System.out.println("JhPaymentDetailServiceImplU coupCount Starts...");
		int coupCount=pd.coupCount(id);
		System.out.println("coupCount->"+coupCount);
		return coupCount;
	}
	// 회원 테이블 마일리지 
	@Override
	public int mileTot(String id) {
		System.out.println("JhPaymentDetailServiceImplU mileTot Starts...");
		int mileTot=pd.mileTot(id);
		System.out.println("mileTot->"+mileTot);
		return mileTot;
	}

	// 마이페이지 메인화면 끝 
	
	

	// 마이페이지 구매내역 시작 ---------------------------------------------------
	// 1-1. 마이페이지 구매내역 (id를 세션에 담아옴_계속 쓸 수 있음 ) 테스트용 아이디 testuser 가지고 있음
	@Override
	public List<Payment> orderList(String id) {
		System.out.println("JhPaymentDetailServiceImplU orderList Starts...");
		List<Payment> orderList=pd.orderList(id);
		return orderList;
	}

	// 1-2. 구매 상세 보기 
	
	 // 상품 상세 정보 (공통) _ 배송지 등 
	@Override
	public Payment detailListC(int payno) {
		System.out.println("JhPaymentDetailServiceImplU detailListC Starts...");

		Payment detailListC=pd.detailListC(payno);
		
		System.out.println("JhPaymentDetailServiceImplU detailListC After"+detailListC.getDel_address());
		
		return detailListC;
	}

	// 상품 상세 정보 (공통) _ 쿠폰할인율
	@Override
	public CouponMaster dcAmount(int payno) {
		System.out.println("JhPaymentDetailServiceImplU dcAmount Starts...");
		
		CouponMaster dcAmount=pd.dcAmount(payno);
		System.out.println("JhPaymentDetailServiceImplU dcAmount After"+dcAmount.getCoup_dc_rate());
		
		return dcAmount;
		
	}

	
	// 상품 상세 정보 
	@Override
	public List<Payment> myOrderDetailU(int payno) {
		System.out.println("JhPaymentDetailServiceImplU myOrderDetailU Starts...");
		
		List<Payment> detailList=pd.myOrderDetailU(payno);
		System.out.println("JhPaymentDetailServiceImplU myOrderDetailU After "+detailList.get(0).getProd_name());
		
		return detailList;
	}


	// 2. [아작스] 구매완료 -> 취소 신청 변경 (상태 들고 와야함) 
	@Override
	public void chSelCanU(Payment payment) {
		System.out.println("JhPaymentDetailServiceImplU chSelCanU Starts...");
		pd.chSelCanU(payment);
		System.out.println("JhPaymentDetailServiceImplU chSelCanU payment.getPay_sts()->"+payment.getPay_sts());
	}

	
	// 3. [아작스] 구매완료/배송완료 -> 구매 확정 변경 (상태 들고 와야함)
	@Override
	public void chSelFixU(Payment payment) {
		System.out.println("JhPaymentDetailServiceImplU chSelDelFixU Starts...");
		pd.chSelFixU(payment);
		System.out.println("JhPaymentDetailServiceImplU chSelDelFixU payment.getPay_sts()->"+payment.getPay_sts());
		
	}

	// 4. [아작스] 배송완료 -> 구매 확정 변경 (상태 들고 와야함)
	// 팝업 페이지 띄우기 
	@Override
	public void chDelFixU(Payment payment) { 
		System.out.println("JhPaymentDetailServiceImplU chDelFixU Starts...");
		pd.chDelFixU(payment);
		System.out.println("JhPaymentDetailServiceImplU chDelFixU payment.getPay_sts()->"+payment.getPay_sts());
		
	}

	//5. [팝업] 배송완료 -> 환불 신청 변경 (상태 들고 와야함) 팝업창에서 입력한 정보 가지고 와서 수정 
	@Override
	public void chDelRefInfoU(Payment payment) { 
		System.out.println("JhPaymentDetailServiceImplU chDelRefInfoU Starts...");
		pd.chDelRefInfoU(payment);
		
	}

	
	
	// 로그인 시작 
	// 1. 로그인 아이디 비번 체크 (결과값 1, 0)
	@Override
	public int loginIdCheck(Member member) {
		System.out.println("JhPaymentDetailServiceImplU loginIdCheck Starts...");
		int result=pd.loginIdCheck(member);
		
		System.out.println("JhPaymentDetailServiceImplU result->"+result);
		return result;
	}

	// 1-4 로그인 비밀번호 찾기 아이디 체크 (결과값 0, 1) 
	@Override
	public int findPwIdCheck(String id) {
		System.out.println("JhPaymentDetailServiceImplU findPwIdCheck Starts...");
		int result=pd.findPwIdCheck(id);
		
		System.out.println("JhPaymentDetailServiceImplU findPwIdCheck result->"+result);
		return result;
	}
	
	
	// 1-5-1 [아작스] 로그인 비밀번호 찾기 이메일 발송 (이메일 찾기)
	@Override
	public String findEmail(String id) {
		System.out.println("JhPaymentDetailServiceImplU findEmail Starts....");
		
		String email=pd.findEmail(id);
		
		System.out.println("JhPaymentDetailServiceImplU findEmail email->"+email);
		
		return email;
	}

	// 1-5-2 [아작스] 로그인 비밀번호 찾기 이메일 발송 (임시비밀번호로 변경)
	@Override
	public int chgPwTemp(Member member) {
		System.out.println("JhPaymentDetailServiceImplU chgPwTemp Starts....");
		
		int result=pd.chgPwTemp(member);
		
		System.out.println("JhPaymentDetailServiceImplU chgPwTemp result->"+result);
		
		return result;
	}

	// 회원가입 성공 후 db insert
	@Override
	public int joinSuc(Member member) {
		System.out.println("JhPaymentDetailServiceImplU joinSuc Starts....");
		
		int result=pd.joinSuc(member);
		System.out.println("JhPaymentDetailServiceImplU joinSuc result->"+result);
		
		return result;
	}

	// 개인정보조회 (마이페이지)
	@Override
	public Member finduserinfo(String id) {
		System.out.println("JhPaymentDetailServiceImplU finduserinfo Starts....");
		
		Member member=null;
		member=pd.finduserinfo(id);
		
		return member;
	}
	// 개인정보 수정 
	@Override
	public int myinfoupdate(Member member) {
		System.out.println("JhPaymentDetailServiceImplU myinfoupdate Starts....");
		int result=0;
		result=pd.myinfoupdate(member);
		System.out.println("JhPaymentDetailServiceImplU myinfoupdate result->"+result);
		return result;
	}

	
}
