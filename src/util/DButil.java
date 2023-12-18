package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import exception.DatabaseConnectionException;


public class DButil {

    public static void main(String[] args) throws DatabaseConnectionException{
        //Connection connection = null;

        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/carconnect","root","Anmol@7233");
              
               System.out.println(con);
               System.out.println("Connected to the database!");
               con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DButil.class.getName()).log(Level.SEVERE,null,ex);
            
            System.err.println("Error connecting to the database!");
            
            //e.printStackTrace();
        } 
        catch(SQLException ex){
            throw new DatabaseConnectionException("Error connecting to the database", ex);
            //Logger.getLogger(DButil.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
