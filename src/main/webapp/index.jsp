<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form  action="hello-servlet" method="get">
    <label>Username:</label>
    <input type="text" name="un"><br>
    <label>Password:</label>
    <input type="password" name="pw"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>