package com.oracle.oBootS20220603.service.sw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.sw.SwEventDaoU;
import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;

@Service
public class SwEventServiceImplU implements SwEventServiceU {
	
	@Autowired
	private SwEventDaoU es;

	@Override
	public List<Event> listEventU(Event event) {
		List<Event> EventUlist = null;
		EventUlist = es.listEventU(event);
		
		return EventUlist;
	}

	@Override
	public Event uSwEvtdetailp(int evt_no) {
		Event eventp = null;
		eventp = es.uSwEvtdetailp(evt_no);
		
		return eventp;
	}

	@Override
	public List<Product> p_list(int evt_no) {
		List<Product> p_list = null;
		p_list = es.p_list(evt_no);
		
		return p_list;
	}

	@Override
	public Event uSwEvtdetailc(int evt_no) {
		Event eventc = null;
		eventc = es.uSwEvtdetailc(evt_no);
		
		return eventc;
	}

	@Override
	public List<CouponMaster> c_list(int evt_no) {
		List<CouponMaster> c_list = null;
		c_list = es.c_list(evt_no);
		
		return c_list;
	}

	@Override
	public int coup_insert(Coupon coupon) {
		// TODO Auto-generated method stub
		int insert_count = 0;
		insert_count = es.coup_insert(coupon);
		System.out.println("SwEventServiceImplU coup_insert insert_count->"+insert_count);
		
		return insert_count;
	}
}
