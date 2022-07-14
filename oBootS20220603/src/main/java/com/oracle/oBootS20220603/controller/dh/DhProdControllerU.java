package com.oracle.oBootS20220603.controller.dh;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.model.Review;
import com.oracle.oBootS20220603.model.Wishlist;
import com.oracle.oBootS20220603.model.Basket;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.service.dh.DhProdServiceU;
import com.oracle.oBootS20220603.service.hk.HKBasketService;

@Controller
public class DhProdControllerU {
	private static final Logger logger = LoggerFactory.getLogger(DhProdControllerU.class);
	
	@Autowired
	private DhProdServiceU dhps;
	
	@Autowired
	private HKBasketService bas;
	
	// 상품 리스트
	@RequestMapping("prodList")
	public String prodList(Product product, String cg, Model model) {
		logger.info("DhProdControllerU prodList Start..");
		int total = dhps.total();								// ALL 상품 총 개수
		int cateTotal = dhps.cateTotal(cg);						// 카테고리별 상품 총 개수
		String category = dhps.category(cg);					// 카테고리명 가져오기
		List<Product> prodList = dhps.listProd();				// ALL 상품 리스트
		List<Product> prodCateList = dhps.listCateProd(cg);		// 카테고리별 상품 리스트
		
		System.out.println(prodList.get(0).getTh_img());
		// 회원아이디 임시 설정
//		model.addAttribute("memberId", "testuser");
//		model.addAttribute("memberId");
		
		model.addAttribute("total", total);
		model.addAttribute("cateTotal", cateTotal);
		model.addAttribute("cg", category);
		model.addAttribute("prodList", prodList);
		model.addAttribute("prodCateList", prodCateList);
		
		return "uDhProdList";
	}
	
	// 상품 상세페이지
	@RequestMapping("prodDetail") //product 
	public String prodDetail(Product product, Review review, HttpServletRequest request, Model model) { //model값안에 prodList와 그 외의 값 
		logger.info("DhProdControllerU prodDetail Start...");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		product.setId(id);
		System.out.println("DhProdControllerU prodDetail id->"+product.getId());
		System.out.println("DhProdControllerU product.prodno()->"+product.getProdno()); 
		Product product1 = dhps.prodDetail(product);
		List<Review> reviewList  = dhps.listReview(product.getProdno());
		int reviewCount = reviewList.size();
		System.out.println("DhProdControllerU reviewList->"+reviewList);
		System.out.println("DhProdControllerU product.prodno() back->"+product1);
////////////////////////////////////위시리스트 시작 ***********************************************************		 
		// 위시리스트 하트 용 추가 시작
		System.out.println("DhProdControllerU prodDetail id->"+id);
		int result=0;
		
		if(id!=null && !id.equals("")) {
			Wishlist wishlist=new Wishlist();
			wishlist.setId(id);
			wishlist.setProdno(product.getProdno());
		
			//id prodno 모두가 같은 품목이 있는지에 따라 하트의 변화를 줄 생각
			result=dhps.wishCount(wishlist);
			// id가 있을 때 (위시리스트에 물건이 있으면 1, 없으면 0)
			System.out.println("DhProdControllerU wishCount result->"+result);
		
		} else {
			result=-1;
			// id가 없을 때 (로그인 화면)
		}
			// 위시리스트 하트 용 추가 끝
		model.addAttribute("reviewCount", reviewCount );
		model.addAttribute("review", reviewList );
		model.addAttribute("product", product1 );
		model.addAttribute("result", result);
		
		return "uDhProdView";
	}
		
		// [아작스] 위시리스트 하트(빈->채) 바뀜
	@ResponseBody
	@RequestMapping(value = "chgEmpToFilHeart", produces = "application/text;charset=UTF-8")
		public String emptyToFill(Wishlist wishlist, HttpServletRequest request) {
		System.out.println("DhProdControllerU emptyToFill Starts...");
		System.out.println("chgEmpToFilHeart prodno -> "+wishlist.getProdno());
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("chgEmpToFilHeart id -> "+id);
		wishlist.setId(id);
		
		
		int result=dhps.emptyToFill(wishlist);
		System.out.println("DhProdControllerU emptyToFill result->"+result);
		// 성공했는지만 보자
		String result1=Integer.toString(result);
		
		return result1;
	}
		
		
		// [아작스] 위시리스트 하트(채->빈) 바뀜
	@ResponseBody
	@RequestMapping(value = "chgFilToEmpHeart", produces = "application/text;charset=UTF-8")
		public String fillToEmpty(Wishlist wishlist, HttpServletRequest request) {
		System.out.println("DhProdControllerU chgFilToEmpHeart Starts...");
		System.out.println("chgFilToEmpHeart prodno -> "+wishlist.getProdno());
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("chgFilToEmpHeart id -> "+id);
		wishlist.setId(id);
		
		int result=dhps.fillToEmpty(wishlist);
		System.out.println("DhProdControllerU chgFilToEmpHeart result->"+result);
		// 성공했는지만 보자
		String result1=Integer.toString(result);
		
		return result1;
	}
	
	
	// 위시리스트 내역 보여주기
	@RequestMapping("wish")
	public String wishList(HttpServletRequest request, Model model) {
		System.out.println("DhProdControllerU wishList Starts...");
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("wishList id -> "+id);
		
		// id에 따른 wishlist list 뽑아내기 
		List<Product> idWishList=dhps.wishList(id);
		
		model.addAttribute("idWishList", idWishList);
		
		return "DhWishList";
	}
////////////////////////////////////위시리스트 끝 ***********************************************************		 
	
	// 장바구니 insert & update
	@RequestMapping("shoppingcartinsert")
	 public String shoppingCart( Product product, Model model, HttpServletRequest request) {
		System.out.println("DhProdControllerU shoppingCart Start...");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("DhProdControllerU shoppingCart id->"+id);
		System.out.println("DhProdControllerU shoppingCart product.getBas_qty()->"+product.getBas_qty());
		int result=0;
		Basket basket=new Basket();
		int bas_cnt = (int) request.getSession().getAttribute("bas_cnt");
		if(id !=null && !id.equals("")) {
			basket.setId(id);
			basket.setProdno(product.getProdno());
			basket.setBas_qty(product.getBas_qty());
			System.out.println("DhProdControllerU shoppingCart product.getBas_qty1()->"+basket.getBas_qty());
			result =dhps.sh_insert(basket); //성공하면 1  실패하면 0
			System.out.println("DhProdControllerU shoppingCart product.getBas_qty2()->"+basket.getBas_qty());
			System.out.println("DhProdControllerU shoppingCart insert result->" +result);
			
			bas_cnt = bas.countItems(id);
			System.out.println("bas_cnt->"+bas_cnt);
			
			session.setAttribute("bas_cnt", bas_cnt);
		}else {
			result=0;	
			return "redirect:/jhLoginFormU";
		}
	    return "forward:shoppingcartList";
		 	
		}
	
	//장바구니 리스트
	@RequestMapping("shoppingcartList")
	public String cartList(Product product, Basket basket, Model model, HttpServletRequest request) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		product.setId(id);
		System.out.println("DhProdControllerU cartList Start...");
		System.out.println("DhProdControllerU cartList product.getBas_qty()->"+product.getBas_qty());
		if(id == null || id.equals("")) {
			System.out.println("id->null...");
		} else {
			System.out.println("id->"+id);
		}
		List<Product> listcart =new ArrayList<Product>();
		int result= 0;
		
		if(id ==null || id.equals("")) {
			result=0;	
			return "redirect:/jhLoginFormU";
		}else{
			listcart =dhps.listcart(product);
			System.out.println("DhProdControllerU cartList.size()->" +listcart.size());
			for(Product product2 : listcart) {
				System.out.println("DhProdControllerU cartList " +product2.getTh_img() );
			}
			Product product3=new Product();
			product3.setId(product.getId());
			result=dhps.prodDetailCount(product3);
		    System.out.println("DhProdControllerU prodDetail result->" +result);
		}
		model.addAttribute("listcart",listcart);
		return "uDhShoppingCart";
	}
	
	// 장바구니 삭제
	@RequestMapping("deletesh")
	public String cartDelete(Product product , Model model,HttpServletRequest request) {
		System.out.println("DhProdControllerU cartDelete Start....");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		product.setId(id);
		product.setProdno(product.getProdno());
		int result=dhps.cartDelete(product);
		System.out.println("deletesh result->"+result);
		
		int bas_cnt = (int) request.getSession().getAttribute("bas_cnt");
		
		bas_cnt = bas.countItems(id);
		System.out.println("bas_cnt->"+bas_cnt);
		
		session.setAttribute("bas_cnt", bas_cnt);
		
		return "forward:shoppingcartList";
	}
	
	

	
}

