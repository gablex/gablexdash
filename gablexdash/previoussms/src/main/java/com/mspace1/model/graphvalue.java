/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mspace
 */
@Entity
@Table(name = "tSMSOUT")
public class graphvalue  implements Serializable{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private int id;
@Column(name="tuser_id")
private int tuser_id;
@Column(name="changed")
private int changed;
@Column(name="status")
private int status;
@Column(name="time_submitted")
private String time_submitted;
@Column(name="time_processed")
private String time_processed;
@Column(name="sentby")
private String sentby;
@Column(name="bind_name")
private String bind_name;
@Column(name="errorinfo")
private String errorinfo;
@Column(name="message_id")
private String message_id;
@Column(name="source_addr")
private String source_addr;
@Column(name="destination_addr")
private String destination_addr;
@Column(name="short_message")
private String short_message;
@Column(name="pdu_body")
private String pdu_body;
@Column(name="registered_delivery")
private int registered_delivery;

    public graphvalue() {
    }

    public graphvalue(int id, int tuser_id, int changed, int status, String time_submitted, String time_processed, String sentby, String bind_name, String errorinfo, String message_id, String source_addr, String destination_addr, String short_message, String pdu_body, int registered_delivery) {
        this.id = id;
        this.tuser_id = tuser_id;
        this.changed = changed;
        this.status = status;
        this.time_submitted = time_submitted;
        this.time_processed = time_processed;
        this.sentby = sentby;
        this.bind_name = bind_name;
        this.errorinfo = errorinfo;
        this.message_id = message_id;
        this.source_addr = source_addr;
        this.destination_addr = destination_addr;
        this.short_message = short_message;
        this.pdu_body = pdu_body;
        this.registered_delivery = registered_delivery;
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



}
