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
 * Totalsms generated by hbm2java
 */
@Entity
@Table(name="totalsms"
    ,catalog="dbSMS"
)
public class Totalsms  implements java.io.Serializable {


     private Integer id;
     private int smsTotal;
     private Date date;
     private Date rundate;

    public Totalsms() {
    }

    public Totalsms(int smsTotal, Date date, Date rundate) {
       this.smsTotal = smsTotal;
       this.date = date;
       this.rundate = rundate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="smsTotal", nullable=false)
    public int getSmsTotal() {
        return this.smsTotal;
    }
    
    public void setSmsTotal(int smsTotal) {
        this.smsTotal = smsTotal;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date", nullable=false, length=10)
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="rundate", nullable=false, length=10)
    public Date getRundate() {
        return this.rundate;
    }
    
    public void setRundate(Date rundate) {
        this.rundate = rundate;
    }




}

