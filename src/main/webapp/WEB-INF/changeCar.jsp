<%@ page import="com.example.rentcarsportalee.model.Car" %>
<%@ page import="com.example.rentcarsportalee.model.CarStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Car</title>
    <link rel="stylesheet" href="../css/changeCar.css">
</head>
<%Car car = (Car) request.getAttribute("car"); %>

<body>
<a href="/">Home</a>
<a href="/cars">Cars</a>

<form action="/changeCar" method="post">
    <input type="hidden" name="id" value="<%= car.getId() %>">
    Brand:<input type="text" name="brand" value="<%=car.getBrand()%>"><br>
    Model:<input type="text" name="model" value="<%=car.getModel()%>"><br>
    Year:<input type="text" name="year" value="<%=car.getYear()%>"><br>
    Daily Rate: <input type="text" name="daily_Rate" value="<%=car.getDailyRate()%>"><br>
    Status:
    <select name="status">
        <option value="AVAILABLE"
                <%if(car.getCarStatus() == CarStatus.AVAILABLE){%>
                    selected
                <%}%>>Available</option>
        <option value="RENTED"
                <%if(car.getCarStatus() == CarStatus.RENTED){%>
                    selected
                <%}%>>Rented</option>
        <option value="MAINTENANCE"
                <%if(car.getCarStatus() == CarStatus.MAINTENANCE){%>
                    selected
                <%}%>>Maintenance</option>
        <option value="INACTIVE"
                <%if(car.getCarStatus() == CarStatus.INACTIVE){%>
                    selected
                <%}%>>Inactive</option>
    </select>
    <input type="submit" value="Change Car"><br>
</form>
</body>
</html>
