/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.interfac.tusersmsin;
import com.mspace1.model.Tusersmsin;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mspace
 */
public class tusersmsinimpl implements tusersmsin {

    HttpSession session1 = getsession.getSession();
    Long id = (Long) session1.getAttribute("id");
    String user= (String) session1.getAttribute("username");

    @Override
    public List<Tusersmsin> list(String sdate1, String edate1) {
        List lista = null;
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista = session.createQuery("from Tusersmsin m where m.userid=:id and m.timereceived between " + sdate1 + " and " + edate1 + " order by m.id desc")
                    .setParameter("id", id)
                    .list();
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }
        return lista;

    }
    public String updatepass(String newpass){
         Session session = getSessionFactory().openSession();
            try {
                session.beginTransaction();
   String   sql = "update Tuser m  set m.password='" + newpass + "' where m.username= :username and m.id= :id";
                Query qry = session.createQuery(sql); 
                qry.setParameter("username", user);
                  qry.setParameter("id", id);
                qry.executeUpdate();
                session.getTransaction().commit();
                
                 } catch (HibernateException ex) {
                     ex.printStackTrace();
                 }
                session.close();
                 
        try {
          
        } catch (Exception ex) {
            Logger.getLogger(tusersmsinimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
return null;
    }

}
