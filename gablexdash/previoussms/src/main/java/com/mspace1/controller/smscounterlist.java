/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.model.smscounter;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "smscounterlist")
@SessionScoped
public class smscounterlist implements Serializable {
private long sentsuccessfully;
private long notsentsuccessfully;
smscounter cnt = new smscounter();
    /**
     * Creates a new instance of smscounterlist
     */
    public smscounterlist() {
        
sentsuccessfully= cnt.getSentsuccefully();
notsentsuccessfully= cnt.getNotsent();
    }
       
    
    
    public long getSentsuccessfully() {
        return sentsuccessfully;
    }

    public void setSentsuccessfully(long sentsuccessfully) {
        this.sentsuccessfully = sentsuccessfully;
    }

    public long getNotsentsuccessfully() {
        return notsentsuccessfully;
    }

    public void setNotsentsuccessfully(long notsentsuccessfully) {
        this.notsentsuccessfully = notsentsuccessfully;
    }
    
    
    
}
