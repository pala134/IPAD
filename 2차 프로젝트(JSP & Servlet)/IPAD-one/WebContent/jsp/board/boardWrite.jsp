<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, minimum-scale=1">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardWrite.css">
    <!-- <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css"> -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>GROWTH</title>
</head>




<body>
	
	<%@ include file="/jsp/common/header.jsp"%>
	<form action="${pageContext.request.contextPath}/board/boardWriteCheck.do" method="post">
    <div class="container" id="qImgBox">
        <img src="${pageContext.request.contextPath}/img/Q&A.jpg"  alt="">
    </div>

    <div class="container">
        <div class="row" id="qWrite">
            <table>
                <div class="col-12">
                    <tr id="qWriteTable">
                        <td id="qWriteTableName">작성자</td>
                        <td><input type="text" name="writer" readonly="readonly" id="inputType" value="${loggedInUser.name }"></td>
                    </tr>
                    <tr id="qWriteTable">
                        <td id="qWriteTableName">제목</td>
                        <td><input type="text" name="subject" id="inputType"></td>
                    </tr>
                    <tr id="qWriteTable">
                        <td id="qWriteTableName">이메일</td>
                        <td><input type="text" name="email" id="inputType" value="" ></td>
                    </tr>
                     <tr id="qWriteTable">
                        <td id="qWriteTableName">비빌번호</td>
                        <td><input type="password" name="password" id="inputType"></td>
                    </tr>
                    <tr id="qWriteTable">
                        <td id="qWriteTableName">글내용</td>
                        <td><textarea placeholder="내용을 입력해주세요" name="content"></textarea></td>
                    </tr>
                </div>
            </table>
        </div>
    </div>


    <div class="container">
        <div class="row" id="qWFile" style="">
            <table>
                <div class="col-12">
                    <tr style=" height: 100px;">
                        <td id="qWFileUplode">첨부파일</td>
                        <td>
                            <input type="file" name="img" id="inputFile" width="">
                        </td>
                    </tr>
                </div>
            </table>
        </div>
    </div>

    <div class="qWBottom">
        <button type="submit" class="btn btn-primary">확인</button>
        <button type="reset" class="btn btn-primary">취소</button>
    </div>
    </form>

  
	 <%@ include file="/jsp/common/footer.jsp"%>
</body>


</html>