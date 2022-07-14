package com.oracle.oBootS20220603.service.sw;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootS20220603.dao.sw.SwFaqDao;
import com.oracle.oBootS20220603.model.Faq;

@Service
public class SwFaqServiceImplA implements SwFaqService {
	
	@Autowired
	private SwFaqDao fd;

	@Override
	public int total() {
		System.out.println("SwFaqServiceImpl Start total...");
		int totCnt = fd.total();
		System.out.println("SwFaqServiceImpl total totCnt->"+totCnt);
		
		return totCnt;
	}

	@Override
	public List<Faq> listFaq(Faq faq) {
		List<Faq> faqList = null;
		System.out.println("SwFaqServiceImpl listFaq Start...");
		faqList = fd.listFaq(faq);
		System.out.println("SwFaqServiceImpl listFaq faqList.size()->" +faqList.size());
		
		return faqList;
	}

	@Override
	public Faq aSwFaqdetail(int boardno) {
		System.out.println("SwFaqServiceImpl detail...");
		Faq faq = null;
		faq = fd.aSwFaqdetail(boardno);
		
		return faq;
	}
	
	

	@Override
	public int aSwFaqupdate(Faq faq) {
		System.out.println("SwFaqServiceImpl aSwFaqupdate...");
		int upup = 0;
		upup = fd.aSwFaqupdate(faq);
		
		return upup;
	}

	@Override
	public int aSwFaqdelete(int boardno) {
		int result = 0;
		System.out.println("SwFaqServiceImpl aSwFaqdelete Start...");
		result = fd.aSwFaqdelete(boardno);
		
		return result;
	}

	@Override
	public List<Faq> ulistFaq(int board_category) {
		List<Faq> ufaqList = null;
		System.out.println("SwFaqServiceImpl listFaq Start...");
		ufaqList = fd.ulistFaq(board_category);
		System.out.println("SwFaqServiceImpl listFaq faqList.size()->" +ufaqList.size());
		
		return ufaqList;
	}

	@Override
	public int aSwFaqwrite(Faq faq) {
		int result = fd.aSwFaqwrite(faq);
		
		return result;
	}


	@Override
	public List<Faq> ulistFaq1(Faq faq) {
		List<Faq> ufaqList = null;
		System.out.println("SwFaqServiceImpl listFaq Start...");
		ufaqList = fd.ulistFaq1(faq);
		System.out.println("SwFaqServiceImpl listFaq faqList.size()->" +ufaqList.size());
		
		return ufaqList;
	}

	
	

	



}
