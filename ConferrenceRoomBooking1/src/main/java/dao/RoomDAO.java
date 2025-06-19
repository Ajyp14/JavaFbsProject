package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Room;
import util.DBUtil;

public class RoomDAO {

    // Get all rooms
    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try {
        	Connection conn = DBUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setCapacity(rs.getInt("capacity"));
                room.setHasProjector(rs.getBoolean("has_projector"));
                room.setHasWhiteboard(rs.getBoolean("has_whiteboard"));

                roomList.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomList;
    }

    // Add a new room (admin feature)
    public boolean addRoom(Room room) {
        String sql = "INSERT INTO rooms (name, capacity, has_projector, has_whiteboard) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, room.getName());
            ps.setInt(2, room.getCapacity());
            ps.setBoolean(3, room.isHasProjector());
            ps.setBoolean(4, room.isHasWhiteboard());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}