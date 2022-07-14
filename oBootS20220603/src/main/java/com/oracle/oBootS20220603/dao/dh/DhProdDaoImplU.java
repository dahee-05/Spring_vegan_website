package com.oracle.oBootS20220603.dao.dh;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.model.Review;
import com.oracle.oBootS20220603.model.Wishlist;
import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.Member;

@Repository
public class DhProdDaoImplU implements DhProdDaoU {

	@Autowired
	private SqlSession session;
	
	// 상품 총 개수
	@Override
	public int total() {
		int tot = 0;
		System.out.println("DhProductDaoImpl total Start..");
		try {
			tot = session.selectOne("dhProdTotal"); 
			System.out.println("DhProductDaoImpl total tot -> " + tot);
		} catch (Exception e) {
			System.out.println("DhProductDaoImpl total Exception -> " + e.getMessage());
		}
		return tot;
	}
	
	// 카테고리별 상품 총 개수
	@Override
	public int cateTotal(String cg) {
		int cgnum = Integer.parseInt(cg);
		int ctot = 0;
		System.out.println("DhProductDaoImpl cateTotal Start..");
		try {
			ctot = session.selectOne("dhCateTotal", cgnum); 
			System.out.println("DhProductDaoImpl cateTotal tot -> " + ctot);
		} catch (Exception e) {
			System.out.println("DhProductDaoImpl cateTotal Exception -> " + e.getMessage());
		}
		return ctot;
	}
	
	// 상품 카테고리
	@Override
	public String category(String cg) {
		System.out.println("DhProductDaoImpl category Start..");
		int cgnum = Integer.parseInt(cg);
		String category = "";
		switch (cgnum) {
		case 0: category = "ALL"; break;
		case 1: category = "비건식품"; break;
		case 2: category = "생활용품"; break;
		case 3: category = "고체뷰티"; break;
		case 4: category = "스킨케어"; break;
		}
		System.out.println("DhProductDaoImpl category -> " + category);
		return category;
	}

	// 상품 리스트
	@Override
	public List<Product> listProd() {
		System.out.println("DhProductDaoImpl listProd Start..");
		List<Product> prodList = null;
		try {
			prodList = session.selectList("dhProdSelect");
			System.out.println("DhProductDaoImpl listProd prodList.size() -> " + prodList.size());
			for (Product rtnProd : prodList) {
				System.out.println("DhProductDaoImpl listProd rtnProd.size()->" + rtnProd.getProdno());
				System.out.println("DhProductDaoImpl listProd rtnProd.size()->" + rtnProd.getProd_name());
				System.out.println("DhProductDaoImpl listProd rtnProd.size()->" + rtnProd.getProd_price());
				System.out.println("DhProductDaoImpl listProd rtnProd.size()->" + rtnProd.getDc_rate());
				System.out.println("DhProductDaoImpl listProd rtnProd.size()->" + rtnProd.getSale_price());
			}
		} catch (Exception e) {
			System.out.println("DhProductDaoImpl listProd Exception->" + e.getMessage());
		}
		return prodList;
	}
	
	// 카테고리별 상품 리스트
	@Override
	public List<Product> listCateProd(String cg) {
		System.out.println("DhProductDaoImpl listCateProd Start..");
		int cgnum = Integer.parseInt(cg);
		List<Product> prodCateList = null;
		try {
			prodCateList = session.selectList("dhCateProdSelect", cgnum);
			System.out.println("DhProductDaoImpl listCateProd prodCateList.size() -> " + prodCateList.size());
			for (Product rtnProd : prodCateList) {
				System.out.println("DhProductDaoImpl listCateProd rtnProd.size()->" + rtnProd.getProdno());
				System.out.println("DhProductDaoImpl listCateProd rtnProd.size()->" + rtnProd.getProd_name());
				System.out.println("DhProductDaoImpl listCateProd rtnProd.size()->" + rtnProd.getProd_price());
				System.out.println("DhProductDaoImpl listCateProd rtnProd.size()->" + rtnProd.getDc_rate());
				System.out.println("DhProductDaoImpl listCateProd rtnProd.size()->" + rtnProd.getSale_price());
			}
		} catch (Exception e) {
			System.out.println("DhProductDaoImpl listCateProd Exception->" + e.getMessage());
		}
		return prodCateList;
	}
	
	// 상품 상세페이지
		@Override
		public Product prodDetail(Product product) {
			System.out.println("DhProductDaoImpl prodDetail Start...");
			Product product1 =new Product();
			System.out.println("DhProductDaoImpl prodDetail prodno-> " +product.getProdno());
			
			try {
				product = session.selectOne("dhProdno",product.getProdno());
			} catch (Exception e) {
				System.out.println("DhProductDaoImpl prodDetail Exception->" +e.getMessage());
			}
			return product;
		}

		// 장바구니 insert
		@Override
		public int sh_insert(Basket basket) {
			int result=0;
			System.out.println("DhProductDaoImpl sh_insert Start..");
			System.out.println("DhProductDaoImpl shoppingCart product.getBas_qty1()->"+basket.getBas_qty());
			result = session.insert("dhinsertBasket",basket);
			System.out.println("DhProductDaoImpl sh_insert result-> "+result);
			return result;
		}
		
		//장바구니 리스트
		@Override
		public List<Product> listcart(Product product) {
			List<Product> productList=null;
			System.out.println("DhProductDaoImpl listcart Start...");
			System.out.println("DhProductDaoImpl listcart +product.getId()..."+product.getId());
			try {
				productList = session.selectList("dhshCartList",product);
				System.out.println("DhProductDaoImpl listcart productList.size()->"+productList.size());
			} catch (Exception e) {
				System.out.println("DhProductDaoImpl listcart Exception->" +e.getMessage());
			}
			return productList;
		}
		
		//장바구니 삭제
		@Override
		public int cartDelete(Product product) {
			System.out.println("DhProductDaoImpl cartDelete Start...");
			int result=0;
			System.out.println("DhProductDaoImpl cartDelete prodno->" +product);
			try {
				result =session.delete("dhcartDel",product); //성공하면 1 실패하면0
				System.out.println("DhProductDaoImpl cartDelete result->"+result);
			} catch (Exception e) {
				System.out.println("DhProductDaoImpl cartDelete Exception->"+e.getMessage());
			}
			return result;
		}
		
		// 상단 장바구니 권한
		@Override
		public int prodDetailCount(Product product3) {
			System.out.println("DhProductDaoImpl prodDetailCount Start...");
			int result=0;
			System.out.println("DhProductDaoImpl prodDetailCount result->"+result);
			try {
				result=session.selectOne("dhshLMember",product3);
				System.out.println("DhProductDaoImpl prodDetailCount result->"+result);
			} catch (Exception e) {
				System.out.println("DhProductDaoImpl prodDetailCount Exception->"+e.getMessage());
			}
			return result;
		}
		
///////////////////////// 위시리스트 시작 ***********************************************************
//id prodno 모두가 같은 품목이 있는지에 따라 ef heart 변화
		@Override
		public int wishCount(Wishlist wishlist) {
			System.out.println("DhProductDaoImpl wishCount Start...");
			int result=0;
			try {
				result=session.selectOne("findProdViewHeart", wishlist);
				System.out.println("DhProductDaoImpl wishCount result->"+result);
			} catch (Exception e) {
				System.out.println("DhProductDaoImpl wishCount Exception->" +e.getMessage());
				}
			return result;
			}
		
		// 위시리스트 하트(빈->채) 바뀜
		@Override
		public int emptyToFill(Wishlist wishlist) {
			System.out.println("DhProductDaoImpl emptyToFill Start...");
			int result=0;
			try {
				result=session.insert("WishchgEmpToFill", wishlist);
				System.out.println("DhProductDaoImpl emptyToFill result->"+result);
			} catch (Exception e) {
				System.out.println("DhProductDaoImpl emptyToFill Exception->" +e.getMessage());
				}
			return result;
			}
		
		// [아작스] 위시리스트 하트(채->빈) 바뀜
		@Override
		public int fillToEmpty(Wishlist wishlist) {
			System.out.println("DhProductDaoImpl fillToEmpty Start...");
			int result=0;
		try {
			result=session.delete("WishchgFillToEmpty", wishlist);
			System.out.println("DhProductDaoImpl fillToEmpty result->"+result);
		} catch (Exception e) {
			System.out.println("DhProductDaoImpl fillToEmpty Exception->" +e.getMessage());
			}
		return result;
		}
		
		// 위시리스트 내역 보여주기
		@Override
		public List<Product> wishList(String id) {
			System.out.println("DhProductDaoImpl wishList Start...");
			List<Product> idWishList=null;
		try {
			idWishList=session.selectList("idWishListAll", id);
			System.out.println("DhProductDaoImpl idWishList idWishList->"+idWishList.size());
		} catch (Exception e) {
			System.out.println("DhProductDaoImpl idWishList Exception->" +e.getMessage());
			}
		return idWishList;
			}
		///////////////////////// 위시리스트 끝 ***********************************************************

		@Override
		public List<Review> prod_rv(int prodno) {
			System.out.println("DhProductDaoImpl prod_rv Start...");
			List<Review> prod_rvlist=null;
			try {
				prod_rvlist= session.selectList("dhProd_rv",prodno);
				System.out.println("DhProductDaoImpl prod_rv product2->"+prod_rvlist);
			} catch (Exception e) {
				System.out.println("DhProductDaoImpl prod_rv Exception->" +e.getMessage());
			}
			return prod_rvlist;
		}
		}
