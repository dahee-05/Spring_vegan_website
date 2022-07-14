package com.oracle.oBootS20220603.controller.ch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootS20220603.model.Product;
import com.oracle.oBootS20220603.service.ch.ChCouponService;
import com.oracle.oBootS20220603.service.ch.ChSearchService;

@Controller
public class ChSearchControllerU {

	private static final Logger logger = LoggerFactory.getLogger(ChSearchControllerU.class);
	
	@Autowired
	private ChSearchService chss;

	
	@RequestMapping(value = "/searchProduct")
	public String searchProductList(Product product, String search, Model model) {
		
		logger.info("ChSearchControllerU searchProductList start...");
		
		List<Product> searchProdList = chss.listSearchProduct(search);
		System.out.println("searchProdList.size()->" + searchProdList.size());
		model.addAttribute("searchProdList", searchProdList);
		model.addAttribute("listSize", searchProdList.size());
		model.addAttribute("search", search);
		
		
		return "uChSearchProdList";
	}
}
