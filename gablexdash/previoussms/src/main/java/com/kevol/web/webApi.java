/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevol.web;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "webApi")
@SessionScoped
public class webApi implements Serializable {

    webapimethods webapimpl = new webapimethods();

    private String reply;

    /**
     * Creates a new instance of webApi
     */
 

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getstatus() {
//        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String username = params.get("uname");
        String passwd = params.get("passwd");
        String dest = params.get("dest");
        String source = params.get("source");
        String messsage = params.get("msg");
        System.out.println("using Web Api ............");
        System.out.println("Destination address is : " + dest + " Username : " + username + "  Source address: " + source + " Password:  " + passwd + "\n "
                + " Message is :" + messsage);        
           int valid = 0 ;
        if(username!=null && passwd !=null && dest!=null){
    
 
    valid = webapimpl.validate(username, passwd, dest, source, messsage);
     if (valid == 0) {
            reply = webapimpl.sendSmsm(username, passwd, dest, source, messsage);
            System.out.println("Send status is : " + reply);
        } else {
            reply = String.valueOf(valid);
        }
    
    
    
   }else{
            reply="7";
          return reply ;
        
        }
       

        return reply;
    }
}
