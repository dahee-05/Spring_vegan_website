package com.oracle.oBootS20220603.dao.jh;

import com.oracle.oBootS20220603.domain.jh.Member;

public interface JhJpaPaymentDetailRepositoryU {

	Member updSubInfo(Member member);
	// JPA 사용 -----------------------------------------------------------	

	int subCanU(String id);

	Member readSubInfo(String id);



}
