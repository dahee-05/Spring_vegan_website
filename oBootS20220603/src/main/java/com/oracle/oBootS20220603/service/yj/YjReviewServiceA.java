package com.oracle.oBootS20220603.service.yj;

import java.util.List;

import com.oracle.oBootS20220603.model.Review;

public interface YjReviewServiceA {

	int 			total();
	List<Review> 	listReview(Review review);
	Review 			rvDetail(int rvno);
	int 			insert(Review review);
	List<Review> 	reply(Review review);
	
	
	

}
