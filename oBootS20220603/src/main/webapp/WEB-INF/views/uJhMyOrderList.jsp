<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String context = request.getContextPath();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지(구매내역)</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<link href="css/myorderList.css" rel="stylesheet" type="text/css">

<!-- --------css 시작------------------------------------------->
<style type="text/css">
ul{
	list-style: none;
}
</style>
<!-- --------css 끝--------------------------------------------->

<!-- --------------------------아작스 시작 ------------------------------------------->
<!-- 2. [아작스] 구매완료 -> 취소 신청 변경 (상태 들고 와야함)chSelCan(${status.index }) --> 
<script type="text/javascript">
	var str="";
	function chSelCan(Vindex) {
			var payno=$('#selcanlist'+Vindex).val();
			$.ajax(
				{
					url:"<%=context%>/jhChSelCanU",
					data:{payno:payno},
					dataType:'text',
					success:function(data){
						if(data==2){
							//alert("주문번호 "+payno+"주문 취소 완료");
							str='취소완료'
							$('#selcanlist'+Vindex).remove();
							$('#selfixlistt'+Vindex).remove();
							$('#ordstatus'+payno).html(str);
						} else{
							alert('상태 변경 실패')
						}
							
					}
				}		
			)
			
		}
</script>	

<!-- 3. [아작스] 구매완료 -> 구매 확정 변경 (상태 들고 와야함)chSelDelFix(${status.index }) -->
<script type="text/javascript">
	var str="";
	function chSelFix(Vindex) {
		var payno=$('#selfixlist'+Vindex).val();
		$.ajax(
			{
				url:"<%=context%>/jhChSelFixU",
				data:{payno:payno},
				dataType:'text',
				success:function(data){
					if(data==6){
						//alert("주문번호 "+payno+"구매확정 완료");
						str='구매확정';
						$('#selcanlist'+Vindex).remove();
						$('#selfixlist'+Vindex).remove();
						$('#ordstatus'+payno).html(str);
						history.go(0);
					} else{
						alert('상태 변경 실패')
					}
					
				}
				
			}	
		)
	}
</script>

<!-- 4. [아작스] 배송완료 -> 구매 확정 변경 (상태 들고 와야함)chSelDelFix(${status.index }) -->	
<script type="text/javascript">
	var str="";
	function chDelFix(Vindex) {
		var payno=$('#delfixlist'+Vindex).val();
		
		$.ajax(
			{
				url:"<%=context%>/jhChDelFixU",
				data:{payno:payno},
				dataType:'text',
				success:function(data){
					if(data==6){
						//alert("주문번호 "+payno+"구매확정 완료");
						str='구매확정';
						$('#delreflist'+Vindex).remove();
						$('#delfixlist'+Vindex).remove();
						$('#ordstatus'+payno).html(str);
						history.go(0);
					}
				}
			}
		)
	}

</script>

<!-- 5. [팝업] 배송완료 -> 환불 신청 변경 (상태 들고 와야함)chDelRef(${status.index }) -->
<script type="text/javascript">

	function chDelRef(Index) {
		var payno=$('#delreflist'+Index).val();
		payno=new Number(payno);
		
		
		let popUrl = "<%=context%>/jhChDelRefPopU?payno="+payno;
		let popOption = "width = 450px, height=380px, top=300px, left=300px, scrollbars=yes";
		
		
		openWin=window.open(popUrl,"환불 정보 입력",popOption);
		//openWin.document.getElementById("delreflist${status.index }").value= payno;
		
		};
		

			
</script>
<!---------------------------------아작스 끝 ------------------------------------------->
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
<!-- ----------------------------------------------------------------------드롭다운 메뉴 시작---------------------------------->
<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<h2>구매내역</h2>
					<br>
					<hr>
					<br>
						<!-- 작업 영역 -->
							<c:forEach var="list" items="${orderList }" varStatus="status">
							<!--1. status.index == 0 처음 시작일 때 무조건   보여줌 -->
								<c:if test="${status.index == 0}">
									<div class="status">
										<ul>
											<li class="stsText"><span id="ordstatus${list.payno }" style="color: green">
												<c:choose>
													<c:when test="${list.pay_sts==1}">
														주문완료
														<button id="selcanlist${status.index }" value="${list.payno }" onclick="chSelCan(${status.index })"> 취소신청</button>
														<button id="selfixlist${status.index }" value="${list.payno }" onclick="chSelFix(${status.index })"> 구매확정</button>
													</c:when>
													<c:when test="${list.pay_sts==2}">
														취소완료
													</c:when>
													<c:when test="${list.pay_sts==3}">
														환불 진행중
													</c:when>
													<c:when test="${list.pay_sts==4}">
														환불완료
													</c:when>	
													<c:when test="${list.pay_sts==5}">
														배송완료
														<button id="delreflist${status.index }"  value="${list.payno }" onclick="chDelRef(${status.index })" >환불신청</button>
														<button id="delfixlist${status.index }"  value="${list.payno }"  onclick="chDelFix(${status.index })" >구매확정</button>
													</c:when>
													<c:when test="${list.pay_sts==6}">
														구매확정
													</c:when>
												</c:choose>
											</span>
											</li>
											<li class="dateText"> 주문일 : <fmt:formatDate value="${list.pay_date}" pattern="yyyy-MM-dd"/> </li>
										</ul>
									</div>
									<div class="infobox">
										<div class="thm_img">
											<img class='thm_rimg' alt="img" src="../upload/${list.th_img }">
										</div>
										
										<div class="prod_info">
											<ul>
												<li class="listTitle"><a href="jhMyOrderDetailU?payno=${list.payno }" > [${list.brand_name}] ${list.prod_name}</a>
													<c:choose>
														<c:when test="${list.rv_status==0 && list.pay_sts==6}">
															<button onclick="location.href='uYjReviewReg?prodno=${list.prodno}&payno=${list.payno }'" value="${list.rv_status}">리뷰작성</button>
														</c:when>
													</c:choose>
												</li>
												<li class="listQty">수량 : ${list.buy_qty}개</li>
												<li class="listMon"><fmt:formatNumber value= "${list.prod_price*(1-(list.dc_rate*0.01))*list.buy_qty}" pattern="###,###,###"/>${pages}원</li>
												
											</ul>
										</div>
									</div><p>
									<c:set var="payno" value="${list.payno}" />
									<input type="hidden" id="ordpayno" value="${list.payno }" >
								</c:if>
								<c:choose>
		     						<c:when test="${status.index > 0 }">
		        					<!-- 2-1. 처음 값이 아닐 때 payno중복되면 안보여줌 -->
			    						<c:if test="${list.payno eq payno}">
				        					<div class="infobox">
				    							<div class="thm_img">
													<img class='thm_rimg' alt="img" src="../upload/${list.th_img }">
												</div>
												<div class="prod_info">
													<ul>
														<li class="listTitle"><a href="jhMyOrderDetailU?payno=${list.payno }" > [${list.brand_name}] ${list.prod_name}</a>
															<c:choose>
																<c:when test="${list.rv_status==0 && list.pay_sts==6}">
																	<button onclick="location.href='uYjReviewReg?prodno=${list.prodno}&payno=${list.payno }'" value="${list.rv_status}">리뷰작성</button>
																</c:when>
															</c:choose>
														</li>												
														<li class="listQty">수량 : ${list.buy_qty}개</li>
														<li class="listMon"><fmt:formatNumber value= "${list.prod_price*(1-(list.dc_rate*0.01))*list.buy_qty}"/>${pages}원</li>
													</ul>
												</div>
				    						<c:set var="payno" value="${list.payno}"/>
				    						<input type="hidden" id="ordpayno" value="${list.payno }" >
				     						</div>
			    						</c:if>
			    						
			     						<!-- 2-2. 처음 값이 아닐 때 중복되지 않는 payno일 땐 보여줌 -->
			    						<c:if test="${list.payno ne payno}">
											<div class="status">
												<ul>
													<li class="stsText"><span id="ordstatus${list.payno }" style="color: green">
														<c:choose>
															<c:when test="${list.pay_sts==1}">
																주문완료
																<button id="selcanlist${status.index }" value="${list.payno }" onclick="chSelCan(${status.index })" >취소신청</button>
																<button id="selfixlist${status.index }" value="${list.payno }"  onclick="chSelFix(${status.index })" >구매확정</button>
															</c:when>
															<c:when test="${list.pay_sts==2}">
																취소완료
															</c:when>
															<c:when test="${list.pay_sts==3}">
																환불 진행중
															</c:when>
															<c:when test="${list.pay_sts==4}">
																환불완료
															</c:when>	
															<c:when test="${list.pay_sts==5}">
																배송완료
																<button id="delreflist${status.index }" value="${list.payno }"  onclick="chDelRef(${status.index })" >환불신청</button>
																<button id="delfixlist${status.index }" value="${list.payno }"  onclick="chDelFix(${status.index })" >구매확정</button>
															</c:when>
															<c:when test="${list.pay_sts==6}">
																구매확정
															</c:when>
														</c:choose>
													</span></li>
													<li class="dateText"> 주문일 : <fmt:formatDate value="${list.pay_date}" pattern="yyyy-MM-dd"/></li>
												</ul>
											</div>
											<div class="infobox">
												<div class="thm_img">
													<img class='thm_rimg' alt="img" src="../upload/${list.th_img }">
												</div>
												<div class="prod_info">
													<ul>
														<li class="listTitle"><a href="jhMyOrderDetailU?payno=${list.payno }" > [${list.brand_name}] ${list.prod_name}</a>
															<c:choose>
																<c:when test="${list.rv_status==0 && list.pay_sts==6}">
																	<button onclick="location.href='uYjReviewReg?prodno=${list.prodno}&payno=${list.payno }'" value="${list.rv_status}">리뷰작성</button>
																</c:when>
															</c:choose>
														</li>
														<li class="listQty">수량 : ${list.buy_qty}개</li>
														<li class="listMon"><fmt:formatNumber value= "${list.prod_price*(1-(list.dc_rate*0.01))*list.buy_qty}"/>${pages}원</li>
													</ul>
												</div>
											<c:set var="payno" value="${list.payno}"/>
											<input type="hidden" id="ordpayno" value="${list.payno }" >
											</div>
										</c:if>
		   						</c:when>
							</c:choose>
						</c:forEach>
						<!-- 작업 영역 -->
				</section>	
			</div>
		</div>
<!-- ----------------------------------------------------------------------여기에 코딩 끝---------------------------------->
<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
	
	<jsp:include page="/WEB-INF/views/footer.jsp"/>
	</div> <!-- wrap -->
</body>
</html>