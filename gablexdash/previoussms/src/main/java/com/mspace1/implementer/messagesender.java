/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.functions.davFunctions;
import com.mspace1.interfac.insertsms;
import com.mspace1.model.Tdist;
import com.mspace1.model.Tsmsout;
import com.mspace1.model.Tusersmsschedule;
import com.mspace1.model.smscounter;
import com.mspace1.model.smsstatust;

import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mspace
 */
public class messagesender extends davFunctions implements insertsms {
//smscounter counter = new smscounter();
    updater k = new updater();
    HttpSession sessionm = getsession.getSession();
    long maxtotal = k.getSmsBalance();
    long maxdaily = (long) sessionm.getAttribute("max_daily");
    long maxweekly = (long) sessionm.getAttribute("max_weekly");
    long maxmonthly = (long) sessionm.getAttribute("max_monthly");
    String user = (String) sessionm.getAttribute("username");
    String usertype = (String) sessionm.getAttribute("agent");
    long id = (long) sessionm.getAttribute("id");
   private  String thestaussummmary;

    private List<smsstatust> SMSList = new ArrayList<>();
    private java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyyMMdd");
    java.text.SimpleDateFormat dformat2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//@PostConstruct
//    private void postConstruct () {
//
//    }

    public List<smsstatust> getSMSList() {
        return SMSList;
    }

    public String getThestaussummmary() {
        return thestaussummmary;
    }

    public void setThestaussummmary(String thestaussummmary) {
        this.thestaussummmary = thestaussummmary;
    }

    @Override
    public List<smsstatust> getsmsstatuslist(String Message, String from, String to, Date time) {
        int status = 0;
        long smscount = 0;
        int smssent = 0;
         long smssent2 = 0;
        int unsucess = 0;
        
        if(Message.length() >670){
          Message = Message.substring(0,670);
        }
        String time1 = converttime(time);
        String[] destination;
        to = to.replaceAll(" ", "");
        destination = to.split("\\r?\\n");
        if (destination == null) {
            return null;
        }
        String hostname = this.executeShellCommand("hostname");
        destination = this.getDistinctSms(destination);
        smscount = getSmsCountToSend(destination.length);

        //execution
        try {

            if (usertype.equalsIgnoreCase("freesms")) {
                from = user.substring(1);
                Message = Message + this.getTrailerMsg();
            }
            int noOfSms = 1;
            if (Message.length() > 160) {
                noOfSms = (Message.length() / 134);
                if (Message.length() % 134 > 0) {
                    noOfSms += 1;
                }
            } else {
            }

            System.out.println("Msg Len: " + Message.length() + ". No Of SMS: " + noOfSms + " no of sms allowed: " + (long) (smscount / noOfSms));
            String line = "", dest1 = "";
            for (int i = 0; i < destination.length; i++) {
                smsstatust smsreport = new smsstatust();
                if (i >= (long) (smscount / noOfSms)) {
                    smsreport.setDestinatin(destination[i]);
                    smsreport.setSmsstatuz("Sent Unsuccessfully. Sms Max Reached");
                    unsucess++;
                    continue;
                }
                dest1 = destination[i].replaceAll("\r", "");
                dest1 = destination[i].replaceAll("\n", "");

                if (hostname != null && hostname.length() > 0 && hostname.equalsIgnoreCase("tzsms.resolution.co.ke")) {
                    dest1 = this.formatMobileNoV2Tz(dest1);
                } else {
                    dest1 = this.formatMobileNoV2(dest1);
                }

                if (usertype.equalsIgnoreCase("freesms")) {
                    line = dest1 + "\t" + Message + "\t" + from + "\t" + "\t" + "\t" + user
                            + "\tfreesms";
                } //          else if(sendtime != null && sendtime.length() > 0 && this.validateSendTime(sendtime) > 0)
                //          {
                //            System.out.println("Scheduling SMS...");
                //          }
                else {
                    line = dest1 + "\t" + Message + "\t" + from + "\t" + "\t" + "\t" + user
                            + "\twebV4";
                }
                status = 0;
                status = this.send_sms(line, 0, "", "", time1, time);
                if (status == 1) {
//            smssent++;
                    smsreport.setDestinatin(destination[i]);
                    smsreport.setSmsstatuz("Sent Successfully.");
                    smssent += noOfSms;
                    smssent2++;

                } else {
                    smsreport.setDestinatin(destination[i]);
                    smsreport.setSmsstatuz("Sent UnSuccessfully.");
                    unsucess++;
                }
                SMSList.add(smsreport);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //update user sms 

        int[] max = this.updateUserSmsSent(smssent, user);

        if (max != null) {

            System.out.println("MAX TOTAL before send are " + maxtotal);
            System.out.println("Total SMS sent::  " + smssent);
            System.out.println("Total Sms not Sent ::  " + unsucess);
            System.out.println("MAX TOTAL remaining after send == " + (maxtotal - smssent));
//    counter.setSentsuccefully(smssent2);
////    counter.setNotsent(unsucess);
//    thestaussummmary="Succefully sent :" +smssent2 + "Not sent are :"+ unsucess;   
        }

        return SMSList;

    }
    public String countlist (){
    
    return thestaussummmary ;
    }
    

    public String converttime(Date time) {
        String time1 = null;
        DateFormat inFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time1 = inFormat2.format(time);

        return time1;
    }

    private long getSmsCountToSend(int submitted) {

        long smsCount = 0;
        try {

            try {
                if (maxmonthly == -1) {
                    smsCount = submitted;
                } else {
                    try {
                        smsCount = maxmonthly;
                    } catch (Exception ex) {
                        smsCount = 0;
                    }
                }
            } catch (Exception ex3) {
                ex3.printStackTrace();
            }

            try {
                if (maxweekly == -1) {
                    smsCount = submitted;
                } else {
                    try {

                        smsCount = maxweekly;
                    } catch (Exception ex) {
                        smsCount = 0;
                    }
                }
            } catch (Exception ex4) {
                ex4.printStackTrace();
            }

            try {
                if (maxdaily == -1) {
                    smsCount = submitted;
                } else {
                    try {
                        smsCount = maxdaily;
                    } catch (Exception ex) {
                        smsCount = 0;
                    }
                }
            } catch (Exception ex5) {
                ex5.printStackTrace();
            }

            try {
                if (maxtotal == -1) {
                    smsCount = submitted;
                } else {
                    try {
                        smsCount = maxtotal;
                    } catch (Exception ex) {
                        smsCount = 0;
                    }
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }

            if (smsCount < 0) {
                smsCount = 0;
            }
        } catch (Exception ex1) {
            System.err.println("Exception: " + new java.util.Date() + "\tSubmitting " + submitted + " sms's.\tmax_daily: " + maxdaily + ". User: " + user + "");
            ex1.printStackTrace();
        }

        return smsCount;
    }

    public String getTrailerMsg() {
        List lista = null;
        String trailerMsg = null;
        String sql2 = "SELECT k.trailerMsg FROM tDAILYFREESMS k where k.active = 1";
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista = session.createQuery(sql2).list();
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }
        trailerMsg = lista.toString()
                .replace(",", "") //remove the commas
                .replace("[", "") //remove the right bracket
                .replace("]", "") //remove the left bracket
                .trim();
        return trailerMsg;
    }

    private int send_sms(String line, int rule_id, String submitted, String dest, String sendtime, Date time) {
        int stat = 0; //Incase of error stat should not be 0

        String[] sms = line.split("\t");

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
        message = this.formatMsg(message);
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
        } else if (check.equals("7")) {
            dest_addr = "254" + dest_addr;
        }
        /////////////////////////////////////If valid sms
        if (dest_addr.length() > 0 && dest_addr.matches("[0-9,+]+") ) {
            ///////////////////////////////////Get distribution info
            Session session5 = getSessionFactory().openSession();
            try {
//String sqll2=": select * from tDIST where (left(dest_addr,5) = left('\"+dest_addr+\"',5) or dest_addr = '%') and owner='admin' order by length(dest_addr) desc";
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

            if (time.before(new Date()) || time.equals(new Date())) {

                Session session4 = getSessionFactory().openSession();
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
                    inse.setUser(user);
                    inse.setErrorinfo("");
                    inse.setMessageId("");
                    inse.setServiceType("");
                    inse.setTimeProcessed("");
                    inse.setTimeSubmitted("");
                    session4.beginTransaction();

                    //logs
                    System.out.println("inserter executing tsmsout /new sms ...");
                    String sql = "insert into tSMSOUT (source_addr,destination_addr,message_payload,user_message_reference,status,sentby,submittedby,esm_class,user) values "
                            + "(\"" + source_addr + "\",\"" + dest_addr + "\",\"" + message + "\",'01',0,\""
                            + sentby + "\",\"" + submitted + "\",'" + String.valueOf(smstype) + "',\""
                            + username + "\")";
                    System.out.println(sql);

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
            } else if (sendtime != null && sendtime.length() > 0 && time.after(new Date())) {
                System.out.println("Scheduling new  SMS...");
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
                    String sql = "insert into tUSERSMSSCHEDULE (username,source,message,dest,sentby,sendtime) values "
                            + "(\"" + username + "\",\"" + source_addr + "\",\"" + message + "\",\"" + dest_addr + "\",\"" + sentby + "\",'" + time + "')";

                    System.out.println(sql);

                    session3.save(schedule);
                    session3.getTransaction().commit();
                    stat = 1;

                } catch (Exception k) {
                    k.printStackTrace();
                    stat = 0;
                } finally {
                    session3.close();
                }

            } else {
                stat = 0;
            }

        }else{
        stat=0;
        }

        return stat;
    }

    private int validateSendTime(String time) {
        int ret = 0;
        try {
            String dt = dformat2.format(dformat2.parse(time));
            ret = 1;
        } catch (ParseException ex) {
            ex.printStackTrace();
            ret = 0;
        }
        return ret;
    }

    private int[] updateUserSmsSent(int smssent, String user) {
        int[] max = null;
        if (smssent > 0) {

            int max_total = 0, max_monthly = 0, max_weekly = 0, max_daily = 0;
            if (maxtotal != -1) {
                max_total = smssent;
            }
            if (maxmonthly != -1) {
                max_monthly = smssent;
            }
            if (maxweekly != -1) {
                max_weekly = smssent;
            }
            if (maxdaily != -1) {
                max_daily = smssent;
            }

            max = new int[4];
            max[3] = max_total;
            max[2] = max_monthly;
            max[1] = max_weekly;
            max[0] = max_daily;

            String sql = "";
            Session session = getSessionFactory().openSession();
            try {

                session.beginTransaction();
                // for  log
                sql = "update tUSER set max_daily=max_daily-" + max_daily + ",max_weekly=max_weekly-" + max_weekly + ","
                        + "max_monthly=max_monthly-" + max_monthly + ",max_total=max_total-" + max_total + ",sms_count_today=sms_count_today+" + smssent
                        + ",sms_count_week=sms_count_week+" + smssent + ",sms_count_month=sms_count_month+" + smssent + ","
                        + "sms_count_total=sms_count_total+" + smssent + ", last_send=now() where username = '" + user + "' and id = '" + id + "'";

                //real query 
                String hql = "update Tuser m  set m.maxDaily=m.maxDaily-" + max_daily + ",m.maxWeekly=m.maxWeekly-" + max_weekly + ","
                        + "m.maxMonthly=m.maxMonthly-" + max_monthly + ",m.maxTotal=m.maxTotal-" + max_total + ",m.smsCountToday=m.smsCountToday+" + smssent
                        + ",m.smsCountWeek=m.smsCountWeek+" + smssent + ",m.smsCountMonth=m.smsCountMonth+" + smssent + ","
                        + "m.smsCountTotal=m.smsCountTotal+" + smssent + ", m.lastSend=now() where m.username= :username and m.id= :id";
                Query query = session.createQuery(hql);
                query.setParameter("username", user);
                query.setParameter("id", id);

                int res = query.executeUpdate();
                session.getTransaction().commit();
                System.out.println("Updating user balance .... \n" + sql);
                if (res != 1) {
                    //write to txt the update sql, and run it later directly from server.
                    String os = System.getProperty("os.name"), fileLoc = "";
            
//          System.out.println("Catalinahome: "+catalina);

                 String errorloglocation ="/shared/smsfiles/temp/";
                     fileLoc = errorloglocation + dformat.format(new java.util.Date()) + "_webv4_newsmssenderror_smssend.err";
  System.out.println("Error occured while updating balance   file copied to  " +fileLoc);
                    try {
                        File outputFile = new File(fileLoc);
                        FileWriter out = new FileWriter(outputFile, true);
                        out.write(new java.util.Date() + "\t" + user + "\t" + id + "\t" + sql + "\n");
                        out.flush();
                        out.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                updater k = new updater();
                try {
                    k.updat();
                } catch (Exception j) {
                    j.printStackTrace();
                }
            } catch (HibernateException ex) {
                session.close();

                   String os = System.getProperty("os.name"), fileLoc = "";
                  String errorloglocation ="/shared/smsfiles/temp/";
                    fileLoc = errorloglocation + dformat.format(new java.util.Date()) + "_webv4_newsmsupdaterror_smssend.err";
                    System.out.println("Error occured while updating balance   file copied to  " +fileLoc);

                sql = "update tUSER set max_daily=max_daily-" + max_daily + ",max_weekly=max_weekly-" + max_weekly + ","
                        + "max_monthly=max_monthly-" + max_monthly + ",max_total=max_total-" + max_total + ",sms_count_today=sms_count_today+" + smssent
                        + ",sms_count_week=sms_count_week+" + smssent + ",sms_count_month=sms_count_month+" + smssent + ","
                        + "sms_count_total=sms_count_total+" + smssent + ", last_send=now() where username = '" + user + "' and id = '" + id + "'";
                try {
                    File outputFile = new File(fileLoc);
                    FileWriter out = new FileWriter(outputFile, true);
                    out.write(new java.util.Date() + "\t" + user + "\t" + id + "\t" + ex.getMessage() + "\t" + sql + "\n");
                    out.flush();
                    out.close();
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }

            }
        }

        return max;

    }

}
