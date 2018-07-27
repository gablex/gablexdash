/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevol.web;

import com.mspace1.controller.tusercontroller;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "Loginapi")
@RequestScoped
public class Loginapi {
tusercontroller logina = new tusercontroller();
    /**
     * Creates a new instance of Loginapi
     */
    public Loginapi() {
    }
        public void getlogindetail() {
            
                Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String username = params.get("uname");
        String passwd = params.get("passwd");      
        
       logina.login2(username, passwd);
  
        }
}
