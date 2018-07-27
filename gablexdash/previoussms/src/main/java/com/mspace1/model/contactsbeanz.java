/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author kevol
 */
public class contactsbeanz implements Serializable{
    private String contactname ;
    private String contactNo;

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.contactNo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final contactsbeanz other = (contactsbeanz) obj;
        if (!Objects.equals(this.contactNo, other.contactNo)) {
            return false;
        }
        return true;
    }
    
    
    
}
