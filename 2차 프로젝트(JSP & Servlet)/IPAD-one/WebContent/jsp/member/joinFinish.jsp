<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/joinFinish.css">

<!-- 스크립트 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<script src="js/main.js"></script>
<title>회원가입 화면</title>


</head>

<body>
	<%@ include file="/jsp/common/header.jsp"%>
	<!-- 회원가입 폼 상단 텍스트 -->
	<div class="container">
		<div id="info">
			<div class="row" id="infoImgText">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					id="infoImgText">
					<h4 id="info1">일반 회원가입</h4>
					<h8 id="info2">혼설팅 가입을 환영합니다.</h8>

				</div>
				<%-- <img src="${pageContext.request.contextPath}/img/joinFormImg.png" alt=""> --%>

			</div>
		</div>
			
			
		 <div class="FinishText">
		 		<h1>${name }</h1>
		 		<h1>${messge }</h1>
		 			
		 			<a class="btn btn-primary" href="${pageContext.request.contextPath}/member/loginPage.do" >로그인하기</a>
         </div>
         


	</div>
	<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>