/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kevol.web;

import com.mspace1.model.Tdist;
import com.mspace1.model.Tsmsout;
import com.mspace1.model.Tuser;
import com.mspace1.model.Tusersmsschedule;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mspace
 */
public class webapimethods {

    public String sendSmsm(String uname, String pass, String dest, String src, String msg) {
        Date sendtime = null;
        String returnMe = "";
        try {
            msg = java.net.URLDecoder.decode(msg, "UTF-8");
        } catch (UnsupportedEncodingException ex2) {
            ex2.printStackTrace();
            return "4";
        }
        msg = this.formatMsg1(msg);
        
           
        if(msg.length() >670){
          msg = msg.substring(0,670);
    }
        
        int smses = msg.length() / 160;
        if (msg.length() % 160 > 0) {
            smses++;
        }

        try {
            List results = null;
            long balance = 0l;
            Session session;
            session = getSessionFactory().openSession();
            try {
                session.getTransaction().begin();
                Criteria crit = session.createCriteria(Tuser.class);
                crit.add(Restrictions.eq("username", uname));
                crit.add(Restrictions.eq("password", pass));
                ProjectionList projList = Projections.projectionList();
                projList.add(Projections.property("maxTotal"));
                crit.setProjection(projList);
                results = crit.list();
                session.getTransaction().commit();
            } catch (Exception k) {
                returnMe = "2"; //user doesnoot exist

                balance = 0l;
            } finally {
                System.out.println("Session one closed checking bal...");
                session.close();
            }

            if (results != null) {
                try {
                    balance = (Long) results.get(0);

                    if (balance >= smses) {
                        this.updateUserSmsSent(uname, pass, smses, msg);
                        String line = dest + "\t" + msg + "\t" + src + "\t" + "\t" + "\t"
                                + uname + "\tWEBSMSv4";
                        balance = send_sms1(line, 0, "", "", sendtime);
                        if (balance == 1) {
                            returnMe = "1";
                        } else {
                            returnMe = "0";
                        }
                    } else {
                        returnMe = "3"; //Max reached
                    }
                } catch (Exception jim) {
                    returnMe = "2";
                }
            } else {
                returnMe = "2"; //user doesnoot exist
                balance = 0l;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return returnMe;
    }

    private void updateUserSmsSent(String user, String passw, int smses, String msg) {
        Session session = getSessionFactory().openSession();
        int res = 0;
        String sqlw = null;
        try {

            session.beginTransaction();
            //for log 
            sqlw = "update tUSER set max_total=max_total-" + smses + ", last_send=now() where username = '"
                    + user + "' and password = '" + passw + "'";
            //real query 
            String hql = "update Tuser m  set m.maxTotal=maxTotal-" + smses + ", lastSend=now() where m.username= :username and m.password= :password";
            Query query = session.createQuery(hql);
            query.setParameter("username", user);
            query.setParameter("password", passw);
            res = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();

        } finally {
            session.close();
        }
        System.out.println("Updating user balance for web api .... \n" + sqlw);
        if (res != 1) {
            //write to txt the update sql, and run it later directly from server.
            String os = System.getProperty("os.name"), fileLoc = "";
            java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyyMMdd");
//          System.out.println("Catalinahome: "+catalina);
            String errorloglocation = "/shared/smsfiles/temp/";
            fileLoc = errorloglocation + dformat.format(new java.util.Date()) + "_webv4_smswebapi_smssend.err";
            System.out.println("Error occured while updating balance   file copied to  " + fileLoc);
            try {
                File outputFile = new File(fileLoc);
                FileWriter out = new FileWriter(outputFile, true);
                out.write(new java.util.Date() + "\t" + user + "\t" + passw + "\t"
                        + smses + "\t" + msg + "\n");
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    //send sms  
    private int send_sms1(String line, int rule_id, String submitted, String dest, Date time) {
        int stat = 0; //Incase of error stat should not be 0
        String[] sms = line.split("\t");

        if (time == null) {
            time = new Date();
        }

        String dest_addr = "";
        String message = "";
        String source_addr = "";

        try {
            dest_addr = sms[0];
        } catch (Exception ex6) {
        }
        try {
            message = sms[1];
        } catch (Exception ex7) {
        }

        try {
            source_addr = sms[2];
        } catch (Exception ex8) {
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
        }
        if (check.equals("0")) {
            dest_addr = "254" + dest_addr.substring(1);
        } else if (check.equals("+")) {
            dest_addr = dest_addr.substring(1);
        }
        /////////////////////////////////////If valid sms
        if (dest_addr.length() > 0) {
            ///////////////////////////////////Get distribution info

            Session session = getSessionFactory().openSession();
                 List employee1 = null;
            String sqlm = null;
            try {

                String sqle = "from Tdist  k where substring(k.destAddr,1,5) = substring('" + dest_addr + "',1,5) or k.destAddr='%' and k.owner='admin' order by length(k.destAddr) desc";
                session.beginTransaction();

                employee1 = session.createQuery(sqle).list();
                   session.getTransaction().commit();
                    } catch (Exception kz) {
                        kz.printStackTrace();
                session.getTransaction().rollback();
            } 
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
                 
                }
           

            if (time.before(new Date()) || time.equals(new Date())) {
//                Session session5 = getSessionFactory().openSession();
                try {
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
                    session.beginTransaction();
                    session.save(inse);
                    session.getTransaction().commit();

                    sqlm = "insert into tSMSOUT (source_addr,destination_addr,message_payload,user_message_reference,status,sentby,submittedby,esm_class,user) values "
                            + "('" + source_addr + "','" + dest_addr + "','" + message + "',01,0,'"
                            + sentby + "','" + submitted + "'," + String.valueOf(smstype) + ",'"
                            + username + "')";
                    System.out.println("inserter executing tsmsout/web api ...\n " + sqlm);
//                    research("groupsmssender", "gs", "Send SMS dump\nStatement " + sqlm + "\n");
                    stat = 1;
                } catch (Exception kp) {
                      System.out.println("Session 5 Error tsmsout...");
                    session.close();
                    kp.printStackTrace();
                } finally {
                    session.close();
                }
            } else if (time.after(new Date())) {
                System.out.println("Scheduling web api...");
                Session session3 = getSessionFactory().openSession();
                try {
                    Tusersmsschedule schedule = new Tusersmsschedule();
                    schedule.setUsername(username);
                    schedule.setSource(source_addr);
                    schedule.setMessage(message);
                    schedule.setDest(dest_addr);
                    schedule.setSentby(sentby);
                    schedule.setSendTime(time);
                    session3.beginTransaction();
                    //logs
                    String sqlk = "insert into tUSERSMSSCHEDULE (username,source,message,dest,sentby,sendtime) values "
                            + "(\"" + username + "\",\"" + source_addr + "\",\"" + message + "\",\"" + dest_addr + "\",\"" + sentby + "\",'" + time + "')";

                    System.out.println(sqlk);

                    session3.save(schedule);
                    session3.getTransaction().commit();
           
                    stat = 1;

                } catch (Exception km) {
                    km.printStackTrace();

                } finally {
                    session3.close();
                }

            } else {

            }

        }

        return stat;
    }

    //////////////////////////////Send Message
    public String formatSmsdest(String msg) {
        String acceptstr = "1234567890";
//            msg = msg.replaceAll("\"", "'");
        String nuMsg = "";
        for (int i = 0; i < msg.length(); i++) {
            if (acceptstr.indexOf(String.valueOf(msg.charAt(i)).toLowerCase()) > -1) {
                nuMsg = nuMsg + String.valueOf(msg.charAt(i));
            }
            if (String.valueOf(msg.charAt(i)).equals("\n")) {
                nuMsg = nuMsg + String.valueOf(msg.charAt(i));
            }
        }
        return nuMsg;
    }

    public String formatMsg1(String msg) {
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

    public int validate(String username, String passwd, String dest, String source, String messsage) {
        int returnMe = 0;
        dest = this.formatSmsdest(dest);

        if (username == null || username.length() < 2) {
            returnMe = 5;
        }
        if (passwd == null || passwd.length() < 2) {
            returnMe = 6;
        }
        if (dest == null || dest.length() < 4) {
            returnMe = 7;
        }
        if (messsage == null || messsage.length() < 1) {
            messsage = " ";
        }
        return returnMe;
    }

}
