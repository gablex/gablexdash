
package dao;
import beans.AllEmails1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Database {
 
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/database",
                    "user", "passwd");
            return con;
        } catch (ClassNotFoundException ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        } catch (SQLException ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
        public static Connection getConnection1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/database",
                    "user", "passwd");
            return con;
        } catch (ClassNotFoundException ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        } catch (SQLException ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
        }
    }
 
    public static void close1(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
        }
        
    }
     
    

    
}
