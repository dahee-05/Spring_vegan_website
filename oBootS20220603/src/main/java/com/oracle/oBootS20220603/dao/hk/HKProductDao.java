package com.oracle.oBootS20220603.dao.hk;

import java.util.HashMap;
import java.util.List;

import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.PaymentDetail;
import com.oracle.oBootS20220603.model.Product;

public interface HKProductDao {

	List<Product> selectBestProduct();

	Product selectOne(int prodno);

	List<Product> selectBasketProduct(HashMap<String, Object> param_map);

	int increaseSaleQty(PaymentDetail paymentDetail);

	
}
