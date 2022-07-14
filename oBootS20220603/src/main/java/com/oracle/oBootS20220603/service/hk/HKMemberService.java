package com.oracle.oBootS20220603.service.hk;

import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;

public interface HKMemberService {

	Member selectOne(String id);

	int updateMileage(Payment payment);

	int updateAddress(Payment payment);

	int pwCheck(Member member);

	int changePw(Member member);

}
