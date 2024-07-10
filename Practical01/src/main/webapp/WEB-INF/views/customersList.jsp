<%@ page import="com.maven.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maven.entities.Address" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="allCss.jsp"%>
<html>
<head>
    <title>Customers Data</title>
</head>
<style>
    .profile-image {
        width: 100px; /* Set desired width */
        height: auto; /* Maintain aspect ratio */
    }
</style>
<body>
<%
    List<User> userList = (List<User>) session.getAttribute("user");
    if (userList==null){
        System.out.println("User Not Found");
    }
    else {
%>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Image</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Contact</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col">Country</th>
            <th scope="col">PinCode</th>
            <th scope="col">Delete</th>
            <th scope="col">Update</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (User user : userList){
        %>
        <tr>
            <form method="post" action="editUser">
                <input type="hidden" name="password" value="<%=user.getPassword()%>" class="form-control">
            <th scope="row"><input type="hidden" name="id" value="<%=user.getId()%>" class="form-control"></th>
            <td><img src="resources/image/<%=user.getAddress().getProfileImage()%>" class="profile-image"></td>
            <td><input type="text" name="userName" value="<%=user.getUserName()%>" class="form-control"></td>
            <td><input type="text" name="email" value="<%=user.getEmail()%>" class="form-control"></td>
            <td><input type="text" name="contact" value="<%=user.getContact()%>" class="form-control"></td>
            <td><input type="text" name="userCity" value="<%=user.getAddress().getUserCity()%>" class="form-control"></td>
            <td><input type="text" name="userState" value="<%=user.getAddress().getUserState()%>" class="form-control"></td>
            <td><input type="text" name="userCountry" value="<%=user.getAddress().getUserCountry()%>" class="form-control"></td>
            <td><input type="text" name="userPincode" value="<%=user.getAddress().getUserPincode()%>" class="form-control"></td>
            <td>
                    <button type="submit" class="btn btn-success">Update</button>
            </td>
            </form>
            <td>
                <form method="post" action="delete">
                    <input type="hidden" name="id" value="<%=user.getId()%>">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
<%}%>
<%@ include file="allJsp.jsp"%>
</body>
</html>
