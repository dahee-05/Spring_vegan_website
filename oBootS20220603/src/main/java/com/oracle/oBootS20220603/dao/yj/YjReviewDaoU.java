package com.oracle.oBootS20220603.dao.yj;

import com.oracle.oBootS20220603.model.Review;

public interface YjReviewDaoU {

	int 	writeRv(Review review);

	void statCh(Review review);

	//Review aYjRvWrite(Review review);

}
