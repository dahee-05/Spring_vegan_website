package com.oracle.oBootS20220603.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.oBootS20220603.service.hk.HKLoginInterceptorA;
import com.oracle.oBootS20220603.service.jh.JhSampleInterceptorU;

@Configuration
public class JhPaymentDetailConfigurationU implements WebMvcConfigurer {
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JhSampleInterceptorU()).addPathPatterns("/jhInterCeptor")
														   .addPathPatterns("/hkWishInterCeptor")
														   .addPathPatterns("/hkCartInterCeptor");
		
//		registry.addInterceptor(new HKLoginInterceptorA()).addPathPatterns("/admin");
//														  .addPathPatterns("/prodSelect")
//														  .addPathPatterns("/jhDelivListA")
//														  .addPathPatterns("/jhCanListA")
//														  .addPathPatterns("/aYjMemberList")
//														  .addPathPatterns("/reviewList")
//														  .addPathPatterns("/coupMasList")
//														  .addPathPatterns("/aSwEventList")
//														  .addPathPatterns("/aSwFaqList")
//														  .addPathPatterns("/chatrooms")
//														  .addPathPatterns("/admin/banner");
		
	}
}
