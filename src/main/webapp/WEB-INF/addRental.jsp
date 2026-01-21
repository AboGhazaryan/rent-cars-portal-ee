<%@ page import="com.example.rentcarsportalee.model.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rentcarsportalee.model.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Rental</title>
    <link rel="stylesheet" href="../css/addRental.css">
</head>
<body>
<a href="/">Home</a>
<a href="/rentals">Rentals</a>
<%List<Car> cars = (List<Car>) request.getAttribute("cars");%>
<%List<Customer> customers = (List<Customer>) request.getAttribute("customers");%>
<form action="/addRental" method="post">
    Car:
    <select name="car_id">
    <%for (Car car: cars){%>
    <option value="<%=car.getId()%>">
            <%=car.getBrand()%> <%=car.getModel()%>
            <%=car.getYear()%> <%=car.getDailyRate()%>/Day
            <%=car.getCarStatus()%>
    </option>
    <%}%>
    </select> <br>
    Customer:
    <select name="customer_id">
    <%for (Customer customer: customers){%>
    <option value="<%=customer.getId()%>">
            <%=customer.getName()%> <%=customer.getSurname()%>
            <%=customer.getLicenseNumber()%> <%=customer.getEmail()%>
    </option>
    <%}%>
    </select> <br>
    Start Date: <input type="date" name="start_date"> <br>
    End Date: <input type="date" name="end_date"> <br>
    Status:
    <select name="status">
    <option value="PENDING">Pending</option>
    <option value="ACTIVE">Active</option>
    <option value="COMPLETED">Completed</option>
    <option value="CANCELED">Canceled</option>
    </select>
    <input type="submit" value="Add Rental">
</form>
</body>
</html>
