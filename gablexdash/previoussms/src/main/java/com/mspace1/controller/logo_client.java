/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.implementer.client_logo;
import com.mspace1.util.getsession;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
/**
 *
 * @author mspace
 */

@ManagedBean(name = "logo_client")
@SessionScoped
public class logo_client implements Serializable {
 
private String logopath;
   private String a;


 

   
   
    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
    /**
     * Creates a new instance of logo_client
     */

    /**
     * Creates a new instance of logo_client
     * @return
     */
     
     
    public String clnt_logo(){ 
        HttpSession session= getsession.getSession();
        client_logo clnt = new client_logo();
        
        if(logopath != null && !logopath.isEmpty()) { 
   System.out.println(" ");
        }else{
            logopath= clnt.clnt_logo();
     
        }
        
         if(logopath.startsWith("../"))
     {
         
         a = logopath.substring(3);
     }else{
         a=logopath;
         }
                
        session.setAttribute("logopath", a);
        
    return a;
    }
    
    public int current_yr(){    
    Calendar now = Calendar.getInstance();   // Gets the current date and time
int year = now.get(Calendar.YEAR);
return year;
    }
    
    
}  
