package com.oracle.oBootS20220603.service.dh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.dh.DhProdDaoU;
import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.model.Review;
import com.oracle.oBootS20220603.model.Wishlist;
import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.Member;

@Service
public class DhProdServiceImplU implements DhProdServiceU {
	
	@Autowired
	private DhProdDaoU dhpd;
	
	// ALL 상품 총 개수
	@Override
	public int total() {
		System.out.println("DhProdServiceImpl total Start..");
		int totCnt = dhpd.total();
		System.out.println("DhProdServiceImpl total -> " + totCnt);
		return totCnt;
	}

	// 카테고리별 상품 총 개수
	@Override
	public int cateTotal(String cg) {
		System.out.println("DhProdServiceImpl cateTotal Start..");
		int cateTotal = dhpd.cateTotal(cg);
		System.out.println("DhProdServiceImpl cateTotal -> " + cateTotal);
		return cateTotal;
	}
	
	// 상품 카테고리
	@Override
	public String category(String cg) {
		System.out.println("DhProdServiceImpl category Start..");
		String category = dhpd.category(cg);
		System.out.println("DhProdServiceImpl category -> " + category);
		return category;
	}
	
	// 상품 리스트
	@Override
	public List<Product> listProd() {
		List<Product> prodList = null;
		System.out.println("DhProdServiceImpl listProd Start..");
		prodList = dhpd.listProd();
		System.out.println("DhProdServiceImpl listEmp prodList.size()->" + prodList.size());
		return prodList;
	}
	
	// 카테고리별 상품 리스트
	@Override
	public List<Product> listCateProd(String cg) {
		List<Product> prodCateList = null;
		System.out.println("DhProdServiceImpl listCateProd Start..");
		prodCateList = dhpd.listCateProd(cg);
		System.out.println("DhProdServiceImpl listCateProd prodCateList.size()->" + prodCateList.size());
		return prodCateList;
	}
	
	// 상품 상세페이지
		@Override
		public Product prodDetail(Product product) {
			System.out.println("DhProdServiceImpl prodDetail Start...");
			Product product1 =null;
			product1 = dhpd.prodDetail(product);
			return product1;
		}
		
		//장바구니 insert
		@Override
		public int sh_insert(Basket basket) {
			int result=0;
			System.out.println("DhProdServiceImpl prodDetail Start...");
			result=dhpd.sh_insert(basket);
			System.out.println("DhProdServiceImpl shList result->" +result);
			return result; 
		}
		
		//장바구니 리스트
		@Override
		public List<Product> listcart(Product product) {
			List<Product> productList =null;
			System.out.println("DhProdServiceImpl listcart Start..");
			productList =dhpd.listcart(product);
			System.out.println("DhProdServiceImpl listcart.size()-> " +productList.size());
			
			return productList;
		}
		
		// 장바구니 삭제
		@Override
		public int cartDelete(Product product) {
			System.out.println("DhProdServiceImpl cartDelete Start..");
			int result =0;
			result = dhpd.cartDelete(product);
			return result;
		}
		
		// 상단 장바구니 권한
		@Override
		public int prodDetailCount(Product product3) {
			int result=0;
			System.out.println("DhProdServiceImpl prodDetailCount Start...");
			result=dhpd.prodDetailCount(product3);
			System.out.println("DhProdServiceImpl prodDetailCount result->"+result);
			return result;
		}
		
///////////// 위시리스트 시작 ***********************************************************
	//id prodno 모두가 같은 품목이 있는지에 따라 ef heart 변화
	@Override
	public int wishCount(Wishlist wishlist) {
		System.out.println("DhProdServiceImpl wishCount Starts...");
		int result=dhpd.wishCount(wishlist);
		System.out.println("DhProdServiceImpl wishCount result->"+result);
		return result;
	}
	// 위시리스트 하트(빈->채) 바뀜
	@Override
	public int emptyToFill(Wishlist wishlist) {
		int result=dhpd.emptyToFill(wishlist);
		System.out.println("DhProdServiceImpl emptyToFill result->"+result);
		return result;
	}
	
	// [아작스] 위시리스트 하트(채->빈) 바뀜
	@Override
	public int fillToEmpty(Wishlist wishlist) {
		System.out.println("DhProdServiceImpl fillToEmpty Starts...");
		int result=dhpd.fillToEmpty(wishlist);
		System.out.println("DhProdServiceImpl fillToEmpty result->"+result);
		return result;
	}

	// 위시리스트 내역 보여주기
	@Override
	public List<Product> wishList(String id) {
		System.out.println("DhProdServiceImpl wishList Starts...");
		List<Product> idWishList=dhpd.wishList(id);
		return idWishList;
	}
	
	
	// 상품 상세페이지 리뷰
	@Override
	public List<Review> listReview(int prodno) {
		System.out.println("DhProdServiceImpl prod_rv Start..");
		List<Review> listReview =null;
		listReview = dhpd.prod_rv(prodno);
		System.out.println("DhProdServiceImpl listReview->"+listReview);
		return listReview;
	}
	
///////////// 위시리스트 끝 ***********************************************************
	}

