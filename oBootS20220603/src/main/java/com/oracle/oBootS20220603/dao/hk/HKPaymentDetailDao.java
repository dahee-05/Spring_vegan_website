package com.oracle.oBootS20220603.dao.hk;

import java.util.List;

import com.oracle.oBootS20220603.model.Payment;
import com.oracle.oBootS20220603.model.PaymentDetail;

public interface HKPaymentDetailDao {

	int insertPaymentDetail(PaymentDetail pd);

	List<PaymentDetail> selectLastPaymentDetail(Payment payment);

}
