package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import exception.DatabaseConnectionException;

    public class DButil {
    private static final String URL = "jdbc:mysql://localhost:3306/carconnect";
    private static final String USER = "root";
    private static final String PASSWORD = "Anmol@7233";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
