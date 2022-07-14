package com.oracle.oBootS20220603.dao.sw;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.oracle.oBootS20220603.model.Faq;



@Repository
public class SwFaqDaoImplA implements SwFaqDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public int total() {
		int tot = 0;
		System.out.println("SwFaqDaoImpl Start total...");
		try {
			tot = session.selectOne("swFaqTotal");
			
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl total Exception->"+e.getMessage());
		}
		
		return tot;
	}

	@Override
	public List<Faq> listFaq(Faq faq) {
		List<Faq> faqList = null;
		System.out.println("SwFaqDaoImpl listFaq Start...");
		try {
			faqList = session.selectList("aSwFaqListAll", faq);
			System.out.println("SwFaqDaoImpl listFaq faqList.size()-->"+faqList.size());
			
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl listFaq Exception->"+e.getMessage());
		}
		return faqList;
	}

	@Override
	public Faq aSwFaqdetail(int boardno) {
		System.out.println("SwFaqDaoImplA aSwFaqdetail start..");
		Faq faq = new Faq();
		try {
			//						mapper ID	, Parameter
			faq = session.selectOne("aSwFaqSelOne", boardno);
			System.out.println("SwFaqDaoImpl detail getboard_category"+faq.getBoard_category());
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl detail Exception->"+e.getMessage());
		}
		return faq;
	}

	@Override
	public int aSwFaqupdate(Faq faq) {
		System.out.println("SwFaqDaoImpl aSwFaqupdate start...");
		int upup = 0;
		try {
			upup = session.update("aSwFaqupdate",faq);
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl aSwFaqupdate Exception->"+e.getMessage());
		}
		return upup;
	}

	@Override
	public int aSwFaqdelete(int boardno) {
		System.out.println("SwFaqDaoImpl aSwFaqdelete start..");
		int result = 0;
		try {
			result = session.delete("aSwFaqdelete",boardno);
			System.out.println("SwFaqDaoImpl aSwFaqdelete result->"+result);
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl aSwFaqdelete Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<Faq> ulistFaq(int board_category) {
		List<Faq> ufaqList = null;
		System.out.println("SwFaqDaoImpl ulistFaq Start...");
		try {
			ufaqList = session.selectList("uSwFaqListAll", board_category);
			System.out.println("SwFaqDaoImpl ulistFaq ufaqList.size()-->"+ufaqList.size());
			
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl ulistFaq Exception->"+e.getMessage());
		}
		return ufaqList;
	}

	@Override
	public int aSwFaqwrite(Faq faq) {
		int result = 0;
		try {
			result = session.insert("aSwFaqwrite", faq);
			
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl ulistFaq Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<Faq> ulistFaq1(Faq faq) {
		List<Faq> ufaqList1 = null;
		System.out.println("SwFaqDaoImpl ulistFaq Start...");
		try {
			ufaqList1 = session.selectList("uSwFaqListAll1", faq);
			System.out.println("SwFaqDaoImpl ulistFaq ufaqList.size()-->"+ufaqList1.size());
			
		} catch (Exception e) {
			System.out.println("SwFaqDaoImpl ulistFaq Exception->"+e.getMessage());
		}
		return ufaqList1;
	}

	

	
	
	
	

}
