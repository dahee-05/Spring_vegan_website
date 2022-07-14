package com.oracle.oBootS20220603.dao.hk;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Payment;

@Repository
public class HKPaymentDaoImpl implements HKPaymentDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public int insertPayment(Payment payment) {
		System.out.println("HKPaymentDaoImpl insertPayment Start...");
		int result = 0;
		
		try {
			result = session.insert("HKInsertPayment", payment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int selectPaynoById(String id) {
		System.out.println("HKPaymentDaoImpl insertPayment Start...");
		int payno = 0;
		
		try {
			payno = session.selectOne("HKselectPaynoById", id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return payno;
	}

	@Override
	public Payment selectLastPayment(Payment p_payment) {
		System.out.println("HKPaymentDaoImpl selectLastPayment Start...");
		Payment payment = null;
		
		try {
			payment = session.selectOne("HKSelectLastPayment", p_payment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return payment;
	}

	@Override
	public int deleteFailPayment(Payment p_payment) {
		System.out.println("HKPaymentDaoImpl deleteFailPayment Start...");
		int result = 0;
		
		try {
			result = session.delete("HKDeleteFailPayment", p_payment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int updatePaydate(Payment payment) {
		System.out.println("HKPaymentDaoImpl deleteFailPayment Start...");
		int result = 0;
		
		try {
			result = session.update("HKUpdatePaydate", payment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
}
