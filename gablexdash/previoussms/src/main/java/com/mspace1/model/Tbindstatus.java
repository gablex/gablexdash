package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tbindstatus generated by hbm2java
 */
@Entity
@Table(name="tBINDSTATUS"
    ,catalog="dbSMS"
)
public class Tbindstatus  implements java.io.Serializable {


     private TbindstatusId id;
     private Date lastupdate;
     private Date started;

    public Tbindstatus() {
    }

	
    public Tbindstatus(TbindstatusId id, Date lastupdate) {
        this.id = id;
        this.lastupdate = lastupdate;
    }
    public Tbindstatus(TbindstatusId id, Date lastupdate, Date started) {
       this.id = id;
       this.lastupdate = lastupdate;
       this.started = started;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="bindName", column=@Column(name="bind_name", nullable=false, length=100) ), 
        @AttributeOverride(name="bindType", column=@Column(name="bind_type", nullable=false, length=100) ) } )
    public TbindstatusId getId() {
        return this.id;
    }
    
    public void setId(TbindstatusId id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastupdate", nullable=false, length=19)
    public Date getLastupdate() {
        return this.lastupdate;
    }
    
    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="started", length=19)
    public Date getStarted() {
        return this.started;
    }
    
    public void setStarted(Date started) {
        this.started = started;
    }




}


