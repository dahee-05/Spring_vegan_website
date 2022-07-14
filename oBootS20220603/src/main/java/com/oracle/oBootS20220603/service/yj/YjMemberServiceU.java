package com.oracle.oBootS20220603.service.yj;

import com.oracle.oBootS20220603.model.Member;

public interface YjMemberServiceU {

	int 		memCount(String id, String pw);
	Member 		getUser(Member member);
	int 		idCheck(Member member);
	String 		getEmail(Member member);
	int 		yjJoinSucU(Member member);

	

}
