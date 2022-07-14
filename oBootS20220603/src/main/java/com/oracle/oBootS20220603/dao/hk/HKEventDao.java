package com.oracle.oBootS20220603.dao.hk;

import java.util.List;

import com.oracle.oBootS20220603.model.Event;

public interface HKEventDao {

	List<Event> selectActiveEvt();

	int getEvtType(int evt_no);

}
