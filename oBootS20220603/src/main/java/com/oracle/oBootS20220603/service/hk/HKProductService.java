package com.oracle.oBootS20220603.service.hk;

import java.util.HashMap;
import java.util.List;

import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.PaymentDetail;
import com.oracle.oBootS20220603.model.Product;

public interface HKProductService {

	List<Product> selectBestProduct();

	Product selectOne(int prodno);

	List<Product> selectBasketProduct(HashMap<String, Object> param_map);

	int increaseSaleQty(PaymentDetail pd);

	
	
}
