package com.oracle.oBootS20220603.controller.dh;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.oBootS20220603.model.Paging;
import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.service.dh.DhProdServiceA;
import com.oracle.oBootS20220603.service.dh.DhProdServiceU;

@Controller
public class DhProdControllerA {
	private static final Logger logger = LoggerFactory.getLogger(DhProdControllerA.class);
	
	@Autowired
	private DhProdServiceA dhps;
	
	// admin 상품 조회
	@RequestMapping("prodSelect")
	public String prodList(Product product, Model model, String currentPage, HttpServletRequest request) {
		logger.info("DhProdControllerA prodSelect Start..");
		
		String admin_id = (String) request.getSession().getAttribute("admin_id");
		if(admin_id == null || admin_id.equals("")) {
			return "aYjLoginForm";
		} else {
			int totalc = dhps.totalC();          // admin 상품 판매상태 표시 카테고리
			int sale = dhps.totalS();			 // 판매중인 상품 개수
			int sold_out =dhps.totalSO();       // 품절된 상품 개수
			int stop_sel=dhps.totalST();        // 판매중지된 상품 개수
			System.out.println("DhProdControllerA AllProdSelect total->" +totalc);
			int total = dhps.total();      //paging 작업
				// 페이징(페이지네이션) 작업-----------------------------------------------------------
				//						40		현재페이지
				Paging pg = new Paging(total, currentPage);
				product.setStart(pg.getStart());	// 시작 시 1
				product.setEnd(pg.getEnd());		// 시작 시 10
				// --------------------------------------------------------------------------
				// 페이지네이션 다음에 리스트를 불러와야 됨! 그래야 mapper에 start, end 값이 들어감
				List<Product> prodList = dhps.listProd(product);
				System.out.println("DhProdControllerA prodSelect prodList.size() -> " + prodList.size());
			
			model.addAttribute("prodList", prodList);
			model.addAttribute("pg", pg);
			model.addAttribute("menu_num", 2);
			model.addAttribute("totalc", totalc);
			model.addAttribute("sale",sale);
			model.addAttribute("sold_out",sold_out);
			model.addAttribute("stop_sel",stop_sel);
			return "aDhProdList";
		}
		
	}
	
	//keyword 조회_ 상품명,상품번호
	@RequestMapping(value="prodSearch" )
	public String list3(Product product ,String currentPage, Model model, HttpServletRequest request) {
		logger.info("DhProdControllerA list3 Start..");
		int totalc = dhps.totalC();          // admin 상품 판매상태 표시 카테고리
		int sale = dhps.totalS();			 // 판매중인 상품 개수
		int sold_out =dhps.totalSO();       // 품절된 상품 개수
		int stop_sel=dhps.totalST();        // 판매중지된 상품 개수
		System.out.println("DhProdControllerA AllProdSelect total->" +totalc);
		
		int total = dhps.keywordtotal(product);      //paging 작업
		System.out.println("DhProdControllerA keywordTotal=>" +total);
		// 페이징(페이지네이션) 작업-----------------------------------------------------------
		//								현재페이지
		Paging pg = new Paging(total, currentPage);
		product.setStart(pg.getStart());	// 시작 시 1
		product.setEnd(pg.getEnd());		// 시작 시 10
		// --------------------------------------------------------------------------
		System.out.println("DhProdControllerA list3 pg.getTotal()=>" +pg.getTotal());
		System.out.println("DhProdControllerA list3 pg.getStart()=>" +pg.getStart());
		System.out.println("DhProdControllerA list3 pg.getEnd()=>" +pg.getEnd());
		// product list 조건조회
		List<Product> listProduct = dhps.listProduct(product);   //keyword 조회_ 상품명,상품번호
		System.out.println("DhProdControllerA list3 listProduct.size()->" +listProduct.size());
		
		model.addAttribute("ListProd",listProduct);
		model.addAttribute("pg", pg);
		model.addAttribute("menu_num", 2);
		model.addAttribute("keyword1",product.getKeyword1());
		model.addAttribute("keyword2",product.getKeyword2());
		model.addAttribute("totalc", totalc);
		model.addAttribute("sale",sale);
		model.addAttribute("sold_out",sold_out);
		model.addAttribute("stop_sel",stop_sel);
		return "aDhProduct_listKeyword";
	}
	
	// 어드민 리스트 상품삭제
	@ResponseBody
	@RequestMapping(value="adminProdDelAJAX", produces = "application/text;charset=UTF-8", method = RequestMethod.POST)
	public String adminProdDelAJAX(@RequestParam(value = "chbox[]") List<String> chArr, Product product) {
		logger.info("DhProdControllerA adminProdDelAJAX Start..");
		System.out.println("DhProdControllerA adminProdDelAJAX chArr.get(0)->"+chArr.get(0));
		int result=0;
		int prodno=0;
		
		for(String i : chArr) {
			prodno =Integer.parseInt(i);
			product.setProdno(prodno);
			System.out.println("DhProdControllerA adminProdDelAJAX prodno "+product.getProdno());
			result=dhps.adminProdDelAJAX(product);
		}
		String result1=Integer.toString(result);
		
		return result1;
	}
	
	// 어드민 상품 상세
	@RequestMapping("prodView")
	public String prodView(Product product, Model model, HttpServletRequest request) {
		logger.info("DhProdControllerA prodView Start..");
		Product product1 = dhps.prodDetail(product.getProdno());
		model.addAttribute("product", product1);
		model.addAttribute("menu_num", 2);
		return "aDhProdView";
	}
	
	// 어드민 상품 삭제
	@RequestMapping("prodDelete")
	public String prodDelete(Product product, Model model,HttpServletRequest request) {
		logger.info("DhProdControllerA prodDelete Start..");
		int result = dhps.delete(product.getProdno());
		System.out.println("DhProdControllerA prodDelete result->"+result);
		return "redirect:prodSelect";
	}
	
	
	 // 어드민 상품 수정
	   @PostMapping("prodUpdate")
	   public String prodUpdate(Product product, Model model,HttpServletRequest request,
	         MultipartFile th_imgJia, MultipartFile main_img1Jia, MultipartFile main_img2Jia , MultipartFile prod_infoJia,
	         String th_imgH, String main_img1H, String main_img2H , String prod_infoH) throws Exception {
	      logger.info("DhProdControllerA prodUpdate Start..");
	      
	      System.out.println("DhProdControllerA prodUpdate th_imgJia -> " + th_imgJia);
	      System.out.println("DhProdControllerA prodUpdate main_img1Jia -> " + main_img1Jia);
	      System.out.println("DhProdControllerA prodUpdate main_img2Jia -> " + main_img2Jia);
	      System.out.println("DhProdControllerA prodUpdate prod_infoJia -> " + prod_infoJia);
	      System.out.println("DhProdControllerA prodUpdate th_imgH -> " + th_imgH);
	      System.out.println("DhProdControllerA prodUpdate main_img1H -> " + main_img1H);
	      System.out.println("DhProdControllerA prodUpdate main_img2H -> " + main_img2H);
	      System.out.println("DhProdControllerA prodUpdate prod_infoH -> " + prod_infoH);
	      
	      
	      
	      String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
	      
//	      logger.info("originalName: " + th_imgJia.getOriginalFilename());
//	      logger.info("title: " + title);
//	      logger.info("size: " + th_imgJia.getSize());
//	      logger.info("contentType: " + th_imgJia.getContentType());
	      logger.info("uploadPath: " + uploadPath);
	      
	      // 오리지널 네임
	      String th_img = th_imgJia.getOriginalFilename();
	      String main_img1 = main_img1Jia.getOriginalFilename();
	      String main_img2 = main_img2Jia.getOriginalFilename();
	      String prod_info = prod_infoJia.getOriginalFilename();
	      System.out.println("th_img" + th_img);
	      System.out.println("main_img1" + main_img1);
	      System.out.println("main_img2" + main_img2);
	      System.out.println("prod_info" + prod_info);
	      
	      String savedName1 = null;
	      String savedName2 = null;
	      String savedName3 = null;
	      String savedName4 = null;
	      
	      // 배너이미지 파일 저장
	      if (th_img.equals(""))
	         savedName1 = th_imgH;
	      else if (th_img != "")
	         savedName1 = uploadFile(th_imgJia.getOriginalFilename(), th_imgJia.getBytes(), uploadPath);
	      
	      // 대표이미지1 파일 저장
	      if (main_img1.equals(""))
	         savedName2 = main_img1H;
	      else if (main_img1 != "")
	         savedName2 = uploadFile(main_img1Jia.getOriginalFilename(), main_img1Jia.getBytes(), uploadPath);
	      
	      // 대표이미지2 파일 저장
	      if (main_img2.equals(""))
	         savedName3 = main_img2H;
	      else if (main_img2 != "")
	         savedName3 = uploadFile(main_img2Jia.getOriginalFilename(), main_img2Jia.getBytes(), uploadPath);
	      
	      // 상세페이지 파일 저장
	      if (prod_info.equals(""))
	         savedName4 = prod_infoH;
	      else if (prod_info != "")
	         savedName4 = uploadFile(prod_infoJia.getOriginalFilename(), prod_infoJia.getBytes(), uploadPath);
	      
	      logger.info("th_img: " + savedName1);
	      logger.info("main_img1: " + savedName2);
	      logger.info("main_img2: " + savedName3);
	      logger.info("prod_info: " + savedName4);
	      
	      product.setTh_img(savedName1);
	      product.setMain_img1(savedName2);
	      product.setMain_img2(savedName3);
	      product.setProd_info(savedName4);

	      int result= dhps.prodUpdate(product);
	      System.out.println("DhProdControllerA prodUpdate result ->" + result);
	      
	      if (result > 0)
	         return "redirect:prodSelect";
	      else {
	         model.addAttribute("result", result);
	         model.addAttribute("msg", "등록되지 않았습니다. 다시 시도해주세요.");
	         return "forward:prodView";
	      }
	      
	   }
	

	private String uploadFile1(String originalName, byte[] fileData, String uploadPath) throws Exception {
//		private으로 잡은 이유
//		이 컨트롤러 클래스의 내부에서만 쓰기 때문
		
//		universally unique identifier : 유효 아이디. 유일한 번호를 돌려준다. 중첩 될 일이 없다.
		UUID uid = UUID.randomUUID();
		
		//requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath -> " + uploadPath);
		// Directory 생성
		File fileDirectory = new File(uploadPath);				// 폴더를 만들어 놓고,
		if (!fileDirectory.exists()) {							// (폴더가 생성되었는지 검사) 폴더가 존재하지 않으면
			fileDirectory.mkdirs(); 							// 					   폴더를 만들어주세요.
			System.out.println("업로드용 폴더 생성: " + uploadPath);
		}
		String savedName = uid.toString() + "_" + originalName;	// 오리지널 파일명에 유효 아이디를 붙여서 savedName으로 파일명 지정
		logger.info("savedName: " + savedName);
		File target = new File(uploadPath, savedName);			// uploadPath + savedName을 target으로 만듦
		FileCopyUtils.copy(fileData, target);					// 실제 경로에 만들어진 파일을 저장할 거야
		// Service --> DAO 연결
		return savedName;
	}
		
		
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 상품 등록 WriteForm
		@RequestMapping("prodWriteForm")
		public String prodWriteFormStart(Model model) {
			logger.info("DhProdControllerA prodWriteForm Start..");
			
			model.addAttribute("menu_num", 2);
			return "aDhProdWriteForm";
		}
		
		// 상품 등록 insert
	      @PostMapping("prodWrite")
	      public String prodWrite(Product product, Model model, HttpServletRequest request,
	            MultipartFile th_imgJia, MultipartFile main_img1Jia, MultipartFile main_img2Jia , MultipartFile prod_infoJia ) throws Exception {
	         logger.info("DhProdControllerA prodWrite Start..");
	         System.out.println("DhProdControllerA prodWrite product.getProd_poststs() -> " + product.getProd_poststs());
	         
	         String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
	         
	         logger.info("originalName: " + th_imgJia.getOriginalFilename());
//	         logger.info("title: " + title);
	         logger.info("size: " + th_imgJia.getSize());
	         logger.info("contentType: " + th_imgJia.getContentType());
	         logger.info("uploadPath: " + uploadPath);
	         String savedName1 = uploadFile(th_imgJia.getOriginalFilename(), th_imgJia.getBytes(), uploadPath);      // 배너이미지 파일 저장
	         String savedName2 = uploadFile(main_img1Jia.getOriginalFilename(), main_img1Jia.getBytes(), uploadPath);   // 대표이미지1 파일 저장
	         String savedName3 = uploadFile(main_img2Jia.getOriginalFilename(), main_img2Jia.getBytes(), uploadPath);   // 대표이미지2 파일 저장
	         String savedName4 = uploadFile(prod_infoJia.getOriginalFilename(), prod_infoJia.getBytes(), uploadPath);   // 상세페이지 파일 저장
//	                           바로 아래 uploadFile 메서드가 있음. 메서드를 따로 빼서 만들어 줌
	         logger.info("th_img: " + savedName1);
	         logger.info("main_img1: " + savedName2);
	         logger.info("main_img2: " + savedName3);
	         logger.info("prod_info: " + savedName4);
	         
	         product.setTh_img(savedName1);
	         product.setMain_img1(savedName2);
	         product.setMain_img2(savedName3);
	         product.setProd_info(savedName4);

	         
	         int result = dhps.prodInsert(product);
	         System.out.println("DhProdControllerA prodWrite result -> " + result);
	         
	         if (result > 0)
	            return "redirect:prodSelect";
	         else {
	            model.addAttribute("result", result);
	            model.addAttribute("msg", "등록되지 않았습니다. 다시 시도해주세요.");
	            return "forward:prodWriteForm";
	         }
	         
	      }
		
		private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws Exception {
//			private으로 잡은 이유
//			이 컨트롤러 클래스의 내부에서만 쓰기 때문
			
//			universally unique identifier : 유효 아이디. 유일한 번호를 돌려준다. 중첩 될 일이 없다.
			UUID uid = UUID.randomUUID();
			
			//requestPath = requestPath + "/resources/image";
			System.out.println("uploadPath -> " + uploadPath);
			// Directory 생성
			File fileDirectory = new File(uploadPath);				// 폴더를 만들어 놓고,
			if (!fileDirectory.exists()) {							// (폴더가 생성되었는지 검사) 폴더가 존재하지 않으면
				fileDirectory.mkdirs(); 							// 					   폴더를 만들어주세요.
				System.out.println("업로드용 폴더 생성: " + uploadPath);
			}
			String savedName = uid.toString() + "_" + originalName;	// 오리지널 파일명에 유효 아이디를 붙여서 savedName으로 파일명 지정
			logger.info("savedName: " + savedName);
			File target = new File(uploadPath, savedName);			// uploadPath + savedName을 target으로 만듦
			FileCopyUtils.copy(fileData, target);					// 실제 경로에 만들어진 파일을 저장할 거야
			// Service --> DAO 연결
			return savedName;
		}
		
		
		
		// 네이버 에디터 파일 업로드
		// 출처: https://fvor001.tistory.com/11 [Dev Log:티스토리]
//		@RequestMapping(value="smarteditorMultiImageUpload")
//		public void smarteditorMultiImageUpload(HttpServletRequest request, HttpServletResponse response){
//			try {
//				//파일정보
//				String sFileInfo = "";
//				//파일명을 받는다 - 일반 원본파일명
//				String sFilename = request.getHeader("file-name");
//				//파일 확장자
//				String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
//				//확장자를소문자로 변경
//				sFilenameExt = sFilenameExt.toLowerCase();
//					
//				//이미지 검증 배열변수
//				String[] allowFileArr = {"jpg","png","bmp","gif"};
//
//				//확장자 체크
//				int nCnt = 0;
//				for(int i=0; i<allowFileArr.length; i++) {
//					if(sFilenameExt.equals(allowFileArr[i])){
//						nCnt++;
//					}
//				}
//
//				//이미지가 아니라면
//				if(nCnt == 0) {
//					PrintWriter print = response.getWriter();
//					print.print("NOTALLOW_"+sFilename);
//					print.flush();
//					print.close();
//				} else {
//					//디렉토리 설정 및 업로드	
//					
//					//파일경로
////					String filePath = "경로설정";
//					String filePath = request.getSession().getServletContext().getRealPath("/upload/");
//					File file = new File(filePath);
//					
//					if(!file.exists()) {
//						file.mkdirs();
//					}
//					
//					String sRealFileNm = "";
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//					String today= formatter.format(new java.util.Date());
//					sRealFileNm = today+UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
//					String rlFileNm = filePath + sRealFileNm;
//					
//					///////////////// 서버에 파일쓰기 ///////////////// 
//					InputStream inputStream = request.getInputStream();
//					OutputStream outputStream=new FileOutputStream(rlFileNm);
//					int numRead;
//					byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
//					while((numRead = inputStream.read(bytes,0,bytes.length)) != -1){
//						outputStream.write(bytes,0,numRead);
//					}
//					if(inputStream != null) {
//						inputStream.close();
//					}
//					outputStream.flush();
//					outputStream.close();
//					
//					///////////////// 이미지 /////////////////
//					// 정보 출력
//					sFileInfo += "&bNewLine=true";
//					// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
//					sFileInfo += "&sFileName=" + sFilename;
//					sFileInfo += "&sFileURL=" + "/upload/" + sRealFileNm;
//					PrintWriter printWriter = response.getWriter();
//					printWriter.print(sFileInfo);
//					printWriter.flush();
//					printWriter.close();
//				}	
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 비게시에서 게시로 아작스 
		@ResponseBody
		@RequestMapping(value = "chgstsnyajax", produces = "application/text;charset=UTF-8")
		public String chgstsny(int prodno) {
			System.out.println("DhProdControllerA chgstsyn Starts...");
			System.out.println("DhProdControllerA prodno->"+prodno);
			
			int result=dhps.chgstsny(prodno);
			
			String result1=Integer.toString(result);
			
			return result1;
			}
			
		// 게시에서 비게시 아작스 
		@ResponseBody
		@RequestMapping(value = "chgstsynajax", produces = "application/text;charset=UTF-8")
		public String chgstsyn(int prodno) {
			System.out.println("DhProdControllerA chgstsyn Starts...");
			System.out.println("DhProdControllerA prodno->"+prodno);
			
			int result=dhps.chgstsyn(prodno);
			
			String result1=Integer.toString(result);
			
			return result1;
			}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

