package com.oracle.oBootS20220603.service.sw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.sw.SwEvt_CouponDao;
import com.oracle.oBootS20220603.model.Event;

@Service
public class SwEvt_CouponServiceImplA implements SwEvt_CouponService {
	
	@Autowired
	private SwEvt_CouponDao ec;

	@Override
	public int insert(Event evt) {
		
		int result = ec.insert(evt);
		
		return result;
		
		
	}

}
