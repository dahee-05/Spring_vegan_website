package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import com.oracle.oBootS20220603.model.Payment;
import com.oracle.oBootS20220603.model.PaymentDetail;

public interface HKPaymentDetailService {

	int insertPaymentDetail(PaymentDetail pd);

	List<PaymentDetail> selectLastPaymentDetail(Payment payment);

}
