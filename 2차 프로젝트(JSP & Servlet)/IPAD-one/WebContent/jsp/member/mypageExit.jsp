<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/memberExit.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css">

<!-- 스크립트 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">



<title>회원탈퇴</title>


</head>

<body>
	<c:if test="${not empty exitMessage1}">
		<script>
			alert('${exitMessage1}');
		</script>a
	</c:if>




	<%@ include file="/jsp/common/header.jsp"%>


	<!-- 마이페이지 상단 텍스트 -->
	<div class="container">
		<div id="MypageTitle">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h4 id="info1">회원탈퇴</h4>
					<h8 id="info2">약관내용을 확인해주세요.</h8>


				</div>
			</div>
		</div>

		<!-- 마이페이지 메뉴 버튼 -->
		<div class="container">
			<div class="row" id="MypageMenu">
				<div class="col-4 mypageButton" onclick="mypageClick()"
					data-href="${pageContext.request.contextPath}/member/mypageEdit.do">회원수정</div>
				<div class="col-4 mypageButton" onclick="mypageClick()"
					data-href="${pageContext.request.contextPath}/member/mypageExit.do">회원탈퇴</div>
				<div class="col-4 mypageButton" onclick="mypageClick()"
					data-href="${pageContext.request.contextPath}/member/">나의 활동
					내역</div>
			</div>
			<div class="MypageText">
				<h4>원하시는 페이지를 선택해 주세요.</h4>
				<h6>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 한번 더 확인합니다.</h6>
			</div>
		</div>

		<!-- 회원탈퇴 약관 문구 -->

		<div class="MypageExitText">
			<h6>탈퇴 후 삭제 내역</h6>
			<h10>· 개인 관련 정보들은 모두 삭제되며 복구되지 않습니다.</h10>
			<br>
			<h10>· 가입된 아이디로 이용하던 모든 서비스와 커뮤니티에서 자동으로 탈퇴되어 이용이 불가능합니다.</h10>
			<br>
			<h6>탈퇴 후 유지내역</h6>
			<h10>· 게시물 삭제 등을 원하는 경우 반드시 삭제 처리 후 탈퇴를 신청해 주시기 바랍니다.</h10>
		</div>

		<!-- 회원탈퇴 아이디 패스워드 폼  -->
		<div class="container">
			<div class="row">
				<form
					action="${pageContext.request.contextPath}/member/mypageExitCheck.do"
					method="post">
					<div class="MypageText">
						<h4>비밀번호를 입력해주세요.</h4>
						<h6>회원탈퇴를 위한 본인확인 절차입니다 비밀번호를 입력해 주세요.</h6>
						 	<h1 id="editMessage">${exitMessage }</h1>
						
						<%
							session.removeAttribute("exitMessage");
						
						%> 
					</div>
					<div class="col-12" id="MypageExitForm">
						<input type="password" name="pass1" placeholder="패스워드를 입력해주세요.">
						<p>
							<button class="btn btn-primary">확인</button>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>


	<!-- Footer  -->
	<%@ include file="/jsp/common/footer.jsp"%>
	<script src="${pageContext.request.contextPath}/js/member.js"></script>
</body>

</html>

