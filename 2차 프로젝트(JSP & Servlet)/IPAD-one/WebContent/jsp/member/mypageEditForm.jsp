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

<title>회원가입 수정 폼</title>


</head>

<body>

	<%@ include file="/jsp/common/header.jsp"%>

	<!-- 회원가입 폼 상단 텍스트 -->
	<div class="container">
		<div id="info">
			<div class="row" id="infoImgText">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					id="infoImgText">
					<h4 id="info1">회원 수정</h4>
					<h6 id="info2">변경할 정보를 입력해주세요.</h6>


				</div>
				<img src="${pageContext.request.contextPath}/img/joinFormImg.png"
					alt="">

			</div>
		</div>
		<!-- 회원가입 폼 -->
		<form
			action="${pageContext.request.contextPath}/member/mypageEditFormCheck.do"
			method="post" onsubmit="editFormMesseage()">
			<div class="container-fluid" id="infoText">
				<div id="loginText">
					<p>회원 정보 입력</p>
				</div>

				<div class="row" id="membership">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6" id="loginInfo">
						<table>
							<tr>
								<td>아이디</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="text" name="id"
									readonly="readonly" value="${editSearch.id }"></td>
							</tr>
							<tr class="tableText">
								<td>※ 아이디 수정은 불가합니다.</td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="password" name=pass1
									placeholder=""></td>
							</tr>
							<tr class="tableText">
								<td>※ 비밀번호 변경 시 입력해 주세요.</td>
							</tr>
							<tr>
								<td>비밀번호 확인</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="password" name="pass2"
									placeholder=""></td>
							</tr>
							<tr class="tableText">
								<td>※ 비밀번호 변경 시 입력해 주세요.</td>
							</tr>
							<tr>
								<td>이메일</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="email" name="email"
									value="${editSearch.email }"></td>
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
									readonly="readonly" value="${editSearch.name }"></td>
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
									readonly="readonly" value="${editSearch.tel }"></td>
							</tr>
							<tr class="tableText">
								<td>" - " 제외 후 입력해 주세요.</td>
							</tr>
							<tr>
								<td>생년월일</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><input type="text" name="year"
									readonly="readonly" value="${editSearch.year }"></td>
							</tr>
							<tr>
								<td><br></td>
							</tr>
							<tr>
								<td>희망 지역</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2"><select name="map">
										<option>${editSearch.map }</option>
										<option value="송파구 위례동">송파구 위례동</option>
										<option value="성남구 위례동">성남구 위례동</option>
										<option value="분당구 위례동">분당구 위례동</option>
								</select></td>
							</tr>
							<tr>
								<td style="font-size: 12px; color: gray;"></td>
							</tr>
						</table>
					</div>


					<div class="infoCheck">
						<input id="infoCheckInput" type="submit" class="btn btn-primary"
							value="회원 수정"> <input id="infoCheckInput" type="reset"
							class="btn btn-primary" value="취소">
					</div>
				</div>
			</div>
		</form>
	</div>


	<%@ include file="/jsp/common/footer.jsp"%>
</body>

</html>