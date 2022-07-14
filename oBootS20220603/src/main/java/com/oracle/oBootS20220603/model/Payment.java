package com.oracle.oBootS20220603.model;
// 이 부분 본인 domain 이름으로 수정 

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
	private int payno;
	private String id;
	private Date pay_date;
	private int org_amount;	// total_amount
	private int real_amount;
	private int coupno;
	private int mile_use;
	private int mile_save;
	private int delivery_fee;
	private int deliveryno;
	private String del_post_code;
	private String del_address;
	private String del_daddress;
	private String rec_name;
	private String rec_phone;
	private Date del_date;
	private int pay_sts;
	private int refund_reason;
	private String refund_account;
	private Date refund_sdate;
	private Date refind_edate;
	private Date cancel_date;
	
	
	// 검색용 (김지호)
	private String search; // 검색어(주문번호, 회원이름) select 
	private String keyword1; // 검색어(주문번호, 회원이름) option
	private String sdate; // 검색어 (시작 날짜) 
	private String edate; // 검색어 (끝 날짜)
	private String pageNum;
	private int start;
	private int end;
	
	// 리스트 조회(김지호)
	private String name; 
	private int buy_qty;
	private String prod_name; 
	private String brand_name;
	private int rv_status;
	private String th_img;
	private int prod_price;
	private int dc_rate;
	private int prodno;
	private String writer_id;

	
}
