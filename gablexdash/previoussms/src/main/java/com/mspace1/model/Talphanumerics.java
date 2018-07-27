package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Talphanumerics generated by hbm2java
 */
@Entity
@Table(name="tAlphanumerics"
    ,catalog="dbSMS"
)
public class Talphanumerics  implements java.io.Serializable {


     private Integer id;
     private String alphanumeric;
     private Integer idDefault;

    public Talphanumerics() {
    }

	
    public Talphanumerics(String alphanumeric) {
        this.alphanumeric = alphanumeric;
    }
    public Talphanumerics(String alphanumeric, Integer idDefault) {
       this.alphanumeric = alphanumeric;
       this.idDefault = idDefault;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="alphanumeric", nullable=false, length=12)
    public String getAlphanumeric() {
        return this.alphanumeric;
    }
    
    public void setAlphanumeric(String alphanumeric) {
        this.alphanumeric = alphanumeric;
    }

    
    @Column(name="idDefault")
    public Integer getIdDefault() {
        return this.idDefault;
    }
    
    public void setIdDefault(Integer idDefault) {
        this.idDefault = idDefault;
    }




}

