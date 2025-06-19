<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 --%><!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null || !"admin".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head><title>Admin Panel - Add Room</title></head>
<body>
    <h2>Admin Panel - Add Conference Room</h2>

    <form action="AdminServlet" method="post">
        Room Name: <input type="text" name="name" required /><br/>
        Capacity: <input type="number" name="capacity" required /><br/>
        Projector: <input type="checkbox" name="projector" /><br/>
        Whiteboard: <input type="checkbox" name="whiteboard" /><br/>
        <input type="submit" value="Add Room" />
    </form>

    <p style="color:green">${message}</p>

    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>