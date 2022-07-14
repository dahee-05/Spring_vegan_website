package com.oracle.oBootS20220603.dao.ch;

import java.util.List;

import com.oracle.oBootS20220603.model.Product;

public interface ChSearchDao {

	List<Product> 		listSearchProduct(String search);
	
	

}
