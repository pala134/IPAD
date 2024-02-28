<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
function idok(id) {
	opener.frm.id.value = document.frm.id.value;
	opener.frm.reid.value = document.frm.id.value;  //중복 검사를 한 아이디인지 아닌지를 저장
	self.close();
}
</script>
<title>회원관리</title>

</head>
<body>

	<h3>아이디 중복확인</h3>
	<form action="${pageContext.request.contextPath}/member/idCheck.do" method="get" name="frm">
		아이디 <input type=text name="id" value="${id}"> 
			 <input type=submit value="중복 체크" onclick="return joinCheck()"> <br>
		<c:if test="${result == 1}">
			<script type="text/javascript">
				opener.document.frm.id.value = "";
			</script>
			<br> ${id}는 이미 사용 중인 아이디입니다.
		</c:if>
		<c:if test="${result==-1}">
		<br> ${id}는 사용 가능한 아이디입니다.
		<input type="button" value="사용" class="cancel" onclick="idok('${id}')">
		</c:if>
	</form>

	<script src="${pageContext.request.contextPath}/js/member.js"></script>

</body>
</html>