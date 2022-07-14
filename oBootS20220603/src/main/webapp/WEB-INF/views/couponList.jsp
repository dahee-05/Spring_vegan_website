<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰</title>
<style type="text/css">
	
	h3 {
		font-size: 22px;
	    color: #5F8350;
	    width: 100%;
	    text-align: center;
	    height:60px;
	    line-height:60px;
	    border-bottom:2px solid #5F8350;
	}


	.coupon_box {
		border:1px solid #c0c0c0;
		width:530px;
		margin:0 auto;
		display:flex;
		flex-direction:row;
		margin-top:20px;
		border-radius:10px;
		
	}
	
	.coup_info {
		padding:15px;
		width:78%;
		height:150px;
	}
	
	.coup_info span {
		font-size:22px;
		font-weight:bold;
		color: red;
	}
	
	.coup_info h4 {
		margin:15px 0 25px 0;
		font-size:20px;
	}
	
	.coup_info p {
		font-size:15px;
		color:#c0c0c0;
		margin: 5px 0 0 0;
	}
		
	.select_btn {
		flex-grow:1;
		height:182px;
		text-align:center;
		line-height:182px;
		background-color:#8AB758;
		cursor:pointer;
		font-size:15px;
		border-bottom-right-radius: 10px;
   		border-top-right-radius: 10px;
	}
	
	
	
</style>
<script src="js/jquery.js"></script>
</head>
<body>
	<h3>쿠폰</h3>
		
	<c:forEach var="coupon" items="${coup_list }">
		<div class="coupon_box clearfix">
			<div class="coup_info">
				<c:if test="${coupon.coup_type == 1 }">
					<span>${coupon.coup_dc_rate }% 할인</span>
				</c:if>
				<c:if test="${coupon.coup_type == 2 }">
					<span><fmt:formatNumber value="${coupon.coup_dc_price }" groupingUsed="true"></fmt:formatNumber>원 할인</span>
				</c:if>
				
				<h4>${coupon.coup_name }</h4>
				
				<p style="color:black">
					<fmt:parseDate value="${coupon.coup_start}" var="coup_start" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${coup_start }" type="DATE" pattern="yyyy-MM-dd"/>
					 ~ 
					<fmt:parseDate value="${coupon.coup_end}" var="coup_end" pattern="yyyy-MM-dd"/>
					<fmt:formatDate value="${coup_end }" type="DATE" pattern="yyyy-MM-dd"/>
				</p>
				
				<p><fmt:formatNumber value="${coupon.coup_req_price }" groupingUsed="true"></fmt:formatNumber> 이상 구매시 적용 가능</p>
			</div>
			
			<div class="select_btn" onclick="couponSelect(${coupon.coupno},${coupon.coup_type },
								 ${coupon.coup_req_price }, ${coupon.coup_dc_rate }, ${coupon.coup_dc_price })">
				적용하기
			</div>							 
		</div>
	</c:forEach>
	
	<script type="text/javascript">
		function couponSelect(coupno, coup_type, coup_req_price, coup_dc_rate, coup_dc_price) {
			var total_amount = opener.document.getElementById("total_amount").value;
			var prod_amount = opener.document.getElementById("prod_amount").value;
			var coup_sale = 0;
			if(prod_amount < coup_req_price) {
				alert("최소 가격을 충족하지 못했습니다.");
			} else {
				if(coup_type == 1) {
					coup_sale = Math.ceil((prod_amount*(coup_dc_rate / 100))/10) * 10;
				} else {
					coup_sale = coup_dc_price;
				}
				window.close();
				var subs = $(opener.document).find("#subs").val();
				
				var mile_use = $(opener.document).find("#mile_use").val();
				//alert(mile_use);
				mile_use = Number(mile_use);
				coup_sale = Number(coup_sale);
				var total_sale = mile_use + coup_sale;
				var total_real_amount = 0;
				
				total_real_amount = total_amount - total_sale;
				
				var mile_use_view = mile_use.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				var coup_sale_view = coup_sale.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				var total_sale_view = total_sale.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				var total_real_amount_view = total_real_amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				
				opener.document.getElementById("coupno").value = coupno;
				opener.document.getElementById("coup_sale").value = coup_sale;
				opener.document.getElementById("coup_sale_span").innerText = coup_sale_view+"원";
				$(opener.document).find("#total_sale").val(total_sale);
				$(opener.document).find(".total_sale_li").html(total_sale_view+"원");
				$(opener.document).find("#total_real_amount_li").html(total_real_amount_view+"원");
				$(opener.document).find("#real_amount").val(total_real_amount);
				$(opener.document).find("#goPay").html(total_real_amount_view+"원 결제하기");
			}
			
		} 
	
	</script>
</body>
</html>