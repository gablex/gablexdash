package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Tstatsin generated by hbm2java
 */
@Entity
@Table(name="tSTATSIN"
    ,catalog="dbSMS"
)
public class Tstatsin  implements java.io.Serializable {


     private TstatsinId id;
     private long total;
     private long safaricom;
     private long cellc;
     private long celltel;
     private long other;

    public Tstatsin() {
    }

    public Tstatsin(TstatsinId id, long total, long safaricom, long cellc, long celltel, long other) {
       this.id = id;
       this.total = total;
       this.safaricom = safaricom;
       this.cellc = cellc;
       this.celltel = celltel;
       this.other = other;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="dbdate", column=@Column(name="dbdate", nullable=false, length=10) ), 
        @AttributeOverride(name="sourceAddr", column=@Column(name="source_addr", nullable=false, length=20) ) } )
    public TstatsinId getId() {
        return this.id;
    }
    
    public void setId(TstatsinId id) {
        this.id = id;
    }

    
    @Column(name="total", nullable=false)
    public long getTotal() {
        return this.total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }

    
    @Column(name="safaricom", nullable=false)
    public long getSafaricom() {
        return this.safaricom;
    }
    
    public void setSafaricom(long safaricom) {
        this.safaricom = safaricom;
    }

    
    @Column(name="cellc", nullable=false)
    public long getCellc() {
        return this.cellc;
    }
    
    public void setCellc(long cellc) {
        this.cellc = cellc;
    }

    
    @Column(name="celltel", nullable=false)
    public long getCelltel() {
        return this.celltel;
    }
    
    public void setCelltel(long celltel) {
        this.celltel = celltel;
    }

    
    @Column(name="other", nullable=false)
    public long getOther() {
        return this.other;
    }
    
    public void setOther(long other) {
        this.other = other;
    }




}


