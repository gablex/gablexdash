/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevol
 */@ManagedBean(name="graphvalue")
@SessionScoped
public class graphvalue {
    private int to_be_sent;
    private int submitted_to_network;
    private int succesfully_sent;
    private int delivered;
    private int network;
    private int undelivered;
    private int expired;
    private int submit_failed;
    private int scheduled;
    private int network2;
        private int OptedOut;
       private int network3;
    
    public graphvalue() {
    }

    public int getNetwork3() {
        return network3;
    }

    public void setNetwork3(int network3) {
        this.network3 = network3;
    }

    public int getOptedOut() {
        return OptedOut;
    }

    public void setOptedOut(int OptedOut) {
        this.OptedOut = OptedOut;
    }

    public int getNetwork2() {
        return network2;
    }

    public void setNetwork2(int network2) {
        this.network2 = network2;
    }

    public int getTo_be_sent() {
        return to_be_sent;
    }

    public void setTo_be_sent(int to_be_sent) {
        this.to_be_sent = to_be_sent;
    }

    public int getSubmitted_to_network() {
        return submitted_to_network;
    }

    public void setSubmitted_to_network(int submitted_to_network) {
        this.submitted_to_network = submitted_to_network;
    }

    public int getSuccesfully_sent() {
        return succesfully_sent;
    }

    public void setSuccesfully_sent(int succesfully_sent) {
        this.succesfully_sent = succesfully_sent;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public int getSubmit_failed() {
        return submit_failed;
    }

    public void setSubmit_failed(int submit_failed) {
        this.submit_failed = submit_failed;
    }

    public int getScheduled() {
        return scheduled;
    }

    public void setScheduled(int scheduled) {
        this.scheduled = scheduled;
    }

    public int getDelivered() {
        return delivered;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    public int getUndelivered() {
        return undelivered;
    }

    public void setUndelivered(int undelivered) {
        this.undelivered = undelivered;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    
}
