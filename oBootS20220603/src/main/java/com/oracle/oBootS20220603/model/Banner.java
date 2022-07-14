package com.oracle.oBootS20220603.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Banner {
	private int evt_no;
	private String banner_img;
	
	// 추가
	private String new_banner_img;
	private int new_evt_no;
}
