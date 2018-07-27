/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author mspace
 */
public class toptsms {
        
private int id;
private int tsmsoutid;
private String time_submitted;
private String source_addr;
private String destination_addr;
private String message;

    public toptsms() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTsmsoutid() {
        return tsmsoutid;
    }

    public void setTsmsoutid(int tsmsoutid) {
        this.tsmsoutid = tsmsoutid;
    }

    public String getTime_submitted() {
        return time_submitted;
    }

    public void setTime_submitted(String time_submitted) {
        this.time_submitted = time_submitted;
    }

   

    public String getSource_addr() {
        return source_addr;
    }

    public void setSource_addr(String source_addr) {
        this.source_addr = source_addr;
    }

    public String getDestination_addr() {
        return destination_addr;
    }

    public void setDestination_addr(String destination_addr) {
        this.destination_addr = destination_addr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
