package com.oracle.oBootS20220603.service.sw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.sw.SwEvt_productDao;
import com.oracle.oBootS20220603.model.Event;

@Service
public class SwEvt_productServiceImplA implements SwEvt_productService {

	@Autowired
	private SwEvt_productDao ed;

	@Override
	public int insert(Event evt) {
	
		
		int result = ed.insert(evt);
		
		return result;
		
	}
}
