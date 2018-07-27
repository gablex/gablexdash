/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.interfac.tuserinterface;
import com.mspace1.model.Tuser;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mspace
 */
public class tuserimplementor implements tuserinterface {

    Calendar calendar = Calendar.getInstance();
    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

    @Override
    public void update(Tuser user) {
        Session session = getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(Tuser user) {
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }
    }

    @Override
    public List<Tuser> list() {
        List lista = null;
        Session session = getSessionFactory().openSession();
        HttpSession sessionm = getsession.getSession();
        long id = (long) sessionm.getAttribute("id");
        String user = (String) sessionm.getAttribute("username");
        try {
            session.beginTransaction();
            lista = session.createQuery("from Tuser u where u.username=:username and u.id=:id")
                    .setParameter("username", user)
                    .setParameter("id", id)
                    .list();
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }

        return lista;
    }

    @Override
    public Tuser getbyid(long id) {
        Session session = getSessionFactory().openSession();

        session.beginTransaction();
        return (Tuser) session.load(Tuser.class, id);

    }

    @Override
    public void saveuser(Tuser user) {
        Session session = getSessionFactory().openSession();
        try {

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }
    }

    @Override
    public Tuser getUser(String username, String password) {
        Tuser u = new Tuser();
        try (Session session = getSessionFactory().openSession()) {
            session.getTransaction().begin();
            String hql = "from Tuser s where s.username = :username and s.password = :password";
            u = (Tuser) session.createQuery(hql)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .list().get(0);
            if (u != null) {
                String sql = "update Tuser m  set m.loggedIn=1, m.loggedInTime='" + ourJavaTimestampObject + "' where m.username = :username and m.password = :password";
//                 System.out.println(sql);
                Query qry = session.createQuery(sql);
                qry .setParameter("username", username);
                 qry   .setParameter("password", password);
                
                qry.executeUpdate();
                session.getTransaction().commit();
            }

        } catch (Exception m) {

            FacesMessage message = new FacesMessage(" Not Succesful", " Either your Username or password is invalid");
            FacesContext.getCurrentInstance().addMessage(null, message);

            System.out.println("password /username error");
//            m.printStackTrace();

        }
        return u;
    }

    @Override
    public void logindetailupdate() {
        HttpSession sessionm = getsession.getSession();
        long id = (long) sessionm.getAttribute("id");
        String user = (String) sessionm.getAttribute("username");
        Session session = getSessionFactory().openSession();       
        try {
            session.beginTransaction();
            String sql = "update Tuser m  set m.loggedIn=0, m.loggedInTime='" + ourJavaTimestampObject + "' where m.username = :username and m.id= :id";
//            System.out.println(sql);
            Query qry = session.createQuery(sql);
             qry.setParameter("username", user);
                 qry.setParameter("id", id);
            qry.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException k) {
            k.printStackTrace();
        } finally {
            session.close();
            System.out.println(user + " id : " + id + " logged out "+ new Date());
        }

    }

}
