package com.oracle.oBootS20220603.model;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
	
	private int evt_no;
	private String evt_name;
	private int dc_rate;
	private String evt_start;
	private String evt_end;
	private Date evt_regDate;
	private String evt_exp;
	private String evt_notice;
	private String evt_img;
	private int evt_sts;
	private int evt_type;
	
	//조회용
	private String pageNum;
	private int start;
	private int end;
	
	
	//할인이벤트
	private int prodno;
	private String brand_name;
	private String prod_name;
	private int prod_price;
	private String th_img;
	
	private int dc_price;
	private int sale_qty;
	
	//쿠폰이벤트
	private int coupno;
	private String coup_name;
	private int coup_type;
	private int coup_dc_rate;
	private int coup_dc_price;
	private int coup_req_price;
	private int coup_expdate;
}
