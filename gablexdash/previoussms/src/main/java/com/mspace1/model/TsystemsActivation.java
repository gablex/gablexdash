package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TsystemsActivation generated by hbm2java
 */
@Entity
@Table(name="tSystemsActivation"
    ,catalog="dbSMS"
)
public class TsystemsActivation  implements java.io.Serializable {


     private String hostname;
     private boolean activated;
     private Date activatedDate;

    public TsystemsActivation() {
    }

	
    public TsystemsActivation(String hostname, boolean activated) {
        this.hostname = hostname;
        this.activated = activated;
    }
    public TsystemsActivation(String hostname, boolean activated, Date activatedDate) {
       this.hostname = hostname;
       this.activated = activated;
       this.activatedDate = activatedDate;
    }
   
     @Id 

    
    @Column(name="hostname", unique=true, nullable=false, length=100)
    public String getHostname() {
        return this.hostname;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    
    @Column(name="activated", nullable=false)
    public boolean isActivated() {
        return this.activated;
    }
    
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="activatedDate", length=19)
    public Date getActivatedDate() {
        return this.activatedDate;
    }
    
    public void setActivatedDate(Date activatedDate) {
        this.activatedDate = activatedDate;
    }




}

