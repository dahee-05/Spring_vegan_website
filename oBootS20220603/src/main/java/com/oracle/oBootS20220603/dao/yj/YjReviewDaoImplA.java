package com.oracle.oBootS20220603.dao.yj;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Review;

@Repository
public class YjReviewDaoImplA implements YjReviewDaoA {

	@Autowired
	private SqlSession session;
	
	@Override
	public int total() {
		int tot = 0;
		System.out.println("YjReviewDaoImpl Start total...");
		
		try {
			tot = session.selectOne("yjReviewTotal");
			System.out.println("YjReviewDaoImpl total tot->"+tot);
		} catch(Exception e) {
			System.out.println("YjReviewDaoImpl total exception->"+e.getMessage());
		}

		return tot;
	}
	//리뷰목록
	@Override
	public List<Review> listReview(Review review) {
		List<Review> reviewList = null;
		System.out.println("YjReviewDaoImpl listReview Start...");
		try {
			reviewList = session.selectList("yjReviewListAll", review);
			System.out.println("YjReviewDaoImpl listReview reviewList.size()->"+reviewList.size());
			
		} catch(Exception e) {
			System.out.println("YjReviewDaoImpl listReview Exception->"+e.getMessage());
		}
		return reviewList;
	}
	//리뷰글 상세보기
	@Override
	public Review rvDetail(int rvno) {
		System.out.println("YjReviewDaoImpl rvDetail Start...");
		Review review = new Review();
		try {
			review = session.selectOne("yjReviewSelOne", rvno);
			System.out.println("YjReviewDaoImpl rvDetail getWriter_id->"+review.getWriter_id());
			
		} catch(Exception e) {
			System.out.println("YjReviewDaoImpl rvDetail Exception->"+e.getMessage());
		}
		
		return review;
	}

	//댓글입력하기
	@Override
	public int insert(Review review) {
		System.out.println("YjReviewDaoImpl insert Start...");
		int result = 0;
		
		try {
			result = session.insert("insertReview", review);
			 
		} catch(Exception e) {
			System.out.println("YjReviewDaoImpl insert Exception-> "+ e.getMessage());
		}
		return result;
	}
	//댓글불러오기

	@Override
	public List<Review> reply(Review review) {
		System.out.println("YjReviewDaoImpl reply Start....");
		List<Review> replyShow = null;
		
		try {
		
			replyShow=session.selectList("yjReviewReply", review);
			System.out.println("YjReviewDaoImpl reply ->"+replyShow);
		
		} catch(Exception e) {
			System.out.println("YjReviewDaoImpl exception->"+e.getMessage());
		}
		return replyShow;
	
	}

}
