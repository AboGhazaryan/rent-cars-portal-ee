<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add customer</title>
    <link rel="stylesheet" href="../css/addCust.css">
</head>

<body>
<a href="/"></a>
<a href="/customers">Customers</a>
<form action="/addCustomer" method="post">
    Name:<input type="text" name="name"><br>
    Surname:<input type="text" name="surname"><br>
    License number:<input type="text" name="license_number"><br>
    Phone:<input type="text" name="phone"><br>
    Email:<input type="text" name="email"><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
