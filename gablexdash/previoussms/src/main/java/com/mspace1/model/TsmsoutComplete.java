package com.mspace1.model;
// Generated Nov 10, 2016 9:55:58 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TsmsoutComplete generated by hbm2java
 */
@Entity
@Table(name="tSMSOUT_COMPLETE"
    ,catalog="dbSMS"
)
public class TsmsoutComplete  implements java.io.Serializable {


     private Long id;
     private Long rid;
     private String seqNo;
     private String serviceType;
     private String sourceAddr;
     private String destinationAddr;
     private String messagePayload;
     private String userMessageReference;
     private String timeSubmitted;
     private String timeProcessed;
     private char status;
     private String errorinfo;
     private String messageId;
     private String sentby;
     private int esmClass;
     private String name;
     private String policies;
     private String rule;
     private String user;
     private String submittedby;

    public TsmsoutComplete() {
    }

	
    public TsmsoutComplete(String serviceType, String sourceAddr, String destinationAddr, String messagePayload, String userMessageReference, String timeSubmitted, String timeProcessed, char status, String errorinfo, String messageId, String sentby, int esmClass) {
        this.serviceType = serviceType;
        this.sourceAddr = sourceAddr;
        this.destinationAddr = destinationAddr;
        this.messagePayload = messagePayload;
        this.userMessageReference = userMessageReference;
        this.timeSubmitted = timeSubmitted;
        this.timeProcessed = timeProcessed;
        this.status = status;
        this.errorinfo = errorinfo;
        this.messageId = messageId;
        this.sentby = sentby;
        this.esmClass = esmClass;
    }
    public TsmsoutComplete(Long rid, String seqNo, String serviceType, String sourceAddr, String destinationAddr, String messagePayload, String userMessageReference, String timeSubmitted, String timeProcessed, char status, String errorinfo, String messageId, String sentby, int esmClass, String name, String policies, String rule, String user, String submittedby) {
       this.rid = rid;
       this.seqNo = seqNo;
       this.serviceType = serviceType;
       this.sourceAddr = sourceAddr;
       this.destinationAddr = destinationAddr;
       this.messagePayload = messagePayload;
       this.userMessageReference = userMessageReference;
       this.timeSubmitted = timeSubmitted;
       this.timeProcessed = timeProcessed;
       this.status = status;
       this.errorinfo = errorinfo;
       this.messageId = messageId;
       this.sentby = sentby;
       this.esmClass = esmClass;
       this.name = name;
       this.policies = policies;
       this.rule = rule;
       this.user = user;
       this.submittedby = submittedby;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="rid")
    public Long getRid() {
        return this.rid;
    }
    
    public void setRid(Long rid) {
        this.rid = rid;
    }

    
    @Column(name="seqNo", length=100)
    public String getSeqNo() {
        return this.seqNo;
    }
    
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    
    @Column(name="service_type", nullable=false, length=5)
    public String getServiceType() {
        return this.serviceType;
    }
    
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    
    @Column(name="source_addr", nullable=false, length=15)
    public String getSourceAddr() {
        return this.sourceAddr;
    }
    
    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    
    @Column(name="destination_addr", nullable=false, length=15)
    public String getDestinationAddr() {
        return this.destinationAddr;
    }
    
    public void setDestinationAddr(String destinationAddr) {
        this.destinationAddr = destinationAddr;
    }

    
    @Column(name="message_payload", nullable=false, length=65535)
    public String getMessagePayload() {
        return this.messagePayload;
    }
    
    public void setMessagePayload(String messagePayload) {
        this.messagePayload = messagePayload;
    }

    
    @Column(name="user_message_reference", nullable=false, length=2)
    public String getUserMessageReference() {
        return this.userMessageReference;
    }
    
    public void setUserMessageReference(String userMessageReference) {
        this.userMessageReference = userMessageReference;
    }

    
    @Column(name="time_submitted", nullable=false, length=14)
    public String getTimeSubmitted() {
        return this.timeSubmitted;
    }
    
    public void setTimeSubmitted(String timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    
    @Column(name="time_processed", nullable=false, length=14)
    public String getTimeProcessed() {
        return this.timeProcessed;
    }
    
    public void setTimeProcessed(String timeProcessed) {
        this.timeProcessed = timeProcessed;
    }

    
    @Column(name="status", nullable=false, length=1)
    public char getStatus() {
        return this.status;
    }
    
    public void setStatus(char status) {
        this.status = status;
    }

    
    @Column(name="errorinfo", nullable=false, length=100)
    public String getErrorinfo() {
        return this.errorinfo;
    }
    
    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo;
    }

    
    @Column(name="message_id", nullable=false, length=50)
    public String getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    
    @Column(name="sentby", nullable=false, length=50)
    public String getSentby() {
        return this.sentby;
    }
    
    public void setSentby(String sentby) {
        this.sentby = sentby;
    }

    
    @Column(name="esm_class", nullable=false)
    public int getEsmClass() {
        return this.esmClass;
    }
    
    public void setEsmClass(int esmClass) {
        this.esmClass = esmClass;
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

    
    @Column(name="rule", length=100)
    public String getRule() {
        return this.rule;
    }
    
    public void setRule(String rule) {
        this.rule = rule;
    }

    
    @Column(name="user", length=100)
    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }

    
    @Column(name="submittedby", length=50)
    public String getSubmittedby() {
        return this.submittedby;
    }
    
    public void setSubmittedby(String submittedby) {
        this.submittedby = submittedby;
    }




}


