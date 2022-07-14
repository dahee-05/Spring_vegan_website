package com.oracle.oBootS20220603.service.yj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.yj.YjMemberDaoA;
import com.oracle.oBootS20220603.model.Admin;
import com.oracle.oBootS20220603.model.Member;

@Service
public class YjMemberServiceImplA implements YjMemberServiceA {
	
	@Autowired
	private YjMemberDaoA mda;

	//회원목록 리스트
	@Override
	public List<Member> listMember(Member member) {
		List<Member> memberList = null;
		System.out.println("YjMemberServiceImplA listMember Start...");
		memberList = mda.listMember(member);
		System.out.println("YjMemberServiceImplA listMember memberList.size()->"+memberList.size());
		
		return memberList;
	}
	//회원목록 글 개수
	@Override
	public int total() {
		System.out.println("YjMemberServiceImplA total Start...");
		int totCntMem = mda.total();
		System.out.println("YjMemberServiceImplA total totCntMem"+totCntMem);
		return totCntMem;
	}

	//마일리지 수정
	@Override
	public int aYjmilEdit(Member member) {
		System.out.println("YjMemberServiceImplA edit Start...");
		int result = 0;
		result = mda.aYjmilEdit(member);
	
		
		return result;
	}
	
	@Override
	public Admin getUser(Admin admin) {
		System.out.println("YjMemberServiceImplA getUser Start...");
		
		return mda.getUser(admin);
	}

}
