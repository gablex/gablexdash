/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.graphs;

import com.mspace1.model.graphvalue;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "smstable")
@ViewScoped
public class smstable implements Serializable{
  private static final String dbUserName = "root";
    private static final String dbPassword = "mysql123M?";
    private static final String dbServer = "jdbc:mysql://127.0.0.1:3306/dbSMPPGateway?useSSL=false";
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    Connection con = null;
    ResultSet result = null;
    Statement stmt = null;

    public smstable() {
    }
     String sql = "select source_addr,LEFT(destination_addr,6),time_submitted,short_message from dbSMPPGateway.tSMSOUT ORDER By id DESC LIMIT 5";

     public List<graphvalue> getdetail() throws ClassNotFoundException {
        String sql = "select source_addr,LEFT(destination_addr,6),time_submitted,short_message from dbSMPPGateway.tSMSOUT ORDER By id DESC LIMIT 5";
        List<graphvalue> list = new LinkedList<>();

        try {

            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbServer, dbUserName, dbPassword);
            stmt = con.createStatement();
            result= stmt.executeQuery(sql);

            while (result.next()) {

                DateFormat inFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
                DateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = null;

                graphvalue cust = new graphvalue();
                cust.setSource_addr(result.getString("source_addr"));
                cust.setDestination_addr(result.getString("LEFT(destination_addr,6)"));
                if ((result.getString("time_submitted") != null) && (result.getString("time_submitted").length() > 0)) {
                    try {
                        date2 = inFormat2.parse(result.getString("time_submitted"));
                    } catch (ParseException ex) {
                        Logger.getLogger(smstable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cust.setTime_submitted(outFormat.format(date2));
                } else {
                    cust.setTime_submitted(result.getString("time_submitted"));
                }
                cust.setShort_message(result.getString("short_message"));
                list.add(cust);
            }

        } catch (SQLException m) {
        } finally {
            try {
                result.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(smstable.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return list;

    }
public List<String> getdata() throws ClassNotFoundException {
        String sql = "select source_addr,LEFT(destination_addr,6) as nun,time_submitted,short_message from tData ORDER By id DESC LIMIT 5 ";
              String sql2="select LEFT(destination_addr,6) as km from tData ORDER By id DESC LIMIT 5";
    Session session = getSessionFactory().openSession();
         List<String> lista3=null; 
          List<String> lista2=null;
           List<String> lista1 = null;
        try {

  session.beginTransaction();
  
   
  
  
  SQLQuery query1=session.createSQLQuery(sql);
  SQLQuery query2=session.createSQLQuery(sql2);
  query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
  query2.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
     lista1=query1.getResultList();
     lista2=query2.getResultList();
 lista1.addAll(lista2);
     
     
     
   
      session.getTransaction().commit();
      System.out.println(lista1);
        }
        catch(HibernateException l){
        }

        return lista1;
        

    }
     
     
}
