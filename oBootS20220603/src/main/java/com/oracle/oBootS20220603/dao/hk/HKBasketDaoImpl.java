package com.oracle.oBootS20220603.dao.hk;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.PaymentDetail;

@Repository
public class HKBasketDaoImpl implements HKBasketDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<Basket> selectAll(String id) {
		System.out.println("HKBasketDaoImpl selectAll Start...");
		List<Basket> b_list = null;
		
		try {
			b_list = session.selectList("HKBasketSelectAll", id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return b_list;
	}

	@Override
	public int deletePurchasedItem(PaymentDetail pd) {
		System.out.println("HKBasketDaoImpl deletePurchasedItem Start...");
		int result = 0;
		
		try {
			result = session.delete("HKDeletePurchasedItem", pd);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public int countItems(String id) {
		System.out.println("HKBasketDaoImpl countItems Start...");
		int result = 0;
		
		try {
			result = session.selectOne("HKCountItems", id);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
}
