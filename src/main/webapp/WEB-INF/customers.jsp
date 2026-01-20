<%@ page import="com.example.rentcarsportalee.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<%List<Customer> customers = (List<Customer>) request.getAttribute("customers");%>
<a href="/">HOME</a>
<h2>Customer</h2>
<a href="/addCustomer">Add Customer</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>License Number</th>
        <th>phone</th>
        <th>email</th>
        <th>Action</th>
    </tr>

    <%for (Customer customer: customers){%>
    <tr>
        <td><%=customer.getId()%></td>
        <td><%=customer.getName()%></td>
        <td><%=customer.getSurname()%></td>
        <td><%=customer.getLicenseNumber()%></td>
        <td><%=customer.getPhone()%></td>
        <td><%=customer.getEmail()%></td>
        <td><a href="/deleteCustomer?id=<%=customer.getId()%>">Delete</a> | <a href="/changeCustomer?id=<%=customer.getId()%>">Change</a> </td>
    </tr>
    <%}%>

</table>
</body>
</html>
