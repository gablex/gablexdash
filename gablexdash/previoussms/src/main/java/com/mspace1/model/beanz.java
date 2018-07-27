/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.model;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author kevol
 */
@ManagedBean(name = "beanz")
@SessionScoped
public class beanz implements Serializable {
private String item= "";
    /**
     * Creates a new instance of beanz
     */
    public beanz() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
}
