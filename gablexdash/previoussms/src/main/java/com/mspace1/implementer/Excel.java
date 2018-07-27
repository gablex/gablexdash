/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.model.Tdist;
import com.mspace1.model.TexcelIntegration;
import com.mspace1.model.Texcelsmstosend;
import com.mspace1.model.Tsmsout;
import com.mspace1.model.Tuser;
import com.mspace1.model.Tusersmsschedule;
import com.mspace1.model.smsstatust;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mspace
 */
public class Excel implements Serializable {

    updater updaterz = new updater();

    public int saveMessage(String msg, String username, String fromaddress) {
        int ret = 0;

        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            TexcelIntegration texcelIntegration = new TexcelIntegration();
            texcelIntegration.setMsg(msg);
            texcelIntegration.setUser(username);
            if (fromaddress.length() > 13) {
                texcelIntegration.setFromPart(fromaddress.substring(0, 13));
            } else {
                texcelIntegration.setFromPart(fromaddress);
            }

            session.update(texcelIntegration);
            session.getTransaction().commit();
            ret = 1;
        } catch (HibernateException k) {
            k.printStackTrace();
            ret = 0;
            session.getTransaction().rollback();
        } finally {
            session.close();

        }
        System.out.println("message saved");
        return ret;
    }

    public String formatMsgDstv(String msg) {
//        msg = msg.replaceAll(" ", "");
        String acceptstr = "1234567890zxcvbnmlkjhgfdsaqwertyuiop,.:;/'?=+-()*&%#@!~[{]}|< > ~";

        msg = msg.replaceAll("\"", "'");
        StringBuffer msg2 = new StringBuffer();
        for (int i = 0; i < msg.length(); i++) {
            if (acceptstr.indexOf(String.valueOf(msg.charAt(i)).toLowerCase()) > -1) {
                if (String.valueOf(msg.charAt(i)).equals("[")) {
                    msg2 = msg2.append(";;;");
                } else if (String.valueOf(msg.charAt(i)).equals("]")) {
                    msg2 = msg2.append(";;;");
                } else {
                    msg2 = msg2.append(msg.charAt(i));
                }
            }
        }
        return msg2.toString();
    }

    private List<smsstatust> SMSList = new ArrayList<>();

    public List<smsstatust> getSMSList() {
        return SMSList;
    }

    public void setSMSList(List<smsstatust> SMSList) {
        this.SMSList = SMSList;
    }

    public String[] getMsg(String uname) {
        String[] ret = null;
        Session session = getSessionFactory().openSession();
        Session session2 = getSessionFactory().openSession();

        try {

            TexcelIntegration u = new TexcelIntegration();
            session.getTransaction().begin();
            String hql = "from TexcelIntegration s where s.user = :user";
            u = (TexcelIntegration) session.createQuery(hql)
                    .setParameter("user", uname)
                    .list().get(0);
            session.getTransaction().commit();
            ret = new String[2];
            ret[0] = u.getMsg();
            ret[1] = u.getFromPart();

        } catch (Exception k) {
            try {
                TexcelIntegration u1 = new TexcelIntegration();
                session2.getTransaction().begin();
                u1.setFromPart("fromPart");
                u1.setMsg("msg");
                u1.setUser(uname);
                session2.save(u1);
                session2.getTransaction().commit();
                ret = new String[2];
                ret[0] = "msg";
                ret[1] = "fromPart";
            } catch (Exception m) {
                ret = null;
            }
        } finally {

            session.close();
        }
        return ret;
    }

    public List<smsstatust> sendMySms20171024(UploadedFile uploadedfile, String sheet, String mobilesCol, String[][] Alldata, String msg, String from, Date time, String uname) {
        int ret = 0;
        String returnMe = "";
        String fileLoc = "";
        String catalina = System.getProperty("catalina.home");
        fileLoc = catalina + "/logs/" + uploadedfile.toString();
        int mobileColumn = Integer.parseInt(mobilesCol.substring(3));
        int noofsmsentall = 0;
        int noofsmsNotsent = 0;
       
        for (int i = 0; i < Alldata.length; i++) {
            String mobile = Alldata[i][mobileColumn];
            String myMsg = this.formatMsgDstv(msg);
            for (int j = 0; j < Alldata[i].length; j++) {
                try {
                    myMsg = myMsg.replaceAll(";;;col" + j + ";;;", Alldata[i][j]);
                    } catch (Exception e) {
//                    e.printStackTrace();
                    System.out.println("Error while replacing ;;;col" + j + ";;; with" + Alldata[i][j] + " File: " + fileLoc);
                }

            }
            //now insert msg and mobile in db.
//            System.out.println("Mobile: " + mobile + " Message: " + myMsg);
if(myMsg.length() >670){
          myMsg = myMsg.substring(0,670);
       }
            String line = mobile + "\t" + myMsg + "\t" + from + "\t" + "\t" + "\t" + uname + "\tweb_excelV4";
//            System.out.println("Line: " + line);

            if (time == null) {
                time = new Date();
            }

            ret = this.send_sms(line, 0, "", time, "");

            smsstatust smsreport = new smsstatust();
            if (ret > 0) {
                noofsmsentall++;
                smsreport.setDestinatin(mobile);
                smsreport.setSmsstatuz("Sent successfully");
            } else {
                noofsmsNotsent++;
                smsreport.setDestinatin(mobile);
                smsreport.setSmsstatuz("Sent Unsuccessfully");
            }
            SMSList.add(smsreport);

        }
        System.out.println("Number of sms sent " + noofsmsentall + "\n No of sms not sent " + noofsmsNotsent);
        System.out.println("Total Number of Sms  " + SMSList.size());
        return SMSList;
    }

    private int send_sms(String line, int rule_id, String submitted, Date time, String dest) {
        int result = 0;
        int stat = 0; //Incase of error stat should not be 0
        System.out.println(line);
        String[] sms = line.split("\t");
        String dest_addr = "";
        String message = "";
        String source_addr = "";

        try {
            dest_addr = sms[0];
        } catch (Exception ex6) {
            ex6.printStackTrace();
        }
        try {
            message = sms[1];

        } catch (Exception ex7) {
            ex7.printStackTrace();
        }
        //message = this.formatMsg(message+ "is tttt fine");
        try {
            source_addr = sms[2];

        } catch (Exception ex8) {
            ex8.printStackTrace();
        }

        String sentby = "";
        String username = "";
        String check = "";
        int smstype = 0;

        try {
            sentby = sms[3];
        } catch (Exception ex) {
            sentby = "";
        }
        try {
            smstype = Integer.parseInt(sms[4]);
        } catch (Exception ex1) {
            smstype = 0;
        }

        try {
            if (sms[6].length() > 0) {
                submitted = sms[6];
            }
        } catch (Exception ex2) {
            submitted = "";
        }

        username = "MSPACE";
        try {
            if (sms[5].length() > 0) {
                username = sms[5];
            }
        } catch (Exception ex3) {
            username = "MSPACE";
        }

        try {
            check = dest_addr.substring(0, 1);
        } catch (Exception ex9) {
//        System.out.println(ex9);
        }
        if (check.equals("0")) {
            dest_addr = "254" + dest_addr.substring(1);
        } else if (check.equals("+")) {
            dest_addr = dest_addr.substring(1);
        } else if (check.equals("7")) {
            dest_addr = "254" + dest_addr;
        }

        /////////////////////////////////////If valid sms
        if (dest_addr.length() >= 7 && dest_addr.matches("[0-9,+]+")) {
            ///////////////////////////////////Get distribution info
            Session session5 = getSessionFactory().openSession();
            try {

                    String sql = "from Tdist  k where substring(k.destAddr,1,5) = substring('" + dest_addr + "',1,5) or k.destAddr='%' and k.owner='admin' order by length(k.destAddr) desc";
                session5.beginTransaction();

                List employee1 = session5.createQuery(sql).list();
                if (employee1 != null || (!sentby.equals(""))) {
                    if (source_addr.length() == 0) {
                        for (Iterator iterator = employee1.iterator(); iterator.hasNext();) {
                            Tdist employee = (Tdist) iterator.next();
                            source_addr = employee.getSourceAddr();
                        }
                    }
                    if (sentby.length() == 0) {
                        for (Iterator iterator = employee1.iterator(); iterator.hasNext();) {
                            Tdist employee = (Tdist) iterator.next();
                            source_addr = employee.getSourceAddr();
                            sentby = employee.getSentby();
                        }

                    }

                    session5.getTransaction().commit();
                }
            } catch (HibernateException k) {
                session5.getTransaction().rollback();
            } finally {
                session5.close();
            }
            if (dest_addr.matches("[0-9,+]+") && this.deductCredit(username, message) >= 1) {
                if (time.before(new Date()) || time.equals(new Date())) {
                    Date dat1 = new Date();
                    Session session4 = getSessionFactory().openSession();
                    try {
                        System.out.println("Sending SMS............................");

                        //for log onlly
                        String sql = "insert into tSMSOUT (source_addr,destination_addr,message_payload,user_message_reference,status,sentby,submittedby,esm_class,user) values "
                                + "(\"" + source_addr + "\",\"" + dest_addr + "\",\"" + message + "\",'01',0,\""
                                + sentby + "\",\"" + submitted + "\",'" + String.valueOf(smstype) + "',\""
                                + username + "\")";
                        System.out.println(sql + dat1);

                        Tsmsout inse = new Tsmsout();
                        inse.setSourceAddr(source_addr);
                        inse.setDestinationAddr(dest_addr);
                        inse.setMessagePayload(message);
                        inse.setUserMessageReference("01");
                        inse.setStatus("0");
                        inse.setSentby(sentby);
                        inse.setSubmittedby(submitted);
                        inse.setEsmClass(smstype);
                        inse.setUser(username);
                        inse.setErrorinfo("");
                        inse.setMessageId("");
                        inse.setServiceType("");
                        inse.setTimeProcessed("");
                        inse.setTimeSubmitted("");
                        session4.beginTransaction();
                        session4.save(inse);
                        session4.getTransaction().commit();
                        stat = 1;
                    } catch (Exception k) {
                        k.printStackTrace();
                        session4.getTransaction().rollback();
                        stat = 0;
                    } finally {
                        session4.close();
                    }
                } else if (time.after(new Date())) {
                    System.out.println("Scheduling SMS.............................");
                    Session session3 = getSessionFactory().openSession();
                    ///for logs only
                    String sql = "insert into tUSERSMSSCHEDULE (username,source,message,dest,sentby,sendtime) values "
                            + "(\"" + username + "\",\"" + source_addr + "\",\"" + message + "\",\"" + dest_addr + "\",\"" + sentby + "\",'" + time + "')";
                    Date dat1 = new Date();
                    System.out.println(sql + dat1);

                    try {

                        Tusersmsschedule schedule = new Tusersmsschedule();
                        schedule.setUsername(username);
                        schedule.setSource(source_addr);
                        schedule.setMessage(message);
                        schedule.setDest(dest_addr);
                        schedule.setSentby(sentby);
                        schedule.setSendTime(time);
                        session3.beginTransaction();
                        session3.save(schedule);
                        session3.getTransaction().commit();
                        stat = 1;
                    } catch (HibernateException k) {
                        k.printStackTrace();
                        stat = 0;
                    } finally {
                        session3.close();
                    }

                } else {
                    stat = 0;
                }
            } else {

                System.out.println("Invalid Number /Failed to deduct credit  coz user has insufficient credits, or error occured or user doesnt exist..");
                stat = 0;
            }

        } else {
            System.out.println("null number");
            stat = 0;
        }
        return stat;
    }

    public int smsToSend(String filename, String sheet, String mobcol, String messagetemplate, String uname, String source, Date time) {

        int i = 0;
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
        int staggerFrequency = 0;

        int smscredits = this.checkCredits(uname, messagetemplate);
        if (smscredits < 1) {
            System.out.println("sms credit not Enough");
            return -1;
        }

        byte km = 1;
        Session session = getSessionFactory().openSession();
        try {
            Texcelsmstosend texcelsmstosend = new Texcelsmstosend();
            texcelsmstosend.setFile(filename);
            texcelsmstosend.setSheet(sheet);
            texcelsmstosend.setMobileColumn(mobcol);
            texcelsmstosend.setMessagetemplate(messagetemplate);
            texcelsmstosend.setUser(uname);
            texcelsmstosend.setSource(source);
            texcelsmstosend.setTimeTosend(time);
            texcelsmstosend.setTimeSubmitted(ourJavaTimestampObject);
            texcelsmstosend.setDeliverFrequency(staggerFrequency);
            texcelsmstosend.setProcessed(km);

            session.beginTransaction();
            session.save(texcelsmstosend);
            session.getTransaction().commit();
            i = 1;
        } catch (Exception k) {
            System.out.println("message not saved ");
//            k.printStackTrace();
            i = 0;
        } finally {
            session.close();
        }

        return i;
    }

    private int checkCredits(String uname, String msg) {
        int ret = 0, smscount = 1;
        if (msg != null && msg.length() > 160) {
            smscount = msg.length() / 134;
            if (msg.length() % 134 > 0) {
                smscount++;
            }
        }
        Session session = getSessionFactory().openSession();
        try {
            if (updaterz.getSmsBalance() >= smscount) {
                ret = 1;
            } else {
                ret = 0;
            }
//System.out.println(results.get(1));
        } catch (Exception k) {
            ret = 0;
            System.out.println(k.getMessage());
        } finally {
            session.close();

        }

        return ret;
    }

    private int deductCredit(String uname, String msg) {
        int ret = 0, smscount = 1;
        if (msg != null && msg.length() > 160) {
            smscount = msg.length() / 134;
            if (msg.length() % 134 > 0) {
                smscount++;
            }
        }

        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            //for log
            String sql2 = "update Tuser m  set m.maxTotal=m.maxTotal-" + smscount + ",m.smsCountToday=m.smsCountToday+" + smscount + ","
                    + "m.smsCountWeek=m.smsCountWeek+" + smscount + ",m.smsCountMonth=m.smsCountMonth+" + smscount + ","
                    + "m.smsCountTotal=m.smsCountTotal+" + smscount + ", m.lastSend=now() where m.username = '" + uname + "' and m.maxTotal >= '" + smscount + "'";

            // for real 
            String sql = "update Tuser m  set m.maxTotal=m.maxTotal-" + smscount + ",m.smsCountToday=m.smsCountToday+" + smscount + ","
                    + "m.smsCountWeek=m.smsCountWeek+" + smscount + ",m.smsCountMonth=m.smsCountMonth+" + smscount + ","
                    + "m.smsCountTotal=m.smsCountTotal+" + smscount + ", m.lastSend=now() where   m.username= :username and m.maxTotal >= '" + smscount + "'";
            System.out.println("updating user balance .......... \n" + sql2);

            Query qry = session.createQuery(sql);
            qry.setParameter("username", uname);
            ret = qry.executeUpdate();
            session.getTransaction().commit();

        } catch (Exception p) {
            ret = 0;
         System.out.println("Error occured while updating user balance  "+ p.getMessage());
         session.getTransaction().rollback();
         session.close();

        } finally {
            session.close();
        }

        return ret;
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            String catalina = System.getProperty("catalina.home");
            String destination = catalina + "/logs/";
            // write the inputStream to a FileOutputStream
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            fileName = timestamp.concat(fileName);
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("Excel file copied to !" + destination + fileName);
        } catch (IOException e) {
            System.out.println("check System space remaining ");
            System.out.println(e.getMessage());
        }
    }

}
