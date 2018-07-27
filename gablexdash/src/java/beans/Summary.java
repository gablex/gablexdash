/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import static javax.management.remote.JMXConnectorFactory.connect;
import static org.apache.tomcat.jni.Local.connect;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.jsoup.Jsoup;
import org.apache.commons.lang.StringUtils;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author mspace-developer
 */
@ManagedBean
public class Summary {

    public List<User> getUsers() throws SQLException {

        Connection con = Database.getConnection();

        List<User> users = new ArrayList<>();
        PreparedStatement pstmt;
           pstmt=con.prepareStatement("select id,msgType,ticketNo,AllocatedTo, status,message,subject,sender,dateReceived from (\n" +
"(SELECT t.id,t.msgType ,t.ticketNo, concat(u.firstname, ' ',u.surname) as  AllocatedTo,t.status as status,t.msg as message ,SUBSTRING(t.subject,1,40) as subject,SUBSTRING(t.src,1,35) as sender,SUBSTRING(t.dateReceived,1,20)as dateReceived from dbTASK.tTASKS t , dbSMS.tUSER u, dbTASK.tALLOCATIONS a WHERE a.ticketNo=t.ticketNo AND  a.allocatedTo=u.id and t.status > 0 and t.msgType=2  ORDER BY t.id DESC LIMIT 5)\n" +
" \n" +
"union(SELECT t.id,t.msgType ,t.ticketNo, 'Not Allocated' as AllocatedTo,t.status as status,t.msg as message,\n" +
"SUBSTRING(t.subject,1,40) as subject,SUBSTRING(t.src,1,20) as sender,SUBSTRING(t.dateReceived,1,19)as dateReceived from dbTASK.tTASKS t WHERE t.status = 0 and t.msgType=2   ORDER BY t.id DESC LIMIT 5)\n" +
") as t1 order by id desc limit 5");
//        pstmt = con.prepareStatement("select id,msgType,ticketNo,AllocatedTo, status,message,message1,subject,sender,dateReceived from (\n"
//                + "(SELECT t.id,t.msgType ,t.ticketNo, concat(u.firstname, ' ',u.surname) as  AllocatedTo,t.status as status,t.msg as message ,SUBSTRING(t.msg,1,100) as message1,SUBSTRING(t.subject,1,40) as subject,SUBSTRING(t.src,1,35) as sender,SUBSTRING(t.dateReceived,1,20)as dateReceived from dbTASK.tTASKS t , dbSMS.tUSER u, dbTASK.tALLOCATIONS a WHERE a.ticketNo=t.ticketNo AND  a.allocatedTo=u.id and t.status > 0  ORDER BY t.id)\n"
//                + "\n"
//                + "union\n"
//                + "\n"
//                + "(SELECT t.id,t.msgType ,t.ticketNo, 'Not Allocated' as AllocatedTo,t.status as status,t.msg as message, SUBSTRING(t.msg,1,100) as message1,\n"
//                + "SUBSTRING(t.subject,1,40) as subject,SUBSTRING(t.src,1,20) as sender,SUBSTRING(t.dateReceived,1,19)as dateReceived from dbTASK.tTASKS t WHERE t.status = 0   ORDER BY t.id )  \n"
//                + ") as t1 order by id");
        ResultSet rs = pstmt.executeQuery();
        String statusName = "";

        while (rs.next()) {

            String ret = rs.getString("message");
           
           
            String message;

            if (ret.contains("html")) {
                message = StringUtils.substringBetween(ret, "", "</html>");

                System.out.println(message);
                Document doc = Jsoup.parse(message);
                String text = Jsoup.clean(doc.text(), Whitelist.none());
                
                Jsoup.parse(text).text();

                if (text.length() > 100) {
                    message = text.substring(0, 100);
                } else {
                    message = text;

                }
            } else {
                if (ret.length() > 100) {
                    message = ret.substring(0, 100);
                } else {
                    message = ret;

                }
            }

            if (rs.getInt("status") == 0) {
                statusName = "To Allocate";

            }
            if (rs.getInt("status") == 1) {
                statusName = "Completed";

            }
            if (rs.getInt("status") == 3) {
                statusName = "Pending";
            }
            User user = new User();
            user.setSource(rs.getString("sender"));
            user.setDateReceived(rs.getString("dateReceived"));
            user.setAllocateTo(rs.getString("AllocatedTo"));
            user.setStatus(statusName);
            user.setSubject(rs.getString("subject"));
            user.setMessage(message);
            users.add(user);

        }
        con.close();
        pstmt.close();
        con.close();
        return users;

    }

    public static void main(String[] x) throws SQLException {
        new Summary().getUsers();
    }

}
