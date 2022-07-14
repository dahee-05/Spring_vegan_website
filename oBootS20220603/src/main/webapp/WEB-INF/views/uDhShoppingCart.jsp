<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 ShoppingList</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/shoppingcart.css" rel="stylesheet" type="text/css">

<script src="js/jquery.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
</head>
<body>
	<div id="cart_wrap">
	
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<!---------------------------------------------------------------------main시작----------------------------------- -->
	<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<div>
						<h2>장바구니</h2>
						<div class="contents_wrap">
							<!-- <div class="cart_clearfix"> -->
							
							<form id="frm" action="cart_payment" onsubmit="return false" >
								<table class="subject_table">
									<tr>
										<th class="td_width_1">
											<!-- 체크박스 전체 여부  -->
											<div class="all_check_input_div">
												<input type="checkbox" class="all_check_input" checked="checked"><span class="all_check_span"></span> 
											</div>
										</th>
										<th class="td_width_2"></th>
										<th class="td_width_3">상품명</th>
										<th class="td_width_4">수량</th>
										<th class="td_width_4">가격</th>
										<th class="td_width_4">비고 </th>			
								 	</tr>
								</table> 
							  	<c:forEach var="cartlist" items="${listcart}" varStatus="status"> <!-- varStatus로 1개의 포이치문이 돌았던 삭제버튼에 인덱스번호 걸어주기   -->
							 		<table class="cart_table">
							 			<caption>표 내용부분</caption>
						 				<tr>
							 				<%-- <input type="hidden" value="${listprod.id}"> --%>
							 				<input type="hidden" value="${cartlist.id }">
							 				<input type="hidden" value="${cartlist.prodno}">
							 				
							 				<td class="td_width_1 cart_info_td">
							 					<input type="checkbox" class="individual_cart_checkbox_input" name='prodno' value="${cartlist.prodno}" checked="checked">
							 					<input type="hidden" class="individual_prodno_input" value="${cartlist.prodno}"> 
												<input type="hidden" class="individual_th_img_input" value="${cartlist.th_img}"> 
												<input type="hidden" class="individual_prod_name_input" value="${cartlist.prod_name}"> 
												<input type="hidden" class="individual_bas_qty_input" value="${cartlist.bas_qty}"> 
												<input type="hidden" class="individual_sale_price_input" value="${cartlist.sale_price}"> 
							 				</td>
											<td class="td_width_2">
												<div id="prod_box">
													<div id="img"><img alt="대표이미지" src="../../upload/${cartlist.th_img}"></div>
												</div>
											</td>
											<td class="td_width_3">${cartlist.prod_name}</td>
											<td class="td_width_4 table_text_align_center">${cartlist.bas_qty}</td>
											<td class="td_width_4 table_text_align_center">
												<fmt:formatNumber value="${cartlist.sale_price}" pattern="###,###,###"/>원</td>
											<td class="td_width_4 table_text_align_center delete_btn">
												<button class="dbnt" id="delshl" onclick="getDelete(${cartlist.prodno})" value="${status.index}">삭제</button>
											</td>
						 				</tr>   <!-- id값 대신 onclick버튼에 prodno 값 넣어서 보내주기 / value값에 인덱스 걸어주기 -->
									</table>
								</c:forEach> 
							 	
								<!-- <table class="list_table"> </table> -->
								<!-- <div class="totalKind">
									<span class="totalKind_span"></span>개	 총 수량														
								</div> -->
								<div class="deliver">
									<span class="delivery_price"></span>
									<span class="totalPrice_span"></span>  <!-- 총 상품 가격 -->
					     		</div>
								<div class="total">
									<span class="org_amount_span"></span>원  <!--배송비 + 총 상품 가격 -->
								</div>
								<a href="payment?prodprice=${0}"></a>
								<div class="content_btn_section">
									<!-- <a onclick="btnClick2()"><button>결제하기</button></a>  -->
									<a><button type="submit" onclick="document.getElementById('frm').submit()">결제하기</button></a>
								</div>	
							</form> 
					<!-- </div> -->
					</div>
				</section>	
			</div>
		</div>
		<!-- ----------------------------------------------------------------------여기에 코딩 끝------------------------------------>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
	</div> <!-- wrap -->

	<script>
	$(document).ready(function(){
		
		setTotalInfo();	/* 종합정보 섹션 정보 삽입 */
	});
	
	/* 체크여부에 따른 종합 정보 변화 */
	$(".individual_cart_checkbox_input").on("change", function(){
		
		setTotalInfo($(".cart_info_td"));  /* 총 주문 정보 세팅 */
	});
	
	
	/* 체크박스 전체 선택 */
	$(".all_check_input").click(function(){
		/* 체크박스 체크&해제 */
		if($(".all_check_input").prop("checked")){
				$(".individual_cart_checkbox_input").prop("checked",true);
		} else {
				$(".individual_cart_checkbox_input").prop("checked",false);
		}
		/* 총 주문 정보 세팅(배송비, 총 가격, 최종가격) */
		setTotalInfo($(".cart_info_td"));
	});
	
	/* 체크 박스 중 하나를 풀었을 경우 전체 체크박스 해제 */
	$(".individual_cart_checkbox_input").click(function(){
		var checkboxLen = $("input:checked[name='prodno']").length; //개별 체크박스의 개수
		var checkboxCheckedLen = $("input:checkbox[name='prodno']:checked").length; // 개별 체크박스 중 선택된 체크박스의 개수
		if($("input[name='prodno']:checked").length == $(".individual_cart_checkbox_input").length){
			$(".all_check_input").prop("checked",true);
		}else{
			$(".all_check_input").prop("checked",false);
		}
		setTotalInfo($(".cart_info_td"));
	});
	
	
	function setTotalInfo(){	
		
		let totalCount  =0;      //총 수량
		let totalPrice = 0;      // 총 가격
		let delivery_fee = 0;   // 배송비
		let org_amount =0;  //최종 가격(배송비 + 최종 상품가)
		
		$(".cart_info_td").each(function(index,element){
			
			if($(element).find(".individual_cart_checkbox_input").is(":checked") === true){  // 체크여부
			//총 가격
			totalPrice += parseInt($(element).find(".individual_sale_price_input").val());
			//총 수량
			/* totalKind += parseInt($(element).find(".individual_bas_qty_input").val()); */
			}
		});
		
		/* 배송비 결정 */
		if( totalPrice ==0 ){
			delivery_fee =0;		
		}else{
			delivery_fee=3000;
		}
		
		/* 최종 가격 */
		org_amount = totalPrice +delivery_fee;
		
		/* 값 삽입 */
		// 총 가격
		$(".totalPrice_span").text("주문금액"+" "+totalPrice.toLocaleString()+"원");
		// 배송비
		$(".delivery_price").text("배송비"+" "+delivery_fee +"원+");
		// 최종가격
		$(".org_amount_span").text("총"+" "+ org_amount.toLocaleString());
	}
		
	function getDelete(a){  /* getDelete()괄호안의 변수명은 아무거나 지정해주어도 위의 해당 값이 들어가게 된다 */
		var str="deletesh?prodno="+a;
		//alert("해당 상품이 삭제되었습니다.");
		window.location.href=str; 
	}
	
	
    </script>
</body>
</html>