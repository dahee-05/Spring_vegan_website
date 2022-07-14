<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/product.css" rel="stylesheet" type="text/css">
<link href="css/accordion.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>
<%
	String context = request.getContextPath();
%>

<!-------------------------------위시리스트 아작스 시작(229-248 번째줄 주석처리 필수)---------------------------------------------------------------------------- -->
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
// 빈 하트 -> 채워진 하트로 위시리스트 집어 넣기 
	function fillh() {
		var prodno=$('#emph').val();
		$.ajax(
			{
				url: "<%=context%>/chgEmpToFilHeart",
				data:{prodno:prodno},
				dataType:'text',
				success:function(data){
					if(data==1){
						$('#imgemph').attr('src','images/icons/fillheart.png');
						history.go(0);
						// 현재 채워진 하트에서 바로 누르는 거 허용하기 위해 새로고침 
					}
					
				}
				
			}		
		)
		
	}


// 채워진 하트 -> 빈 하트로 위시리스트에서 삭제하기 
	function emptyh() {
		var prodno=$('#fillh').val();
		$.ajax(
			{
				url: "<%=context%>/chgFilToEmpHeart",
				data:{prodno:prodno},
				dataType:'text',
				success:function(data){
					if(data==1){
						$('#imgfillh').attr('src','images/icons/emptyheart.png');
						history.go(0);
					}
					
				}
				
			}		
		)
	}
</script>
<!-------------------------------위시리스트 아작스  끝----------------------------------------------------------------------------------------- -->


</head>
<body>
	<div id="wrap">

		<jsp:include page="/WEB-INF/views/header.jsp" />

		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->


		<div class="margin_wrap">
			<div class="main">
				<input type="hidden" id="user_id" value="${id }">
				<!-- 상단 상품 정보 -->
				<div class="prod_wrap clearfix">
					<div class="prod_main_img">
						<img alt="대표 이미지 1" src="../upload/${product.main_img1}"> <img
							alt="대표 이미지 2" src="../upload/${product.main_img2}">
					</div>
					<!-- prod_info_wrap -->
	                <div class="prod_info_wrap">
	                   <div class="prod_view_name">
	                      <p>${product.brand_name}</p>
	                      <p>${product.prod_name}</p>
	                   </div>
	                   <div class="prod_view_price">
	                      <%-- 
	                      <c:if test="${product.dc_rate > 0}">
	                         <span>${product.dc_rate}%</span>
	                         <span><fmt:formatNumber value="${product.sale_price}" pattern="###,###,###"/>원</span>
	                         <span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###" />원</span>                           
	                      </c:if>
  	                      <c:if test="${product.dc_rate == 0}">
 	                         <span></span>
 	                         <span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###" />원</span> 
	                         <span></span>
	                      </c:if>
	                       --%>
	                      
	                      
	                      <!-- 할인율이 있는 경우 -->
	                      <c:if test="${product.dc_rate > 0}">
	                         <!-- 품절되지 않은 경우 -->
	                         <c:if test="${product.prod_salests == 1}">
	                            <span>${product.dc_rate}%</span>
	                            <span><fmt:formatNumber value="${product.sale_price}" pattern="###,###,###"/>원</span>
	                            <span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###"/>원</span>
	                         </c:if>
	                         <!-- 품절된 경우 -->
	                         <c:if test="${product.prod_salests == 2}">
	                            <span>${product.dc_rate}%</span>
	                            <span><fmt:formatNumber value="${product.sale_price}" pattern="###,###,###"/>원</span>
	                            <span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###"/>원</span>
	                            <span>품절</span>
	                         </c:if>
	                      </c:if>
	                      <!-- 할인율이 없는 경우 -->
	                      <c:if test="${product.dc_rate == 0}">
	                         <!-- 품절되지 않은 경우 -->
	                         <c:if test="${product.prod_salests == 1}">
	                            <span></span>
	                            <span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###"/>원</span>
	                            <span></span>
	                         </c:if>
	                         <!-- 품절된 경우 -->
	                         <c:if test="${product.prod_salests == 2}">
	                            <span></span>
	                            <span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###"/>원</span>
	                            <span></span>
	                            <span>품절</span>
	                         </c:if>
	                      </c:if>
	                   </div>
	                   <div class="prod_count_wrap">
							<span class="txt_line">[${product.brand_name}] ${product.prod_name}</span>
							<div class="prod_count">
								<input type='button' onclick='count("minus")' value='-' />
								 <span id='result'>1</span>
								  <input type='button' onclick='count("plus")' value='+' />
							</div>
						</div>
						<div class="total">
								<span id="total_span"><fmt:formatNumber value="${product.sale_price}" pattern="###,###,###"/>원</span>
						</div>
						<div class="prod_view_btn">
							<!-------------------------------위시리스트 담기 버튼 시작----------------------------------------------------------------------------------------- -->
								<c:choose>
									<c:when test="${result==0 }">
										<button id="emph" value="${product.prodno }" onclick="fillh()" >
											<img alt="" src="images/icons/emptyheart.png" id="imgemph">
										</button>
									</c:when>
									<c:when test="${result==1 }">
										<button id="fillh" value="${product.prodno }" onclick="emptyh()" >
											<img alt="" src="images/icons/fillheart.png" id="imgfillh">
										</button>
									</c:when>
									<c:when test="${result==-1 }">
										<a href="jhLoginFormU" onclick="alert('회원가입 후 이용해주세요')">
											<img alt="" src="images/icons/emptyheart.png" >
										</a>
									</c:when>								
								</c:choose>
<!-------------------------------위시리스트 담기 버튼 끝----------------------------------------------------------------------------------------- -->
							<!-- 위시리스트 담기 -->
							<a onclick="btnClick()"><button>장바구니 담기</button></a>
							<a onclick="btnClick2()"><button>바로 구매하기</button></a>
						</div>
						<!-- 아코디언 -->
						<div>
							<section id="wrapper">
								<div class="container">
									<ul class="accordion">
										<li class="item">
											<h2 class="accordionTitle">
												배송정보 <span class="accIcon"></span>
											</h2>
											<div class="text">
												<ul>
													<li>플라스틱 없이 최소한의 종이 보충재만 사용한 친환경 포장 후 발송</li>
													<li>배송비 : 3,000원 (5만원 이상 주문 시 무료배송), 도서 / 산간 지역 추가 배송비
														3,000원</li>
													<li>배송 방법 : CJ대한통운 (전국)</li>
													<li>오후 1시 전까지 결제 완료 시 당일 출고 (주문 폭주 및 자연재해 시 지연 가능)</li>
												</ul>
											</div>
										</li>
										<li class="item">
											<h2 class="accordionTitle">
												교환 및 반품 정보 <span class="accIcon"></span>
											</h2>
											<div class="text">
												<ul>
													<li>상품 수령일로부터 7일 이내 상품 미훼손 및 미사용한 경우 교환 및 반품 가능</li>
													<li>[주문 내역 > 교환/반품] 접수 또는 고객센터로 문의 가능 (1800-9520)</li>
													<li>교환/반품 접수 또는 고객센터 문의없이 일방적으로 상품 발송 시 반송처리함</li>
												</ul>
											</div>
										</li>
									</ul>
								</div>
							</section>
						</div>
					</div>
					<!-- prod_info_wrap -->
				</div>
				<!-- 상단 상품 정보 -->


				<!-- 상품 상세 + 리뷰-->
				<div class="prod_contents_wrap">
					<!-- 탭  -->
					<div class="tap">
						<div class="on">
							<button>상품 상세</button>
						</div>
						<div>
							<button>리뷰</button>
						</div>
					</div>
					<!-- 탭  -->
					
					
					<!-- 뷰 -->
					<div class="view">
						<!-- 상품 상세 설명 -->
						<div class="on">
			                   	<img alt="" src="../upload/${product.prod_info }" width="840px">
						</div>
						<!-- 상품 상세 설명 -->
						
						<!-- 리뷰 -->
						<div>
		
		                    <c:if test="${reviewCount == 0 }">
		                        <section style="text-align: center; margin: 100px 0;">
		                           <p>아직 작성된 리뷰가 없습니다</p>
		                           <img alt="리뷰가 없어서 슬픈 잔망루피" src="images/review_none.png">
		                        </section>
		                    </c:if>
		                    
		                    <c:if test="${reviewCount > 0 }">
								<c:forEach var="review" items="${review}" >
									<div class="rv_total">
										<div class="rv_line">
											<span class="rv_id">${review.writer_id}</span><p><br>
					        				<span>평점 : ${review.rv_stars}/5 </span> &nbsp
					        				<fmt:parseDate value="${review.rv_date}" var="rv_date" pattern="yy-MM-dd"/>
					        				<fmt:formatDate value="${rv_date }" pattern="yy-MM-dd"/>
								        </div>
								        <div class="review_img" >
								            <img alt="" src="../upload/${review.rv_img}" width="200px"> <!-- 경로 미정 -->
								        </div>
								        <div class="rv_ct">
								            <p>${review.rv_content}</p>
								        </div><br>
								        <hr class=""><br>
							        </div>
								</c:forEach>
							 </c:if>	
						<!-- 리뷰 -->
						</div>
						<!-- 뷰 -->
					
				</div>
				<!-- 상품 상세 + 리뷰-->
				
			</div>
		</div>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		</div>
		<jsp:include page="/WEB-INF/views/footer.jsp" />

	</div>
	<!-- wrap -->

	<script>
		// 위시리스트 담기 버튼---------------------------------------------------------
		$(function(){
			/* 위시리스트 담기 -> heart 아이콘 활성화 */
			$(".prod_view_btn").click(function(){
				$(this).toggleClass("active")
			});
			
			/* 탭 선택 시 뷰 보여주기 */
			$(".tap div").click(function(){
				$(".tap div").removeClass("on")
				$(".tap div").eq($(this).index()).addClass("on");
				$(".view div").removeClass("on")
				$(".view div").eq($(this).index()).addClass("on");
			});
			
			
			
		})
		
		// 수량 버튼---------------------------------------------------------
		const price =${product.sale_price};     //"${product.prod_price}"; //상품가격
		function count(type)  {
			  // 결과를 표시할 element
			  const resultElement = document.getElementById('result');
			  // 현재 화면에 표시된 값
			  let bas_qty = resultElement.innerText;
			  
			  // 더하기/빼기
			  if(type === 'plus') {
				  bas_qty = parseInt(bas_qty) + 1;
			    
			    // 상품 잔여수량 체크해서 조건문 걸어줘야 함
			    
			  } else if (type === 'minus')  {
				  bas_qty = parseInt(bas_qty) - 1;
			    if (bas_qty < 1) bas_qty = 1;
			  }
			  
			  var total = bas_qty * price;
			  var total_view = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); //?
			  
			  // 결과 출력
			  resultElement.innerText = bas_qty;
			  $("#total_span").html(total_view+"원");
		}
		function btnClick() {
			/* <span>, <p> 이런애들은 value를 사용하지 않으니까 text로 값을 출력한다. */
			var result = $('#result').text();	// bas_qty
			/* <input id="무언가" type="text" value="값을적잖아요">
			$("#무언가").val(); == 값을적잖아여 */
			var total_price = result * price;	// product.sale_price
			var str = "shoppingcartinsert?prodno=${product.prodno}&sale_price="+total_price+"&bas_qty="+result;
			window.location.href=str;
		}
		
		function btnClick2() {
			var id = $("#user_id").val();
			
			if(!id) {
				alert("로그인 후 이용해주세요.");
				location.href="/jhLoginFormU";
			} else {
				/* <span>, <p> 이런애들은 value를 사용하지 않으니까 text로 값을 출력한다. */
				var result = $('#result').text();	// bas_qty
				/* <input id="무언가" type="text" value="값을적잖아요">
				$("#무언가").val(); == 값을적잖아여 */
				var total_price = result * price;	// product.sale_price
				var str = "payment?prodno=${product.prodno}&sale_price="+price+"&quantity="+result;
				/* alert(str); */
				window.location.href=str;
			}
		}
		
		// 아코디언---------------------------------------------------------
		// variables
		var accordionBtn = document.querySelectorAll('.accordionTitle');
		var allTexts = document.querySelectorAll('.text');
		var accIcon = document.querySelectorAll('.accIcon');

		// event listener
		accordionBtn.forEach(function (el) {
		    el.addEventListener('click', toggleAccordion)
		});

		// function
		function toggleAccordion(el) {
		   var targetText = el.currentTarget.nextElementSibling.classList;
		   var targetAccIcon = el.currentTarget.children[0];
		   var target = el.currentTarget;
		   
		   if (targetText.contains('show')) {
		       targetText.remove('show');
		       targetAccIcon.classList.remove('anime');
		       target.classList.remove('accordionTitleActive');
		   } 
		   else {
		      accordionBtn.forEach(function (el) {
		         el.classList.remove('accordionTitleActive');
		         
		         allTexts.forEach(function (el) {
		            el.classList.remove('show');
		         })
		         
		         accIcon.forEach(function (el) {
		          el.classList.remove('anime');
		         }) 
		         
		      })
		      
		         targetText.add('show');
		         target.classList.add('accordionTitleActive');
		         targetAccIcon.classList.add('anime');
		   }  
		}
	</script>
</body>
</html>