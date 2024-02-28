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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/joinForm.css">

<!-- 스크립트 -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">


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
				<%-- <img src="${pageContext.request.contextPath}/img/joinFormImg.png"
					alt=""> --%>

			</div>
		</div>
		<!-- 회원가입 폼 -->
		<form action="${pageContext.request.contextPath}/member/joinFormCheck.do" method="post" id="joinForm" name="frm">
			<div class="container-fluid" id="infoText">
				<div id="loginText">
					<p>회원 정보 입력</p>
				</div>

				<div class="row" id="membership">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" id="loginInfo">
						<table>
							<tr>
								<td>아이디</td>
							
							</tr>
							<tr>
								<td colspan="2"><input type="text" id="id" name="id" value="${id}" placeholder="아이디를 써주세요">
									<input type="hidden" name="reid" size="20">
									<button type="button" id="re_check" value="중복 체크" onclick="idCheck()">중복체크</button>
								</td>
							</tr>
							<tr class="tableText">
								<td>※ 4글자 이상 입력해주세요..</td>
							</tr>
							<tr>
								<td>비밀번호</td>
							
							</tr>
							<tr>
								<td colspan="2"><input type="password" name=pass1
									placeholder="비밀번호를 입력해주세요."></td>
							</tr>
							<tr class="tableText">
								<td>※ 비밀번호 확인을 위해 두 번 작성됩니다.</td>
							</tr>
							<tr>
								<td>비밀번호</td>
							
							</tr>
							<tr>
								<td colspan="2"><input type="password" name="pass2"
									placeholder="비밀번호를 다시 한 번 입력 해주세요."></td>
							</tr>
							<tr class="tableText">
								<td>　</td>
							</tr>
							<tr>
								<td>이메일</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="email" name="email"
									placeholder="이메일 주소를 입력해 주세요."></td>
							</tr>

						</table>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" id="loginInfo">
						<table>

							<tr>
								<td>이름</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="text" name="name"
									placeholder="이름을 입력해 주세요."></td>
							</tr>
							<tr class="tableText">
								<td>※ 한글만 입력해 주세요.</td>
							</tr>
							<tr>
								<td>휴대폰 번호</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="text" name="tel"
									placeholder="휴대폰번호를 입력해 주세요."></td>
							</tr>
							<tr class="tableText">
								<td>" - " 제외 후 입력해 주세요.</td>
							</tr>
							<tr>
								<td>생년월일</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="date" name="year"></td>
							</tr>
							<tr>
								<td><br></td>
							</tr>
							<tr>
								<td>희망지역</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><select name="map">
										<option label="선택해 주세요"></option>
										<option value="송파구 위례동">서울특별시 송파구 위례동</option>
										<option value="성남구 위례동">성남시 수정구 위례동</option>
										<option value="분당구 위례동">하남시 위례동</option>
								</select></td>
							</tr>
							<tr>
								<td style="font-size: 12px; color: gray;"></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="infoCheck">
			<input id="infoCheckInput" type="submit" class="btn btn-primary"
			value="회원가입" onclick="return joinCheck()"> <input id="infoCheckInput" type="reset"
			class="btn btn-primary" value="취소">
	</div>
        </form>
	</div>
	



	<%@ include file="/jsp/common/footer.jsp"%>


	<script src="${pageContext.request.contextPath}/js/member.js"></script>






</body>

</html>