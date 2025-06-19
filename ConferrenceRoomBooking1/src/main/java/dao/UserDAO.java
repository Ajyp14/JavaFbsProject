package dao;

import java.sql.*;

import model.User;
import util.DBUtil;

public class UserDAO {


    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try {

        	Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            		
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try{
        	Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public boolean usernameExists(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try{
        	Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}