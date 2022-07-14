package com.oracle.oBootS20220603.model;

import lombok.Data;

@Data
public class AmountVO {
	private Integer total, tax_free, vat, point, discount;
}
