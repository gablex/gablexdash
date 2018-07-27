/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.converters;

import com.mspace1.model.contactsbeanz;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kevol
 */
@FacesConverter(forClass = contactsbeanz.class, value = "contactname_numberconverter")
public class contactname_numberconverter implements  Converter{

    /**
     * Creates a new instance of contactname_numberconverter
     */
 
      @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        contactsbeanz country = new contactsbeanz();
        try {
            country.setContactNo(value);
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value2) {
   if (value2 instanceof contactsbeanz) {
        contactsbeanz c = (contactsbeanz) value2;
        String km = null;
        try {
            km = c.getContactNo();
        } catch (Exception kl) {
            kl.printStackTrace();
        }
        return km;
   }else{
   
       return "";
   }
}
}