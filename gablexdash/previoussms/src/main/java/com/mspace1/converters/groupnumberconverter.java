/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.converters;

import com.mspace1.model.group_contact;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kevol
 */

@FacesConverter(forClass = group_contact.class, value = "groupnumberconverter")
public class groupnumberconverter  implements Converter {

    /**
     * Creates a new instance of groupnumberconverter
     */
  
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        group_contact country1 = new group_contact();
        try {
            country1.setNumberz(value);
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return country1;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value2) {
   if (value2 instanceof group_contact) {
        group_contact c = (group_contact) value2;
        String kmz = null;
        try {
            kmz = c.getNumberz();
        } catch (Exception kl) {
            kl.printStackTrace();
        }
        return kmz;
   }else{
   
       return "";
   }
    }
    
}
