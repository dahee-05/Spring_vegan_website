package com.oracle.oBootS20220603.dao.sw;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;

@Repository
public class SwEventDaoImplU implements SwEventDaoU {

	@Autowired
	private SqlSession session;

	@Override
	public List<Event> listEventU(Event event) {
		List<Event> listEventU = null;
		try {
			listEventU = session.selectList("uSwEventListAll", event);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listEventU;
	}

	@Override
	public Event uSwEvtdetailp(int evt_no) {
		Event eventp = null;
		try {
			eventp = session.selectOne("uSwEvtdetailp", evt_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return eventp;
	}

	@Override
	public List<Product> p_list(int evt_no) {
		List<Product> p_list = null;
		try {
			p_list = session.selectList("uSwp_list", evt_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p_list;
	}

	@Override
	public Event uSwEvtdetailc(int evt_no) {
		Event eventc = null;
		try {
			eventc = session.selectOne("uSwEvtdetailc", evt_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return eventc;
	}

	@Override
	public List<CouponMaster> c_list(int evt_no) {
		List<CouponMaster> c_list = null;
		try {
			c_list = session.selectList("uSwc_list", evt_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c_list;
	}

	@Override
	public int coup_insert(Coupon coupon) {
		int coup_insert_result = 0;
		try {
			
			/* coupon.setCoup_expdate(5); */
			System.out.println("SwEventDaoImplU coup_insert coupon.getCoup_expdate()->"+coupon.getCoup_expdate());
			coup_insert_result = session.insert("couponInsert", coupon);
		} catch (Exception e) {
			System.out.println("SwEventDaoImplU coup_insert Exception->"+e.getMessage());
		}
		return coup_insert_result;
	}
	
}
