/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mspace
 */
public class getsession {
    public static HttpSession getSession(){
        return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
    }
    public static HttpServletRequest getrequest(){
    return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
}
//  <p:chart type="bar"  id="updat1" model="#{ChartView.animatedModel2}"  style="width:520px; height: 310px" />
