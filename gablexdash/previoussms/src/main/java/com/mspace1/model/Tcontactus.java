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
 * Tcontactus generated by hbm2java
 */
@Entity
@Table(name="tCONTACTUS"
    ,catalog="dbSMS"
)
public class Tcontactus  implements java.io.Serializable {


     private Integer id;
     private String fname;
     private String lname;
     private String mobile;
     private String email;
     private String subject;
     private String msg;
     private boolean status;
     private Date dbDate;

    public Tcontactus() {
    }

    public Tcontactus(String fname, String lname, String mobile, String email, String subject, String msg, boolean status, Date dbDate) {
       this.fname = fname;
       this.lname = lname;
       this.mobile = mobile;
       this.email = email;
       this.subject = subject;
       this.msg = msg;
       this.status = status;
       this.dbDate = dbDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="fname", nullable=false, length=100)
    public String getFname() {
        return this.fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }

    
    @Column(name="lname", nullable=false, length=100)
    public String getLname() {
        return this.lname;
    }
    
    public void setLname(String lname) {
        this.lname = lname;
    }

    
    @Column(name="mobile", nullable=false, length=100)
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
    @Column(name="email", nullable=false, length=200)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="subject", nullable=false, length=100)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    
    @Column(name="msg", nullable=false, length=65535)
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    @Column(name="status", nullable=false)
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dbDate", nullable=false, length=19)
    public Date getDbDate() {
        return this.dbDate;
    }
    
    public void setDbDate(Date dbDate) {
        this.dbDate = dbDate;
    }




}

