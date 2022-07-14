package com.oracle.oBootS20220603.service.yj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.yj.YjReviewDaoA;
import com.oracle.oBootS20220603.model.Review;

@Service
public class YjReviewServiceImplA implements YjReviewServiceA {
	
	@Autowired
	private YjReviewDaoA rd;
	

	@Override
	public int total() {
		System.out.println("YjReviewServiceImplA total Start...");
		int totCnt = rd.total();
		System.out.println("YjReviewServiceImplA total totCnt->"+totCnt);
		
		return totCnt;
	}

	@Override
	public List<Review> listReview(Review review) {
		List<Review> reviewList = null;
		System.out.println("YjReviewServiceImplA listReview Start...");
		reviewList = rd.listReview(review);
		System.out.println("YjReviewServiceImplA listReview reviewList.size()->"+reviewList.size());
		
		return reviewList;
	}

	@Override
	public Review rvDetail(int rvno) {
		System.out.println("YjReviewServiceImplA rvDetail Start...");
		Review review = null;
		review = rd.rvDetail(rvno);
		
		return review;
	}


	//관리자 댓글달기
	@Override
	public int insert(Review review) {
		System.out.println("YjReviewServiceImplA insert Start..");
		int result = 0;
		
		result = rd.insert(review);
		System.out.println("YjReviewServiceImplA insert result->"+result);
		
		return result;
	}


	//관리자 댓글불러오기
	@Override
	public List<Review> reply(Review review) {
		List<Review> replyShow=null;
		System.out.println("YjReviewServiceImplA replyShow Start....");
		
		replyShow = rd.reply(review);
		System.out.println("YjReviewServiceImplA replyShow.size()->"+replyShow.size());
		
		return replyShow;
		
	}

}
