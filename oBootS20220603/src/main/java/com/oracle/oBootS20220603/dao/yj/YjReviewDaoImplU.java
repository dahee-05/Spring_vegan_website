package com.oracle.oBootS20220603.dao.yj;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootS20220603.model.Review;

@Repository
public class YjReviewDaoImplU implements YjReviewDaoU {
	
	@Autowired
	private SqlSession session;
	

	//사용자 리뷰 등록
	@Override
	public int writeRv(Review review) {
		System.out.println("YjReviewDaoImplU writeRv Start...");
		int result = 0;
		try {
			
			result = session.insert("uReviewWrite", review);
			
		} catch(Exception e) {
			System.out.println("YjReviewDaoImplU writeRv exception"+e.getMessage());
		}
		
		return result;
	}

	//리뷰 등록 상태 변경
	@ResponseBody
	@Override
	public void statCh(Review review) {
		System.out.println("YjReviewDaoImplU statCh Start...");
		
		try {
			session.update("yjReviewUpdateU", review);
			
		} catch(Exception e) {
			System.out.println("YjReviewDaoImplU statCh exception->"+e.getMessage());
		}
		
		// TODO Auto-generated method stub
		
	}


//	@Override
//	public Review aYjRvWrite(Review review) {
//		
//		try {
//			Review result = ;
//		} catch(Exception e) {
//			System.out.println("YjReviewDaoImplU aYjRvWrite exception->"+e.getMessage());
//		}
//		return result;
//	}

}
