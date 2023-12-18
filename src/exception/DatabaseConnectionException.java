package exception;

import java.sql.SQLException;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String str) {
        super(str);
    }

    public DatabaseConnectionException(String error_connecting_to_the_database, SQLException ex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
