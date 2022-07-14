package com.oracle.oBootS20220603.dao.hk;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;

@Repository
public class HKMemberDaoImpl implements HKMemberDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public Member selectOne(String id) {
		System.out.println("HKMemberDaoImpl selectOne Start...");
		Member member = null;
		
		try {
			member = session.selectOne("HKMemberSelectOne", id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return member;
	}

	@Override
	public int updateMileage(Payment payment) {
		System.out.println("HKMemberDaoImpl updateMileage Start...");
		int result = 0;
		
		try {
			result = session.update("HKUpdateMileage", payment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int updateAddress(Payment payment) {
		System.out.println("HKMemberDaoImpl updateAddress Start...");
		int result = 0;
		
		try {
			result = session.update("HKUpdateAddress", payment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int pwCheck(Member member) {
		System.out.println("HKMemberDaoImpl pwCheck Start...");
		int result = 0;
		
		try {
			result = session.selectOne("HKPwCheck", member);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int changePw(Member member) {
		System.out.println("HKMemberDaoImpl changePw Start...");
		int result = 0;
		
		try {
			result = session.update("HKChangePw", member);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

}
