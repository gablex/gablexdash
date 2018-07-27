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
import javax.persistence.Transient;

/**
 *
 * @author mspace
 */
@Entity
@Table(name = "sentsms")
public class sentsmsview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user1")
    private String user1;
    @Column(name = "Status")
    private String Status;
    @Column(name = "destination_addr")
    private String destination_addr;
      @Column(name = "message_payload")
    private String message_payload;
    @Column(name = "source_addr")
    private String source_addr;
  
    @Column(name = "time_submitted")
    private String time_submitted;
    @Column(name = "time_processed")
    private String time_processed;

    @Transient
    private Long smscount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getDestination_addr() {
        return destination_addr;
    }

    public void setDestination_addr(String destination_addr) {
        this.destination_addr = destination_addr;
    }

    public String getMessage_payload() {
        return message_payload;
    }

    public void setMessage_payload(String message_payload) {
        this.message_payload = message_payload;
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

    public String getSource_addr() {
        return source_addr;
    }

    public void setSource_addr(String source_addr) {
        this.source_addr = source_addr;
    }

    public Long getSmscount() {
        return smscount;
    }

    public void setSmscount(Long smscount) {
        this.smscount = smscount;
    }

}
