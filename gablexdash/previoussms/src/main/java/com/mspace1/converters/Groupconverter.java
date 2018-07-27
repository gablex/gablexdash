/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.converters;

import com.mspace1.model.Groups_bean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author mspace
 */
@FacesConverter(forClass = Groups_bean.class, value = "groupconverter")
public class Groupconverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        Groups_bean country = new Groups_bean();
        try {
            country.setGrpnamez(value);
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return country;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value2) {
   if (value2 instanceof Groups_bean) {
        Groups_bean c = (Groups_bean) value2;
        String km = null;
        try {
            km = c.getGrpnamez();
        } catch (Exception kl) {
            kl.printStackTrace();
        }
        return km;
   }else{
   
       return "";
   }
    }

}
