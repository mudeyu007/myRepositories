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
<form action="showtake" method="post">
起飞机场：
<select name="takeoffid">
	<option value="0">请选择</option>
	<c:forEach items="${takeport }" var="port">
		<option value="${port.id }">${port.name}</option>
	</c:forEach>	
</select>
抵达机场：
<select name="landid">
	<option value="0">请选择</option>
	<c:forEach items="${landport }" var="port">
		<option value="${port.id}">${port.name }</option>
	</c:forEach>
</select>
<button type="submit">查询</button>
</form>
<table border="1">
	<tr>
		<th>飞机编号</th>
		<th>起飞机场</th>
		<th>起飞城市</th>
		<th>飞行时间</th>
		<th>机票价格</th>
		<th>抵达机场</th>
		<th>抵达城市</th>
	</tr>
	<c:forEach items="${list }" var="line">
		<tr>
			<th>${line.airno }</th>
			<th>${line.takeoffPort.name }</th>
			<th>${line.takeoffPort.city }</th>
			<th>${line.time }</th>
			<th>${line.price }</th>
			<th>${line.landPort.name }</th>
			<th>${line.landPort.city }</th>
		</tr>
	
	</c:forEach>

</table>
</body>
</html>