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
 * Tschedules generated by hbm2java
 */
@Entity
@Table(name="tSchedules"
    ,catalog="dbSMS"
)
public class Tschedules  implements java.io.Serializable {


     private Integer id;
     private String errorEmail;
     private String scheduleName;
     private int connectionToUse;
     private String message;
     private Date scheduleCreationTime;
     private Date scheduleRunTime;
     private String sqlStatement;
     private String mobileColumn;
     private int frequency;
     private int frequencyId;
     private String alphanumeric;

    public Tschedules() {
    }

	
    public Tschedules(String scheduleName, int connectionToUse, String message, Date scheduleCreationTime, String sqlStatement, String mobileColumn, int frequency, int frequencyId) {
        this.scheduleName = scheduleName;
        this.connectionToUse = connectionToUse;
        this.message = message;
        this.scheduleCreationTime = scheduleCreationTime;
        this.sqlStatement = sqlStatement;
        this.mobileColumn = mobileColumn;
        this.frequency = frequency;
        this.frequencyId = frequencyId;
    }
    public Tschedules(String errorEmail, String scheduleName, int connectionToUse, String message, Date scheduleCreationTime, Date scheduleRunTime, String sqlStatement, String mobileColumn, int frequency, int frequencyId, String alphanumeric) {
       this.errorEmail = errorEmail;
       this.scheduleName = scheduleName;
       this.connectionToUse = connectionToUse;
       this.message = message;
       this.scheduleCreationTime = scheduleCreationTime;
       this.scheduleRunTime = scheduleRunTime;
       this.sqlStatement = sqlStatement;
       this.mobileColumn = mobileColumn;
       this.frequency = frequency;
       this.frequencyId = frequencyId;
       this.alphanumeric = alphanumeric;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="errorEmail", length=100)
    public String getErrorEmail() {
        return this.errorEmail;
    }
    
    public void setErrorEmail(String errorEmail) {
        this.errorEmail = errorEmail;
    }

    
    @Column(name="scheduleName", nullable=false, length=100)
    public String getScheduleName() {
        return this.scheduleName;
    }
    
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    
    @Column(name="connectionToUse", nullable=false)
    public int getConnectionToUse() {
        return this.connectionToUse;
    }
    
    public void setConnectionToUse(int connectionToUse) {
        this.connectionToUse = connectionToUse;
    }

    
    @Column(name="message", nullable=false, length=1000)
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="scheduleCreationTime", nullable=false, length=19)
    public Date getScheduleCreationTime() {
        return this.scheduleCreationTime;
    }
    
    public void setScheduleCreationTime(Date scheduleCreationTime) {
        this.scheduleCreationTime = scheduleCreationTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="scheduleRunTime", length=19)
    public Date getScheduleRunTime() {
        return this.scheduleRunTime;
    }
    
    public void setScheduleRunTime(Date scheduleRunTime) {
        this.scheduleRunTime = scheduleRunTime;
    }

    
    @Column(name="sqlStatement", nullable=false, length=500)
    public String getSqlStatement() {
        return this.sqlStatement;
    }
    
    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    
    @Column(name="mobileColumn", nullable=false, length=20)
    public String getMobileColumn() {
        return this.mobileColumn;
    }
    
    public void setMobileColumn(String mobileColumn) {
        this.mobileColumn = mobileColumn;
    }

    
    @Column(name="frequency", nullable=false)
    public int getFrequency() {
        return this.frequency;
    }
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    
    @Column(name="frequencyId", nullable=false)
    public int getFrequencyId() {
        return this.frequencyId;
    }
    
    public void setFrequencyId(int frequencyId) {
        this.frequencyId = frequencyId;
    }

    
    @Column(name="alphanumeric", length=45)
    public String getAlphanumeric() {
        return this.alphanumeric;
    }
    
    public void setAlphanumeric(String alphanumeric) {
        this.alphanumeric = alphanumeric;
    }




}


