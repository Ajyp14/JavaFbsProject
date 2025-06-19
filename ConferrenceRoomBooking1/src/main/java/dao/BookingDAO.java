package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import model.Booking;
import util.DBUtil;

public class BookingDAO {

    // Check if a booking overlaps
    public boolean isBookingConflict(int roomId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        String sql = "SELECT * FROM bookings WHERE room_id = ? AND booking_date = ? AND (start_time < ? AND end_time > ?)";

        try {
        	Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, roomId);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, Time.valueOf(endTime));     
            ps.setTime(4, Time.valueOf(startTime));   

            ResultSet rs = ps.executeQuery();
            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true; 
    }

    
    public boolean insertBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, room_id, booking_date, start_time, end_time) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getRoomId());
            ps.setDate(3, Date.valueOf(booking.getBookingDate()));
            ps.setTime(4, Time.valueOf(booking.getStartTime()));
            ps.setTime(5, Time.valueOf(booking.getEndTime()));

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}