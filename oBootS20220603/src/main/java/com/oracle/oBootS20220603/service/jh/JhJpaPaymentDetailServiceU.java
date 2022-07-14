package com.oracle.oBootS20220603.service.jh;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.jh.JhJpaPaymentDetailRepositoryU;
import com.oracle.oBootS20220603.domain.jh.Member;

@Service
@Transactional
public class JhJpaPaymentDetailServiceU {
	// JPA 사용 -----------------------------------------------------------	
	private final JhJpaPaymentDetailRepositoryU jr;
	
	public JhJpaPaymentDetailServiceU(JhJpaPaymentDetailRepositoryU jr) {
		this.jr=jr;
	}

	// 구독전 -> 구독 정보 입력
	public Member updSubInfo(Member member) { // 구독 정보 입력 (구독전0)
		System.out.println("JhJpaPaymentDetailServiceU updSubInfo Starts...");
		
		Member member1=jr.updSubInfo(member);
		
		System.out.println("JhJpaPaymentDetailServiceU updSubInfo After member1.getSubs() ->"+member1.getSubs());
		System.out.println("JhJpaPaymentDetailServiceU updSubInfo After member1.getSub_start() ->"+member1.getSub_start());
		System.out.println("JhJpaPaymentDetailServiceU updSubInfo After member1.getSub_exp()->"+member1.getSub_exp());
		
		return member1;
	}

	// 구독중 -> 구독정보 열람 
	public Member readSubInfo(String id) {
		System.out.println("JhJpaPaymentDetailServiceU readSubInfo Starts...");
		
		Member member=new Member();
		
		member=jr.readSubInfo(id);
		
		return member;
	}

	 // 구독 중 -> 구독해지 
	public int subCanU(String id) {
		System.out.println("JhJpaPaymentDetailServiceU subCanU Starts...");
		
		int result=jr.subCanU(id);
		System.out.println("After JhJpaPaymentDetailServiceU result-> "+result);
		
		return result;
	}
}
