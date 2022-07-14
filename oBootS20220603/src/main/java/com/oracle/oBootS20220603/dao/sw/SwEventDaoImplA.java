package com.oracle.oBootS20220603.dao.sw;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;

@Repository
public class SwEventDaoImplA implements SwEventDao {

	@Autowired
	private SqlSession session;

	@Override
	public int total() {
		int tot = 0;
		System.out.println("SwEventDaoImpl Start total...");
		try {
			tot = session.selectOne("swEventTotal");
			
		} catch (Exception e) {
			System.out.println("SwEventDaoImpl total Exception->"+e.getMessage());
		}
		return tot;
	}

	@Override
	public List<Event> listEvent(Event event) {
		List<Event> eventList = null;
		try {
			eventList = session.selectList("aSwEventListAll", event);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return eventList;
	}

	@Override
	public Event aSwEvtdetailp(int evt_no) {
		Event eventp = null;
		try {
			eventp = session.selectOne("aSwEventSelOnep", evt_no);
		} catch (Exception e) {
			
		}
		return eventp;
	}

	@Override
	public List<Product> p_list(int evt_no) {
		List<Product> p_list = null;
		try {
			p_list = session.selectList("aSwp_list", evt_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return p_list;
	}

	@Override
	public int aSwEventdelete(int evt_no) {
		int result = 0;
		try {
			result = session.delete("aSwEventdelete",evt_no);
			System.out.println("SwEventDaoImpl aSwEventdelete result->"+result);
		} catch (Exception e) {
			System.out.println("SwEventDaoImpl aSwEventdelete Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public Event aSwEvtdetailc(int evt_no) {
		Event eventc = null;
		try {
			eventc = session.selectOne("aSwEventSelOnec", evt_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return eventc;
	}

	@Override
	public List<CouponMaster> c_list(int evt_no) {
		List<CouponMaster> c_list = null;
		try {
			c_list = session.selectList("aSwc_list", evt_no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return c_list;
	}

	@Override
	public int aSwEventwritep(Event event) {
		int result = 0;
		try {
			result = session.insert("aSwEventwritep", event);
		} catch (Exception e) {
			System.out.println("SwEventDaoImplA aSwEventList Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<Product> p_list1(Product product) {
		List<Product> p_list1 = null;
		try {
			p_list1 = session.selectList("aSwp_list1", product);
		} catch (Exception e) {
			System.out.println("SwEventDaoImplA p_list1 Exception->"+e.getMessage());
		}
		return p_list1;
	}

	@Override
	public Event aSwEventwritepp(Event event) {
		Event result = null;
		try {
			result= session.selectOne("aSwEventwritepp", event);
					
		} catch (Exception e) {
			System.out.println("SwEventDaoImplA aSwEventwritepp Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<CouponMaster> c_list1(CouponMaster couponmaster) {
		List<CouponMaster> c_list1 = null;
		try {
			c_list1 = session.selectList("aSwc_list1", couponmaster);
			
		} catch (Exception e) {
			System.out.println("SwEventDaoImplA c_list1 Exception->"+e.getMessage());
		}
		return c_list1;
	}

	@Override
	public int aSwEventwritec(Event event) {
		int result = 0;
		try {
			result = session.insert("aSwEventwritec", event);
		} catch (Exception e) {
			System.out.println("SwEventDaoImplA aSwEventList Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public Event aSwEventwritecc(Event event) {
		Event result = null;
		try {
			result = session.selectOne("aSwEventwritecc", event);
		} catch (Exception e) {
			System.out.println("SwEventDaoImplA aSwEventwritecc Exception->"+e.getMessage());
		}
		return result;
	}

}
