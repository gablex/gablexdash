/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.navigationcontroller;


import com.mspace1.controller.Exceluploader;
import com.mspace1.controller.tuseraddressbook;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mspace
 */
@ManagedBean(name="userInfo")
@SessionScoped
public class UserInfo implements Serializable{
	private tuseraddressbook user;
	@ManagedProperty(value="#{facePainter}")
	public FacePainter facePainter;
	
	public FacePainter getFacePainter() {
		return facePainter;
	}

    public tuseraddressbook getUser() {
        return user;
    }

    public void setUser(tuseraddressbook user) {
        this.user = user;
    }

	public void setFacePainter(FacePainter facePainter) {
		this.facePainter = facePainter;
	}

	public void smshome(){
		facePainter.setMainContent("sms/sms.xhtml");
	}
	
	public void newsms() {
                    
		facePainter.setMainContent("sms/newsms.xhtml");
	}

	public void newsmsgroup() {                   
		facePainter.setMainContent("sms/newgroupsms.xhtml");
	}
        public void uploadexcel() {
         		facePainter.setMainContent("sms/excelupld.xhtml");
	}
         public void upload_addressExcel() {
		facePainter.setMainContent("sms/addressupload.xhtml");
	}
        public void smsreceived() {
		facePainter.setMainContent("sms/smsin.xhtml");
	}
        public void sentsms() {
		facePainter.setMainContent("sms/sentsms.xhtml");
	}
        public void userprofile() {
		facePainter.setMainContent("sms/account.xhtml");
	}
        public void addressbook() {
            
		facePainter.setMainContent("sms/addressbook.xhtml");
	}
        public void task() {
		facePainter.setMainContent("sms/task.xhtml");
	}
        public void email() {
            
		facePainter.setMainContent("sms/email.xhtml");
	}
        //invisible
        public void addcontact(){
		facePainter.setMainContent("sms/addcontacts.xhtml");
	}
        public void addnos(){
		facePainter.setMainContent("sms/addno.xhtml");
	}
        public void viewuploaded(){
		facePainter.setMainContent("sms/viewuploaded.xhtml");
	}
      
      
}
