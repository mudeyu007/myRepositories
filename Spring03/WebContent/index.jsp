<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" align="center">
<tr>
	<th>机场编号</th>
	<th>机场名称</th>
	<th>城市</th>
</tr>
<c:forEach items="${list }" var="line">
	<tr>
		<th>${line.id }</th>
		<th>${line.name }</th>
		<th>${line.city }</th>
	</tr>
</c:forEach>
</table>
</body>
</html>