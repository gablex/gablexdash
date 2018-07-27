/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.model;

import java.io.Serializable;

/**
 *
 * @author mspace
 */
public class smsstatust implements Serializable{
    private String  destinatin;

    public smsstatust(String destinatin, String smsstatuz) {
        this.destinatin = destinatin;
        this.smsstatuz = smsstatuz;
    }


    public smsstatust() {
    }
    public String getDestinatin() {
        return destinatin;
    }

    public void setDestinatin(String destinatin) {
        this.destinatin = destinatin;
    }

    public String getSmsstatuz() {
        return smsstatuz;
    }

    public void setSmsstatuz(String smsstatuz) {
        this.smsstatuz = smsstatuz;
    }
    private String smsstatuz;


    
    
    
    
    
}
