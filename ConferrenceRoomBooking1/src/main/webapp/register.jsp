<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 --%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Register</title></head>
<body>
    <h2>User Registration</h2>
    
    <form action="RegisterServlet" method="post">
        Username: <input type="text" name="username" required /><br/>
        Password: <input type="password" name="password" required /><br/>
		Role : 
		<select name="role" required>
		 <option value="user">User</option>
		 <option value="admin">Admin</option>
		  </select><br><br>
        <input type="submit" value="Register" />
    </form>

    <p style="color:red">${error}</p>
    <p style="color:green">${success}</p>

    <a href="login.jsp">Already have an account? Login</a>
</body>
</html>