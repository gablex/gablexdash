/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kevol
 */
public class connector {
    
     public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException,SQLException {
        PreparedStatement ps = null;
        String dblink = "jdbc:mysql://10.240.181.14:3306/dbSMPPGateway?useSSL=false";
        String username = "dlr";
        String password = "dlr123";
Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(dblink, username, password);
        ps = con.prepareStatement(sql);
      
        
                return ps;
    
  
    }
     
          public static PreparedStatement getPreparedStatement2(String sql) throws ClassNotFoundException,SQLException {
        PreparedStatement ps = null;
        String dblink = "jdbc:mysql://10.240.181.14:3306/dbSMS?useSSL=false";
        String username = "dlr";
        String password = "dlr123";
Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(dblink, username, password);
        ps = con.prepareStatement(sql);
      
        
                return ps;
    
  
    }
     
     
     
}
