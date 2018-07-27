/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.navigationcontroller;

import com.mspace1.implementer.updater;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mspace
 */@ManagedBean(name="facePainter")
@SessionScoped
public class FacePainter implements Serializable{
    private String mainContent = "sms/sms.xhtml";

	public String getMainContent() {
         
		return mainContent;
	}

	public void setMainContent(String mainContent) {
                updater k = new updater();
        try {
            k.getSmsBalance();
        } catch (Exception ex) {
            System.out.println("getting balance error");
        }
		this.mainContent = mainContent;
	}

}