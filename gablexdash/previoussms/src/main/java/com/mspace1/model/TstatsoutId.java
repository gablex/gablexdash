package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TstatsoutId generated by hbm2java
 */
@Embeddable
public class TstatsoutId  implements java.io.Serializable {


     private Date dbdate;
     private String user;
     private String rule;
     private String message;

    public TstatsoutId() {
    }

    public TstatsoutId(Date dbdate, String user, String rule, String message) {
       this.dbdate = dbdate;
       this.user = user;
       this.rule = rule;
       this.message = message;
    }
   


    @Column(name="dbdate", nullable=false, length=10)
    public Date getDbdate() {
        return this.dbdate;
    }
    
    public void setDbdate(Date dbdate) {
        this.dbdate = dbdate;
    }


    @Column(name="user", nullable=false, length=100)
    public String getUser() {
        return this.user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }


    @Column(name="rule", nullable=false, length=100)
    public String getRule() {
        return this.rule;
    }
    
    public void setRule(String rule) {
        this.rule = rule;
    }


    @Column(name="message", nullable=false, length=65535)
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TstatsoutId) ) return false;
		 TstatsoutId castOther = ( TstatsoutId ) other; 
         
		 return ( (this.getDbdate()==castOther.getDbdate()) || ( this.getDbdate()!=null && castOther.getDbdate()!=null && this.getDbdate().equals(castOther.getDbdate()) ) )
 && ( (this.getUser()==castOther.getUser()) || ( this.getUser()!=null && castOther.getUser()!=null && this.getUser().equals(castOther.getUser()) ) )
 && ( (this.getRule()==castOther.getRule()) || ( this.getRule()!=null && castOther.getRule()!=null && this.getRule().equals(castOther.getRule()) ) )
 && ( (this.getMessage()==castOther.getMessage()) || ( this.getMessage()!=null && castOther.getMessage()!=null && this.getMessage().equals(castOther.getMessage()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDbdate() == null ? 0 : this.getDbdate().hashCode() );
         result = 37 * result + ( getUser() == null ? 0 : this.getUser().hashCode() );
         result = 37 * result + ( getRule() == null ? 0 : this.getRule().hashCode() );
         result = 37 * result + ( getMessage() == null ? 0 : this.getMessage().hashCode() );
         return result;
   }   


}

