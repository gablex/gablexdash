package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tconnection generated by hbm2java
 */
@Entity
@Table(name="tCONNECTION"
    ,catalog="dbSMS"
)
public class Tconnection  implements java.io.Serializable {


     private Integer id;
     private String type;
     private String host;
     private String systemId;
     private String password;
     private String systemType;
     private String port;
     private String addrRange;
     private String sourceAddr;
     private String ton;
     private String npi;
     private Boolean active;
     private String name;
     private String owner;
     private long smscount;

    public Tconnection() {
    }

	
    public Tconnection(String type, String host, String systemId, String password, String systemType, String port, String addrRange, String sourceAddr, String ton, String npi, long smscount) {
        this.type = type;
        this.host = host;
        this.systemId = systemId;
        this.password = password;
        this.systemType = systemType;
        this.port = port;
        this.addrRange = addrRange;
        this.sourceAddr = sourceAddr;
        this.ton = ton;
        this.npi = npi;
        this.smscount = smscount;
    }
    public Tconnection(String type, String host, String systemId, String password, String systemType, String port, String addrRange, String sourceAddr, String ton, String npi, Boolean active, String name, String owner, long smscount) {
       this.type = type;
       this.host = host;
       this.systemId = systemId;
       this.password = password;
       this.systemType = systemType;
       this.port = port;
       this.addrRange = addrRange;
       this.sourceAddr = sourceAddr;
       this.ton = ton;
       this.npi = npi;
       this.active = active;
       this.name = name;
       this.owner = owner;
       this.smscount = smscount;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="type", nullable=false, length=4)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Column(name="host", nullable=false, length=100)
    public String getHost() {
        return this.host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }

    
    @Column(name="system_id", nullable=false, length=100)
    public String getSystemId() {
        return this.systemId;
    }
    
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    
    @Column(name="password", nullable=false, length=100)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="system_type", nullable=false, length=100)
    public String getSystemType() {
        return this.systemType;
    }
    
    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    
    @Column(name="port", nullable=false, length=100)
    public String getPort() {
        return this.port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }

    
    @Column(name="addr_range", nullable=false, length=100)
    public String getAddrRange() {
        return this.addrRange;
    }
    
    public void setAddrRange(String addrRange) {
        this.addrRange = addrRange;
    }

    
    @Column(name="source_addr", nullable=false, length=100)
    public String getSourceAddr() {
        return this.sourceAddr;
    }
    
    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    
    @Column(name="ton", nullable=false, length=100)
    public String getTon() {
        return this.ton;
    }
    
    public void setTon(String ton) {
        this.ton = ton;
    }

    
    @Column(name="npi", nullable=false, length=100)
    public String getNpi() {
        return this.npi;
    }
    
    public void setNpi(String npi) {
        this.npi = npi;
    }

    
    @Column(name="active")
    public Boolean getActive() {
        return this.active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }

    
    @Column(name="name", length=35)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="owner", length=100)
    public String getOwner() {
        return this.owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }

    
    @Column(name="smscount", nullable=false)
    public long getSmscount() {
        return this.smscount;
    }
    
    public void setSmscount(long smscount) {
        this.smscount = smscount;
    }




}

