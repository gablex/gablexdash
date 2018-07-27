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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.smsout1;

/**
 *
 * @author mspace
 */


@ManagedBean(name = "viewsms_dbsms")
@ViewScoped
public class viewsms_dbsms  implements Serializable{
 //private static final String dbUserName = "dlr";
 private static final String dbUserName = "mysql";
    //private static final String dbPassword = "dlr123";
 private static final String dbPassword = "mysql123";
    private static final String dbServer = "jdbc:mysql://192.168.1.51:3306/dbSMS?useSSL=false";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    Connection con = null;
    ResultSet result = null; 
    Statement stmt = null;
    /**
     * Creates a new instance of viewsms_dbsms
     */
    public viewsms_dbsms() {
    }
    
    
       public List<smsout1> getsms2() throws ClassNotFoundException {

        String sql = "select source_addr,LEFT(destination_addr,6),time_submitted,message_payload,status from dbSMS.tSMSOUT ORDER By id DESC LIMIT 5";
        List<smsout1> list = new LinkedList<>();

        try {

            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbServer, dbUserName, dbPassword);
            stmt = con.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
              smsout1 cust = new smsout1();
                DateFormat inFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
                DateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = null;
               
                cust.setSource_addr(result.getString("source_addr"));
                cust.setDestination_addr(result.getString("LEFT(destination_addr,6)"));
                switch (result.getInt("status")) {
                    case 0:
                        cust.setStatus2("To Be Sent");
                        break;
                    case 1:
                        cust.setStatus2("Submitted To Network");
                        break;
                    case 2:
                        cust.setStatus2("Network");
                        break;
                    case 3:
                        cust.setStatus2("Delivered");
                        break;
                    case 4:
                        cust.setStatus2("Network");
                        break;
                    case 5:
                        cust.setStatus2("Undelivered");
                        break;
                    case 6:
                        cust.setStatus2("Expired");
                        break;
                    case 7:
                        cust.setStatus2("Submit Failed");
                        break;
                    case 8:
                        cust.setStatus2("Network");
                        break;
                         case 9:
                        cust.setStatus2("OptedOut");
                        break;
                    case 11:
                        cust.setStatus2("Scheduled");
                        break;
                    default:
                        break;
                }
                
                  if ((result.getString("time_submitted") != null) && (result.getString("time_submitted").length() > 0)) {
                    try {
                        date2 = inFormat2.parse(result.getString("time_submitted"));
                    } catch (ParseException ex) {
                        Logger.getLogger(viewsms.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cust.setTime_submitted(outFormat.format(date2));
                } else {
                    cust.setTime_submitted(result.getString("time_submitted"));
                }
                cust.setShort_message(result.getString("message_payload"));
               list.add(cust); 
            } 

        } catch (SQLException m) {
            m.printStackTrace();
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
