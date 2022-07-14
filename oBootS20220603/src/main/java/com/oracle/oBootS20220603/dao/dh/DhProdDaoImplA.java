package com.oracle.oBootS20220603.dao.dh;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.oracle.oBootS20220603.model.Product;

@Repository
public class DhProdDaoImplA implements DhProdDaoA {

	@Autowired
	private SqlSession session;
	
	//전체 상품개수
	@Override
	public int totalC() {
		int result =0;
		System.out.println("DhProductServiceImplA totalC Start...");
		try {
			result=session.selectOne("dhtotalCSelect");
			System.out.println("DhProductServiceImplA totalC ");
		} catch (Exception e) {
			System.out.println("DhProductServiceImplA totalC Exception->" +e.getMessage());
		}
		return result;
	}
	
	// 판매중인 상품개수
	@Override
	public int totalS() {
		int result=0;
		System.out.println("DhProductServiceImplA totalS Start...");
		try {
			result=session.selectOne("dhtotalSSelect");
		} catch (Exception e) {
			System.out.println("DhProductServiceImplA totalS Exception->" +e.getMessage());
		}
		return result;
	}
	
	//품절된 상품 개수
	@Override
	public int totalSO() {
		int result=0;
		System.out.println("DhProductServiceImplA totalSO Start...");
		try {
			result=session.selectOne("dhtotalSOSelect");
		} catch (Exception e) {
			System.out.println("DhProductServiceImplA totalSO Exception->" +e.getMessage());
		}
		return result;
	}
	
	// 판매중지 상품개수
	@Override
	public int totalST() {
		int result=0;
		System.out.println("DhProductServiceImplA totalST Start...");
		try {
			result=session.selectOne("dhtotalSTSelect");
		} catch (Exception e) {
			System.out.println("DhProductServiceImplA totalST Exception->" +e.getMessage());
		}
		return result;
	}
	
	// 어드민 상품 리스트 토탈 개수
	@Override
	public int total() {
		int tot = 0;
		System.out.println("DhProductDaoImplA total Start..");
		try {
			tot = session.selectOne("dhProdTotal"); 
			System.out.println("DhProductDaoImplA total tot -> " + tot);
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA total Exception -> " + e.getMessage());
		}
		return tot;
	}
	
	// 어드민 상품리스트 페이지
	@Override
	public List<Product> listProd(Product product) {
		System.out.println("DhProductDaoImplA listProd Start..");
		List<Product> prodList = null;
		try {
			prodList = session.selectList("dhAdminProdSelect", product);
			System.out.println("DhProductDaoImplA listProd prodList.size() -> " + prodList.size());
			for (Product rtnProd : prodList) {
				System.out.println("DhProductDaoImplA listProd rtnProd.size()->" + rtnProd.getProdno());
				System.out.println("DhProductDaoImplA listProd rtnProd.size()->" + rtnProd.getProd_name());
				System.out.println("DhProductDaoImplA listProd rtnProd.size()->" + rtnProd.getProd_price());
				System.out.println("DhProductDaoImplA listProd rtnProd.size()->" + rtnProd.getDc_rate());
				System.out.println("DhProductDaoImplA listProd rtnProd.size()->" + rtnProd.getSale_price());
			}
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA listProd Exception->" + e.getMessage());
		}
		return prodList;
	}
	
	// 관리자 상품리스트에서 상품삭제
	@Override
	public int adminProdDelAJAX(Product product) {
		System.out.println("DhProductDaoImplA adminProdDelAJAX Start..");
		System.out.println("DhProductDaoImplA adminProdDelAJAX prodno "+product.getProdno());
		int result=0;
		try {
			result=session.delete("dhDelete",product);
			System.out.println("DhProductDaoImplA adminProdDelAJAX result-> "+result);
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA adminProdDelAJAX Exception-> "+e.getMessage());
		}
		return result;
	}
	
	// 어드민 상품조회 키워드 토탈 개수
	@Override
	public int keywordtotal(Product product) {
		int tot=0;
		System.out.println("DhProductDaoImplA keywordtotal Start..");
//		System.out.println("DhProductDaoImplA keywordtotal product.getSdate()->"+product.getSdate());
//		System.out.println("DhProductDaoImplA keywordtotal product.getEdate()->"+product.getEdate());
			if( product.getKeyword1()== null || product.getKeyword1().equals(""))  
					product.setKeyword1("");
			if( product.getKeyword2()== null || product.getKeyword2().equals("")) 
					product.setKeyword2("");
//			if(product.getSdate()==null || product.getSdate().equals("")) {
//		    	product.setSdate("1900-01-01");
//				System.out.println("sdate->"+product.getSdate());
//			}
//			if(product.getEdate()==null || product.getEdate().equals("")) {
//				product.setEdate("2050-12-30");
//				System.out.println("edate->"+product.getEdate());
//			}
		try {
			tot=session.selectOne("dhAdminKeywordTotal",product);
			System.out.println("DhProductDaoImplA listProduct tot->" +tot);
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA listProduct Exception->" +e.getMessage());
		}
		return tot;
	}
	
	// keyword 조건조회 _ 상품명, 상품번호, 날짜조회
	@Override
	public List<Product> listProduct(Product product) {
		System.out.println("DhProductDaoImplA listProduct Start..");
		List<Product> productList2=null;
		System.out.println("DhProductDaoImplA listProduct product.getSearch()->" +product.getSearch());
		System.out.println("DhProductDaoImplA listProduct product.getKeyword2()->" +product.getKeyword2());
		System.out.println("DhProductDaoImplA listProduct product.getKeyword1()->" +product.getKeyword1());
			if(product.getKeyword1()== null || product.getKeyword1().equals(""))
						product.setKeyword1("");
		    if(product.getKeyword2()== null || product.getKeyword2().equals(""))
		    			product.setKeyword2("");
//		    if(product.getSdate()==null || product.getSdate().equals("")) {
//		    	product.setSdate("1900-01-01");
//				System.out.println("sdate->"+product.getSdate());
//			}
//			if(product.getEdate()==null || product.getEdate().equals("")) {
//				product.setEdate("2050-12-30");
//				System.out.println("edate->"+product.getEdate());
//			}
		try {
			productList2=session.selectList("dhAdminKeyword",product);
			System.out.println("DhProductDaoImplA listProduct productList2.size()->" +productList2.size());
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA listProduct Exception->" +e.getMessage());
		}
		return productList2;
	}
	
	
//	// keyword 조건조회 _ 상품명, 상품번호
//	@Override
//	public List<Product> listProduct(Product product) {
//		System.out.println("DhProductDaoImplA listProduct Start..");
//		List<Product> productList2=null;
//		System.out.println("DhProductDaoImplA listProduct product.getSearch()->" +product.getSearch());
//		System.out.println("DhProductDaoImplA listProduct product.getKeyword2()->" +product.getKeyword2());
//		System.out.println("DhProductDaoImplA listProduct product.getKeyword1()->" +product.getKeyword1());
//			if(product.getKeyword1()== null || product.getKeyword1().equals(""))
//						product.setKeyword1("");
//		    if(product.getKeyword2()== null || product.getKeyword2().equals(""))
//		    			product.setKeyword2("");
//		try {
//			productList2=session.selectList("dhAdminKeyword",product);
//			System.out.println("DhProductDaoImplA listProduct productList2.size()->" +productList2.size());
//		} catch (Exception e) {
//			System.out.println("DhProductDaoImplA listProduct Exception->" +e.getMessage());
//		}
//		return productList2;
//	}

	
	// 어드민 상품 상세
	@Override
	public Product prodDetail(int prodno) {
		System.out.println("DhProductDaoImplA prodDetail Start..");
		Product product = null;
		try {
			product = session.selectOne("dhProdDetail", prodno);
			System.out.println("DhProductDaoImplA prodDetail .getProd_name() -> " + product.getProd_name());
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA listProd Exception->" + e.getMessage());
		}
		return product;
	}
	
	// 상품 등록 insert
	@Override
	public int prodInsert(Product product) {
		System.out.println("DhProductDaoImplA prodInsert Start..");
		System.out.println("DhProductDaoImplA prodInsert prod_poststs -> " + product.getProd_poststs());
		int result = 0;
		try {
			result = session.insert("dhProdInsert", product);
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA prodInsert Exception->" + e.getMessage());
		}
		return result;
	}
	
	// 상품 삭제
	@Override
	public int delete(int prodno) {
		System.out.println("DhProductDaoImplA delete Start...");
		System.out.println("DhProductDaoImplA delete prodno1->" +prodno);
		int result=0;
		try {
			result=session.delete("dhprodnoDelete",prodno);
			System.out.println("DhProductDaoImplA delete result->" +result);
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA delete Exception->" +e.getMessage());
		}
		return result; // 성공하면 1 실패하면 0
	}

	// 어드민 상품 수정
	@Override
	public int prodUpdate(Product product) {
		System.out.println("DhProductDaoImplA prodUpdate Start...");
		int result=0;
		try {
			result=session.update("dhprodUpdate",product);
			System.out.println("DhProductDaoImplA prodUpdate result->"+result);
		} catch (Exception e) {
			System.out.println("DhProductDaoImplA prodUpdate Exception->" +e.getMessage());
		}
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 게시에서 비게시 아작스 
	@Override
	public int chgstsyn(int prodno) {
		System.out.println("DhProductDaoImplA chgstsyn Starts...");
		int result=0;
		
		try {
		result=session.update("dhChgstsyn",prodno);
		System.out.println("DhProductDaoImplA chgstsyn result->"+result);
		} catch (Exception e) {
		System.out.println("DhProductDaoImplA chgstsyn Exception-> "+e.getMessage());
		}
	return result;
	}
	
	// 비게시에서 게시로 아작스 
	@Override
	public int chgstsny(int prodno) {
		System.out.println("DhProductDaoImplA chgstsny Starts...");
		int result=0;
		
		try {
		result=session.update("dhChgstsny",prodno);
		System.out.println("DhProductDaoImplA chgstsny result->"+result);
		} catch (Exception e) {
		System.out.println("DhProductDaoImplA chgstsny Exception-> "+e.getMessage());
		}
	return result;
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
