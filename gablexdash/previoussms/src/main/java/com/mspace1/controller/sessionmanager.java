/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.util.getsession;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mspace
 */

@ManagedBean(name = "sessionmanager")
@ViewScoped
public class sessionmanager implements Serializable{

 long remainigsms;
String company_logopath;
    public long getRemainigsms() {     
        return remainigsms;
    }

    public void setRemainigsms(long remainigsms) {
        this.remainigsms = remainigsms;
    }

    public String getCompany_logopath() {
        return company_logopath;
    }

    public void setCompany_logopath(String company_logopath) {
        this.company_logopath = company_logopath;
    }

    public sessionmanager() {
    }
    
    public String getsessionname(){
             HttpSession session = getsession.getSession();
        String m="Welcome ";
   String username= m+(String)session.getAttribute("username");
    remainigsms= (long)session.getAttribute("max_total");
   return username;
    }
        public String getLogoPath(){
                 HttpSession session = getsession.getSession();
      company_logopath= (String)session.getAttribute("logopath");

            return company_logopath;
}  
}