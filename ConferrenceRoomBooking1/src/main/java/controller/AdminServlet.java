package controller;

import java.io.IOException;

import dao.RoomDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Room;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private final RoomDAO roomDAO = new RoomDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("role");

        // Only admin allowed
        if (session == null || !"admin".equals(role)) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String name = request.getParameter("name");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            boolean hasProjector = request.getParameter("projector") != null;
            boolean hasWhiteboard = request.getParameter("whiteboard") != null;

            Room room = new Room(0, name, capacity, hasProjector, hasWhiteboard);
            boolean success = roomDAO.addRoom(room);

            if (success) {
                request.setAttribute("message", "Room added successfully.");
            } else {
                request.setAttribute("message", "Failed to add room.");
            }

        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}