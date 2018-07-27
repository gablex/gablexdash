package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Temailstatus generated by hbm2java
 */
@Entity
@Table(name="tEMAILSTATUS"
    ,catalog="dbSMS"
)
public class Temailstatus  implements java.io.Serializable {


     private String id;
     private String description;

    public Temailstatus() {
    }

    public Temailstatus(String id, String description) {
       this.id = id;
       this.description = description;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false, length=100)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="description", nullable=false, length=100)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


