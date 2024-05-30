<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
<!-- 표준 action은 각각 컴파일해서 합친다. -->
<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>
<!-- include 지시자는 합쳐서 컴파일한다. -->
<%@ include file="../common/header.jsp" %>
<hr>
<button id="btnRetrieve">조회(ajax)</button>
<input type="number" id="deptid" value="60">
<button id="btnDetail">상세보기(ajax)</button>
<hr>
<a href="${path}/dept/deptInsert.do">신규 부서 등록</a>
<h1>부서목록</h1>
<p>${deptResult}</p>
<div id="here">
	<table border="1">
		<thead>
			<tr>
				<th>부서번호</th>
				<th>부서이름</th>
				<th>매니저</th>
				<th>지역코드</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dept" items="${deptlist}">
				<tr>
					<td>
						<a href="${path}/dept/deptUpdate.do?deptid=${dept.department_id}">${dept.department_id}</a>
					</td>
					<td>${dept.department_name}</td>
					<td>${dept.manager_id}</td>
					<td>${dept.location_id}</td>
					<td><button onclick="location.href='${path}/dept/deptDelete.do?deptid=${dept.department_id}'">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	$(function(){
		$("#btnRetrieve").on("click", f_retrieve);
		$("#btnDetail").on("click", f_detail);
	});
	
	function f_retrieve(){
		$.ajax({
			url:"${path}/dept/api/deptAll",
			type:"get",
			success:function(responseData){
				var output = "<ul>";
				$.each(responseData, function(index, item){
					output += "<li>" + item.department_name + "</li>";
				});
				output += "</ul>";
				$("#here").html(output);
			},
			error:function(){
				
			}
		});
	}
	
	function f_detail(){
		var deptid = $("#deptid").val();
		$.ajax({
			url:"${path}/dept/api/detail/"+deptid,
			type:"get",
			success:function(responseData){
				var output = "";
				output += "<p>부서아이디 : " + responseData.department_id + "</p>";
				output += "<p>부서이름 : " + responseData.department_name + "</p>";
				output += "<p>매니저아이디 : " + responseData.manager_id + "</p>";
				output += "<p>지역코드 : " + responseData.location_id + "</p>";
				$("#here").html(output);
			},
			error:function(){
				
			}
		});
	}
</script>
<script>
	setTimeout(() => {
		var message = "${deptResult}";
		if(message!="") {
			alert(message);
		}
	}, 1000);

	/*  */
</script>
</body>
</html>