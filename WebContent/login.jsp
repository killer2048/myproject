<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    	<p>${param.fail=="true"?"用户名或密码输入错误":""}</p>
		<form action="LoginServlet" method="post">
    	<div>用户名 ： <br /> <input type="text" name="username" id="username" /></div>
    	<div>密码：    <br /> <input type="text" name="password" id="password"/></div>
    	<br>
    	<div><input type="submit" name="submit"></input></div>
 		</form>
    </body>
</html>