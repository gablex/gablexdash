
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mspace
 */
public class test {
          public static void main(String[] args) {
         final String dbUserName = "root";
    final String dbPassword = "mysql123M?";
    final String dbServer = "jdbc:mysql://127.0.0.1:3306/dbSMS?useSSL=false";
 final String dbDriver = "com.mysql.jdbc.Driver";
    Connection con = null;
    ResultSet result = null; 
    Statement stmt = null;
  String   sql="select * from tUSER where id=6";
    try{
         Class.forName(dbDriver);
            con = DriverManager.getConnection(dbServer, dbUserName, dbPassword);
            stmt = con.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
          result.getLong("id");
            } }
    catch(Exception k){
            k.printStackTrace();
            
            }}
}
