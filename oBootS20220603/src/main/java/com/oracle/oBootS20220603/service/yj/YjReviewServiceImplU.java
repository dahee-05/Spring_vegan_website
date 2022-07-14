package com.oracle.oBootS20220603.service.yj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.yj.YjReviewDaoU;
import com.oracle.oBootS20220603.model.Review;

@Service
public class YjReviewServiceImplU implements YjReviewServiceU {

	@Autowired
	private YjReviewDaoU rdu;
	//리뷰등록
	@Override
	public int writeRv(Review review) {
		System.out.println("YjReviewServiceImplU writeRv Start...");
		
		int result = rdu.writeRv(review); 
		
		return result;
	}
	//리뷰상태 변경 
	@Override
	public void statCh(Review review) {
		System.out.println("YjReviewServiceImplU statCh Start...");
		rdu.statCh(review);
		
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Review aYjRvWrite(Review review) {
//		Review result = rdu.aYjRvWrite(review);
//		
//		
//		return null;
//	}

}
