package com.oracle.oBootS20220603.dao.yj;

import com.oracle.oBootS20220603.model.Member;

public interface YjMemberDaoU {

	int 		memCount(String id, String pw);
	Member 		getUser(Member member);
	int 		idCheck(Member member);
	Member 		getEmail(Member member);
	int 		pwChange(Member member);
	int 		yjJoinSucU(Member member);
	

	


}
