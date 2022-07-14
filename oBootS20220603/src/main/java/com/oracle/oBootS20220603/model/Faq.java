package com.oracle.oBootS20220603.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Faq {
	private int 	boardno;
	private String	board_title;
	private String	board_content;
	private Date	board_date;
	private int		board_category;
	
	//조회용
	private String pageNum;
	private int start;
	private int end;
	
}
