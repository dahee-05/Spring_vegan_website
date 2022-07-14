package com.oracle.oBootS20220603.service.ch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.ch.ChCouponDao;
import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;


@Service
public class ChCouponServiceImplA implements ChCouponService {

	@Autowired
	private ChCouponDao chcd;
	
	@Override
	public List<CouponMaster> listCoupMaster(CouponMaster couponMaster) {

		List<CouponMaster> coupMasList =null;
		System.out.println("ChCouponServiceImplA listCoupMaster Start..." );
		coupMasList = chcd.listCoupMaster(couponMaster);
		System.out.println("ChCouponServiceImplA listCoupMaster coupMasList.size()->" +  coupMasList.size());
		return coupMasList;
	}

	@Override
	public CouponMaster detailCoupMaster(int coupno) {
		System.out.println("ChCouponServiceImplA detailCoupMaster Start..." );
		CouponMaster couponMaster = chcd.detailCoupMaster(coupno);
		
		return couponMaster;
	}

	@Override
	public void updateCoupMaster(CouponMaster couponMaster) {
		
		System.out.println("ChCouponServiceImplA detailCoupMaster Start..." );
		chcd.updateCoupMaster(couponMaster);
		
	}

	@Override
	public void insertCoupMaster(CouponMaster couponMaster) {

		System.out.println("ChCouponServiceImplA insertCoupMaster Start..." );
		chcd.insertCoupMaster(couponMaster);
		
	}

	@Override
	public void deleteCoupMaster(int coupno) {

		System.out.println("ChCouponServiceImplA deleteCoupMaster Start..." );
		chcd.deleteCoupMaster(coupno);
		
	}

	@Override
	public List<Coupon> userCoupList(String id) {

		System.out.println("ChCouponServiceImplA userCoupList Start..." );
		List<Coupon> listUserCoupon = chcd.userCoupList(id);
		System.out.println("ChCouponServiceImplA coup_list.size()->"+listUserCoupon.size());
		
		return listUserCoupon;
	}



}
