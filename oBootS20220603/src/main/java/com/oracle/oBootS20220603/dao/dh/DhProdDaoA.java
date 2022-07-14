package com.oracle.oBootS20220603.dao.dh;

import java.util.List;

import com.oracle.oBootS20220603.model.Product;

public interface DhProdDaoA {

	int           totalC();   // 전체상품 개수
	int           totalSO();  // 품절된 상품 개수
	int       	  totalST();  // 판매중지 상품개수
	int        	  totalS();	  // 판매중 상품개수	
	int           total();    // 상품 총 개수(페이징)
	List<Product> listProd(Product product);  		  // 관리자 상품리스트_
	int           adminProdDelAJAX(Product product);  // 관리자 상품리스트에서 상품삭제
	List<Product> listProduct(Product product);		  // keyword 조회 _상품명, 상품번호
	int           keywordtotal(Product product);	  // keyword 토탈 개수
	int           prodInsert(Product product);  	  // 상품등록 insert
	Product       prodDetail(int prodno); 			  // 상품 뷰 이동
	int           delete(int prodno);    			  // 어드민 상품삭제
	int           prodUpdate(Product product); 		  // 어드민 상품수정 
	
	////////////게시 비게시/////////////////////////
	int chgstsyn(int prodno);
	int chgstsny(int prodno);
	////////////게시 비게시/////////////////////////
	
}
