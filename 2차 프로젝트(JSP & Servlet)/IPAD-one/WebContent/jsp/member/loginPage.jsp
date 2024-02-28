<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/mainMap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginPage.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap">

	<!-- 스크립트 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/member.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <title>로그인페이지</title>
</head>
<body>
     <%@ include file="/jsp/common/header.jsp"%>

    <!-- 로그인 페이지 -->
    <div class="container">
        <div class="col-12">
        <form action="${pageContext.request.contextPath}/member/loginPageCheck.do" method="post" name="frm" onsubmit="return loginCheck()">
            <div class="loginPage">
               <h3>고객회원 로그인</h3>
            </div>
            <div class="loginWrite">
                <p><input type="text" name="id" placeholder="아이디를 입력해 주세요"></p>
                <p><input type="password" name="pass1" placeholder="비밀번호를 입력해 주세요"></p>
            </div>
            <div class="loginClickTable">
                <input type="submit" class="btn btn-primary"  id="loginClick"  for="btn-check" value="로그인"></input>
            </div>
		</form>           
        </div>
        <div class="loginInfoSerch">
                <a href="${pageContext.request.contextPath}/member/joinForm.do">회원가입</a>
                <td> |  </td>
                <a href="#">아이디찾기</a>
                <td> | </td>
                <a href="#">비밀번호찾기</a>
        </div>
    </div>
   
    
   
    
</body>
</html>