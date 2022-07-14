package com.oracle.oBootS20220603.service.ch;

import java.util.List;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Product;

public interface ChCouponService {

	List<CouponMaster> 		listCoupMaster(CouponMaster couponMaster);

	CouponMaster 			detailCoupMaster(int coupno);

	void 					updateCoupMaster(CouponMaster couponMaster);

	void 					insertCoupMaster(CouponMaster couponMaster);

	void 					deleteCoupMaster(int coupno);

	List<Coupon> 			userCoupList(String id);

	
	
	
	
	
	
	

}
