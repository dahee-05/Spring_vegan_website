package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.hk.HKPaymentDetailDao;
import com.oracle.oBootS20220603.model.Payment;
import com.oracle.oBootS20220603.model.PaymentDetail;

@Service
public class HKPaymentDetailServiceImpl implements HKPaymentDetailService {
	
	@Autowired
	private HKPaymentDetailDao pdd;

	@Override
	public int insertPaymentDetail(PaymentDetail pd) {
		System.out.println("HKPaymentDetailServiceImpl insertPaymentDetail Start...");
		int result = pdd.insertPaymentDetail(pd);
		
		return result;
	}

	@Override
	public List<PaymentDetail> selectLastPaymentDetail(Payment payment) {
		System.out.println("HKPaymentDetailServiceImpl selectLastPaymentDetail Start...");
		List<PaymentDetail> purchased_list = pdd.selectLastPaymentDetail(payment);
		return purchased_list;
	}
	
	
}
