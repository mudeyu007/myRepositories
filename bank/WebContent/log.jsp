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
			<td>编号</td>
			<td>转账账户</td>
			<td>收款账户</td>
			<td>转账金额</td>
		</tr>
		<c:forEach items="${pageInfo.list }" var="log">
		<tr>
			<th>${log.id }</th>
			<th>${log.accOut }</th>
			<th>${log.accIn }</th>
			<th>${log.money }</th>
		</tr>
		</c:forEach>
	</table>
	<a href="show?pageNumber=${pageInfo.pageNumber-1 }&pageSize=${pageInfo.pageSize}" <c:if test="${pageInfo.pageNumber<=1 }"> onclick="javascript:return false;" </c:if> >上一页</a>
	<a href="show?pageNumber=${pageInfo.pageNumber+1 }&pageSize=${pageInfo.pageSize}" <c:if test="${pageInfo.pageNumber>=pageInfo.total }">onclick="javascript:return false;"</c:if> >下一页</a>
</body>
</html>