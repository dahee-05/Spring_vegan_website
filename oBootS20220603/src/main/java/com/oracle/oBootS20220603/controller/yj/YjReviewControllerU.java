package com.oracle.oBootS20220603.controller.yj;

import java.io.File;
import java.io.IOException;
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

import com.oracle.oBootS20220603.model.Event;
import com.oracle.oBootS20220603.model.Review;
import com.oracle.oBootS20220603.service.yj.YjReviewServiceU;

@Controller
public class YjReviewControllerU {
	private static final Logger logger = LoggerFactory.getLogger(YjReviewControllerU.class);

	@Autowired
	private YjReviewServiceU rsu;
	
	//리뷰등록페이지 
	@RequestMapping(value="uYjReviewReg")
	public String uYjReviewReg(int prodno, int payno, HttpServletRequest request,  Model model) {
		System.out.println("YjReviewControllerU uYjReviewReg Start...");
		String writer_id = (String) request.getSession().getAttribute("id");
		model.addAttribute("prodno", prodno);
		model.addAttribute("payno", payno);
		model.addAttribute("writer_id", writer_id);
		System.out.println("YjReviewControllerU uYjReviewReg prodno->"+prodno);
		System.out.println("YjReviewControllerU uYjReviewReg writer_id->"+writer_id);
		return "uYjReviewReg";
	}
	
	
	//리뷰등록하기
	@PostMapping(value="uYjReviewWrite")
	public String writeRv(HttpServletRequest request, Review review, Model model, MultipartFile review_imgUp) throws  Exception {
		System.out.println("YjReviewControllerU writeRv Strat...");
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		
		System.out.println("uploadPath1->"+uploadPath);
		
		System.out.println("uploadForm POST Start");
		logger.info("orifinalName: " + review_imgUp.getOriginalFilename());
		//loger.info("title: "+ title)
		logger.info("size: " + review_imgUp.getSize());
		logger.info("contentType: " + review_imgUp.getContentType());
		String savedName1 = uploadFile(review_imgUp.getOriginalFilename(), review_imgUp.getBytes(), uploadPath);
		
		logger.info("savedName: " + savedName1);
		
		review.setRv_img(savedName1);

		int result = rsu.writeRv(review);
		System.out.println("insert result->"+result);
		
		
		
		if(result>0) {
			// payment detail -> rv_status=1 로 변경 
			rsu.statCh(review);
			
			return "redirect:/jhMyOrderListU";
		}
		 else {
	            model.addAttribute("result", result);
	            model.addAttribute("msg", "등록되지 않았습니다. 다시 시도해주세요.");
	            return "forward:uYjReviewReg";
	         }
		
	}


	private String uploadFile(String originalName, byte[] fileData, String uploadPath) throws Exception{
//		UUID uid = UUID.randomUUID();
		//requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath2->"+uploadPath);
		//Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성: " + uploadPath);
		}
		
		String savedName = originalName;
		logger.info("savedName: "+ savedName);
		File target = new File(uploadPath, savedName);
		//File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target);//org.springframework.util.FileCopyUtils
		
		return savedName;
		
		
	}
	
	
}
