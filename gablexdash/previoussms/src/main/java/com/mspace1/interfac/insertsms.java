/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.interfac;

import com.mspace1.model.smsstatust;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mspace
 */
public interface insertsms {

     public List<smsstatust> getsmsstatuslist(String Message, String from, String to, Date time);
}
