package com.oracle.oBootS20220603.service.dh;

import java.util.List;

import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.model.Review;
import com.oracle.oBootS20220603.model.Wishlist;
import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.Member;

public interface DhProdServiceU {
	int total();
	
	int cateTotal(String cg);
	
	String category(String cg);

	List<Product> listProd();

	List<Product> listCateProd(String cg);

	Product          prodDetail(Product product);
	int              sh_insert(Basket basket);
	List<Product>    listcart(Product product);
	int              cartDelete(Product product);
	int              prodDetailCount(Product product3); 	//상단 장바구니 조회 -접근권한
	int              wishCount(Wishlist wishlist);      	// 위시리스트 시작
	int              emptyToFill(Wishlist wishlist);        // [아작스] 위시리스트 하트(빈->채) 바뀜
	int              fillToEmpty(Wishlist wishlist);		// [아작스] 위시리스트 하트(채->빈) 바뀜
	List<Product>    wishList(String id);					// 위시리스트 내역 보여주기
	List<Review>     listReview(int prodno);


	

	 	

}
