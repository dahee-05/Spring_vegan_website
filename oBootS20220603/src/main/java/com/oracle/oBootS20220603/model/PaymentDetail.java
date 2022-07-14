package com.oracle.oBootS20220603.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetail {
	private int payno;
	private int prodno;
	private int buy_qty;
	private int dc_rate;
	private int dc_price;
	private int rv_status;
	
	// 추가
	private String id;
}
