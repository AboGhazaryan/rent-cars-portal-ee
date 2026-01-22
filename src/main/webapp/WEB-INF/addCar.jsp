<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Car</title>
    <link rel ="stylesheet" href="../css/addCar.css">
</head>

<body>
<a href="/">Home</a>
<a href="/cars">Cars</a>
<form action="/addCar" method="post" enctype="multipart/form-data">
    Brand:<input type="text" name="brand"><br>
    Model:<input type="text" name="model"><br>
    Year:<input type="text" name="year"><br>
    Daily Rate: <input type="text" name="daily_Rate"><br>
    Status:
    <select name="status">
        <option value="AVAILABLE">Available</option>
        <option value="RENTED">Rented</option>
        <option value="MAINTENANCE">Maintenance</option>
        <option value="INACTIVE">Inactive</option>
    </select>
    <input type="file" name="image"><br>
    <input type="submit" value="Add Car">
</form>
</body>
</html>
