package com.oracle.oBootS20220603.dao.yj;

import java.util.List;

import com.oracle.oBootS20220603.model.Admin;
import com.oracle.oBootS20220603.model.Member;

public interface YjMemberDaoA {

	List<Member> 	listMember(Member member);
	int 			total();
	int 			aYjmilEdit(Member member);
	Admin 			getUser(Admin admin);

}
