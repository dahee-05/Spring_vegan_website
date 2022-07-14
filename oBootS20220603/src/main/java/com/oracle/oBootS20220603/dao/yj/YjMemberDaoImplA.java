package com.oracle.oBootS20220603.dao.yj;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Admin;
import com.oracle.oBootS20220603.model.Member;
@Repository
public class YjMemberDaoImplA implements YjMemberDaoA {

	@Autowired
	private SqlSession session;
	
	//회원목록
	@Override
	public List<Member> listMember(Member member) {
		List<Member> memberList = null;
		System.out.println("YjMemberDaoImplA listMember Start...");
		try {
			
			memberList = session.selectList("yjMemberList", member);
			System.out.println("YjMemberDaoImplA memberList.size()->"+memberList.size());
			
		} catch(Exception e ) {
			System.out.println("YjMemberDaoImplA exception->"+e.getMessage());
		}
		
		return memberList;
	}
	//글 개수 
	@Override
	public int total() {
		int tot = 0;
		System.out.println("YjMemberDaoImplA total Start...");
		try {
			
			tot = session.selectOne("YjMemberTotal");
			System.out.println("YjMemberDaoImplA total tot->"+tot);
			
		} catch(Exception e) {
			System.out.println("YjMemberDaoImplA total exception"+e.getMessage());
		}
		
		return tot;
	}
	//마일리지 업데이트
	@Override
	public int aYjmilEdit(Member member) {
		System.out.println("YjMemberDaoImplA aYjmilEdit Start..");
		
		int result = 0;
		
		try {
			result = session.update("yjMilEdit", member);
			System.out.println("result->"+result);
			
		} catch (Exception e) {
			System.out.println("YjMemberDaoImplA aYjmilEdit exception->"+e.getMessage());
		}
		return result;
	}
	
	@Override
	public Admin getUser(Admin admin) {
		Admin user2 = null;
		System.out.println("YjMemberDaoImplA Start getUser...");
		try {
			user2 = session.selectOne("yjLoginA", admin);
			
		} catch (Exception e) {
			System.out.println("YjMemberDaoImplA total Exception->"+e.getMessage());
		}
		
		return user2;
	}

}
