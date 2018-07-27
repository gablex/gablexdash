/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.implementer.tuserimplementor;
import com.mspace1.interfac.tuserinterface;
import com.mspace1.model.Tuser;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author kevol
 */
@ManagedBean(name = "tuserdaoaccess")
@SessionScoped

public class tuserdaoaccess implements Serializable{

  private Tuser user;
  private DataModel listusers;

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
    
    public String adduser() {
      tuserinterface dao = new tuserimplementor();
        dao.saveuser(user);
        return "user updated";
    }
    
    public DataModel getuserlist(){ 
     List<Tuser>lista=new tuserimplementor().list();
     listusers=new ListDataModel(lista);
     return listusers;
     
     }
    
      public String prepareadduser(){
     user=new Tuser();
      return"adduser";
      }
      
    public String preparealteruser(){
     user=(Tuser)listusers.getRowData();
     return "adduser";
     }
    
     public String delete(){
     Tuser usertemp=(Tuser)(listusers.getRowData());
     tuserinterface dao= new tuserimplementor();
     dao.remove(usertemp);
     return"deleted";
     } 
     
     public String update(){
   tuserinterface dao= new tuserimplementor();
   dao.update(user);
   return"index";
   }
}
