package com.oracle.oBootS20220603.controller.sw;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oracle.oBootS20220603.model.Faq;
import com.oracle.oBootS20220603.service.Paging;
import com.oracle.oBootS20220603.service.sw.SwFaqService;


@Controller
public class SwFaqControllerA {
	private static final Logger logger = LoggerFactory.getLogger(SwFaqControllerA.class);
	
	@Autowired
	private SwFaqService fs;
	//FAQ리스트 불러오기
	@RequestMapping(value="aSwFaqList")
	public String aSwFaqList(Faq faq, String currentPage, Model model, HttpServletRequest request) {
		logger.info("SwFaqController Start list...");
		
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			int total = fs.total();
			
			System.out.println("SwFaqControllerA total=>" + total);
			Paging pg = new Paging(total, currentPage);
			faq.setStart(pg.getStart()); //시작시1
			faq.setEnd(pg.getEnd()); //시작시 10으로 
			List<Faq> listFaq = fs.listFaq(faq);
				System.out.println("SwFaqControllerA list listFaq.size()=>" + listFaq.size());
				
			model.addAttribute("listFaq",listFaq);
			model.addAttribute("pg",pg);
			model.addAttribute("total",total);
			model.addAttribute("menu_num", 9);

			return "aSwFaqList";
		}
		
	}
	
	
	//FAQ 상세페이지
	@GetMapping(value="aSwFaqdetail")
	public String aSwFaqdetail(int boardno, Model model) {
		// Faq faq = fs.aSwFaqdetail(faq)
		// Dao - > 	 fd.aSwFaqdetail(faq)
		// mapper --> aSwFaqSelOne , boardno
		System.out.println("SwFaqControllerA Start aSwFaqdetail...");
		Faq faq = fs.aSwFaqdetail(boardno);
		
		model.addAttribute("faq", faq);
		
		return "aSwFaqdetail";
	}
	
	@GetMapping(value = "aSwFaqwrite_view")
	public String aSwFaqwrite_view(Model model) {
		logger.info("aSwFaqwrite_view start..");
		
		return "aSwFaqwrite_view";
		
	}
	
	@PostMapping(value = "aSwFaqwrite")
	public String aSwFaqwrite(Faq faq, Model model) {
		logger.info("aSwFaqwrite start...");
		int result = fs.aSwFaqwrite(faq);
		
		model.addAttribute("faq", faq);
		
		return "redirect:aSwFaqList";
	}
	
	@GetMapping(value = "aSwFaqupdateForm")
	public String aSwFaqupdateForm(int boardno, Model model) {
			System.out.println("SwFaqController Start aSwFaqupdateForm...");
			
		Faq faq = fs.aSwFaqdetail(boardno);
		
		model.addAttribute("faq", faq);
		
		return "aSwFaqupdateForm";
	}
	
	@PostMapping(value = "aSwFaqupdate")
	public String aSwFaqupdate(Faq faq, Model model) {
		int uptCnt = fs.aSwFaqupdate(faq);
			System.out.println("fs.aSwFaqupdate(faq) Count-->"+uptCnt);
		
		model.addAttribute("uptCnt", uptCnt);
		model.addAttribute("upup","Messsage Test");
		
		return "forward:aSwFaqList";
	}
	
	@RequestMapping(value = "aSwFaqdelete")
	public String aSwFaqdelete(int boardno, Model model) {
			System.out.println("SwFaqController Start aSwFaqdelete...");
		
		int result = fs.aSwFaqdelete(boardno);
		
		return "redirect:aSwFaqList";
	}
	
	@RequestMapping(value = "uSwFaqList")
	public String aSwFaqList(Faq faq, Model model) { 
		
		List<Faq> ulistFaq = fs.ulistFaq1(faq);
			System.out.println("SwFaqControllerA list listFaq.size()=>" + ulistFaq.size());
		
		model.addAttribute("ulistFaq",ulistFaq);
		
		return "uSwFaqList";
	}
	
	@ResponseBody
	@RequestMapping("getBoardCategoryList")
	public List<Faq> getBoardCategoryList(int  board_category) { 
		List<Faq> ulistFaq = fs.ulistFaq(board_category);
			System.out.println("SwFaqControllerA list listFaq.size()=>" + ulistFaq.size());

		return ulistFaq;
	}
	
	
	
	
	
}
