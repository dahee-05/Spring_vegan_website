package com.oracle.oBootS20220603.service.hk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.hk.HKPaymentDao;
import com.oracle.oBootS20220603.model.Payment;

@Service
public class HKPaymentServiceImpl implements HKPaymentService {
	
	@Autowired
	private HKPaymentDao pd;

	@Override
	public int insertPayment(Payment payment) {
		System.out.println("HKPaymentServiceImpl insertPayment Start...");
		int result = pd.insertPayment(payment);
		
		return result;
	}

	@Override
	public int selectPaynoById(String id) {
		System.out.println("HKPaymentServiceImpl selectPaynoById Start...");
		int payno = pd.selectPaynoById(id);
		return payno;
	}

	@Override
	public Payment selectLastPayment(Payment p_payment) {
		System.out.println("HKPaymentServiceImpl selectLastPayment Start...");
		Payment payment = pd.selectLastPayment(p_payment);
		
		return payment;
	}

	@Override
	public int deleteFailPayment(Payment p_payment) {
		System.out.println("HKPaymentServiceImpl deleteFailPayment Start...");
		int result = pd.deleteFailPayment(p_payment);
		return result;
	}

	@Override
	public int updatePaydate(Payment payment) {
		System.out.println("HKPaymentServiceImpl updatePaydate Start...");
		int result = pd.updatePaydate(payment);
		return result;
	}
	
	
}
