package com.oracle.oBootS20220603.service.hk;

import com.oracle.oBootS20220603.model.Payment;

public interface HKPaymentService {

	int insertPayment(Payment payment);

	int selectPaynoById(String id);

	Payment selectLastPayment(Payment p_payment);

	int deleteFailPayment(Payment p_payment);

	int updatePaydate(Payment payment);

}
