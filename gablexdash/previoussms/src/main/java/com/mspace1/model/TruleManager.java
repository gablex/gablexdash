package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TruleManager generated by hbm2java
 */
@Entity
@Table(name="tRuleManager"
    ,catalog="dbSMS"
)
public class TruleManager  implements java.io.Serializable {


     private Integer id;
     private String autoplay;
     private String keyword;
     private String sms;
     private String taskType;
     private String taskUser;
     private String alertsEmail;
     private String emailEscalations;
     private String smsEmail;
     private String smtpEmail;
     private String campaignName;
     private Integer executionOrder;
     private String errorsEmail;
     private String alphanumeric;
     private String shortCode;
     private Integer active;
     private String dbconnection;
     private String samplemobile;
     private String samplesms;
     private String replysms;
     private String createdBy;
     private String messageCategory;
     private Integer daysToEscallationExpiry;

    public TruleManager() {
    }

    public TruleManager(String autoplay, String keyword, String sms, String taskType, String taskUser, String alertsEmail, String emailEscalations, String smsEmail, String smtpEmail, String campaignName, Integer executionOrder, String errorsEmail, String alphanumeric, String shortCode, Integer active, String dbconnection, String samplemobile, String samplesms, String replysms, String createdBy, String messageCategory, Integer daysToEscallationExpiry) {
       this.autoplay = autoplay;
       this.keyword = keyword;
       this.sms = sms;
       this.taskType = taskType;
       this.taskUser = taskUser;
       this.alertsEmail = alertsEmail;
       this.emailEscalations = emailEscalations;
       this.smsEmail = smsEmail;
       this.smtpEmail = smtpEmail;
       this.campaignName = campaignName;
       this.executionOrder = executionOrder;
       this.errorsEmail = errorsEmail;
       this.alphanumeric = alphanumeric;
       this.shortCode = shortCode;
       this.active = active;
       this.dbconnection = dbconnection;
       this.samplemobile = samplemobile;
       this.samplesms = samplesms;
       this.replysms = replysms;
       this.createdBy = createdBy;
       this.messageCategory = messageCategory;
       this.daysToEscallationExpiry = daysToEscallationExpiry;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="autoplay", length=45)
    public String getAutoplay() {
        return this.autoplay;
    }
    
    public void setAutoplay(String autoplay) {
        this.autoplay = autoplay;
    }

    
    @Column(name="keyword", length=45)
    public String getKeyword() {
        return this.keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    
    @Column(name="sms", length=300)
    public String getSms() {
        return this.sms;
    }
    
    public void setSms(String sms) {
        this.sms = sms;
    }

    
    @Column(name="taskType", length=45)
    public String getTaskType() {
        return this.taskType;
    }
    
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    
    @Column(name="taskUser", length=100)
    public String getTaskUser() {
        return this.taskUser;
    }
    
    public void setTaskUser(String taskUser) {
        this.taskUser = taskUser;
    }

    
    @Column(name="alertsEmail", length=45)
    public String getAlertsEmail() {
        return this.alertsEmail;
    }
    
    public void setAlertsEmail(String alertsEmail) {
        this.alertsEmail = alertsEmail;
    }

    
    @Column(name="emailEscalations", length=10)
    public String getEmailEscalations() {
        return this.emailEscalations;
    }
    
    public void setEmailEscalations(String emailEscalations) {
        this.emailEscalations = emailEscalations;
    }

    
    @Column(name="smsEmail", length=45)
    public String getSmsEmail() {
        return this.smsEmail;
    }
    
    public void setSmsEmail(String smsEmail) {
        this.smsEmail = smsEmail;
    }

    
    @Column(name="smtpEmail", length=45)
    public String getSmtpEmail() {
        return this.smtpEmail;
    }
    
    public void setSmtpEmail(String smtpEmail) {
        this.smtpEmail = smtpEmail;
    }

    
    @Column(name="campaignName", length=100)
    public String getCampaignName() {
        return this.campaignName;
    }
    
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    
    @Column(name="executionOrder")
    public Integer getExecutionOrder() {
        return this.executionOrder;
    }
    
    public void setExecutionOrder(Integer executionOrder) {
        this.executionOrder = executionOrder;
    }

    
    @Column(name="errorsEmail", length=45)
    public String getErrorsEmail() {
        return this.errorsEmail;
    }
    
    public void setErrorsEmail(String errorsEmail) {
        this.errorsEmail = errorsEmail;
    }

    
    @Column(name="alphanumeric", length=45)
    public String getAlphanumeric() {
        return this.alphanumeric;
    }
    
    public void setAlphanumeric(String alphanumeric) {
        this.alphanumeric = alphanumeric;
    }

    
    @Column(name="shortCode", length=20)
    public String getShortCode() {
        return this.shortCode;
    }
    
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    
    @Column(name="active")
    public Integer getActive() {
        return this.active;
    }
    
    public void setActive(Integer active) {
        this.active = active;
    }

    
    @Column(name="dbconnection", length=50)
    public String getDbconnection() {
        return this.dbconnection;
    }
    
    public void setDbconnection(String dbconnection) {
        this.dbconnection = dbconnection;
    }

    
    @Column(name="samplemobile", length=45)
    public String getSamplemobile() {
        return this.samplemobile;
    }
    
    public void setSamplemobile(String samplemobile) {
        this.samplemobile = samplemobile;
    }

    
    @Column(name="samplesms", length=150)
    public String getSamplesms() {
        return this.samplesms;
    }
    
    public void setSamplesms(String samplesms) {
        this.samplesms = samplesms;
    }

    
    @Column(name="replysms", length=65535)
    public String getReplysms() {
        return this.replysms;
    }
    
    public void setReplysms(String replysms) {
        this.replysms = replysms;
    }

    
    @Column(name="createdBy", length=100)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    
    @Column(name="messageCategory", length=100)
    public String getMessageCategory() {
        return this.messageCategory;
    }
    
    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    
    @Column(name="daysToEscallationExpiry")
    public Integer getDaysToEscallationExpiry() {
        return this.daysToEscallationExpiry;
    }
    
    public void setDaysToEscallationExpiry(Integer daysToEscallationExpiry) {
        this.daysToEscallationExpiry = daysToEscallationExpiry;
    }




}


