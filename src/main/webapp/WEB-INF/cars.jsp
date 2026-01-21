<%@ page import="com.example.rentcarsportalee.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rentcarsportalee.model.User" %>
<%@ page import="com.example.rentcarsportalee.model.UserRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CARS</title>
    <link rel="stylesheet" href="../css/cars.css">
</head>
<body>
<%User user = (User) session.getAttribute("user");%>
<%List<Car> cars = (List<Car>) request.getAttribute("cars");%>
<div class="top-links">
    <a href="/">HOME</a>
    <h2>Cars</h2>
    <%if (user.getUserRole() == UserRole.ADMIN) {%>
    <a href="/addCar">➕ Add Cars</a>
    <%}%>
</div>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Picture</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Year</th>
        <th>Daily Rate</th>
        <th>Status</th>
        <%if (user.getUserRole() == UserRole.ADMIN) {%>
        <th>Action</th>
        <%}%>
    </tr>
    <%for (Car car : cars) {%>
    <tr>
        <td><%=car.getId()%>
        </td>
        <td>
            <%if (car.getPictureName() == null || car.getPictureName().isEmpty()) {%>
            <img width="50" src="/img/img.png">
            <%} else {%>
            <img width="50" src="getImage?picture_name=<%=car.getPictureName()%>">
            <%}%>
        </td>
        <td><%=car.getBrand()%>
        </td>
        <td><%=car.getModel()%>
        </td>
        <td><%=car.getYear()%>
        </td>
        <td><%=car.getDailyRate()%>
        </td>
        <td><%=car.getCarStatus().name()%>
        </td>
        <%if (user.getUserRole() == UserRole.ADMIN) {%>
        <td>
            <a class="btn btn-delete" href="/deleteCar?id=<%=car.getId()%>">Delete</a>
            <a  class="btn btn-edit" href="/changeCar?id=<%=car.getId()%>">change</a>
        </td>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
