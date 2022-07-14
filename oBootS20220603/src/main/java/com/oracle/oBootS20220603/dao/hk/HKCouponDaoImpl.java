package com.oracle.oBootS20220603.dao.hk;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.Payment;

@Repository
public class HKCouponDaoImpl implements HKCouponDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<Coupon> selectAll(String id) {
		System.out.println("HKCouponDaoImpl selectAll Start...");
		List<Coupon> coup_list = null;
		
		try {
			coup_list = session.selectList("HKCouponSelectAll", id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return coup_list;
	}

	@Override
	public int updateCoupSts(Payment payment) {
		System.out.println("HKCouponDaoImpl updateCoupSts Start...");
		int result = 0;
		
		try {
			result = session.update("HKUpdateCoupSts", payment);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int coupGetChk(Coupon coupon) {
		System.out.println("HKCouponDaoImpl coupGetChk Start...");
		int result = 0;
		
		try {
			result = session.selectOne("HKCoupGetChk", coupon);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
}
