<%@ page import="entities.Account" %><%--
  Created by IntelliJ IDEA.
  User: Thanh
  Date: 9/25/2024
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <%
        String accountAndItsRole = (String) request.getAttribute("accountAndItsRole");
        Account account = (Account) request.getAttribute("account");
    %>
    <h1>Welcome, <%=accountAndItsRole%></h1>
    <form action="controller-servlet" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="acc" value="<%=account.getAccountId()%>">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
