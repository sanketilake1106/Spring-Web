<%@ page import="com.maven.entities.User" %>
<%@ page import="com.maven.entities.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="allCss.jsp"%>
<%@ include file="allJsp.jsp"%>
<html>
<head>
    <title>Edit Profile</title>
</head>
<style>
    body {
        background: rgb(99, 39, 120)
    }

    .form-control:focus {
        box-shadow: none;
        border-color: #BA68C8
    }

    .profile-button {
        background: rgb(99, 39, 120);
        box-shadow: none;
        border: none
    }

    .profile-button:hover {
        background: #682773
    }

    .profile-button:focus {
        background: #682773;
        box-shadow: none
    }

    .profile-button:active {
        background: #682773;
        box-shadow: none
    }

    .back:hover {
        color: #682773;
        cursor: pointer
    }

    .labels {
        font-size: 11px
    }

    .add-experience:hover {
        background: #BA68C8;
        color: #fff;
        cursor: pointer;
        border: solid 1px #BA68C8
    }
</style>
<body>
<%
    User user = (User) session.getAttribute("user");
    Address address = user.getAddress();
%>
<% if (user!=null){%>
<form method="post" action="../updateProfile" enctype="multipart/form-data">
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px" src="<%= address.getProfileImage() != null ? "image/" + address.getProfileImage() : "default.png" %>">
                <span class="font-weight-bold"></span>
                <span class="text-black-50"></span>
            </div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>
                <div class="row mt-2">
                    <div class="col-md-12"><label class="labels">User Id</label><input type="text" name="id" class="form-control" placeholder="User Id" value="<%= user.getId()%>"></div>
                    <div class="col-md-6"><label class="labels">Name</label><input type="text" name="userName" class="form-control" placeholder="first name" value="<%=user.getUserName() %>"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" name="contact" class="form-control" placeholder="enter phone number" value=<%= user.getContact()%>></div>
                        <div class="col-md-12">
                            <label class="labels">City</label>
                            <input type="text" name="userCity" class="form-control" placeholder="enter address line 1" value="<%=address.getUserCity()%>">

                        </div>
                        <div class="col-md-12">
                            <label class="labels">State</label>
                            <input type="text" name="userState" class="form-control" placeholder="enter address line 2" value="<%=address.getUserState()%>">
                        </div>
                        <div class="col-md-12">
                            <label class="labels">Country</label>
                            <input type="text" name="userCountry" class="form-control" placeholder="enter address line 2" value="<%=address.getUserCountry()%>">
                        </div>
                        <div class="col-md-12">
                            <label class="labels">PinCode</label>
                            <input type="text" name="userPincode" class="form-control" placeholder="enter address line 2" value="<%=address.getUserPincode()%>">
                        </div>
                    <div class="col-md-12">
                        <label class="labels">Profile</label>
                        <input type="file" name="profileImage" class="form-control">
                    </div>



                    <div class="col-md-12">
                        <label class="labels">Password</label>
                        <input type="text" name="password" class="form-control" placeholder="enter address line 2" value="<%=user.getPassword()%>">
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Email ID</label>
                        <input type="text" name="email" class="form-control" placeholder="enter email id" value="<%=user.getEmail()%>">
                    </div>
                </div>
                <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</form>
<%}else{
    response.sendRedirect("login");
}%>
</body>
</html>
