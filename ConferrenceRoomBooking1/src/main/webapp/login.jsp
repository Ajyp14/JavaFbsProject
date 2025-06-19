
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
    <h2>Login</h2>

    <form action="LoginServlet" method="get">
        Username: <input type="text" name="username" required /><br/>
        Password: <input type="password" name="password" required /><br/>
        <input type="submit" value="Login" />
    </form>

    <p style="color:red">${error}</p>

    <a href="register.jsp">Don't have an account? Register here</a>
</body>
</html>