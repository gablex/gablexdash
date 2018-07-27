/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.implementer.getsender;
import com.mspace1.model.TallowedAlphanumerics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author mspace
 */

@ManagedBean(name = "senderaddressource")
@SessionScoped
public class senderaddressource implements Serializable{
 private TallowedAlphanumerics tallowedAlphanumerics;
   List<TallowedAlphanumerics>lista = new ArrayList<>();
 
  private DataModel listusers;
     @PostConstruct
    public void init() {
        tallowedAlphanumerics= new TallowedAlphanumerics();
     }

    public List<TallowedAlphanumerics> getLista() {
        return lista;
    }

    public void setLista(List<TallowedAlphanumerics> lista) {
        this.lista = lista;
    }

    public TallowedAlphanumerics getTallowedAlphanumerics() {
        return tallowedAlphanumerics;
    }

    public void setTallowedAlphanumerics(TallowedAlphanumerics tallowedAlphanumerics) {
        this.tallowedAlphanumerics = tallowedAlphanumerics;
    }

    public DataModel getListusers() {
        return listusers;
    }

    public void setListusers(DataModel listusers) {
        this.listusers = listusers;
    }
    public  void getuserlist(){ 
       lista=new getsender().getsendername();   
       
       
     }
    
    public  List<TallowedAlphanumerics> getuserlist2(){ 
         return lista;
    }
    
    public  List<TallowedAlphanumerics> getuserlist3(){ 
         lista=new getsender().getsendername();   
         return lista;
    }
}
