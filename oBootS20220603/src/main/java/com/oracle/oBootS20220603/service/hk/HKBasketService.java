package com.oracle.oBootS20220603.service.hk;

import java.util.List;

import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.PaymentDetail;

public interface HKBasketService {

	List<Basket> selectAll(String id);

	int deletePurchasedItem(PaymentDetail pd);

	int countItems(String id);

}
