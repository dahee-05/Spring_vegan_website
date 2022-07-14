package com.oracle.oBootS20220603.dao.hk;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.PaymentDetail;
import com.oracle.oBootS20220603.model.Product;

@Repository
public class HKProductDaoImpl implements HKProductDao {

	@Autowired
	private SqlSession session;
	
	public List<Product> selectBestProduct() {
		System.out.println("HKProductDaoImpl selectBestProduct Start...");
		List<Product> p_list = null;
		
		try {
			p_list = session.selectList("HKSelectBestProduct");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return p_list;
	}

	@Override
	public Product selectOne(int prodno) {
		System.out.println("HKProductDaoImpl selectOne Start...");
		Product product = null;
		
		try {
			product = session.selectOne("HKSelectOne", prodno);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return product;
	}

	@Override
	public List<Product> selectBasketProduct(HashMap<String, Object> param_map) {
		System.out.println("HKProductDaoImpl selectBasketProduct Start...");
		List<Product> p_list = null;
		
		try {
			p_list = session.selectList("HKSelectBasketProduct", param_map);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return p_list;
	}

	@Override
	public int increaseSaleQty(PaymentDetail paymentDetail) {
		System.out.println("HKProductDaoImpl increaseSaleQty Start...");
		int result = 0;
		
		try {
			result = session.update("HKIncreaseSaleQty", paymentDetail);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	
}
