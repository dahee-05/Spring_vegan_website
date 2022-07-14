package com.oracle.oBootS20220603.service.sw;

import java.util.List;

import com.oracle.oBootS20220603.model.Coupon;
import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;

public interface SwEventServiceU {

	List<Event> listEventU(Event event);

	Event uSwEvtdetailp(int evt_no);

	List<Product> p_list(int evt_no);

	Event uSwEvtdetailc(int evt_no);

	List<CouponMaster> c_list(int evt_no);

	int coup_insert(Coupon coupon);

}
