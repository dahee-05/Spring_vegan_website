package com.oracle.oBootS20220603.dao.ch;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;


@Repository
public class ChCouponDaoImplA implements ChCouponDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<CouponMaster> listCoupMaster(CouponMaster couponMaster) {

		List<CouponMaster> coupMasList = null;
		System.out.println("ChCouponDaoImplA listCoupMaster start..");
		
		try {
			coupMasList = session.selectList("chCoupMasListAll", couponMaster);
		}catch (Exception e){
			System.out.println("ChCouponDaoImplA listCoupMaster Exception->" + e.getMessage());
		}
		return coupMasList;
	}

	@Override
	public CouponMaster detailCoupMaster(int coupno) {

		CouponMaster couponMaster = null;
		System.out.println("ChCouponDaoImplA detailCoupMaster start..");
		
		try {
			couponMaster = session.selectOne("chCoupMasDetail", coupno);
		}catch (Exception e){
			System.out.println("ChCouponDaoImplA listCoupMaster Exception->" + e.getMessage());
		}
		
		return couponMaster;
	}

	@Override
	public void updateCoupMaster(CouponMaster couponMaster) {

		System.out.println("ChCouponDaoImplA updateCoupMaster start..");
		
		try {
			int result = session.update("chCoupMasUpdate", couponMaster);
		}catch (Exception e){
			System.out.println("ChCouponDaoImplA updateCoupMaster Exception->" + e.getMessage());
		}
		
	}

	@Override
	public void insertCoupMaster(CouponMaster couponMaster) {

		System.out.println("ChCouponDaoImplA insertCoupMaster start..");
		
		try {
			int result = session.insert("chCoupMasInsert", couponMaster);
		}catch (Exception e){
			System.out.println("ChCouponDaoImplA insertCoupMaster Exception->" + e.getMessage());
		}
		
	}

	@Override
	public void deleteCoupMaster(int coupno) {

		System.out.println("ChCouponDaoImplA deleteCoupMaster start..");
		
		try {
			int result = session.delete("chCoupMasDelete", coupno);
		}catch (Exception e){
			System.out.println("ChCouponDaoImplA deleteCoupMaster Exception->" + e.getMessage());
		}
		
	}

	@Override
	public List<Coupon> userCoupList(String id) {

		System.out.println("ChCouponDaoImplA userCoupList start..");
		
		List<Coupon>  listUserCoupon = null;
		
		try {
				listUserCoupon = session.selectList("chUserCoupList", id);
				System.out.println("ChCouponDaoImplA coup_list.size()->"+listUserCoupon.size());
				
			}catch (Exception e){
				
				System.out.println("ChCouponDaoImplA userCoupList Exception->" + e.getMessage());
		}
		
		return listUserCoupon;
	}



}
