package com.oracle.oBootS20220603.service.sw;

import java.util.List;

import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;

public interface SwEventService {

	int total();

	List<Event> listEvent(Event event);

	Event aSwEvtdetailp(int evt_no);

	List<Product> p_list(int evt_no);

	int aSwEventdelete(int evt_no);

	Event aSwEvtdetailc(int evt_no);

	List<CouponMaster> c_list(int evt_no);

	int aSwEventwritep(Event event);

	List<Product> p_list1(Product product);

	Event aSwEventwritepp(Event event);

	List<CouponMaster> c_list1(CouponMaster couponmaster);

	int aSwEventwritec(Event event);

	Event aSwEventwritecc(Event event);

	



}
