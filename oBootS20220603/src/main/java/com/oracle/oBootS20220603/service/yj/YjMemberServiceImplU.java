package com.oracle.oBootS20220603.service.yj;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oracle.oBootS20220603.dao.yj.YjMemberDaoU;
import com.oracle.oBootS20220603.model.Member;

@Service
public class YjMemberServiceImplU implements YjMemberServiceU {

	@Autowired
	private YjMemberDaoU md;
	
	@Autowired
	private JavaMailSender mailSender; 
	
	
	@Override
	public int memCount(String id, String pw) {
		System.out.println("YjMemberServiceImplU memCount id->"+id);
		System.out.println("YjMemberServiceImplU memCount pw->"+pw);
		
		return md.memCount(id, pw);
	}

	
	@Override
	public Member getUser(Member member) {
	return 	md.getUser(member);
		
		
	}


	@Override
	public int idCheck(Member member) {
		System.out.println("YjMemberServiceImplU idCheck Start..");
		int result = 0;
		result = md.idCheck(member);
		
		return result;
	}
	/**
	 * 1. id로 이메일을 조회
	 * 2. 임시 비밀번호 생성
	 * 3. 임시 비밀번호 발송
	 * 4. 임시 비밀번호 저장
	 * 5. 결과 반환 -> 컨트롤러
	 */
	@Override
	public String getEmail(Member member) {
		System.out.println("YjMemberServiceImplU getEmail Start...");
		member = md.getEmail(member);
		int result = mailTransport(member);
		if(result > 0 ) {
			return "임시비밀번호를 메일로  발송했습니다.";
			
		} else {
			return "관리자문의";
		}
		
		
	}
	//member 매개변수로 받아서 스트링 리턴
	public int mailTransport(Member member) {
		System.out.println("mailSending...");
		
	
		String tomail=member.getEmail(); 	//받는사람 이메일
		System.out.println(tomail);
		
		String setfrom = "happiiyujin@gmail.com"; //보내는 사람 이메일 
		System.out.println(setfrom);
		
		String title = "임시비밀번호입니다."; 	//제목
		int result = 0;
		try {
			//Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage(); // 
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); 								//보내는 사람 생략하거나 하면 정상 작동을 안함
			messageHelper.setTo(tomail); 									//받는사람 이메일
			messageHelper.setSubject(title); 								//메일제목은 생략이 가능하다
			String tempPassword = (int) (Math.random()*999999) + 1 + ""; 	//비밀번호 임시 설정-> 6자리숫자
			//컨트롤러니까 서비스 연결 가능
		
			messageHelper.setText("임시 비밀번호 입니다 : "+ tempPassword); 		//메일내용
			System.out.println("임시 비밀번호 입니다 : " + tempPassword);
			
			//첨부문서 있을 때만 
			DataSource dataSource = new FileDataSource("c:\\log\\8.jpg");	//첨부파일 
			//													지정이름 및 확장자			B는 Base64
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource); 
		
			mailSender.send(message);
			//임시 비번 저장로직 Service --> DAO --> mapper (패스워드 업데이트로 연결) 
			// member.tempPassword 라는 필드를 만들어 놓고 저장  임시비번으로 해놓고 사용자가 직접 바꾸도록 설정
			
			//임시 비번 저장 
			member.setPw(tempPassword);
			result = md.pwChange(member);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}


	@Override
	public int yjJoinSucU(Member member) {
		int result = 0;
		System.out.println("YjMemberServiceImplU yjJoinSucU Start...");
		result = md.yjJoinSucU(member);
		
		return result;
	}

}
