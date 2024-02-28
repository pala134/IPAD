<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/boardList.css">

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>인구분석-유동인구</title>
</head>



<body>

	<%@ include file="/jsp/common/header.jsp"%>


	<div class="container" id="qImgBox">
		<img src="${pageContext.request.contextPath}/img/Q&A.jpg" alt="">
	</div>


	<div class="container">

		<div class="table-responsive">
			<div class="search">

				<input type="text" name="" id="">
				<svg xmlns="http://www.w3.org/2000/svg" width="22" height="20"
					fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
          <path
						d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
        </svg>

			</div>

			<table class="table table-hover ">
				<thead>
					<tr class="text-center">
						<th scope="col" style="width: 10%;">번호</th>
						<th scope="col" style="width: 55%;">제목</th>
						<th scope="col" style="width: 15%;">작성자</th>
						<th scope="col" style="width: 15%;">작성일</th>
						<th scope="col" style="width: 20%;">조회수</th>
					</tr>
				</thead>

				<c:set var="number" value="${number}"/>
				<c:forEach var="bDto" items="${list}">
				
					<tr class="text-center">
						<td scope="col" style="width: 10%;">${number}</td>
						<td scope="col" style="width: 55%;">
						<c:if test="${bDto.re_step > 1 }">
						<c:forEach var="j" begin="1" end="${(bDto.re_step-1)*5}"></c:forEach>
						</c:if>
						<a href="${pageContext.request.contextPath}/board/boardWriteViewCheck.do?num=${bDto.num}" > ${bDto.subject} </a>
						
						</td>
						<td scope="col" style="width: 15%;">${bDto.writer}</td>
						<td scope="col" style="width: 15%;">${bDto.reg_date}</td>
						<td scope="col" style="width: 20%;">${bDto.readcount}</td>
						
					</tr>
				<c:set var="number" value="${number-1}" />
				</c:forEach>

			</table>
			<a href="${pageContext.request.contextPath}/board/boardListWriteClickAction.do"><button
					type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#exampleModal">글쓰기</button></a>

		</div>
	</div>
	
	<p>
	<!-- 페이지 카운터링 소스 작성 !-->
	<c:if test="${count > 0}">
	<c:set var="pageCount" value="${count / pageSize + (count%pageSize == 0 ? 0 : 1)}" />
	<c:set var="startPage" value="${1}" />
	<c:if test="${currentPage %10 != 0}">
		<!-- 결과를 정수형으로 리턴 받아야 함 -->
		<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true" />
		<c:set var="startPage" value="${result*10+1}"/>
	</c:if>
	
	<c:if test="${currentPage %10 == 0}">
		<!-- 결과를 정수형으로 리턴 받아야 함 -->
		<fmt:parseNumber var="result" value="${currentPage/10}" integerOnly="true" />
		<c:set var="startPage" value="${(result-1)*10+1}"/>
	</c:if>
	
	<!-- 화면에 보여질 페이지 숫자 처리  -->
	<c:set var="pageBlock" value="${10}" />
	<c:set var="endPage" value="${startPage+pageBlock-1}" />
	
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value= "${pageCount}"/>
	</c:if>
	
	<!-- 이전 링크를 걸지 파악 -->
	<center>
	<c:if test="${startPage > 10}"><
		<a href="${pageContext.request.contextPath}/board/boardList.do?pageNum=${startPage-10}">[이전]</a>
	</c:if>
	
	
	
	<!--페이징처리  -->
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="${pageContext.request.contextPath}/board/boardList.do?pageNum=${i}">[${i}]</a>
	
	</c:forEach>
	
	<!-- 다음 !-->
	<c:if test="${endPage<pageCount}">
		<a href="${pageContext.request.contextPath}/board/boardList.do?pageNum=${startPage-10}">[다음]</a>
	</c:if>
	</center>
	</c:if>
	



	<%@ include file="/jsp/common/footer.jsp"%>

</body>

</html>