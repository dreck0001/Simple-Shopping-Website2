<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hw4_6_LogingErrorHTML</title>
</head>
<body>
	<h4 style="color:red">Please Try Logging In Again</h4>
	<form action="login" method="POST">
			<input type="hidden" name="operation" value="login">
            <input type="text" name="username" placeholder="Username"><br>
            <input type="password" name="password" placeholder="Password"><br><br>
            <input type="submit" value="Login">
    </form>
</body>
</html>