<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

{
"boardlist":[
	<c:forEach var="board" items="${blist}">
		{
			"bno": ${board.bno},
			"title": ${board.title},
			"content": ${board.content}
		}	
	</c:forEach>
]}