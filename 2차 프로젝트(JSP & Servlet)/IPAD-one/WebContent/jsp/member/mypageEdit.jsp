<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/mainMap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">

<!-- 스크립트 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/member.js"></script>
<script>
	
</script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<title>회원수정</title>
</head>

<body>

	<%@ include file="/jsp/common/header.jsp"%>

	<!-- 마이페이지 상단 텍스트 -->
	<div class="container">
		<div id="MypageTitle">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<h4 id="info1">회원 수정</h4>
					<h8 id="info2">정보 보호를 위해 비밀번호를 입력해주세요.</h8>
				</div>
			</div>
		</div>

		<!-- 마이페이지 회원 수정 메뉴 버튼 -->
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

		<div class="container">
			<div class="row">
				<form
					action="${pageContext.request.contextPath}/member/MemberEditCheck.do">
					<div class="col-12" id="MypageForm">
						<h1 id="editMessage">${editMessage }</h1>
						<%
							session.removeAttribute("editMessage");
						%>


						<input type="password" name="pass1" placeholder="패스워드를 입력해주세요.">
						<p>
							<button type="submit" class="btn btn-primary">확인</button>
						</p>
					</div>
				</form>
			</div>
		</div>


		<!-- 마이페이지 메뉴선택  -->

		<%@ include file="/jsp/common/footer.jsp"%>
		<script src="${pageContext.request.contextPath}/js/member.js"></script>
</body>

</html>