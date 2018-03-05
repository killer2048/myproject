<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RegServlet" method="post">
	<div>用户名 ： <br /> <input type="text" name="username" id="username" onblur=""/></div>
    <div>密码：    <br /> <input type="text" name="password" id="password"/></div>
    <br>
    <div><input type="submit" name="submit"></input></div>
	
	</form>
</body>
</html>