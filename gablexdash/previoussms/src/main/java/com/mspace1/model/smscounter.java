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
public class smscounter implements Serializable{
    private long sentsuccefully;
    private long notsent;

    public long getSentsuccefully() {
        return sentsuccefully;
    }

    public void setSentsuccefully(long sentsuccefully) {
        this.sentsuccefully = sentsuccefully;
    }

    public long getNotsent() {
        return notsent;
    }

    public void setNotsent(long notsent) {
        this.notsent = notsent;
    }

   
    
}
