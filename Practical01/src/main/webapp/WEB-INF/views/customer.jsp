<%@ page import="com.maven.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");

%>
<h2>User Info</h2>
<ul>
    <%if(user!=null){%>
    <li>Name: <%= user.getUserName()%></li>
    <li>Contact: <%= user.getContact()%></li>
    <li>Email: <%= user.getEmail()%></li>
    <%}else{
        response.sendRedirect("login");
    }%>
</ul>
<form method="post" action="logout">
    <button type="submit">Logout</button>
</form>
</body>
</html>
