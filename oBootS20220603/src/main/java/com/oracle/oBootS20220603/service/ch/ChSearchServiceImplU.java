package com.oracle.oBootS20220603.service.ch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.oracle.oBootS20220603.dao.ch.ChSearchDao;
import com.oracle.oBootS20220603.model.Product;

@Service
public class ChSearchServiceImplU implements ChSearchService {
	
	@Autowired
	private ChSearchDao chsd = null;


	@Override
	public List<Product> listSearchProduct(String search) {
		
		List<Product> searchProdList = null;
		System.out.println("ChSearchServiceImplU listSearchProduct Start..." );
		searchProdList = chsd.listSearchProduct(search);
		System.out.println("ChSearchServiceImplU listSearchProduct searchProdList.size()->" +  searchProdList.size());
		
		
		return searchProdList;
	}

}
