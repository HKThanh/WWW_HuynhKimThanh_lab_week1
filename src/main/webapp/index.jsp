<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Login" %>
</h1>
<form  action="controller-servlet" method="get">
    <input type="hidden" name="action" value="login">
    <label>Username:</label>
    <input type="text" name="un"><br>
    <label>Password:</label>
    <input type="password" name="pw"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>