
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection_new {

    private static final String URL = "jdbc:mysql://localhost:3306/faizan?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Faizan@000";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connected to MySQL successfully!");
            } else {
                System.out.println("❌ Connection failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error while closing connection: " + e.getMessage());
        }
    }
}





