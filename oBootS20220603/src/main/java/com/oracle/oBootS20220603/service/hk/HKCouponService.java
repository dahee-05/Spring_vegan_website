package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.Payment;

public interface HKCouponService {

	List<Coupon> selectAll(String id);

	int updateCoupSts(Payment payment);

	int coupGetChk(Coupon coupon);

}
