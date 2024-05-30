<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
setTimeout(() => {
	var message = "${resultMessage}";
	if(message!="") alert(message);
}, 500);

</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%
    request.setCharacterEncoding("utf-8");
%>


<button id="btnJSON">JSON보내기</button>
<button id="btnJSON2">JSON받기</button>
<div id="here">여기</div>

<a href="${path}/board/boardInsert.do">게시글등록</a>
<h1>Board목록</h1>
<form action="${path}/board/selectDelete.do">
<table border="1">
	<tr>
		<th>선택</th>
		<th>bno</th>
		<th>title</th>
		<th>content</th>
		<th>writer</th>
		<th>pic</th>
		<th>작성일</th>
		<th></th>
	</tr>
	<c:forEach items="${blist}" var="board">
		<tr>
			<td><input type="checkbox" value="${board.bno}" name="checkBno"></td>
			<td>
				<a href="${path}/board/boardDetail.do?bno=${board.bno}">${board.bno}</a>
			</td>
			<td>${board.title}</td>
			<td>${board.content}</td>
			<td>${board.writer}</td>
			<td>
				<img alt="${board.title}" src="${path}/resources/uploads/${board.pic}" width="80">
				<%-- <a href="/download.do?filename=${board.pic}">내려받기</a> --%>
			</td>
			<td>${board.create_date}</td>
			<td>
				<input type="button"
					value="삭제하기"
					onclick="location.href='${path}/board/boardDelete.do?bno=${board.bno}'">
			</td>
		</tr>
	</c:forEach>
</table>
<input type = "submit" value="선택삭제"> 
</form>
</body>
</html>
<!-- 
	form tag내의 <button>은 submit로 수행한다.
 -->