package com.oracle.oBootS20220603.service.hk;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oracle.oBootS20220603.model.KakaoPayApprovalVO;
import com.oracle.oBootS20220603.model.KakaoPayReadyVO;
import com.oracle.oBootS20220603.model.Member;
import com.oracle.oBootS20220603.model.Payment;
import com.oracle.oBootS20220603.model.Product;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Service
@Log
public class KakaoPay {
	
	private static final String HOST = "https://kapi.kakao.com";
    
    private KakaoPayReadyVO kakaoPayReadyVO;
    
    
    public String kakaoPayReady(Model model) {
    	
    	Map<String, Object> map = model.asMap();
    	Payment payment = (Payment) map.get("payment");
    	Member member = (Member) map.get("member");
    	HashMap<Product, Integer> order_list = (HashMap<Product, Integer>) map.get("order_list");
    	String item_name = (String) map.get("item_name");
    	if(order_list.size() > 1) {
    		item_name += " 외 "+ (order_list.size()-1)+"종";
    	}
    	
    	
        RestTemplate restTemplate = new RestTemplate();
        
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "094acf1ade7ace93cebe519ba2fcab35");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");			// 고정 
        params.add("partner_order_id", Integer.toString(payment.getPayno()));		// 결제 번호
        params.add("partner_user_id", member.getId());	// 회원 id 
        params.add("item_name", item_name);			// 상품명
        params.add("quantity", "1");				// 수량
        params.add("total_amount", Integer.toString(payment.getReal_amount()));			// 실제 결제 금액 real_amount
        params.add("tax_free_amount", "100");		// 상품 비과세 금액
        params.add("approval_url", "http://localhost:8380/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8380/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8380/kakaoPaySuccessFail");
 
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
 
        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            
            log.info("" + kakaoPayReadyVO);
            
            return kakaoPayReadyVO.getNext_redirect_pc_url();
 
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "/pay";
    }
    
    
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, Payment payment) {
    	 
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");
        
        RestTemplate restTemplate = new RestTemplate();
 
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "094acf1ade7ace93cebe519ba2fcab35");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
 
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", Integer.toString(payment.getPayno()));
        params.add("partner_user_id", payment.getId());
        params.add("pg_token", pg_token);
        params.add("total_amount", Integer.toString(payment.getReal_amount()));
        
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        
        try {
            KakaoPayApprovalVO kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);
          
            return kakaoPayApprovalVO;
        
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
    
    
}
