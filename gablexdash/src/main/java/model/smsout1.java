/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author mspace
 */
public class smsout1 implements Serializable{
    
private int id;
private int tuser_id;
private int changed;
private int status;
private String time_submitted;
private String time_processed;
private String sentby;
private String bind_name;
private String errorinfo;
private String message_id;
private String source_addr;
private String destination_addr;
private String short_message;
private String pdu_body;
private int registered_delivery;
private String status2;

    /**
     * Creates a new instance of smsout
     */
    public smsout1() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTuser_id() {
        return tuser_id;
    }

    public void setTuser_id(int tuser_id) {
        this.tuser_id = tuser_id;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime_submitted() {
        return time_submitted;
    }

    public void setTime_submitted(String time_submitted) {
        this.time_submitted = time_submitted;
    }

    public String getTime_processed() {
        return time_processed;
    }

    public void setTime_processed(String time_processed) {
        this.time_processed = time_processed;
    }

    public String getSentby() {
        return sentby;
    }

    public void setSentby(String sentby) {
        this.sentby = sentby;
    }

    public String getBind_name() {
        return bind_name;
    }

    public void setBind_name(String bind_name) {
        this.bind_name = bind_name;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
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

    public String getShort_message() {
        return short_message;
    }

    public void setShort_message(String short_message) {
        this.short_message = short_message;
    }

    public String getPdu_body() {
        return pdu_body;
    }

    public void setPdu_body(String pdu_body) {
        this.pdu_body = pdu_body;
    }

    public int getRegistered_delivery() {
        return registered_delivery;
    }

    public void setRegistered_delivery(int registered_delivery) {
        this.registered_delivery = registered_delivery;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }
    
    
}


