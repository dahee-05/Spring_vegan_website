package com.oracle.oBootS20220603.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private int prodno;
	private int category;
	private String brand_name;
	private String prod_name;
	private int prod_price;
	private int stock;
	private int sale_qty;
	private Date prod_reg;
	private int prod_poststs;
	private int prod_salests;
	private String th_img;
	private String main_img1;
	private String main_img2;
	private String prod_info;
	
	
	// 조회용
	private int dc_rate;
	private int sale_price;
	// 상품 view 바로구매 수량
	private int quantity;
	// 장바구니 구매 수량
	private int bas_qty;
	
	private String memberId; // 임시 회원 아이디 가져오기
	
	private String search; // 검색어 (상품명, 상품번호) select 
	private String keyword1; // option //상품번호
	private String keyword2; //상품명
	
	//장바구니 조회용 임시 아이디
	private String id;
	
	// 조회용
	private int start;
	private int end;
	
	
}
