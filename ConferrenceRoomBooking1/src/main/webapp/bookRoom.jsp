<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 --%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Room" %>
<%@ page import="dao.RoomDAO" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    RoomDAO roomDAO = new RoomDAO();
    List<Room> roomList = roomDAO.getAllRooms();
%>
<!DOCTYPE html>
<html>
<head><title>Book a Room</title></head>
<body>
    <h2>Book a Conference Room</h2>

    <form action="BookingServlet" method="post">
        Room:
        <select name="room_id" required>
            <option value="">Select Room</option>
            <% for (Room room : roomList) { %>
                <option value="<%= room.getId() %>">
                    <%= room.getName() %> (Capacity: <%= room.getCapacity() %>)
                </option>
            <% } %>
        </select><br/>

        Date: <input type="date" name="booking_date" required /><br/>
        Start Time: <input type="time" name="start_time" required /><br/>
        End Time: <input type="time" name="end_time" required /><br/>
        <input type="submit" value="Book Room" />
    </form>

    <p style="color:blue">${message}</p>

    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>