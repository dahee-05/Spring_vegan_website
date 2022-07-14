package com.oracle.oBootS20220603.model;
//조유진작성
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private String 	id;		
	private String 	pw;
	private String 	name;
	private String 	email;
	private String 	tel;
	private int 	status;			//회원상태
	private Date 	reg_date;		//가입날짜
	private int 	mileage;		//마일리지
	private String 	post_code;		//우편번호
	private String 	address;		//주소
	private String 	det_address;	//상세주소
	private int 	subs;			//구독여부
	private int 	bank;			//계좌은행
	private String 	account;		//계좌번호
	private Date 	sub_start;		//구독시작일
	private Date 	sub_exp;		//구독만료일
	private int 	birth;			//생년월일
	
	
	
	// 조회용
	private int start;
	private int end;
	private int rn;
	
	private String new_pw;
	
}
