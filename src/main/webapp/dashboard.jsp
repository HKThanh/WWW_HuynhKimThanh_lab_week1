<%@ page import="entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Role" %>
<%@ page import="dto.AccountDTO" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Thanh
  Date: 9/25/2024
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DashBoard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>
        <%
            Account account = (Account) request.getAttribute("account");
            String hello = account.getFullName();
        %>
    </h1>
    <h1>WELCOME TO DASHBOARD <%=hello%></h1>

    <h1>LIST OF ACCOUNTS</h1>
    <table>
        <thead>
            <tr>
                <th>Account ID</th>
                <th>Full Name</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<AccountDTO> accounts = (List<AccountDTO>) request.getAttribute("accountAndRoles");
                for (AccountDTO accountDTO : accounts) {
            %>
            <tr>
                <td><%=accountDTO.getAccountId()%></td>
                <td><%=accountDTO.getFullName()%></td>
                <td><%=accountDTO.getPassword()%></td>
                <td><%=accountDTO.getRole()%></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <form action="controller-servlet" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="hidden" name="acc" value="<%=account.getAccountId()%>">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
