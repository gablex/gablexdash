/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.implementer.tuserimplementor;
import com.mspace1.interfac.tuserinterface;
import com.mspace1.model.Tuser;
import com.mspace1.util.getsession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "tusercontroller")
@ViewScoped
public class tusercontroller implements Serializable {
 HttpSession session1 = getsession.getSession();
    private Tuser user;
    FacesMessage message;
    logo_client km =new logo_client();
    /**
     * Creates a;; new instance of tusercontroller
     */
    @PostConstruct
    public void init() {
        user = new Tuser();
   
    }
  

    public Tuser getUser() {
        return user;
    }

    public void setUser(Tuser user) {
        this.user = user;
    }

    public String login() {
  
        tuserinterface dao;
        Tuser result = null;

   String user1="";
          
        dao = new tuserimplementor();
        try {
      HttpSession session = getsession.getSession();
            result = dao.getUser(user.getUsername(), user.getPassword());
            if (result.getUsername() != null) {           
                user1=result.getUsername();
                session.setAttribute("username", user1);
                session.setAttribute("accountype", result.getAgent());
                session.setAttribute("admin", result.getAdmin());
                session.setAttribute("id", result.getId());
                session.setAttribute("agent", result.getAgent());
                session.setAttribute("max_total", result.getMaxTotal());
                session.setAttribute("max_daily", result.getMaxDaily());
                session.setAttribute("max_weekly", result.getMaxWeekly());
                session.setAttribute("max_monthly", result.getMaxMonthly());
                session.setAttribute("counttoday", result.getSmsCountToday());
                session.setAttribute("countweek", result.getSmsCountWeek());
                session.setAttribute("countmonth", result.getSmsCountMonth());
                session.setAttribute("counttotoal", result.getSmsCountTotal());
            } else {
                return "home.jsf";
            }
 
        } catch (Exception nullk) {
//        nullk.printStackTrace();
            System.out.println("Caused by: java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)");
          
               return("home.jsf");
       
        } 
   
    System.out.println(user1+ " logged in at "+ new Date());
        return "sms.jsf";
    }
    
    
    
    
    
    
    public void login2(String username ,String password) {
        km.clnt_logo();
  
        tuserinterface dao;     
        Tuser result = null;

   String user1="";                
          
        dao = new tuserimplementor();
        try {
      HttpSession session = getsession.getSession();
            result = dao.getUser(username, password);
            if (result.getUsername() != null) {           
                user1=result.getUsername();
                session.setAttribute("username", user1);
                session.setAttribute("accountype", result.getAgent());
                session.setAttribute("admin", result.getAdmin());
                session.setAttribute("id", result.getId());
                session.setAttribute("agent", result.getAgent());
                session.setAttribute("max_total", result.getMaxTotal());
                session.setAttribute("max_daily", result.getMaxDaily());
                session.setAttribute("max_weekly", result.getMaxWeekly());
                session.setAttribute("max_monthly", result.getMaxMonthly());
                session.setAttribute("counttoday", result.getSmsCountToday());
                session.setAttribute("countweek", result.getSmsCountWeek());
                session.setAttribute("countmonth", result.getSmsCountMonth());
                session.setAttribute("counttotoal", result.getSmsCountTotal());
                
                //login redirection 
                System.out.println(user1+ " logged in at "+ new Date());
                
     
    
              FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            response.sendRedirect("./sms.jsf");
//        return "sms.jsf";
        } catch (IOException ex) {
            System.out.println("redirection error");
        }  
        
                
            } else {
                try{
          ExternalContext context1 = FacesContext.getCurrentInstance().getExternalContext();
            context1.redirect("http://smsgateway.mspace.co.ke/sms/");
          
        } catch (IOException ex) {
         Logger.getLogger(tusercontroller.class.getName()).log(Level.SEVERE, null, ex);            
        } 
//                return "home.jsf";
            }
 
        } catch (Exception nullk) {
            System.out.println(nullk.getMessage());

       
        } 
   
    
//return null;


    }
    
    
    
    
    
    
    
    
    
    
    
    
    

    public void logout() { 
        try { 
            try {
                tuserinterface dao = new tuserimplementor();
                dao.logindetailupdate();
                HttpSession session = getsession.getSession();
                session.invalidate();
            } catch (Exception k) {            
                System.out.println("logout error");
            }
            ExternalContext context1 = FacesContext.getCurrentInstance().getExternalContext();
            context1.redirect("http://smsgateway.mspace.co.ke/sms/");
          
        } catch (IOException ex) {
         Logger.getLogger(tusercontroller.class.getName()).log(Level.SEVERE, null, ex);            
        }         
    }    

      
    
   
}  
