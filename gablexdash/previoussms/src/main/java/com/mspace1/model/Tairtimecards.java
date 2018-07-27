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
import javax.persistence.UniqueConstraint;

/**
 * Tairtimecards generated by hbm2java
 */
@Entity
@Table(name="tAIRTIMECARDS"
    ,catalog="dbSMS"
    , uniqueConstraints = {@UniqueConstraint(columnNames={"SerialNo", "NetID"}), @UniqueConstraint(columnNames={"PinHash", "NetID"})} 
)
public class Tairtimecards  implements java.io.Serializable {


     private Integer id;
     private String serialNo;
     private String pinNo;
     private String pinHash;
     private int amount;
     private Date expiryDate;
     private int netId;
     private Date entryDate;
     private char consumed;
     private Date consumptionDate;
     private char enroute;
     private String memberNo;
     private String issuer;
     private Date lastModified;

    public Tairtimecards() {
    }

	
    public Tairtimecards(String serialNo, String pinNo, String pinHash, int amount, Date expiryDate, int netId, char consumed, char enroute, Date lastModified) {
        this.serialNo = serialNo;
        this.pinNo = pinNo;
        this.pinHash = pinHash;
        this.amount = amount;
        this.expiryDate = expiryDate;
        this.netId = netId;
        this.consumed = consumed;
        this.enroute = enroute;
        this.lastModified = lastModified;
    }
    public Tairtimecards(String serialNo, String pinNo, String pinHash, int amount, Date expiryDate, int netId, Date entryDate, char consumed, Date consumptionDate, char enroute, String memberNo, String issuer, Date lastModified) {
       this.serialNo = serialNo;
       this.pinNo = pinNo;
       this.pinHash = pinHash;
       this.amount = amount;
       this.expiryDate = expiryDate;
       this.netId = netId;
       this.entryDate = entryDate;
       this.consumed = consumed;
       this.consumptionDate = consumptionDate;
       this.enroute = enroute;
       this.memberNo = memberNo;
       this.issuer = issuer;
       this.lastModified = lastModified;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="SerialNo", nullable=false, length=20)
    public String getSerialNo() {
        return this.serialNo;
    }
    
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    
    @Column(name="PinNo", nullable=false)
    public String getPinNo() {
        return this.pinNo;
    }
    
    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    
    @Column(name="PinHash", nullable=false)
    public String getPinHash() {
        return this.pinHash;
    }
    
    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }

    
    @Column(name="Amount", nullable=false)
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="ExpiryDate", nullable=false, length=10)
    public Date getExpiryDate() {
        return this.expiryDate;
    }
    
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    
    @Column(name="NetID", nullable=false)
    public int getNetId() {
        return this.netId;
    }
    
    public void setNetId(int netId) {
        this.netId = netId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EntryDate", length=19)
    public Date getEntryDate() {
        return this.entryDate;
    }
    
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    
    @Column(name="Consumed", nullable=false, length=1)
    public char getConsumed() {
        return this.consumed;
    }
    
    public void setConsumed(char consumed) {
        this.consumed = consumed;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ConsumptionDate", length=19)
    public Date getConsumptionDate() {
        return this.consumptionDate;
    }
    
    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    
    @Column(name="Enroute", nullable=false, length=1)
    public char getEnroute() {
        return this.enroute;
    }
    
    public void setEnroute(char enroute) {
        this.enroute = enroute;
    }

    
    @Column(name="MemberNo", length=10)
    public String getMemberNo() {
        return this.memberNo;
    }
    
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    
    @Column(name="Issuer", length=45)
    public String getIssuer() {
        return this.issuer;
    }
    
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LastModified", nullable=false, length=19)
    public Date getLastModified() {
        return this.lastModified;
    }
    
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }




}


