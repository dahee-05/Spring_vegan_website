package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import com.oracle.oBootS20220603.model.Event;

public interface HKEventService {

	List<Event> selectActiveEvt();

	int getEvtType(int evt_no);

}
