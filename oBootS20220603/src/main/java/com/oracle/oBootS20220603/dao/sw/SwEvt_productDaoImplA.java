package com.oracle.oBootS20220603.dao.sw;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Event;

@Repository
public class SwEvt_productDaoImplA implements SwEvt_productDao {

	@Autowired
	private SqlSession session;

	@Override
	public int insert(Event evt) {
		int result = 0;
		try {
			result = session.insert("aSwEvtwritec", evt);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
