<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 --%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Dashboard</title></head>
<body>
    <h2>Welcome, <%= username %>!</h2>

    <ul>
        <li><a href="bookRoom.jsp">Book a Conference Room</a></li>

        <% if ("admin".equals(role)) { %>
            <li><a href="admin.jsp">Admin Panel (Manage Rooms)</a></li>
        <% } %>

        <li><a href="LogoutServlet">Logout</a></li>
    </ul>
</body>
</html>