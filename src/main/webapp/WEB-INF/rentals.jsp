<%@ page import="com.example.rentcarsportalee.model.Rental" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rentcarsportalee.model.User" %>
<%@ page import="com.example.rentcarsportalee.model.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Rentals</title>
    <link rel="stylesheet" href="../css/rentals.css">
</head>
<body>
<%User user = (User) session.getAttribute("user");%>
<%List<Rental> rentals = (List<Rental>) request.getAttribute("rentals");%>
<a href="/">Home</a>
<h2>Rentals</h2>
<a href="/addRental">Add Rental</a>
<table border = 1>
  <tr>
    <th>ID</th>
    <th>Car</th>
    <th>Customer</th>
    <th>Start Date</th>
    <th>End Date</th>
    <th>Total Cost</th>
    <th>Status</th>
    <%if(user.getUserRole() == UserRole.ADMIN){%>
    <th>Action</th>
    <%}%>
  </tr>
  <%for(Rental rental : rentals){%>
  <tr>
    <td><%=rental.getId()%></td>
    <td><%=rental.getCar().getBrand()+" "+rental.getCar().getModel()%></td>
    <td><%=rental.getCustomer().getName()+" "+rental.getCustomer().getSurname() + " "+ rental.getCustomer().getLicenseNumber()%></td>
    <td><%=rental.getStartDate()%></td>
    <td><%=rental.getEndDate()%></td>
    <td><%=rental.getTotalCost()%></td>
    <td><%=rental.getRentalStatus().name()%></td>
    <%if(user.getUserRole() == UserRole.ADMIN){%>
    <td><a href="/deleteRental?id=<%=rental.getId()%>">Delete</a> <a href="/changeRental?id=<%=rental.getId()%>">Change</a> </td>
    <%}%>
  </tr>
  <%}%>
</table>
</body>
</html>
