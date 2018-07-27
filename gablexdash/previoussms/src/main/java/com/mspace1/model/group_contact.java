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
public class group_contact implements Serializable{
    private String group_name;
    private String numberz;
    private long groupname_count;

    public long getGroupname_count() {
        return groupname_count;
    }

    public void setGroupname_count(long groupname_count) {
        this.groupname_count = groupname_count;
    }

    
    
    
    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getNumberz() {
        return numberz;
    }

    public void setNumberz(String numberz) {
        this.numberz = numberz;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.numberz);
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
        final group_contact other = (group_contact) obj;
        if (!Objects.equals(this.numberz, other.numberz)) {
            return false;
        }
        return true;
    }
    
    
    
}
