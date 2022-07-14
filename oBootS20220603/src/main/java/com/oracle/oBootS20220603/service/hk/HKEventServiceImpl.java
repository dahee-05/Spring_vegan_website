package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.hk.HKEventDao;
import com.oracle.oBootS20220603.model.Event;

@Service
public class HKEventServiceImpl implements HKEventService {

	@Autowired
	private HKEventDao ed;
	
	@Override
	public List<Event> selectActiveEvt() {
		System.out.println("HKEventServiceImpl selectActiveEvt Start...");
		List<Event> evt_list = ed.selectActiveEvt();
		
		return evt_list;
	}

	@Override
	public int getEvtType(int evt_no) {
		System.out.println("HKEventServiceImpl getEvtType Start...");
		int result = ed.getEvtType(evt_no);
		
		return result;
	}

}
