<%@ page import="com.example.rentcarsportalee.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rentcarsportalee.model.User" %>
<%@ page import="com.example.rentcarsportalee.model.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CARS</title>
</head>
<body>
<%User user = (User) session.getAttribute("user");%>
<%List<Car> cars = (List<Car>) request.getAttribute("cars");%>
<a href="/">HOME</a>
<h2>Cars</h2>
<%if(user.getUserRole() == UserRole.ADMIN){%>
<a href="/addCar">Add Cars</a>
<%}%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Year</th>
        <th>Daily Rate</th>
        <th>Status</th>
        <%if(user.getUserRole() == UserRole.ADMIN){%>
        <th>Action</th>
        <%}%>
    </tr>
    <%for (Car car : cars){%>
    <tr>
        <td><%=car.getId()%></td>
        <td><%=car.getBrand()%></td>
        <td><%=car.getModel()%></td>
        <td><%=car.getYear()%></td>
        <td><%=car.getDailyRate()%></td>
        <td><%=car.getCarStatus().name()%></td>
        <%if(user.getUserRole() == UserRole.ADMIN){%>
        <td><a href="/deleteCar?id=<%=car.getId()%>">Delete</a> | <a href="/changeCar?id=<%=car.getId()%>">change</a></td>
        <%}%>
    </tr>
    <%}%>

</table>
</body>
</html>
