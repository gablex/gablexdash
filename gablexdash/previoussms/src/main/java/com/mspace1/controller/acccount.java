/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;
 
import com.mspace1.implementer.tusersmsinimpl;
import com.mspace1.implementer.updater;
import com.mspace1.util.getsession;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mspace
 */

@ManagedBean(name = "acccount")
@ViewScoped
public class acccount implements Serializable{
private String username;
private String password;
private Date startdate;
private Date endate;
private Long maxdaily;
private Long maxweekly;
private Long maxmonthly;
private Long maxtotal;
private String agent;
private Long senttoday;
private Long  sentweek;
private Long sentmonth;
private  Long senttotal;
 private String newpass;

  HttpSession sessionm = getsession.getSession();
       updater k = new updater();
    public acccount() {
    try {
        k.updat();
    } catch (Exception ex) {
        Logger.getLogger(acccount.class.getName()).log(Level.SEVERE, null, ex);
    }
        username=(String) sessionm.getAttribute("username");
        password=(String) sessionm.getAttribute("pass");
        startdate=(Date)sessionm.getAttribute("startd");
        endate=(Date)sessionm.getAttribute("endd");
        maxdaily=(long) sessionm.getAttribute("max_daily");
        maxweekly=(long) sessionm.getAttribute("max_weekly");
           maxmonthly = (long) sessionm.getAttribute("max_monthly");
         maxtotal = (long) sessionm.getAttribute("max_total");
         agent=(String) sessionm.getAttribute("agent");
         senttoday=(long) sessionm.getAttribute("counttoday");
        sentweek=(long) sessionm.getAttribute("countweek");
        sentmonth=(long) sessionm.getAttribute("countmonth");
        senttotal=(long) sessionm.getAttribute("counttotoal");
   
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEndate() {
        return endate;
    }

    public void setEndate(Date endate) {
        this.endate = endate;
    }

    public Long getMaxdaily() {
        return maxdaily;
    }

    public void setMaxdaily(Long maxdaily) {
        this.maxdaily = maxdaily;
    }

    public Long getMaxweekly() {
        return maxweekly;
    }

    public void setMaxweekly(Long maxweekly) {
        this.maxweekly = maxweekly;
    }

    public Long getMaxtotal() {
        return maxtotal;
    }

    public void setMaxtotal(Long maxtotal) {
        this.maxtotal = maxtotal;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Long getSenttoday() {
        return senttoday;
    }

    public void setSenttoday(Long senttoday) {
        this.senttoday = senttoday;
    }

    public Long getSentweek() {
        return sentweek;
    }

    public void setSentweek(Long sentweek) {
        this.sentweek = sentweek;
    }

    public Long getSentmonth() {
        return sentmonth;
    }

    public void setSentmonth(Long sentmonth) {
        this.sentmonth = sentmonth;
    }

    public Long getSenttotal() {
        return senttotal;
    }

    public void setSenttotal(Long senttotal) {
        this.senttotal = senttotal;
    }

    public HttpSession getSessionm() {
        return sessionm;
    }

    public void setSessionm(HttpSession sessionm) {
        this.sessionm = sessionm;
    }

    public Long getMaxmonthly() {
        return maxmonthly;
    }

    public void setMaxmonthly(Long maxmonthly) {
        this.maxmonthly = maxmonthly;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }
//
//public  void changepassword(){
//    System.out.println(newpass+ " newpaaaaaaaaaaaaaaaaaaaaaaaasword");
//    tusersmsinimpl k=new tusersmsinimpl();
//k.updatepass(newpass);
//}
    
    
        public void changepassword(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean changed = false;
         
        if(newpass != null ) {
             
    tusersmsinimpl m=new tusersmsinimpl();
      m.updatepass(newpass);
      password=newpass;
            try {
            
            } catch (Exception ex) {
                Logger.getLogger(acccount.class.getName()).log(Level.SEVERE, null, ex);
            }
            changed = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome password changed to", newpass);
        } else {
            changed = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "passsword did not change", "retry");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", changed);
    }
    
    public void showdialog(){
    RequestContext context = RequestContext.getCurrentInstance();
context.execute("PF('myDialogVar').show();");
    }
   
   
    
    
}
