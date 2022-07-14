<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String context = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<title>비숲 | 상품 관리</title>
<script type="text/javascript" src="<%=context %>/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
	//var oEditors = [];
	$(function(){
	      /* nhn.husky.EZCreator.createInIFrame({
	          oAppRef: oEditors,
	          elPlaceHolder: "editorTxt", //textarea에서 지정한 id와 일치해야 합니다. 
	          //SmartEditor2Skin.html 파일이 존재하는 경로
	          sSkinURI: "smarteditor/SmartEditor2Skin.html",  
	          htParams : {
	              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	              bUseToolbar : true,             
	              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	              bUseVerticalResizer : true,     
	              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	              bUseModeChanger : true,         
	              fOnBeforeUnload : function(){
	                   
	              }
	          }, 
	          //	fOnAppLoad : function(){
	              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
	          //    oEditors.getById["editorTxt"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
	          },
	          fCreator: "createSEditor2"
	      }); */
	      
	      //저장버튼 클릭시 form 전송
	      $("#save").click(function(){
	    	  if (!frm.brand_name.value) {
					alert("브랜드명을 입력해주세요.");
					frm.brand_name.focus();
					return false;
	    	  }
	    	  if (!frm.prod_name.value) {
					alert("상품명을 입력해주세요.");
					frm.prod_name.focus();
					return false;
	    	  }
	    	  if (!frm.price.value) {
					alert("판매가를 입력해주세요.");
					frm.price.focus();
					return false;
	    	  }
	    	  if (!frm.stock.value) {
					alert("재고수량을 입력해주세요.");
					frm.stock.focus();
					return false;
	    	  }
	    	  if (!frm.th_imgJia.value) {
					alert("배너 이미지를 등록해주세요.");
					frm.th_imgJia.focus();
					return false;
	    	  }
	    	  if (!frm.main_img1Jia.value) {
					alert("대표 이미지를 등록해주세요.");
					frm.main_img1Jia.focus();
					return false;
	    	  }
	    	  if (!frm.main_img2Jia.value) {
					alert("대표 이미지를 등록해주세요.");
					frm.main_img2Jia.focus();
					return false;
	    	  }
	    	  if (!frm.prod_infoJia.value) {
					alert("상세 정보를 등록해주세요.");
					frm.prod_infoJia.focus();
					return false;
	    	  }
	    	  
	          //oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", []);
	          //$("#frm").submit();
	          return true;
	      });    
	});
	
	
	// 파일 미리보기
	function PreviewImage() {
        // 파일리더 생성 
        var preview = new FileReader();
        preview.onload = function (e) {
        // img id 값 
        document.getElementById("user_image").src = e.target.result;
	    };
	    // input id 값 
	    preview.readAsDataURL(document.getElementById("user_profile_img").files[0]);
	 };
	function PreviewImage2() {
        // 파일리더 생성 
        var preview = new FileReader();
        preview.onload = function (e) {
        // img id 값 
        document.getElementById("user_image2").src = e.target.result;
	    };
	    // input id 값 
	    preview.readAsDataURL(document.getElementById("user_profile_img2").files[0]);
	 };
	function PreviewImage3() {
        // 파일리더 생성 
        var preview = new FileReader();
        preview.onload = function (e) {
        // img id 값 
        document.getElementById("user_image3").src = e.target.result;
	    };
	    // input id 값 
	    preview.readAsDataURL(document.getElementById("user_profile_img3").files[0]);
	 };
	function PreviewImage4() {
        // 파일리더 생성 
        var preview = new FileReader();
        preview.onload = function (e) {
        // img id 값 
        document.getElementById("user_image4").src = e.target.result;
	    };
	    // input id 값 
	    preview.readAsDataURL(document.getElementById("user_profile_img4").files[0]);
	 };
	
	
</script>
	
	
<link href="css/admin_product.css" rel="stylesheet" type="text/css">
<style type="text/css">
    td:nth-child(1) {
    	width: 150px;
    }
    table tr td span {
    	font-size: 20px;
    }
    .img_text {
    	font-size: 12px; 
    	color: #3b82f6;
    }
    .btn_wrapper {
    	text-align: right;
    }
</style>
<jsp:include page="A_head.jsp"/>
</head>
<body>
	<!-- 상품 등록 실패시 얼럿 노출 -->
	<c:if test="${result == 0}">
		<script type="text/javascript">
			alert("${msg}");
		</script>
	</c:if>


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

						<form action="prodWrite" name="frm" method="post" enctype="multipart/form-data">
				            <div class="card mb-4">
				              <div class="table-responsive text-nowrap">
				                <table class="table table-borderless">
				                  <tr>
				                    <td>게시상태</td>
				                    <td>
				                    	<div class="form-check form-check-inline mt-3">
				                            <input type="radio" class="form-check-input" checked="checked"
				                              name="prod_poststs" id="prod_poststs1" value="1"/>
			                            	<label class="form-check-label" for="inlineRadio1">게시</label>
			                          </div>
			                          <div class="form-check form-check-inline">
				                            <input type="radio" class="form-check-input" 
				                              name="prod_poststs" id="prod_poststs2" value="0"/>
				                            <label class="form-check-label" for="inlineRadio2">비게시</label>
			                          </div>
				                    </td>
				                  </tr>
				                  <tr>
				                    <td>카테고리</td>
				                    <td>
				                      <select name="category" class="form-select mt-3">
				                          <option value="1" selected="selected">비건식품</option>
				                          <option value="2">생활용품</option>
				                          <option value="3">고체뷰티</option>
				                          <option value="4">스킨케어</option>
				                      </select>
				                    </td>
				                  </tr>
				                  <tr>
				                    <td>브랜드명</td>
				                    <td><input type="text" name="brand_name" class="form-control mt-3" required="required"/></td>
				                  </tr>
				                  <tr>
				                    <td>상품명</td>
				                    <td><input type="text" name="prod_name" class="form-control mt-3" required="required"/></td>
				                  </tr>
				                  <tr>
				                    <td>판매가</td>
				                    <td><input type="text" name="price" class="form-control mt-3" placeholder="원단위 입력" required="required"/></td>
				                  </tr>
				                  <tr>
				                    <td>재고수량</td>
				                    <td><input type="text" name="stock" class="form-control mt-3" placeholder="개수 입력" required="required"/></td>
				                  </tr>
				                  <tr>
				                    <td>배너 이미지</td>
				                    <td>
				                      <input  type="file" name="th_imgJia" class="form-control mt-3" id="user_profile_img" accept="image/*" onchange="PreviewImage();" required="required"/>
    								  <img id="user_image" src="#" alt="" width="200px" class="mt-1" >
				                    </td>
				                  </tr>
				                  
				                  <tr>
				                    <td>상품 이미지</td>
				                    <td>
				                      <span class="mt-3 img_text">* 2장의 이미지를 첨부해주세요</span>
				                      
				                      <input type="file" name="main_img1Jia" class="form-control mt-1" id="user_profile_img2" accept="image/*" onchange="PreviewImage2();" required="required"/>
				                      <img id="user_image2" src="#" alt="" width="200px" class="mt-1" >
				                      <input type="file" name="main_img2Jia" class="form-control mt-1" id="user_profile_img3" accept="image/*" onchange="PreviewImage3();" required="required"/>
				                      <img id="user_image3" src="#" alt="" width="200px" class="mt-1" >
				                    </td>
				                  </tr>
					              <tr>
				                    <td>상세정보</td>
				                    <td>
				                      <p class="mt-3">
				                      <input type="file" name="prod_infoJia" class="form-control mt-1" id="user_profile_img4" accept="image/*" onchange="PreviewImage4();" required="required"/>
				                      <img id="user_image4" src="#" alt="" height="300px" class="mt-1" >
				                     <!--  <textarea 
				                      		name="editorTxt" id="editorTxt" style="width: 100%;" 
				                      		rows="12" cols="137"></textarea> -->
				                      <p>
				                    </td>
				                  </tr>
				                </table>
				              </div>
				            </div>
				            <div class="mb-3 btn_wrapper">
				                <input type="button" value="목록" class="btn btn-outline-secondary" onclick="location.href='prodSelect'"></button>
				                <input type="submit" id="save" value="등록" class="btn btn-primary"></button>
				            </div>
				        </form>
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
</body>
</html>