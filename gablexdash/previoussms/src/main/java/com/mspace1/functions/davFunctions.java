/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mspace
 */
public class davFunctions {

    public String executeShellCommand(String com) {
        String ret = "";
        String[] command = com.split(" ");
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(command);
            InputStream is = proc.getInputStream();
            int returnMe = proc.waitFor();
            InputStreamReader inr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(inr);
            String line = null;
            while ((line = br.readLine()) != null) {
                ret = ret + line;
            }
            closeStreams(proc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public void closeStreams(Process p) {
        try {
            p.getInputStream().close();
            p.getOutputStream().close();
            p.getErrorStream().close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 public String[] getDistinctSms(String[] destination)
  {
    String[] returnMe = null;
    if(destination.length > 0)
    {
      try {
        Set set = new HashSet();
        for (int rahab = 0; rahab < destination.length; rahab++) {
          destination[rahab] = destination[rahab].replaceAll(" ","");
          destination[rahab] = destination[rahab].trim();
          if(destination[rahab].length() < 3)
          {
            continue;
          }
          set.add(destination[rahab]);
        }
        returnMe = new String[set.size()];
        set.toArray(returnMe);
        System.out.println("\nSmssender.SEND_SM: Old sms Dest len: "+destination.length+". New sms Dest len: "+returnMe.length);
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return returnMe;
  }
  public String formatMobileNoV2(String mobile)
  {
    String src = "";
    try
    {
      src = formatNumbersOnly(mobile);
      if (src.length() < 1) {
        return "";
      }
      if (src.substring(0, 1).equals("0")) {
        src = "254" + src.substring(1);
      }
      if (src.substring(0, 1).equals("7")) {
        src = "254" + src.substring(0);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      return mobile;
    }
    if ((src != null) && (src.length() > 15)) {
      src = src.substring(0, 15);
    }
    return src;
  }
    public String formatMobileNo(String mobile)
  {
    String src = "";
    try
    {
      src = mobile.replace('+', ' ');
      src = src.replaceAll(" ", "");
      Long.parseLong(src);
      if (src.substring(0, 1).equals("0")) {
        src = "254" + src.substring(1);
      }
      if (src.substring(0, 1).equals("7")) {
        src = "254" + src.substring(0);
      }
    }
    catch (Exception ex)
    {
      return mobile;
    }
    return src;
  }
  
  public String formatMobileNoV2Tz(String mobile)
  {
    String src = "";
    try
    {
      src = formatNumbersOnly(mobile);
      if (src.length() < 1) {
        return "";
      }
      if (src.substring(0, 1).equals("0")) {
        src = "255" + src.substring(1);
      }
      if (src.substring(0, 1).equals("7")) {
        src = "255" + src.substring(0);
      }
    }
    catch (Exception ex)
    {
      return mobile;
    }
    if ((src != null) && (src.length() > 15)) {
      src = src.substring(0, 15);
    }
    return src;
  }
    public String formatNumbersOnly(String msg)
  {
    String acceptstr = "1234567890";
    if ((msg == null) || (msg.length() == 0)) {
      return "";
    }
    msg = msg.replaceAll("\"", "'");
    String nuMsg = "";
    for (int i = 0; i < msg.length(); i++) {
      if (acceptstr.indexOf(String.valueOf(msg.charAt(i)).toLowerCase()) > -1) {
        nuMsg = nuMsg + String.valueOf(msg.charAt(i));
      }
    }
    return nuMsg;
  }
    
    public String formatMsg(String msg)
  {
    String acceptstr = "1234567890zxcvbnmlkjhgfdsaqwertyuiop,.:;/'?=+-()*&%#@!~[{]}|< > ";
    if ((msg == null) || (msg.length() == 0)) {
      return "";
    }
    msg = msg.replaceAll("\"", "'");
    String nuMsg = "";
    for (int i = 0; i < msg.length(); i++) {
      if (acceptstr.indexOf(String.valueOf(msg.charAt(i)).toLowerCase()) > -1) {
        nuMsg = nuMsg + String.valueOf(msg.charAt(i));
      }
    }
    return nuMsg;
  }
}
