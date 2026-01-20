
<%@ page import="com.example.rentcarsportalee.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Customer</title>
</head>
<%Customer customer = (Customer) request.getAttribute("customer"); %>

<body>
<a href="/">Home</a>
<a href="/customers">Customers</a>

<form action="/changeCustomer" method="post">
    <input type="hidden" name="id" value="<%= customer.getId() %>">
    Name:<input type="text" name="name" value="<%=customer.getName()%>"><br>
    Surname:<input type="text" name="surname" value="<%=customer.getSurname()%>"><br>
    License number:<input type="text" name="license_number" value="<%=customer.getLicenseNumber()%>"><br>
    Phone: <input type="text" name="phone" value="<%=customer.getPhone()%>"><br>
    Email: <input type="text" name="email" value="<%=customer.getEmail()%>"><br>

    <input type="submit" value="Change Customer"><br>
</form>
</body>
</html>
