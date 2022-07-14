package com.oracle.oBootS20220603.service.hk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.hk.HKMemberDao;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;

@Service
public class HKMemberServiceImpl implements HKMemberService {
	
	@Autowired
	private HKMemberDao md;

	@Override
	public Member selectOne(String id) {
		System.out.println("HKMemberServiceImpl selectOne Start...");
		Member member = md.selectOne(id);
		
		return member;
	}

	@Override
	public int updateMileage(Payment payment) {
		System.out.println("HKMemberServiceImpl updateMileage Start...");
		int result = md.updateMileage(payment);
		return result;
	}

	@Override
	public int updateAddress(Payment payment) {
		System.out.println("HKMemberServiceImpl updateAddress Start...");
		int result = md.updateAddress(payment);
		return result;
	}

	@Override
	public int pwCheck(Member member) {
		System.out.println("HKMemberServiceImpl pwCheck Start...");
		int result = md.pwCheck(member);
		return result;
	}

	@Override
	public int changePw(Member member) {
		System.out.println("HKMemberServiceImpl changePw Start...");
		int result = md.changePw(member);
		return result;
	}
}
