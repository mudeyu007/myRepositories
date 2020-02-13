<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="transfer" method="post">
转账账号：<input type="text" name="accOutaccNo" value=""/><br/>
转账密码：<input type="password" name="accOutpassword" value="" /><br/>
转账金额：<input type="text" name="balance" value=""/><br/>
转入账户：<input type="text" name="accInaccNo" value=""/><br/>
转入户名：<input type="text" name="accInname" value=""/><br/>
<input type="submit" value="转账" /> 

</form>
</body>
</html>