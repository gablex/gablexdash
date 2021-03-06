package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tsmsin generated by hbm2java
 */
@Entity
@Table(name="tSMSIN"
    ,catalog="dbSMS"
)
public class Tsmsin  implements java.io.Serializable {


     private Long id;
     private String shortMessage;
     private String data;
     private String sourceAddr;
     private String destinationAddr;
     private String esmClass;
     private String timereceived;

    public Tsmsin() {
    }

	
    public Tsmsin(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }
    public Tsmsin(String shortMessage, String data, String sourceAddr, String destinationAddr, String esmClass, String timereceived) {
       this.shortMessage = shortMessage;
       this.data = data;
       this.sourceAddr = sourceAddr;
       this.destinationAddr = destinationAddr;
       this.esmClass = esmClass;
       this.timereceived = timereceived;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="short_message", length=65535)
    public String getShortMessage() {
        return this.shortMessage;
    }
    
    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    
    @Column(name="data", length=65535)
    public String getData() {
        return this.data;
    }
    
    public void setData(String data) {
        this.data = data;
    }

    
    @Column(name="source_addr", nullable=false, length=20)
    public String getSourceAddr() {
        return this.sourceAddr;
    }
    
    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    
    @Column(name="destination_addr", length=15)
    public String getDestinationAddr() {
        return this.destinationAddr;
    }
    
    public void setDestinationAddr(String destinationAddr) {
        this.destinationAddr = destinationAddr;
    }

    
    @Column(name="esm_class", length=4)
    public String getEsmClass() {
        return this.esmClass;
    }
    
    public void setEsmClass(String esmClass) {
        this.esmClass = esmClass;
    }

    
    @Column(name="timereceived", length=14)
    public String getTimereceived() {
        return this.timereceived;
    }
    
    public void setTimereceived(String timereceived) {
        this.timereceived = timereceived;
    }




}


