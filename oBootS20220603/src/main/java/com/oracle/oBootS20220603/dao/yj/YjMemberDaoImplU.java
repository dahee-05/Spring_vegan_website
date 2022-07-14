package com.oracle.oBootS20220603.dao.yj;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Member;

@Repository
public class YjMemberDaoImplU implements YjMemberDaoU {

	@Autowired
	private SqlSession session;
	
	@Override
	public int memCount(String id, String pw) {
		int result = 0;
		System.out.println("YjMemberDaoImplU id->"+id);
		System.out.println("YjMemberDaoImplU pw->"+pw);
		
		
		return 0;
	}

	@Override
	public Member getUser(Member member) {
		
		Member user = null;
		System.out.println("YjMemberDaoImplU Start getUser...");
		try {
			user = session.selectOne("yjLogin", member);
			
		} catch (Exception e) {
			System.out.println("YjMemberDaoImplU total Exception->"+e.getMessage());
		}
		
		return user;
	}

	@Override
	public int idCheck(Member member) {
		System.out.println("YjMemberDaoImplU idCheck Start...");
		int result = 0;
		
		try {
			
			result = session.selectOne("yjIdCheck", member);
			
		} catch(Exception e) {
			System.out.println("YjMemberDaoImplU idCheck exception->"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public Member getEmail(Member member) {
		Member user = null;
		System.out.println("YjMemberDaoImplU Start getUser...");
		try {
			user = session.selectOne("yjIdFind", member);
			
		} catch (Exception e) {
			System.out.println("YjMemberDaoImplU total Exception->"+e.getMessage());
		}
		
		return user;
	}

	@Override
	public int pwChange(Member member) {
		int result = 0;
		
		try {
			
			result = session.update("yjPwChange", member);
			
		} catch(Exception e) {
			System.out.println("YjMemberDaoImplU idCheck exception->"+e.getMessage());
		}
		
		return result;
	
	}
	//회원가입
	@Override
	public int yjJoinSucU(Member member) {
		System.out.println("YjMemberDaoImplU yjJoinSucU Start...");
		int result = 0;
		
		try {
			result = session.insert("yjInsertUser", member);
			
		} catch(Exception e) {
			System.out.println("YjMemberDaoImplU yjJoinSucU Exception"+e.getMessage());
		}
		return result;
	}

	


}
