<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String context = request.getContextPath();
%>


<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
	<meta charset="utf-8" />
	<title>비숲 | 상품 관리</title>
	<link href="css/admin_product.css" rel="stylesheet" type="text/css">
	<jsp:include page="A_head.jsp"/>
	
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>


<!-- 게시 비게시 ------------------------------------------------------------------------------- -->
<script type="text/javascript">
	 function deleteBtn() {
		var confirm_val=confirm('정말로 삭제하시겠습니까?');
		var cp=$('#cp').val();
		
		if(confirm_val){
			var checkArr=new Array();
			$("input[name='boxchk']:checked").each(function(){
				checkArr.push($(this).attr("value"))
				
			});
			
			$.ajax(
					{
						url: "<%=context%>/adminProdDelAJAX",
						type: "post",
						data : {chbox: checkArr},
						dataType: "text",
						success : function(result){
							if(result ==1){
								alert('삭제가 완료되었습니다')
								location.href = "prodSelect?currentPage="+cp;
							}else{
								alert("삭제실패");
							}					
						}
					}
				)
			}
		};
		
		// 비게시에서 게시로 아작스
		function chgstsny(prodno) {
			var prodno=prodno;
			//alert('prodno->'+prodno);
			var data1='게시';
			//alert('data1->'+data1);
			var data2='비게시';
			//alert('data2->'+data2);	
			var data3='판매중'
			//alert('data3->'+data3);
			
			$.ajax(
				{
					url: "<%=context%>/chgstsnyajax",
					data: {prodno:prodno},
					////////////////////////////
					dataType: "text",
					success : function(result1){
						if(result1 ==1){
							//alert('게시로 상태 변경 완료')
							$('#nshowy'+prodno).html(data1);
							$('#nshowyT'+prodno).html(data2);
							$('#stopT'+prodno).html(data3);
							/* var yshown=$('btn btn-sm btn-outline-secondary').attr('class');
							$('.btn btn-sm btn-primary').text(yshown); */
							$('#nshowyB'+prodno).attr('class','btn btn-sm btn-outline-secondary');
							$('#stop'+prodno).attr('class','badge bg-label-primary me-1');
							history.go(0);
						}else{
							alert("상태변경 실패");
						}		
					}
					
				}		
			)
		};
		
		// 게시에서 비게시 아작스
		function chgstsyn(prodno) {
			var prodno=prodno;
			//alert('prodno->'+prodno);
			var data1='비게시';
			//alert('data1->'+data1);
			var data2='게시';
			//alert('data2->'+data2);
			var data3='판매중지'
			//alert('data3->'+data3);
			
			
			$.ajax(
				{
					url: "<%=context%>/chgstsynajax",
					data: {prodno:prodno},
					////////////////////////////
					dataType: "text",
					success : function(result1){
						if(result1 ==1){
							//alert('비게시로 상태 변경 완료')
							$('#yshown'+prodno).html(data1);
							$('#yshownT'+prodno).html(data2);
							$('#ingT'+prodno).html(data3);
							/* var nshown=$('btn btn-sm btn-primary').attr('class');
							$('.btn btn-sm btn-outline-secondary').text(nshown); */
							$('#yshownB'+prodno).attr('class','btn btn-sm btn-primary');
							$('#ing'+prodno).attr('class','badge bg-label-danger me-1');
							history.go(0);
						}else{
							alert("상태변경 실패");
						}		
					}
					
				}		
			)
		};
		
</script>	
<!-- 게시 비게시 ------------------------------------------------------------------------------- -->
	
</head>
<body>
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-content-navbar">
		<div class="layout-container">
			<jsp:include page="A_menu.jsp"/>

			<!-- Layout container -->
			<div class="layout-page">

				<!-- Content wrapper -->
				<div class="content-wrapper">

					<!-- Content -->
					<div class="container-xxl flex-grow-1 container-p-y">
						<h4 class="fw-bold py-3 mb-4">상품 관리</h4>
							<!-- Account -->

                            <!-- 판매 상태 카운팅 -->
							<div class="card mb-4">
                                <div class="parent">
                                    <div class="parent_inner">
                                        <div class="parent_content">    
                                            <div class="prod_count">
                                                <span>전체</span><br>
                                                <span class="prod_count_point">${totalc}</span>
                                                <span> 건</span>
                                            </div>
                                            <div class="prod_count clearfix">
                                                <span>판매중</span><br>
                                                <span class="prod_count_point">${sale}</span>
                                                <span> 건</span>
                                            </div>
                                            <div class="prod_count">
                                                <span>품절</span><br>
                                                <span class="prod_count_point">${sold_out}</span>
                                                <span> 건</span>
                                            </div>
                                            <div class="prod_count">
                                                <span>판매중지</span><br>
                                                <span class="prod_count_point">${stop_sel}</span>
                                                <span> 건</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
							</div>
                            <!-- 판매 상태 카운팅 -->
                            <!-- 상품 상세 조회(검색) -->
							<div class="card mb-5">
							  <div class="card-body">
							  <input type="hidden" name="currentPage" id="cp" value="${pg.currentPage }">
							  <!-- keyword1 keyword2, prod_salests 같이 조회  -->
                                   <form id="ffrr" action="prodSearch" onsubmit="return false">
                                    <div class="search">
	                                    <div class="mb-3 row">
	                                        <label class="col-md-2 col-form-label">카테고리</label>
	                                        <div class="col-md-3">
	                                        <select   name="search"  class="form-select" >
	                                            <option value="0" selected="selected">ALL</option>
	                                            <option value="1">비건식품</option>
	                                            <option value="2">생활용품</option>
	                                            <option value="3">고체뷰티</option>
	                                            <option value="4">스킨케어</option>
	                                        </select>
	                                        </div>
	                                    </div>
				                        <div class="mb-3 row">
				                          <label class="col-md-2 col-form-label">상품번호</label>
				                          <div class="col-md-3">
				                            <input class="form-control" type="text"  name="keyword1" value="${keyword1}" />
				                          </div>
	                                      <label class="ms-4 col-md-1 col-form-label">상품명</label>
	                                      <div class="col-md-3">
	                                        <input class="form-control" type="text" name="keyword2" value="${keyword2}"/>
	                                      </div>
				                        </div>
				                       </div> 
			                       
                                    <!-- 날짜 선택 -->
                                    <div class="mt-4" style="text-align: center;">
                                        <button type="submit" class="btn btn-primary" style="font-size: 14px;" 
                                        			onclick="document.getElementById('ffrr').submit()">검색</button>
                                        <button class="reset_btn"><img src="images/icons/reset.svg" style="vertical-align: middle"> 초기화</button>
                                    </div>
								  </form>
							  </div>
							</div>
							<!-- 상품 상세 조회(검색) -->
                            
                            <div class="mb-3" style="text-align: right;">
                                <button type="button" class="btn btn-secondary" onclick="deleteBtn()" style="font-size: 14px;">삭제</button>
                                <button type="button" class="btn btn-primary" style="font-size: 14px;"
                                		onclick="location.href='prodWriteForm'">상품 등록</button>
                            </div>
                                <!-- 상품 리스트 -->
							<div class="card mb-1">
				                <div class="table-responsive text-nowrap">
				                  <table class="table table-borderless">
				                    <thead>
				                      <tr>
				                        <th><input class="all_form_check_input" type="checkbox" name="prodch"/></th>
				                        <th>상품번호</th>
				                        <th>상품명</th>
				                        <th>상품등록일</th>
				                        <th>판매상태 / 판매수량</th>
				                        <th>게시상태</th>
				                      </tr>
				                    </thead>
				                    <tbody>
				                      <c:forEach var="product" items="${prodList}" varStatus="status">
					                      <tr>
					                        <td><input class="form_check_input" name="boxchk" type="checkbox" value="${product.prodno}"/></td>
					                        <td>${product.prodno}</td>
					                        <td class="txt_line" 
					                        	style="cursor: pointer" 
					                        	onclick="location.href='prodView?prodno=${product.prodno}'">
					                        	[${product.brand_name}] ${product.prod_name}
					                        </td>
	                                        <td><fmt:formatDate value="${product.prod_reg}" type="DATE" pattern="yyyy-MM-dd"/></td>
					                        <td>
<!-- -----------------------------------------------------------------------------------------------------------------게시 비게시 ------------------------------------------------------------------------------- -->
					                        	<c:if test="${product.prod_salests == 0}">
							                        <span id="stop${product.prodno}" class="badge bg-label-danger me-1"><span id="stopT${product.prodno}">판매 중지</span> / ${product.sale_qty} 건</span>
					                        	</c:if>
					                        	<c:if test="${product.prod_salests == 1}">
					                        		<span id="ing${product.prodno}" class="badge bg-label-primary me-1"><span id="ingT${product.prodno}">판매 중</span> / ${product.sale_qty} 건</span>
					                        	</c:if>
					                        	<c:if test="${product.prod_salests == 2}">
							                        <span id="soldout${product.prodno}" class="badge bg-label-secondary me-1"><span id="soldoutT${product.prodno}">품절</span> / ${product.sale_qty} 건</span>
					                        	</c:if>
					                        </td>
					                        <td>
					                        	<c:if test="${product.prod_poststs == 0}">
													<span id="nshowy${product.prodno}">비게시</span> &nbsp;
		                                        	<button type="button" id="nshowyB${product.prodno}" class="btn btn-sm btn-primary" onclick="chgstsny(${product.prodno})"><span id="nshowyT${product.prodno}">게시</span></button>
	                                            </c:if>
					                        	<c:if test="${product.prod_poststs == 1}">
													<span id="yshown${product.prodno}">게시</span> &nbsp;
		                                            <button type="button" id="yshownB${product.prodno}" class="btn btn-sm btn-outline-secondary" onclick="chgstsyn(${product.prodno})"><span id="yshownT${product.prodno}" >비게시</span></button>
	                                            </c:if>
<!-- -----------------------------------------------------------------------------------------------------------------게시 비게시 ------------------------------------------------------------------------------- -->
	                                            
	                                        </td>
					                      </tr>
				                      </c:forEach>
				                    </tbody>
				                  </table>
				                </div>
							</div>
                            <!-- 상품 리스트 -->
                            
                            
                            <!-- Basic Pagination -->
                            <nav aria-label="Page navigation" class="container-p-y">
                             <ul class="pagination justify-content-center">
                             
                               <!-- 이전 버튼 -->
                               <c:if test="${pg.startPage > pg.pageBlock }">
                                 <li class="page-item prev">
                                   <a class="page-link" href="prodSelect?currentPage=${pg.startPage-pg.pageBlock}"><i class="tf-icon bx bx-chevrons-left"></i></a>
                                 </li>
                               </c:if>
                               
                               <!-- 넘버 -->
                               <c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
                                   <li class="page-item">
                                     <a class="page-link" href="prodSelect?currentPage=${i}">
                                     ${i}</a>
                                   </li>
                               </c:forEach>
                               
                               <!-- 다음 버튼 -->
                               <c:if test="${pg.endPage < pg.totalPage }">
                                 <li class="page-item next">
                                   <a class="page-link" href="prodSelect?currentPage=${pg.startPage-pg.pageBlock}"><i class="tf-icon bx bx-chevrons-right"></i></a>
                                 </li>
                               </c:if>
                               
                             </ul>
                            </nav>
                            <!--/ Basic Pagination -->
							<!-- /Account -->
							
					</div>
					<!-- / Content -->

					<div class="content-backdrop fade"></div>
				</div>
				<!-- Content wrapper -->
			</div>
			<!-- / Layout page -->
		</div>

		<!-- Overlay -->
		<div class="layout-overlay layout-menu-toggle"></div>
	</div>
	<!-- / Layout wrapper -->

	<jsp:include page="A_footer.jsp"/>
	<!-- wrap -->
	<script>
	
	</script>
</body>
</html>