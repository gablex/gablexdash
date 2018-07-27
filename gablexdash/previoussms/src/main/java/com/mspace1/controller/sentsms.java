/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.model.sentsmsview;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "sentsms")
@SessionScoped
public class sentsms implements Serializable {

    private sentsmsview sentview;
    DateFormat inFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
    DateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    List<sentsmsview> values = null;
    DateFormat dateFormat0 = new SimpleDateFormat("yyyyMMdd");
    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    private List<sentsmsview> sntsms;
    private Date sdate;
    private Date edate;
    private Date date2;
    private Date date3;
    private Long smssentcount;

    private List<sentsmsview> filteredsms;
    List<sentsmsview> lista1 = null;

    HttpSession session1 = getsession.getSession();
    String user = (String) session1.getAttribute("username");

    @PostConstruct
    public void init() {
        sentview = new sentsmsview();
    }
 public Long getSmssentcount() {
        return smssentcount;
    }
public void setSmssentcount(Long smssentcount) {
        this.smssentcount = smssentcount;
    }
public List<sentsmsview> getSntsms() {
        return sntsms;
    }
 public void setSntsms(List<sentsmsview> sntsms) {
        this.sntsms = sntsms;
    }
    public List<sentsmsview> getFilteredsms() {
        return filteredsms;
    }

    public void setFilteredsms(List<sentsmsview> filteredsms) {
        this.filteredsms = filteredsms;
    }

    public sentsmsview getSentview() {
        return sentview;
    }

    public void setSentview(sentsmsview sentview) {
        this.sentview = sentview;
    }
    public Date getSdate() {
        return sdate;
    }
    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }
    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }
    public void getsentsmsdetail() {
        smssentcount = 0l;
        if (this.getSdate() == null || this.getEdate() == null) {
            lista1 = null;
        }
        String sdate1 = (dateFormat0.format(sdate));
        String edate1 = (dateFormat0.format(edate));
        
        String sdate_shedule=(outFormat.format(sdate));
          String edate_shedule=(outFormat.format(edate));
        
        sdate1 = sdate1 + "000001";
        edate1 = edate1 + "235959";
        
        
        
        
        String hql0 = "from sentsmsview m where m.user1=:user and ( (m.time_submitted  between '" + sdate1 + "' and '" + edate1 + "')or(m.time_submitted  between '" + sdate_shedule + "' and '" + edate_shedule + "') ) order by m.id desc ";
Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista1 = session.createQuery(hql0)
                    .setParameter("user", user)
                    .list();
            session.getTransaction().commit();             
            if (lista1 != null) {

                for (Iterator iterator = lista1.iterator(); iterator.hasNext();) {
                    sentsmsview employee = (sentsmsview) iterator.next();

                    employee.setSmscount(getSmsCount(employee.getMessage_payload()));
                    
                    smssentcount = getSmsCount(employee.getMessage_payload()) + smssentcount;
                    String stato = employee.getStatus();
                    if (stato.equalsIgnoreCase("0")) {
                        employee.setStatus("To Be Sent");
                    } else if (stato.equalsIgnoreCase("1")) {

                        employee.setStatus("Submitted To Network");
                    } else if (stato.equalsIgnoreCase("2")) {

                        employee.setStatus("Network");
                    } else if (stato.equalsIgnoreCase("3")) {

                        employee.setStatus("Network");
                    } else if (stato.equalsIgnoreCase("4")) {

                        employee.setStatus("Delivered");
                    } else if (stato.equalsIgnoreCase("5")) {

                        employee.setStatus("Network");
                    } else if (stato.equalsIgnoreCase("6")) {

                        employee.setStatus("Undelivered");
                    } else if (stato.equalsIgnoreCase("7")) {

                        employee.setStatus("Expired");
                    } else if (stato.equalsIgnoreCase("8")) {

                        employee.setStatus("Submit Failed");
                    } else if (stato.equalsIgnoreCase("9")) {

                        employee.setStatus("Network");
                    } else if (stato.equalsIgnoreCase("9")) {

                        employee.setStatus("OptedOut");
                    } else if (stato.equalsIgnoreCase("11")) {

                        employee.setStatus("Scheduled");
                    }

                    try {
                        date2 = inFormat2.parse(employee.getTime_processed());
                        date3 = inFormat2.parse(employee.getTime_submitted());

                        employee.setTime_processed(outFormat.format(date2));
                        employee.setTime_submitted(outFormat.format(date3));
                    } catch (ParseException ex) {
                        employee.setTime_processed(employee.getTime_processed());
                        employee.setTime_submitted(employee.getTime_submitted());
                    }

                }

            }

        } catch (Exception k) {
            k.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<sentsmsview> outputvalue() {
        values = lista1;
        return values;
    }

    public String nameExcel() {
        String name = "";
        String dateString;
        String dateString2;

        if (sdate != null) {
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
            dateString = sdfr.format(sdate);
            dateString2 = sdfr.format(edate);
            String name1 = "Sentsms_from";
            String range = "to";
            name = name1 + dateString + range + dateString2;
        } else {
        }
        return name;
    }

    
     public String nameExcel2() {
        String name = "";
        String dateString;
Date date = new Date();
        if (sdate != null) {
            SimpleDateFormat sdfr = new SimpleDateFormat("dd:MMM:yyyy");
            dateString = sdfr.format(date);
            String name1 = user;
            name = name1 + dateString ;
        } else {
        }
        return name;
    }
    public void onRowselect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Sent Sms ", ((sentsmsview) event.getObject()).getMessage_payload());
        FacesContext.getCurrentInstance().addMessage(null, msg);
//        sentsmsview editeduser = (sentsmsview) event.getObject();
    }

    public void reset() {
        values = null;
        lista1 = null;
        smssentcount = 0l;
    }

    private static long getSmsCount(String msg) {
        long ret = 0;
        if (msg == null || msg.length() <= 160) {
            return 1;
        }
        ret = msg.length() / 134;
        if (msg.length() % 134 > 0) {
            ret++;
        }
        return ret;
    }

}
