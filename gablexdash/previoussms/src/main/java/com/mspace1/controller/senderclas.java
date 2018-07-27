/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.event.AjaxBehaviorEvent;


/**
 *
 * @author mspace
 */

@ManagedBean(name = "senderclas")
@SessionScoped
public class senderclas {
    
@ManagedProperty(value="#{tuseraddressbook}")
private tuseraddressbook test;
private String numbers;

@PostConstruct
    public void init() {
       test = new tuseraddressbook();
       }

    public tuseraddressbook getTest() {
        return test;
    }

    public void setTest(tuseraddressbook test) {
        this.test = test;
    }
    
//    public void getvalues(){
////    AjaxBehaviorEvent event = null;
//    numbers =new tuseraddressbook().selectednumbers();
//    String names2 =new tuseraddressbook().selectednames();
//
//     
//     } 
//    
    
}
