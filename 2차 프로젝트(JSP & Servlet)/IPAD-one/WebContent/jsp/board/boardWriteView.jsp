<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/boardWriteView.css">
<!-- <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css"> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>GROWTH</title>
</head>




<body>

	  <%@ include file="/jsp/common/header.jsp"%>
	
	<div class="container" id="qImgBox">
		<img src="${pageContext.request.contextPath}/img/Q&A.jpg" alt="">
	</div>

	<div class="container">
		<div class="row" id="qWriteView">
			<table>
				<div class="col-12">
					<tr id="qWriteViewTable">
						<td>${bDto.subject }</td>
					</tr>
					<tr id="qWriteViewTable">
						<td style="width: 10%;">${bDto.writer }</td>
						<td>${bDto.reg_date }</td>
						<td style="text-align: right;">${bDto.readcount }</td>
					</tr>
					<tr id="qWriteViewTable" style="height: 400px;">
						<td>${bDto.content }</td>
					</tr>

				</div>
			</table>
		</div>
	</div>

	<%--   <div class="container">

        <div class="col-12" id="qWriteViewButtonBox">
            <div class="qWriteViewButton">
                <a ><button onclick="location.href='${pageContext.request.contextPath}/board/boardDelete.do?num=${bDto.num}'">삭제</button></a>
                <a><button onclick="location.href='${pageContext.request.contextPath}/board/boardWriteEidt.do?num=${bDto.num}'" >수정</button></a>
                <a><button onclick="location.href='${pageContext.request.contextPath}/board/boardList.do'">목록</button></a>
            </div>       
        </div> --%>




	
	<div class="container">
		<c:set var="writeUser" value="${sessionScope.writeUser}" />
		<c:choose>
			<c:when test="${isWriter}">


				<!-- 글 작성자인 경우 -->
				<div class="col-12" id="qWriteViewButtonBox">
					<div class="qWriteViewButton">
						<form id="deleteForm"
							action="${pageContext.request.contextPath}/board/boardWritedelet.do"
							method="post">
							<!-- 기타 필요한 hidden input 등을 추가 -->
							<input type="hidden" name="num" value="${bDto.num}"> 
							<input type="hidden" name="password">
						</form>
						<a><button id="delete" onclick="showDeleteModal()">삭제</button></a>
						<a><button
								onclick="location.href='${pageContext.request.contextPath}/board/boardWritedelet.do?num=${bDto.num}'">수정</button></a>
						<a><button
								onclick="location.href='${pageContext.request.contextPath}/board/boardList.do'">목록</button></a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<!-- 글 작성자가 아닌 경우 -->
				<div class="col-12" id="qWriteViewButtonBox">
					<div class="qWriteViewButton">
						<!-- 현재 로그인한 사용자가 글 작성자인 경우에만 수정, 삭제 버튼을 표시 -->
						<c:if test="${isWriter != null && !isWriter}">
							<a><button
									onclick="location.href='${pageContext.request.contextPath}/board/boardList.do'">목록</button></a>
						</c:if>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>






	<div class="container">
		<h6 style="margin-top: 5%;">N개의 댓글</h6>
		<div class="row">
			<table>
				<div class="col-12">
					<tr class="qWriteViewcomment">
						<td><input onclick="location.href='boardReWriter.do'"
							type="text" placeholder="내용을 입력해주세요">
							<button>등록</button></td>

					</tr>
				</div>
			</table>
		</div>
	</div>

	<div class="qWBottom">
		<button type="button" class="btn btn-primary">확인</button>
		<button type="button" class="btn btn-primary">취소</button>
	</div>

	<footer>
		<div class="container">
			<div class="row" style="width: 100%; margin-top: 40%;">
				<div class="col-lg-3" style="margin-left: 5%;">
					<ul id="logo">
						<li>GROWTH</li>
					</ul>

				</div>


				<div class="col-lg-9" style="margin-left: 5%;">
					<ul id="text">
						<li>회사소개</li>
						<li>이용약관</li>
						<li>개인정보취급방침</li>
					</ul>
					<br>

					<ul id="text1">
						<li>고객센터 : 1588-1588</li>
						<li>주소 : 서울시 강남구 테헤란로 111 강남빌딩</li>
					</ul>
				</div>
			</div>
	</footer>

	<script src="${pageContext.request.contextPath}/js/board.js"></script>

</body>


</html>
