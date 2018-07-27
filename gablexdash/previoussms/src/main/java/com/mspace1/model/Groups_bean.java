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
 * @author mspace
 */
public class Groups_bean implements Serializable{
    private long group_count;
        private String  grpnamez;          
     public long getGroup_count() {
        return group_count;
    }

    public void setGroup_count(long group_count) {
        this.group_count = group_count;
    }

    public String getGrpnamez() {
        return grpnamez;
    }

    public void setGrpnamez(String grpnamez) {
        this.grpnamez = grpnamez;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.grpnamez);
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
        final Groups_bean other = (Groups_bean) obj;
        if (!Objects.equals(this.grpnamez, other.grpnamez)) {
            return false;
        }
        return true;
    }
    
        
        
}
