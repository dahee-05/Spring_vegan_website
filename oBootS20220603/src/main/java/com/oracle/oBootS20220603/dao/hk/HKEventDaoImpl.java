package com.oracle.oBootS20220603.dao.hk;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Event;

@Repository
public class HKEventDaoImpl implements HKEventDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Event> selectActiveEvt() {
		System.out.println("HKEventDaoImpl selectActiveEvt Start...");
		List<Event> evt_list = null;
		
		try {
			evt_list = session.selectList("HKActiveEvtSelect");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return evt_list;
	}

	@Override
	public int getEvtType(int evt_no) {
		System.out.println("HKEventDaoImpl selectActiveEvt Start...");
		int result = 0;
		
		try {
			result = session.selectOne("HKGetEvtType", evt_no);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

}
