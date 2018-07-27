package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TsmsTransactions generated by hbm2java
 */
@Entity
@Table(name="tSMS_TRANSACTIONS"
    ,catalog="dbSMS"
)
public class TsmsTransactions  implements java.io.Serializable {


     private Long id;
     private String mobile;
     private String msg;
     private Date receiveTime;
     private String name;
     private String policies;
     private String reply;
     private String shortCode;
     private Date replyTime;
     private String rule;

    public TsmsTransactions() {
    }

	
    public TsmsTransactions(String mobile, String msg, Date receiveTime, String reply, String shortCode, Date replyTime, String rule) {
        this.mobile = mobile;
        this.msg = msg;
        this.receiveTime = receiveTime;
        this.reply = reply;
        this.shortCode = shortCode;
        this.replyTime = replyTime;
        this.rule = rule;
    }
    public TsmsTransactions(String mobile, String msg, Date receiveTime, String name, String policies, String reply, String shortCode, Date replyTime, String rule) {
       this.mobile = mobile;
       this.msg = msg;
       this.receiveTime = receiveTime;
       this.name = name;
       this.policies = policies;
       this.reply = reply;
       this.shortCode = shortCode;
       this.replyTime = replyTime;
       this.rule = rule;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="mobile", nullable=false, length=100)
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
    @Column(name="msg", nullable=false, length=65535)
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="receiveTime", nullable=false, length=19)
    public Date getReceiveTime() {
        return this.receiveTime;
    }
    
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    
    @Column(name="name", length=250)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="policies", length=65535)
    public String getPolicies() {
        return this.policies;
    }
    
    public void setPolicies(String policies) {
        this.policies = policies;
    }

    
    @Column(name="reply", nullable=false, length=65535)
    public String getReply() {
        return this.reply;
    }
    
    public void setReply(String reply) {
        this.reply = reply;
    }

    
    @Column(name="short_code", nullable=false, length=100)
    public String getShortCode() {
        return this.shortCode;
    }
    
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="replyTime", nullable=false, length=19)
    public Date getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    
    @Column(name="rule", nullable=false, length=100)
    public String getRule() {
        return this.rule;
    }
    
    public void setRule(String rule) {
        this.rule = rule;
    }




}


