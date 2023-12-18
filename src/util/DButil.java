package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import exception.DatabaseConnectionException;


//public class DButil {

//    public static void main(String[] args) throws DatabaseConnectionException{
//        try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//            // Establish a connection
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carconnect","root","Anmol@7233");
//              
//               System.out.println(connection);
//               System.out.println("Connected to the database!");
//               connection.close();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DButil.class.getName()).log(Level.SEVERE,null,ex); 
//            System.err.println("Error connecting to the database!");
//            
//            //e.printStackTrace();
//        } 
//        catch(SQLException ex){
//            throw new DatabaseConnectionException("Error connecting to the database", ex);
//            //Logger.getLogger(DButil.class.getName()).log(Level.SEVERE,null,ex);
//        }
//    }
    
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
