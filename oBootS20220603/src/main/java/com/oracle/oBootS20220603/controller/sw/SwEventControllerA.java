package com.oracle.oBootS20220603.controller.sw;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootS20220603.model.CouponMaster;
import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.service.Paging;
import com.oracle.oBootS20220603.service.sw.SwEventService;
import com.oracle.oBootS20220603.service.sw.SwEvt_CouponService;
import com.oracle.oBootS20220603.service.sw.SwEvt_productService;

@Controller
public class SwEventControllerA {
	private static final Logger logger = LoggerFactory.getLogger(SwEventControllerA.class);
	
	@Autowired
	private SwEventService es;
	
	@Autowired
	private SwEvt_productService ep;
	
	@Autowired
	private SwEvt_CouponService ec;
	
	//이벤트 리스트 
	@RequestMapping(value="aSwEventList")
	public String aSwFaqList(Event event, String currentPage, Model model, HttpServletRequest request) {
		
		logger.info("SwFaqController Start list...");
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			int total = es.total();
		
			System.out.println("SwFaqControllerA total=>" + total);
			Paging pg = new Paging(total, currentPage);
			event.setStart(pg.getStart()); //시작시1
			event.setEnd(pg.getEnd()); //시작시 10으로 
			
			List<Event> listEvent = es.listEvent(event);
				System.out.println("SwEventControllerA list listEvent.size()=>" + listEvent.size());
			
			for(Event event2 : listEvent ) {
				System.out.println("SwEventControllerA aSwEventList event2.getEvt_start()-->"+event2.getEvt_start());
				
			}
			
			model.addAttribute("listEvent",listEvent);
			model.addAttribute("pg",pg);
			model.addAttribute("total",total);
			model.addAttribute("menu_num", 8);
	
			return "aSwEventList";
		}
		
	}
	
	//할인 이벤트 상세  p(product줄임말)
	@GetMapping(value="aSwEvtdetailp")
	public String aSwEvtdetailp(int evt_no, Model model) {
			System.out.println("SwEventControllerA Start aSwEventdetailp...");
		
		Event event = es.aSwEvtdetailp(evt_no);
		List<Product> p_list = es.p_list(evt_no);
		
		model.addAttribute("event", event);
		model.addAttribute("p_list",p_list);
			
		return "aSwEvtdetailp";
	}
	
	//쿠폰 이벤트 상세 c(coupon줄임말)
	@GetMapping(value = "aSwEvtdetailc")
	public String aSwEvtdetailc(int evt_no, Model model) {
			System.out.println("SwEventControllerA Start aSwEventdatailc...");
		
		Event eventc = es.aSwEvtdetailc(evt_no);
		List<CouponMaster> c_list = es.c_list(evt_no);
		
			model.addAttribute("event",eventc);
			model.addAttribute("c_list",c_list);
			
		return "aSwEvtdetailc";
		
	}
	
	//이벤트 삭제 
	@RequestMapping(value = "aSwEventdelete")
	public String aSwEventdelete(int evt_no, Model model) {
		System.out.println("SwFaqController Start aSwFaqdelete...");
		
		int result = es.aSwEventdelete(evt_no);
		
		return "redirect:aSwEventList";
	}
	
	//할인 이벤트 등록 페이지
	@GetMapping(value = "aSwEventwrite_viewp")
	public String aSwEventwrite_viewp(Product product , Model model) {
		logger.info("aSwEventwrite_viewp start..");
		// 상품을가져오는리스트 작성
		List<Product> p_list1 = es.p_list1(product);
			model.addAttribute("p_list1",p_list1);
			
		return "aSwEventwrite_viewp";
	}
	
//	할인 이벤트 등록
	@PostMapping(value = "aSwEventwritep")
	public String aSwEventwritep(HttpServletRequest request, Event event, int[] prodnoArr, 
			MultipartFile evt_imgUpLoad, Model model) throws Exception{ /*{
		logger.info("aSwEventwritep start...");
		for(int i=0; i < prodnoArr.length; i++) {
			System.out.println("prodnoArr i->"+prodnoArr);
			System.out.println("prodnoArr->"+prodnoArr);
		}*/
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/event");
			System.out.println("uploadPath1->"+uploadPath);
		
			System.out.println("uploadForm POST Start");
			logger.info("orifinalName: " + evt_imgUpLoad.getOriginalFilename());
		//loger.info("title: "+ title)
			logger.info("size: " + evt_imgUpLoad.getSize());
			logger.info("contentType: " + evt_imgUpLoad.getContentType());
		String savedName1 = uploadFile(evt_imgUpLoad.getOriginalFilename(), evt_imgUpLoad.getBytes(), uploadPath);
		/*
		 * String savedName2 = uploadFile(file2.getOriginalFilename(), file2.getBytes(),
		 * uploadPath);
		 */
			logger.info("savedName: " + savedName1);
		event.setEvt_img(savedName1);

		int result = es.aSwEventwritep(event);
		
		Event evt_latest = es.aSwEventwritepp(event);
		
		//select * from event where evt_name =#{evt_name}
		
		
		Event evt = null;
		for(int num : prodnoArr) {
			evt = new Event();
			evt.setEvt_no(evt_latest.getEvt_no());
			evt.setProdno(num);
			
			ep.insert(evt);
			
		}
		
		
		return "redirect:aSwEventList";
	}
	
	private String uploadFile(String originalName, byte[] fileData, String uploadPath)
			throws Exception{
		UUID uid = UUID.randomUUID();
		//requestPath = requestPath + "/resources/image";
			System.out.println("uploadPath2->"+uploadPath);
		//Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성: " + uploadPath);
		}
		
		String event = uid.toString() + "_" + originalName;
			logger.info("savedName: "+ event);
		File target = new File(uploadPath, event);
		//File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target);//org.springframework.util.FileCopyUtils
		
		return event;
	}
	
	//쿠폰 이벤트 등록 페이지
	@GetMapping(value = "aSwEventwrite_viewc")
	public String aSwEventwrite_viewc(CouponMaster couponmaster, Model model) {
			logger.info("aSwEventwrtie_viewc start..");
		//쿠폰을 불러오는 리스트 작성
		List<CouponMaster> c_list1 = es.c_list1(couponmaster);
		model.addAttribute("c_list1",c_list1);
		
		return "aSwEventwrite_viewc";
	}
	
	//쿠폰 이벤트 등록
	@PostMapping(value = "aSwEventwritec")
	public String aSwEventwritec(HttpServletRequest request, Event event, int[] coupnoArr,
			MultipartFile evt_imgUpload1, Model model) throws Exception{
		
		String uploadPath1 = request.getSession().getServletContext().getRealPath("/upload/event");
		System.out.println("uploadPath1->"+uploadPath1);
	
		System.out.println("uploadForm POST Start");
		logger.info("orifinalName: " + evt_imgUpload1.getOriginalFilename());
	//loger.info("title: "+ title)
		logger.info("size: " + evt_imgUpload1.getSize());
		logger.info("contentType: " + evt_imgUpload1.getContentType());
		String savedName2 = uploadFile1(evt_imgUpload1.getOriginalFilename(), evt_imgUpload1.getBytes(), uploadPath1);
	/*
	 * String savedName2 = uploadFile(file2.getOriginalFilename(), file2.getBytes(),
	 * uploadPath);
	 */
		logger.info("savedName: " + savedName2);
		event.setEvt_img(savedName2);
		
		int result = es.aSwEventwritec(event);
		
		Event evt_latest1 = es.aSwEventwritecc(event);
		//select * from event where coup_name =#{coup_name}
		
		Event evt =null;
		for(int num: coupnoArr) {
			evt = new Event();
			evt.setEvt_no(evt_latest1.getEvt_no());
			evt.setCoupno(num);
			
			ec.insert(evt);
		}
		
		return "redirect:aSwEventList";
	}
	
	private String uploadFile1(String originalName, byte[] fileData, String uploadPath1)
			throws Exception{
		UUID uid = UUID.randomUUID();
		//requestPath = requestPath + "/resources/image";
			System.out.println("uploadPath2->"+uploadPath1);
		//Directory 생성
		File fileDirectory = new File(uploadPath1);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성: " + uploadPath1);
		}
		
		String event = uid.toString() + "_" + originalName;
			logger.info("savedName: "+ event);
		File target = new File(uploadPath1, event);
		//File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target);//org.springframework.util.FileCopyUtils
		
		return event;
	}

}
