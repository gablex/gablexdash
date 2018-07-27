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
 * Tuserstats generated by hbm2java
 */
@Entity
@Table(name="tUSERSTATS"
    ,catalog="dbSMS"
)
public class Tuserstats  implements java.io.Serializable {


     private Long id;
     private long userid;
     private Date day;
     private long receivedToday;
     private long sendToday;

    public Tuserstats() {
    }

    public Tuserstats(long userid, Date day, long receivedToday, long sendToday) {
       this.userid = userid;
       this.day = day;
       this.receivedToday = receivedToday;
       this.sendToday = sendToday;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    
    @Column(name="userid", nullable=false)
    public long getUserid() {
        return this.userid;
    }
    
    public void setUserid(long userid) {
        this.userid = userid;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="day", nullable=false, length=19)
    public Date getDay() {
        return this.day;
    }
    
    public void setDay(Date day) {
        this.day = day;
    }

    
    @Column(name="received_today", nullable=false)
    public long getReceivedToday() {
        return this.receivedToday;
    }
    
    public void setReceivedToday(long receivedToday) {
        this.receivedToday = receivedToday;
    }

    
    @Column(name="send_today", nullable=false)
    public long getSendToday() {
        return this.sendToday;
    }
    
    public void setSendToday(long sendToday) {
        this.sendToday = sendToday;
    }




}


