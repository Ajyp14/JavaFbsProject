package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.BookingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Booking;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private final BookingDAO bookingDAO = new BookingDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int userId = (int) session.getAttribute("userId");
            int roomId = Integer.parseInt(request.getParameter("room_id"));
            LocalDate bookingDate = LocalDate.parse(request.getParameter("booking_date"));
            LocalTime startTime = LocalTime.parse(request.getParameter("start_time"));
            LocalTime endTime = LocalTime.parse(request.getParameter("end_time"));

            // Conflict check
            if (bookingDAO.isBookingConflict(roomId, bookingDate, startTime, endTime)) {
                request.setAttribute("message", "Booking conflict: Room is already booked at that time.");
            } else {
                Booking booking = new Booking(0, userId, roomId, bookingDate, startTime, endTime);
                boolean success = bookingDAO.insertBooking(booking);

                if (success) {
                    request.setAttribute("message", "Room booked successfully!");
                } else {
                    request.setAttribute("message", "Failed to book room. Try again.");
                }
            }

        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("bookRoom.jsp").forward(request, response);
    }
}