<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../css/login.css">
</head>
<body>
<div class="login-container">
    <%String message = (String) session.getAttribute("message");%>
    <%if (message != null && !message.isEmpty()) {%>
    <p style="color:red"><%=message%>
    </p>
    <%session.removeAttribute("message");%>
    <%}%>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="Username"><br>
        <input type="password" name="password" placeholder="Password"><br>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
