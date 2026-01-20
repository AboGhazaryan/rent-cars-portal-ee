
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%String message = (String) session.getAttribute("message");%>
<%if(message != null && !message.isEmpty()){%>

<p style="color: red"><%=message%></p>
<%session.removeAttribute("message");%>
<%}%>
<form action="/register" method="post">
    <input type="text" name="name" placeholder="Name"><br>
    <input type="text" name="surname" placeholder="Surname"><br>
    <input type="text" name="username" placeholder="Username"><br>
    <input type="password" name="password" placeholder="Password"><br>
    Role <select name="role">
    <option value="ADMIN">Admin</option>
    <option value="USER">User</option>
</select><br>
    <input type="submit" value="Register">
</form>

</body>
</html>
