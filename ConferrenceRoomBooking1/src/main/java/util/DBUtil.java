package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
    private static final String URL = "jdbc:mysql://localhost:3306/room_booking";
    private static final String USER = "root";
    private static final String PASSWORD = "ajy8805"; 

    public static Connection getConnection() throws SQLException {
//    	Class.forName("");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}