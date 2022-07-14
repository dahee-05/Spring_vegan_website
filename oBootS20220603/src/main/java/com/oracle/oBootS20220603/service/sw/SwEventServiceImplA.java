package com.oracle.oBootS20220603.service.sw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.sw.SwEventDao;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;

@Service
public class SwEventServiceImplA implements SwEventService {
	
	@Autowired
	private SwEventDao es;

	@Override
	public int total() {
		System.out.println("SwFaqServiceImpl Start total...");
		int totCnt = es.total();
		System.out.println("SwFaqServiceImpl total totCnt->"+totCnt);
		return totCnt;
	}

	@Override
	public List<Event> listEvent(Event event) {
		List<Event> eventList = null;
		eventList = es.listEvent(event);
		
		return eventList;
	}

	@Override
	public Event aSwEvtdetailp(int evt_no) {
		Event event = null;
		event = es.aSwEvtdetailp(evt_no);
		
		return event;
	}

	@Override
	public List<Product> p_list(int evt_no) {
		List<Product> p_list = null;
		p_list = es.p_list(evt_no);
		
		return p_list;
	}

	@Override
	public int aSwEventdelete(int evt_no) {
		int result = 0;
		System.out.println("SwFaqServiceImpl aSwFaqdelete Start...");
		result = es.aSwEventdelete(evt_no);
		
		return result;
	}

	@Override
	public Event aSwEvtdetailc(int evt_no) {
		Event eventc = null;
		eventc = es.aSwEvtdetailc(evt_no);
		
		return eventc;
	}

	@Override
	public List<CouponMaster> c_list(int evt_no) {
		List<CouponMaster> c_list = null;
		c_list = es.c_list(evt_no);
		
		return c_list;
	}

	@Override
	public int aSwEventwritep(Event event) {
		int result = es.aSwEventwritep(event);
		System.out.println("SwEventServiceImpl aSwEventwritep result->"+result);
		
		return result;
	}

	@Override
	public List<Product> p_list1(Product product) {
		List<Product> p_list1 = null;
		
		p_list1 = es.p_list1(product);
		
		return p_list1;
	}

	@Override
	public Event aSwEventwritepp(Event event) {
		
		Event result = es.aSwEventwritepp(event);
		System.out.println("SwEventServiceImpl aSwEventwritepp result->"+result);
		
		return result;
	}

	@Override
	public List<CouponMaster> c_list1(CouponMaster couponmaster) {
		List<CouponMaster> c_list1 = null;
		
		c_list1 = es.c_list1(couponmaster);
		
		return c_list1;
	}

	@Override
	public int aSwEventwritec(Event event) {
		int result = es.aSwEventwritec(event);
		System.out.println("SwEventServiceIMpl aSwEventwritec result->"+result);
		
		return result;
	}

	@Override
	public Event aSwEventwritecc(Event event) {
		Event result = es.aSwEventwritecc(event);
		System.out.println("SwEventServiceImpl aSwEventwritecc result->"+result);
		
		return result;
	}

	

}
