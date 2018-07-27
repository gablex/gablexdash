/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.implementer.tusersmsinimpl;
import com.mspace1.model.Tusersmsin;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author mspace
 */

@ManagedBean(name = "tusersmsincontroller")
@ViewScoped
public class tusersmsincontroller implements Serializable{
    private Tusersmsin tusersmin;
      private DataModel listusers;
      private Date sdate;
     private Date edate;
     DateFormat dateFormat0 = new SimpleDateFormat("yyyyMMdd");
       DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
         DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             private List<Tusersmsin> filteredsmsin;
 @PostConstruct
    public void init() {
        tusersmin = new Tusersmsin();
    }

    public List<Tusersmsin> getFilteredsmsin() {
        return filteredsmsin;
    }

    public void setFilteredsmsin(List<Tusersmsin> filteredsmsin) {
        this.filteredsmsin = filteredsmsin;
    }

    public Tusersmsin getTusersmin() {
        return tusersmin;
    }

    public void setTusersmin(Tusersmsin tusersmin) {
        this.tusersmin = tusersmin;
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
    

   public DataModel getsmsinlist(){ 
        if(this.getSdate() == null || this.getEdate()== null)
   {
       return null;
   }
       String sdate1 = (dateFormat0.format(sdate));
       String edate1 =(dateFormat0.format(edate));
       sdate1 = sdate1+"000001";
       edate1 = edate1+"235959";
       try{
     List<Tusersmsin>lista=new tusersmsinimpl().list(sdate1, edate1);
     listusers=new ListDataModel(lista);
       }catch(Exception m){
       System.out.println("no list Found");
       return null;
       }
     return listusers;
     }
    
   
   
}
