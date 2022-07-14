package com.oracle.oBootS20220603.dao.jh;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.domain.jh.Member;

@Repository
public class JhJpaPaymentDetailRepositoryImplU implements JhJpaPaymentDetailRepositoryU {
	// JPA 사용 -----------------------------------------------------------	
	private final EntityManager em;
	public JhJpaPaymentDetailRepositoryImplU(EntityManager em) {
		this.em=em;
	}
	

	// 구독 페이지 시작
	// 구독전 -> 구독폼 작성 
	@Override
	public Member updSubInfo(Member member) { // 구독 정보 입력 (구독전0)
		System.out.println("JhJpaPaymentDetailRepositoryImplU updSubInfo Starts...");
		
		LocalDate today=LocalDate.now();
		Date tDate=java.sql.Date.valueOf(today); // 오늘 날짜
		
		LocalDate exp=today.plusMonths(1);
		Date expDate=java.sql.Date.valueOf(exp); // 오늘 날짜 +1month
		
		System.out.println("expDate"+expDate);
		System.out.println("tDate"+tDate);
		
		Member member1=em.find(Member.class, member.getId());
		member1.setId(member.getId()); 			// 받아온 값1
		member1.setBank(member.getBank()); 		// 받아온 값2
		member1.setAccount(member.getAccount()); // 받아온 값3
		member1.setSubs(1); 			  			// 입력 값
		member1.setSub_start(tDate); 			// 입력 값
		member1.setSub_exp(expDate); 			// 입력 값
		member1.setPw(member1.getPw());
		
		em.persist(member1);
		System.out.println("member1.set After...");

		return member1;
	}



	// 구독 중  -> 정보 읽기 
	@Override
	public Member readSubInfo(String id) {
		System.out.println("JhJpaPaymentDetailRepositoryImplU readSubInfo Starts...");
		
		Member member=new Member();
		
		member=em.find(Member.class, id);
		System.out.println("JhJpaPaymentDetailRepositoryImplU member.getBank()"+member.getBank());
		System.out.println("JhJpaPaymentDetailRepositoryImplU member.getBank()"+member.getSub_exp());
		
		return member;
	}

	// 구독 중 -> 구독 해지 
	@Override
	public int subCanU(String id) { // 구독해지 
		System.out.println("JhJpaPaymentDetailRepositoryImplU subCanU Starts...");
		int result=0;
		Member member=em.find(Member.class, id); // id에 해당하는 객체를 찾음
		if(member!=null) {
			member.setSubs(0); // 구독해지
			em.persist(member);
			System.out.println("JhJpaPaymentDetailRepositoryImplU em.persist(member) 이후"+member.getId());
			result=1;
		}
		
		return result; // result는 아무런 의미 없음
	}
}
