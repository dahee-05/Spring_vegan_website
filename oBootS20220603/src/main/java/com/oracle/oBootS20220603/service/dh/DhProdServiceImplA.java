package com.oracle.oBootS20220603.service.dh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.dh.DhProdDaoA;
import com.oracle.oBootS20220603.model.Product;

@Service
public class DhProdServiceImplA implements DhProdServiceA {

	@Autowired
	private DhProdDaoA dhpd;
	
	// 전체 상품 수
	@Override
	public int totalC() {
		System.out.println("DhProductServiceImplA totalC Start...");
		int result=dhpd.totalC();
		return result;
	}
	// 품절된 상품 개수
	@Override
	public int totalSO() {
		System.out.println("DhProductServiceImplA totalSO Start...");
		int result=dhpd.totalSO();
		return result;
	}
	// 판매중지 상품개수
	@Override
	public int totalST() {
		System.out.println("DhProductServiceImplA totalST Start...");
		int result = dhpd.totalST();
		return result;
	}
	// 판매중인 상품개수
	@Override
	public int totalS() {
		System.out.println("DhProductServiceImplA totalS Start...");
		int result = dhpd.totalS();
		return result;
	}
	
	// 어드민 상품 리스트
	@Override
	public List<Product> listProd(Product product) {
		List<Product> prodList = null;
		System.out.println("DhProdServiceImplA listProd Start..");
		prodList = dhpd.listProd(product);
		System.out.println("DhProdServiceImplA listProd prodList.size()->" + prodList.size());
		return prodList;
	}
	
	// 어드민 상품리스트 ALL 상품 총 개수
	@Override
	public int total() {
		System.out.println("DhProdServiceImplA total Start..");
		int totCnt = dhpd.total();
		System.out.println("DhProdServiceImplA total -> " + totCnt);
		return totCnt;
	}
		
	// 관리자 상품리스트에서 상품삭제
	@Override
	public int adminProdDelAJAX(Product product) {
		System.out.println("DhProdServiceImplA adminProdDelAJAX Start...");
		System.out.println("DhProdServiceImplA adminProdDelAJAX prodno "+product.getProdno());
		int result=dhpd.adminProdDelAJAX(product);
		System.out.println("DhProdServiceImplA adminProdDelAJAX result->"+result);
		return result;
	}
		
	// 키워드 상품 개수
	@Override
	public int keywordtotal(Product product) {
		System.out.println("DhProdServiceImplA keywordtotal Start..");
		int totCnt=dhpd.keywordtotal(product);
		System.out.println("DhProdServiceImplA keywordtotal totCnt->" +totCnt);
		return totCnt;
	}
	
	// keyword 조건조회 _ 상품명, 상품번호
	@Override
	public List<Product> listProduct(Product product) {
		List<Product> productList=null;
		System.out.println("DhProdServiceImplA listProduct Start...");
		System.out.println("DhProdServiceImplA  listProduct pg.getStart()=>" +product.getStart());
		productList = dhpd.listProduct(product);
		System.out.println("DhProdServiceImplA listProduct productList->"+productList);
		return productList;
	}
	
	// 어드민 상품 상세
	@Override
	public Product prodDetail(int prodno) {
		System.out.println("DhProdServiceImplA prodDetail Start..");
		Product product = dhpd.prodDetail(prodno);
		return product;
	}
	
    // 상품 등록 insert
	@Override
	public int prodInsert(Product product) {
		System.out.println("DhProdServiceImplA prodInsert Start..");
		System.out.println("DhProdServiceImplA prodInsert prod_poststs -> " + product.getProd_poststs());
		int result = 0;
		result = dhpd.prodInsert(product);
		return result;
	}
		
	// 어드민 상품 삭제
	@Override
	public int delete(int prodno) {
		System.out.println("DhProdServiceImplA delete Start...");
		int result= dhpd.delete(prodno);
		System.out.println("DhProdServiceImplA delete result->"+result); // 성공하면 1 실패하면 0
		return result;
	}
	
	// 어드민 상품 수정
	@Override
	public int prodUpdate(Product product) {
		System.out.println("DhProdServiceImplA prodUpdate Start...");
		int result=0;
		result=dhpd.prodUpdate(product);
		System.out.println("DhProdServiceImplA prodUpdate result(업데이트 성공개수)->" +result);
		return result;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 게시에서 비게시 아작스 
	@Override
	public int chgstsyn(int prodno) {
		System.out.println("DhProdServiceImplA chgstsyn Starts...");
		int result=dhpd.chgstsyn(prodno);
		System.out.println("DhProdServiceImplA chgstsyn result->"+result);
		return result;
		}
	
	// 비게시에서 게시로 아작스 
	@Override
	public int chgstsny(int prodno) {
		System.out.println("DhProdServiceImplA chgstsny Starts...");
		int result=dhpd.chgstsny(prodno);
		System.out.println("DhProdServiceImplA chgstsny result->"+result);
		return result;
		}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
