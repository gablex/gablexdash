package com.mspace1.model;
// Generated Nov 3, 2016 10:30:32 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TbindstatusId generated by hbm2java
 */
@Embeddable
public class TbindstatusId  implements java.io.Serializable {


     private String bindName;
     private String bindType;

    public TbindstatusId() {
    }

    public TbindstatusId(String bindName, String bindType) {
       this.bindName = bindName;
       this.bindType = bindType;
    }
   


    @Column(name="bind_name", nullable=false, length=100)
    public String getBindName() {
        return this.bindName;
    }
    
    public void setBindName(String bindName) {
        this.bindName = bindName;
    }


    @Column(name="bind_type", nullable=false, length=100)
    public String getBindType() {
        return this.bindType;
    }
    
    public void setBindType(String bindType) {
        this.bindType = bindType;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TbindstatusId) ) return false;
		 TbindstatusId castOther = ( TbindstatusId ) other; 
         
		 return ( (this.getBindName()==castOther.getBindName()) || ( this.getBindName()!=null && castOther.getBindName()!=null && this.getBindName().equals(castOther.getBindName()) ) )
 && ( (this.getBindType()==castOther.getBindType()) || ( this.getBindType()!=null && castOther.getBindType()!=null && this.getBindType().equals(castOther.getBindType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBindName() == null ? 0 : this.getBindName().hashCode() );
         result = 37 * result + ( getBindType() == null ? 0 : this.getBindType().hashCode() );
         return result;
   }   


}

