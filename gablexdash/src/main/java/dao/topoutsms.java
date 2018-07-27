/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.smsout;
import model.toptsms;

/**
 *
 * @author mspace
 */

@ManagedBean(name = "topsms")
@ViewScoped
public class topoutsms implements  Serializable{

    /**
     * Creates a new instance of topoutsms
     */

  //private static final String dbUserName = "dlr";
 private static final String dbUserName = "mysql";
    //private static final String dbPassword = "dlr123";
 private static final String dbPassword = "mysql123";
    private static final String dbServer = "jdbc:mysql://192.168.1.51:3306/dbSMPPGateway?useSSL=false";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    Connection con = null;
    ResultSet result = null;
    Statement stmt = null;

    public topoutsms() {
    }
    public List<toptsms> getsms() throws ClassNotFoundException {

        String sql = "select id,tSMSOUT_id,source_addr,LEFT(destination_addr,6),time_submitted,message from dbSMPPGateway.tOPTOUTSMS ORDER By id DESC LIMIT 5";
        List<toptsms> list = new LinkedList<>();

        try {

            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbServer, dbUserName, dbPassword);
            stmt = con.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
               toptsms cust = new toptsms();
                DateFormat inFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
                DateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = null;

               
                cust.setSource_addr(result.getString("source_addr"));
             cust.setDestination_addr(result.getString("LEFT(destination_addr,6)"));
                
                  if ((result.getDate("time_submitted") != null)) {
//                    try {
//                        date2 = inFormat2.parse(result.getString("time_submitted"));
//                    } catch (ParseException ex) {
//                        Logger.getLogger(viewsms.class.getName()).log(Level.SEVERE, null, ex);
//                    }
 cust.setTime_submitted(outFormat.format(result.getTimestamp("time_submitted")));
                 
                } else {
                    cust.setTime_submitted("");
                }
                cust.setMessage(result.getString("message"));
                cust.setTsmsoutid(result.getInt("tSMSOUT_id"));
                cust.setId(result.getInt("id"));
                   list.add(cust); 
            } 

        } catch (SQLException m) {
        } finally {
            try {
                result.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(viewsms.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return list;

    }
}
