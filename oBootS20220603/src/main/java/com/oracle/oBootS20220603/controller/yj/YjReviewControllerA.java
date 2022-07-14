package com.oracle.oBootS20220603.controller.yj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootS20220603.model.Paging;
import com.oracle.oBootS20220603.model.Review;
import com.oracle.oBootS20220603.service.yj.YjReviewServiceA;

import lombok.Getter;

@Transactional
@Controller
public class YjReviewControllerA {
	private static final Logger logger = LoggerFactory.getLogger(YjReviewControllerA.class);
	
	@Autowired
	private YjReviewServiceA rs;
	
	//리뷰목록
	@RequestMapping(value="reviewList")
	public String reviewList(Review review, String currentPage, Model model, HttpServletRequest request) {
		System.out.println("YjReviewControllerA Start...");
		
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			//페이징
			int total = rs.total(); //글 전체 개수 가져오기
			System.out.println("YjReviewControllerA total=>"+total);
			Paging pg = new Paging(total, currentPage);
			System.out.println("total->"+pg.getTotal());
			System.out.println("currentPage->"+pg.getCurrentPage());
			System.out.println("endPage->"+pg.getEndPage());
			
			review.setStart(pg.getStart());
			review.setEnd(pg.getEnd());
			
			//리뷰목록
			List<Review> listReview = rs.listReview(review);
			System.out.println("YjReviewControllerA list listReview.size()->"+listReview.size());
			model.addAttribute("listReview", listReview);
			model.addAttribute("pg", pg);
			model.addAttribute("total", total);
			model.addAttribute("menu_num", 6);
			
			return "reviewList";
		}

	}
	//리뷰상세보기
	@GetMapping(value="rvDetail")
	public String rvDetail(int rvno, Model model) {
		System.out.println("YjReviewControllerA rvDetail Start...");
		
		Review review = rs.rvDetail(rvno);
		model.addAttribute("review", review); //원글
		System.out.println("***review.getProdno()->"+review.getProdno());
		System.out.println("***review.getRvno()->"+review.getRvno());
		
		//댓글 보여주기용도 기존의 글의 ref 가져와 (replyRef는 개수???)
		int replyRef = review.getRef();
		System.out.println("YjReviewControllerA rvDetail replyRef->"+replyRef);
		
		System.out.println("@@@review.getRvno()->"+review.getRvno());
		
		//기존댓글불러오기
		List<Review> reply=rs.reply(review);
		System.out.println("YjReviewControllerA rvDetail reply.size()->"+reply.size());
		System.out.println("%%%%review.getRvno()->"+review.getRvno());
				
		
		model.addAttribute("reply", reply); //댓글
		model.addAttribute("menu_num", 6);
		
		return "rvDetail";
		
	}
	
	//리뷰내에서 댓글 달기 
	@RequestMapping(value="rvReplyWrite", method=RequestMethod.POST)
	public String rvReplyWrite(Review review, Model model) {
		System.out.println("YjReviewControllerA rvReplyWrite Start...");
		
		int result = rs.insert(review);
		System.out.println("YjReviewControllerA result->"+result);
		
		System.out.println("###review.getRvno()->"+review.getRvno());
		
		//입력성공 
		if(result>0) return "redirect:/reviewList";
		else{
			model.addAttribute("msg", "입력 실패 확인해보세요");
			return "forward:/reviewList";
		}
	}
		
}
	

