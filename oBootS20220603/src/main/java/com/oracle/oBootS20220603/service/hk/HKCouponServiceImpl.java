package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.hk.HKCouponDao;
import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.Payment;

@Service
public class HKCouponServiceImpl implements HKCouponService {
	
	@Autowired
	private HKCouponDao cd;

	@Override
	public List<Coupon> selectAll(String id) {
		System.out.println("HKCouponServiceImpl selectAll Start...");
		List<Coupon> coup_list = cd.selectAll(id);
		
		return coup_list;
	}

	@Override
	public int updateCoupSts(Payment payment) {
		System.out.println("HKCouponServiceImpl updateCoupSts Start...");
		int result = cd.updateCoupSts(payment);
		return result;
	}

	@Override
	public int coupGetChk(Coupon coupon) {
		System.out.println("HKCouponServiceImpl coupGetChk Start...");
		int result = cd.coupGetChk(coupon);
		
		return result;
	}

}
