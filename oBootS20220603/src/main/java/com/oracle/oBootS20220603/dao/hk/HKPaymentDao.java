package com.oracle.oBootS20220603.dao.hk;

import com.oracle.oBootS20220603.model.Payment;

public interface HKPaymentDao {

	int insertPayment(Payment payment);

	int selectPaynoById(String id);

	Payment selectLastPayment(Payment p_payment);

	int deleteFailPayment(Payment p_payment);

	int updatePaydate(Payment payment);

}
