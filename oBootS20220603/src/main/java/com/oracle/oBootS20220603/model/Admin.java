package com.oracle.oBootS20220603.model;
//조유진작성

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
	private String	ad_id;
	private String	ad_pw;
	private String	ad_name;
	private int 	ad_type;	//관리자유형
}
