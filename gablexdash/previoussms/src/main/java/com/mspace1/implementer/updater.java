/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.model.Tuser;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "updater")
@SessionScoped

public class updater implements Serializable {

    HttpSession sessionj = getsession.getSession();
    long id = (long) sessionj.getAttribute("id");
    String user = (String) sessionj.getAttribute("username");

    public Tuser getUser() {
        Tuser u = new Tuser();
        Session session = getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            u = (Tuser) session.get(Tuser.class, id); //proxy
            session.getTransaction().commit();
        } catch (Exception k) {
            k.printStackTrace();
        } finally {
            session.close();
        }
        return u;
    }

    public void updat() throws Exception {
        Tuser result = this.getUser();
        if (result.getUsername() != null) {
            sessionj.setAttribute("max_total", result.getMaxTotal());
            sessionj.setAttribute("max_daily", result.getMaxDaily());
            sessionj.setAttribute("max_weekly", result.getMaxWeekly());
            sessionj.setAttribute("max_monthly", result.getMaxMonthly());
            sessionj.setAttribute("agent", result.getAgent());
            sessionj.setAttribute("startd", result.getStartDate());
            sessionj.setAttribute("endd", result.getEndDate());
            sessionj.setAttribute("counttoday", result.getSmsCountToday());
            sessionj.setAttribute("countweek", result.getSmsCountWeek());
            sessionj.setAttribute("countmonth", result.getSmsCountMonth());
            sessionj.setAttribute("counttotoal", result.getSmsCountTotal());   
        }
    }

   public Long getSmsBalance() {
        Date date = new Date();
        List results = null;
        Session session = getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Criteria crit = session.createCriteria(Tuser.class);
            crit.add(Restrictions.eq("username", user));
            crit.add(Restrictions.eq("id", id));
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.property("maxTotal"));
            crit.setProjection(projList);
            results = crit.list();
        } catch (Exception k) {
            System.out.println(k.getMessage());
        } finally {
            session.close();
        }
        long balance;
        if (results != null) {
            balance = (Long) results.get(0);
            sessionj.setAttribute("max_total", balance);
            System.out.println(user + "::Max total sms are :" + results.get(0) + " " + date);
        } else {
            balance = 0l;
        }
        return balance;
    }

    public Long getSmsBalance2(String username, String password) {
        List results = null;
         long balance=0l;
        Session session = getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Criteria crit = session.createCriteria(Tuser.class);
            crit.add(Restrictions.eq("username", username));
            crit.add(Restrictions.eq("password", password));
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.property("maxTotal"));
            crit.setProjection(projList);
            results = crit.list();
        } catch (Exception k) {
               balance = 0l;
            System.out.println(k.getMessage());
        } finally {
            session.close();
        }
   
        if (results != null) {
            balance = (Long) results.get(0);
        } else {
            balance = 0l;
        }
        return balance;
    }
    

}
