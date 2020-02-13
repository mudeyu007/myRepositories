<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/Students/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	var pageSize = "${pageInfo.pageSize}";
	var pageNumber ="${pageInfo.pageNumber}";
	var sname = "${pageInfo.sname}";
	var tname = "${pageInfo.tname}";
	var total = ${pageInfo.total};
	//循环遍历单选框，
	//i:循环脚标；n:迭代变量；而且n为dom对象
	//dom对象转jquery对象：$(dom对象)
	//jquery对象转dom对象：jquery对象[0] / jquery对象.get(0)
	$.each($(":radio"),function(i,n){
		if($(n).val()==pageSize){
			$(n).attr("checked","checked");
		}
	});
	
	//对输入框设置值
	$(":text[name='sname']").val(sname);
	$(":text[name='tname']").val(tname);
	
	//查询按钮点击事件
	$("button").click(function(){
		location.href="showpage?pageSize="+pageSize+"&pageNumber"+pageNumber+"&sname="+$(":text[name='sname']").val()+"&tname="+$(":text[name='tname']").val();
	});
	
	//单选框点击事件
	$(":radio").click(function(){
		pageSize=$(this).val();
		location.href="showpage?pageSize="+pageSize+"&pageNumber"+pageNumber+"&sname="+$(":text[name='sname']").val()+"&tname="+$(":text[name='tname']").val();
	});
	
	//上一页点击事件
	$(".page_a:eq(0)").click(function(){
		pageNumber= parseInt(pageNumber)-1;
		if(pageNumber>=1){
			location.href="showpage?pageSize="+pageSize+"&pageNumber"+pageNumber+"&sname="+$(":text[name='sname']").val()+"&tname="+$(":text[name='tname']").val();			
		}else{
			pageNumber = 1;
		}
		return false;
	});
	
	//下一页点击事件
	$(".page_a:eq(1)").click(function(){
		pageNumber=parseInt(pageNumber)+1;
		if(pageNumber<=total){
			location.href="showpage?pageSize="+pageSize+"&pageNumber"+pageNumber+"&sname="+$(":text[name='sname']").val()+"&tname="+$(":text[name='tname']").val();			
		}else{
			pageNumber = total;
		}
		return false;
	});
});
</script>
</head>
<body>
<input type="radio" value="2" name="pageSize" />2
<input type="radio" value="3" name="pageSize" />3
<input type="radio" value="4" name="pageSize" />4<br/>
学生姓名：<input type="text" name="sname" />老师姓名：<input type="text" name="tname"/><button>查询</button>
<table border="1">
	<tr>
		<th>学生编号</th>
		<th>学生姓名</th>
		<th>学生年龄</th>
		<th>老师姓名</th>
	</tr>
	<c:forEach items="${pageInfo.list }" var="stu">
		<tr>
			<th>${stu.id }</th>
			<th>${stu.sname }</th>
			<th>${stu.age }</th>
			<th>${stu.teacher.tname}</th>
		</tr>
	</c:forEach>
</table>
<a href="" class="page_a">上一页</a><a href="" class="class_a">下一页</a>
</body>
</html>