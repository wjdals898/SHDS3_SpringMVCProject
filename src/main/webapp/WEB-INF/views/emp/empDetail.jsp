<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="/webshop21/static/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<!-- include 지시자는 파일을 합쳐서 컴파일한다. -->
<div class="container mt-3">
	<h1>직원상세보기(spring)</h1>
	<form action="${path}/emp/empDetail.do" method="post">
    <div class="mb-3 mt-3">
      <label for="employee_id">직원번호:</label>
      <input type="number" class="form-control" id="employee_id" placeholder="Enter employee_id" name="employee_id" value="${emp.employee_id}">
    </div>
    <div class="mb-3 mt-3">
      <label for="first_name">이름:</label>
      <input type="text" class="form-control" id="first_name" placeholder="Enter first_name" name="first_name" value="${emp.first_name}">
    </div>
    <div class="mb-3 mt-3">
      <label for="last_name">last_name:</label>
      <input type="text" class="form-control" id="last_name" placeholder="Enter last_name" name="last_name" value="${emp.last_name}">
    </div>
    <div class="mb-3 mt-3">
      <label for="email">email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${emp.email}">
    </div>
    <div class="mb-3 mt-3">
      <label for="phone_number">phone_number:</label>
      <input type="text" class="form-control" id="phone_number" placeholder="Enter phone_number" name="phone_number" value="${emp.phone_number}">
    </div>
    <div class="mb-3 mt-3">
      <label for="salary">salary:</label>
      <input type="number" class="form-control" id="salary" placeholder="Enter salary" name="salary" value="${emp.salary}">
    </div>
    <div class="mb-3 mt-3">
      <label for="hire_date">hire_date:</label>
      <input type="date" class="form-control" id="hire_date" placeholder="Enter hire_date" name="hire_date"  value="${emp.hire_date}">
    </div>
    <%-- ScriptLet문법보다는, EL(반복문없음) : ${}, JSTL문법 : <c:forEach>을 사용하는 것이 좋다. --%>
    <div class="mb-3 mt-3">
      <label for="job_id">job_id:</label>
      <select name="job_id">
      	<c:forEach items="${joblist}" var="job">
      		<option value="${job.job_id}" ${emp.job_id==job.job_id?"selected":""}>[${job.job_id}] ${job.job_title}</option>
      	</c:forEach>  
      </select>
    </div>
    <%-- --%>
    <div class="mb-3 mt-3">
      <label for="department_id">department_id:</label>
      <select name="department_id">
      	<option value="0">부서없음</option>
      	<c:forEach items="${deptlist}" var="dept">
      		<option value="${dept.department_id}" ${emp.department_id==dept.department_id?"selected":"" }>${dept.department_name}</option>
      	</c:forEach>
      </select>
    </div>
    <div class="mb-3 mt-3">
      <label for="manager_id">manager_id:</label>
      <select name="manager_id">
      	<option value="0">매니저없음</option>
        <c:forEach items="${mgrlist}" var="manager">
        	<option value="${manager.employee_id}" ${emp.manager_id==manager.employee_id?"selected":"" }>${manager.fullname}</option>
        </c:forEach>
      	</select>
    </div>
    <div class="mb-3 mt-3">
      <label for="commission_pct">"commission_pct":</label>
      <input type="text" class="form-control" id="commission_pct" placeholder="Enter commission_pct" name="commission_pct" value="${emp.commission_pct}">
    </div>
    <input type="submit" class="btn btn-primary" value="수정하기">
  </form>
</div>
</body>
</html>