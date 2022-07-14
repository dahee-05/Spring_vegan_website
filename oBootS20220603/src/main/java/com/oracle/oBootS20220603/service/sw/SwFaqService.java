package com.oracle.oBootS20220603.service.sw;

import java.util.List;

import com.oracle.oBootS20220603.model.Faq;

public interface SwFaqService {
	int				total();

	List<Faq> 		listFaq(Faq faq);

	Faq 			aSwFaqdetail(int boardno);

	int 			aSwFaqupdate(Faq faq);

	int aSwFaqdelete(int boardno);

	List<Faq> ulistFaq(int board_category);

	int aSwFaqwrite(Faq faq);


	List<Faq> ulistFaq1(Faq faq);

	

	
}
