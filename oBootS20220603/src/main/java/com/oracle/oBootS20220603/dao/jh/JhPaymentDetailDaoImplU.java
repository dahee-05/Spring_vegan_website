package com.oracle.oBootS20220603.dao.jh;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;


@Repository
public class JhPaymentDetailDaoImplU implements JhPaymentDetailDaoU {
	
	@Autowired
	private SqlSession session;
	
	

	// 아이디 체크 시작 -----------------------------------------------------------
	// 인터셉터 아이디 체크  [회원인지] 결과 값은 있다면 1 , 없으면 0
	@Override 
	public int memCount(String id) { 
		int result=0;
		System.out.println("JhPaymentDetailDaoImplU memCount id->"+id);
		
		if(id==null) {
			result=0;
		}else {
			try {
				result=session.selectOne("jhPaymentInterceptorU",id);
				System.out.println("JhPaymentDetailDaoImplU memCount jhPaymentInterceptorU result->"+result);
			} catch (Exception e) {
				System.out.println("JhPaymentDetailDaoImplU result Exception"+e.getMessage());
			}
		}
		return result;
	}

	// [현재날짜>구독날짜] [그 후에 구독상태 추출] 아이디에서 현재날짜>구독날짜 구독상태 변경 후  구독 여부 추출 ***************** (변경 가능 - 로그인 아이디 정보)
	@Override 
	public void subSts(Member member) {
		System.out.println("JhPaymentDetailDaoImplU subSts Starts...");
		
		try {
			session.selectOne("jhMemberSubStsU", member);
			System.out.println("JhPaymentDetailDaoImplU member.getSubs()->"+member.getSubs());
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU subSts Exception"+e.getMessage());
		}
	}
	 
	// [마이페이지 메뉴에 이름 담기 위함]
	@Override
	public String findName(String id) { 
		String name=null;
		try {
			name=session.selectOne("jhMemberFindNameU",id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU findName Exception->"+e.getMessage());
		}
		return name;
	}

	
	// 마이페이지 메인화면 시작
	// 1. 결제완료
	@Override
	public int selCount(String id) {
		System.out.println("JhPaymentDetailDaoImplU selCount Starts...");
		int selCount=0;
		try {
			selCount=session.selectOne("jhPaymentSelCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU selCount Exception->"+e.getMessage());
		}
		return selCount;
	}
	// 2. 취소완료
	@Override
	public int canCount(String id) {
		System.out.println("JhPaymentDetailDaoImplU canCount Starts...");
		int canCount=0;
		try {
			canCount=session.selectOne("jhPaymentCanCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU canCount Exception->"+e.getMessage());
		}
		return canCount;
	}
	// 3. 환불진행중
	@Override
	public int refingCount(String id) {
		System.out.println("JhPaymentDetailDaoImplU refingCount Starts...");
		int refingCount=0;
		try {
			refingCount=session.selectOne("jhPaymentRefingCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU refingCount Exception->"+e.getMessage());
		}
		return refingCount;
	}
	// 4. 환불완료
	@Override
	public int refCount(String id) {
		System.out.println("JhPaymentDetailDaoImplU refCount Starts...");
		int refCount=0;
		try {
			refCount=session.selectOne("jhPaymentRefCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU refCount Exception->"+e.getMessage());
		}
		return refCount;
	}
	// 5. 배송완료
	@Override
	public int delCount(String id) {
		System.out.println("JhPaymentDetailDaoImplU delCount Starts...");
		int delCount=0;
		try {
			delCount=session.selectOne("jhPaymentDelCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU delCount Exception->"+e.getMessage());
		}
		return delCount;
	}
	// 6. 구매확정 
	@Override
	public int selFixCount(String id) {
		System.out.println("JhPaymentDetailDaoImplU selFixCount Starts...");
		int selFixCount=0;
		try {
			selFixCount=session.selectOne("jhPaymentSelFixCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU selFixCount Exception->"+e.getMessage());
		}
		return selFixCount;
	}
	// 사용자 쿠폰 테이블 쿠폰 개수 
	@Override
	public int coupCount(String id) {
		System.out.println("JhPaymentDetailDaoImplU coupCount Starts...");
		int coupCount=0;
		try {
			coupCount=session.selectOne("jhCouponCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU coupCount Exception->"+e.getMessage());
		}
		return coupCount;
	}
	// 회원 테이블 마일리지
	@Override
	public int mileTot(String id) {
		System.out.println("JhPaymentDetailDaoImplU mileTot Starts...");
		int mileTot=0;
		try {
			mileTot=session.selectOne("jhMemberMileCountU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU mileTot Exception->"+e.getMessage());
		}
		return mileTot;
	}
	// 마이페이지 메인화면 끝 
	
	
	
	
	// 마이페이지 구매내역 시작 ---------------------------------------------------
	// 1-1. 마이페이지 구매내역 (id를 세션에 담아옴_계속 쓸 수 있음 ) 테스트용 아이디 testuser 가지고 있음
	@Override
	public List<Payment> orderList(String id) { // 구매내역 회원 id에 따른 구매상태 조회 
		List<Payment> orderList=null;
		System.out.println("JhPaymentDetailDaoImplU orderList Starts...");
		
		try {
			orderList=session.selectList("jhPaymentOrderListU", id);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU orderList Exception->"+e.getMessage());
		}
		return orderList;
	}

	
	// 1-2. 구매 상세 보기 
	// 상품 상세 정보 (공통) _ 배송지 등 
	@Override
	public Payment detailListC(int payno) {
		System.out.println("JhPaymentDetailDaoImplU detailListC Starts...");
		Payment detailListC=null;
		try {
			detailListC=session.selectOne("jhPaymentOrderCommonDetailU", payno);
			System.out.println("JhPaymentDetailDaoImplU detailListC After "+detailListC.getOrg_amount());
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU detailListC Exception->"+e.getMessage());
		}
		return detailListC;
	}

	// 상품 상세 정보 (공통) _ 쿠폰할인율
	@Override
	public CouponMaster dcAmount(int payno) {
		System.out.println("JhPaymentDetailDaoImplU dcAmount Starts...");
		CouponMaster dcAmount=new CouponMaster() ;
			// 1. couponmaster Count를 검증
			// 2. 1의 결과 따라  jhPaymentOrderCommonDetailCouponU 수행  없으면 NULL
			int result=session.selectOne("jhPaymentOrderCommonDetailCouponTotU", payno);
			System.out.println("jhPaymentOrderCommonDetailCouponU  dcAmount payno->"+payno);
			System.out.println("jhPaymentOrderCommonDetailCouponU dcAmount result -> "+result);

			if(result>0) {
				try {
					dcAmount=session.selectOne("jhPaymentOrderCommonDetailCouponU", payno);
					System.out.println("jhPaymentOrderCommonDetailCouponU dcAmount result>0 After -> "+dcAmount.getCoup_name());
				} catch (Exception e) {
					System.out.println("JhPaymentDetailDaoImplU dcAmount result>0 Exception->"+e.getMessage());
				}
				return dcAmount;
			} 
			return dcAmount;
	}

	
	// (for each) 상품 상세 정보 
	@Override
	public List<Payment> myOrderDetailU(int payno) {
		System.out.println("JhPaymentDetailDaoImplU myOrderDetailU Starts...");
		List<Payment> detailList=null;
		
		try {
			detailList=session.selectList("jhPaymentOrderDetailU",payno);
			System.out.println("JhPaymentDetailDaoImplU myOrderDetailU After "+detailList.get(0).getProd_name());
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU myOrderDetailU Exception->"+e.getMessage());
		}
		return detailList;
	}



	// 2. [아작스] 구매완료 -> 취소 신청 변경 (상태 들고 와야함) 
	@Override
	public void chSelCanU(Payment payment) {
		System.out.println("JhPaymentDetailDaoImplU chSelCanU Starts...");
		
		try {
			session.selectOne("jhPaymentChSelCanU", payment);
			System.out.println("JhPaymentDetailDaoImplU chSelCanU payment.getPay_sts()->"+payment.getPay_sts());
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU chSelCanU Exception->"+e.getMessage());
		}
	}


	// 3. [아작스] 구매완료 -> 구매 확정 변경 (상태 들고 와야함)
	@Override
	public void chSelFixU(Payment payment) {
		System.out.println("JhPaymentDetailDaoImplU chSelDelFixU Starts...");
		
		try {
			session.selectOne("jhPaymentChSelFixU", payment);
			System.out.println("JhPaymentDetailDaoImplU chSelDelFixU payment.getPay_sts()->"+payment.getPay_sts());
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU chSelDelFixU Exception->"+e.getMessage());
		}
		
	}


	// 4. [아작스] 배송완료 -> 구매 확정 변경 (상태 들고 와야함)
	@Override
	public void chDelFixU(Payment payment) {
		System.out.println("JhPaymentDetailDaoImplU chDelFixU Starts...");
		
		try {
			session.selectOne("jhPaymentChDelFixU", payment);
			System.out.println("JhPaymentDetailDaoImplU chDelFixU payment.getPay_sts()->"+payment.getPay_sts());
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU chDelFixU Exception->"+e.getMessage());
		}
		
	}


	// 5. [팝업] 배송완료 -> 환불 신청 변경 (상태 들고 와야함) 팝업창에서 입력한 정보 가지고 와서 수정 
	@Override
	public void chDelRefInfoU(Payment payment) {
		System.out.println("JhPaymentDetailDaoImplU chDelRefInfoU Starts...");
		try {
			session.update("jhPaymentDelRefInfoU", payment);
			
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU chDelRefInfoU Exception->"+e.getMessage());
		}
		
	}
	
	// 로그인 시작 
	// 1. 로그인 아이디 비번 체크 (결과값 1, 0)
	@Override
	public int loginIdCheck(Member member) {
		System.out.println("JhPaymentDetailDaoImplU loginIdCheck Starts...");
		int result=0;
		
		try {
			result=session.selectOne("jhMemberLoginCheckU", member);
			System.out.println("JhPaymentDetailDaoImplU result->"+result);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU loginIdCheck Exception->"+e.getMessage());
		}
		return result;
	}
	
	
	// 1-4 로그인 비밀번호 찾기 아이디 체크 (결과값 0, 1) 
	@Override
	public int findPwIdCheck(String id) {
		System.out.println("JhPaymentDetailDaoImplU findPwIdCheck Starts...");
		int result=0;
		
		try {
			result=session.selectOne("jhMemberFindPwIdCheckU", id);
			System.out.println("JhPaymentDetailDaoImplU findPwIdCheck result->"+result);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU findPwIdCheck Exception->"+e.getMessage());
		}
		return result;
	}

	// 1-5-1 [아작스] 로그인 비밀번호 찾기 이메일 발송 
	@Override
	public String findEmail(String id) {
		System.out.println("JhPaymentDetailDaoImplU findEmail Starts....");
		
		String email=null;
		
		try {
			email=session.selectOne("jhMemberFindPwEmailGetU", id);
			System.out.println("JhPaymentDetailDaoImplU findEmail email->"+email);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU findEmail Exception->"+e.getMessage());
		}
		return email;
	}

	
	// 1-5-2 [아작스] 로그인 비밀번호 찾기 이메일 발송 (임시비밀번호로 변경)
	@Override
	public int chgPwTemp(Member member) {
		System.out.println("JhPaymentDetailDaoImplU chgPwTemp Starts....");
		
		int result=0;
		
		try {
			result=session.update("jhMemberFindPwChgToTempPwU", member);
			System.out.println("JhPaymentDetailDaoImplU chgPwTemp result->"+result);
			
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU chgPwTemp Exception->"+e.getMessage());
		}
		return result;
	}

	// 회원가입 성공 후 db insert
	@Override
	public int joinSuc(Member member) {
		System.out.println("JhPaymentDetailDaoImplU joinSuc Starts....");
		
		int result=0;
		
		try {
			result=session.insert("jhMemberJoinInsertU", member);
			System.out.println("JhPaymentDetailDaoImplU joinSuc result->"+result);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU joinSuc Exception->"+e.getMessage());
		}
		return result;
	}

	// 개인정보조회 (마이페이지)
	@Override
	public Member finduserinfo(String id) {
		System.out.println("JhPaymentDetailDaoImplU finduserinfo Starts....");
		
		Member member=null;
		
		try {
			member=session.selectOne("userInfoFindMemberU", id);
			System.out.println("JhPaymentDetailDaoImplU finduserinfo member.getName()"+member.getName());
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU finduserinfo Exception->"+e.getMessage());
		}
		return member;
	}
	
	// 개인정보 수정 
	@Override
	public int myinfoupdate(Member member) {
		System.out.println("JhPaymentDetailDaoImplU myinfoupdate Starts....");
		
		int result=0;
		
		try {
			System.out.println("JhPaymentDetaSlDaoImplU myinfoupdate getId-->"+member.getId());
			System.out.println("JhPaymentDetaSlDaoImplU myinfoupdate getPost_code-->"+member.getPost_code());
			System.out.println("JhPaymentDetaSlDaoImplU myinfoupdate address-->"+member.getAddress());
			System.out.println("JhPaymentDetaSlDaoImplU myinfoupdate det_address-->"+member.getDet_address());
			result=session.update("userInfoUpdateMemberU", member);
			System.out.println("JhPaymentDetaSlDaoImplU myinfoupdate result-->"+result);
		} catch (Exception e) {
			System.out.println("JhPaymentDetailDaoImplU myinfoupdate Exception->"+e.getMessage());
		}
		return result;
	}

}
