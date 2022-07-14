package com.oracle.oBootS20220603.dao.ch;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Product;

@Repository
public class ChSearchDaoImplU implements ChSearchDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<Product> listSearchProduct(String search) {

		List<Product> searchProdList = null;
		System.out.println("ChSearchDaoImplU listSearchProduct start..");
		
		try {
			searchProdList = session.selectList("chSearchProdList", search);
		}catch (Exception e){
			System.out.println("ChSearchDaoImplU listSearchProduct Exception->" + e.getMessage());
		}
		
		
 		return searchProdList;
	}

}
