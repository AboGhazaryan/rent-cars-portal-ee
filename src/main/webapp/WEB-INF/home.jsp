<%@ page import="com.example.rentcarsportalee.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>home</title>
    <link rel="stylesheet" href="../css/home.css">
</head>
<%User user = (User) session.getAttribute("user");%>
<body>
<div class="container">
    <h1>WELCOME TO CAR RENTAL SYSTEM</h1>
    <p class="auth-links">
        <%if (user == null) {%>
        <a href="/login">LOGIN</a>
        | <a href="/register">REGISTER</a>
        <%} else {%>
        Welcome <%=user.getName() + " " + user.getSurname()%> <a href="/logout">LOGOUT</a>
        <%}%>

    </p><br/>
    <div class="menu">
        <a href="/cars">Cars</a><br>
        <a href="/customers">Customers</a><br>
        <a href="/rentals">Rentals</a>
    </div>
</div>
</body>
</html>
