/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.functions.davFunctions;
import com.mspace1.interfac.groupsender;
import com.mspace1.model.Tdist;
import com.mspace1.model.Tinfo;
import com.mspace1.model.Tsmsout;
import com.mspace1.model.Tusersmsschedule;
import com.mspace1.model.smsstatust;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
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
public class groupsmssender extends davFunctions implements groupsender {

    updater k = new updater();
    HttpSession sessionm = getsession.getSession();
    long maxtotal = k.getSmsBalance();
    long maxdaily = (long) sessionm.getAttribute("max_daily");
    long maxweekly = (long) sessionm.getAttribute("max_weekly");
    long maxmonthly = (long) sessionm.getAttribute("max_monthly");
    String user = (String) sessionm.getAttribute("username");
    String usertype = (String) sessionm.getAttribute("agent");
    long id = (long) sessionm.getAttribute("id");
    int destlenth;
    private List<smsstatust> SMSList = new ArrayList<>();
    private java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyyMMdd");

    public List<smsstatust> getSMSList() {
        return SMSList;
    }

    @Override
    public List<smsstatust> getsmsstatuslist(String msg, String from, String[] dest, Date time) {
        destlenth = dest.length;
           if(msg.length() >670){
        msg = msg.substring(0,670);
           }
    int[] max = null;
        int unsucess = 0;
        int status;
        int smssent = 0;
//        String[][] returnMe = null;
        if (dest == null) {
            return null;
        }
//         returnMe = new String[dest.length][6];
        try {
            if (usertype.equalsIgnoreCase("freesms")) {
                from = user.substring(1);
                msg = msg + this.getTrailerMsg();
            }
            int noOfSms = 1;
            if (msg.length() > 160) {

//            noOfSms = (Message.length()/140);
                noOfSms = (msg.length() / 134);
                if (msg.length() % 134 > 0) {
                    noOfSms += 1;
                }
            } else {
            }

            String line = "", dest1 = "";

            for (int d = 0; d < dest.length; d++) {
                String[] contacts = this.getGrpContacts(dest[d]);
                contacts = this.getDistinctSms(contacts);
                if (contacts != null) {
                    for (int i = 0; i < contacts.length; i++) {
                        smsstatust smsreport1 = new smsstatust();
                        dest1 = contacts[i].replaceAll("\r", "");
                        dest1 = dest1.replaceAll("\n", "");
                        if (maxtotal > 0 || maxtotal == -1) {

                        } else {
                            smsreport1.setDestinatin(dest1);
                            smsreport1.setSmsstatuz("Credit Insufficient");
                            unsucess++;
                            continue;
                        }

                        if (usertype.equalsIgnoreCase("freesms")) {
                            line = dest1 + "\t" + msg + "\t" + from + "\t" + "\t" + "\t" + user
                                    + "\tfreesmsv4";
                        } //          else if(sendtime != null && sendtime.length() > 0 && this.validateSendTime(sendtime) > 0)
                        //          {
                        //            System.out.println("Scheduling SMS...");
                        //          }
                        else {
                            line = dest1 + "\t" + msg + "\t" + from + "\t" + "\t" + "\t" + user
                                    + "\twebV4";
                        }

                        status = 0;
                        if (k.getSmsBalance() >= noOfSms) {
                            status = this.send_sms(line, 0, "", "", time);
                        } else {
                            status = 0;
                        }
                        if (status == 1) {
                   max = this.updateUserSmsSent(noOfSms, user);
                            smsreport1.setDestinatin(dest1);
                            smsreport1.setSmsstatuz("Sent Successfully.");
                            smssent += noOfSms;

                        } else {
                            smsreport1.setDestinatin(dest1);
                            smsreport1.setSmsstatuz("Sent UnSuccessfully.");
                            unsucess++;

                        }
                        SMSList.add(smsreport1);
                    }
                } else {
                    contacts = null;
                    unsucess++;
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
  
        if (max != null) {
            System.out.println("MAX TOTAL before send are :: " + maxtotal);
            System.out.println("Total SMS sent ::  " + smssent);
            System.out.println("Total Sms not Sent ::  " + unsucess);

            System.out.println("MAX TOTAL remaining after send == " + (maxtotal - smssent));
        }

        return SMSList;
    }

//get contacts
    private String[] getGrpContacts(String grp) {
        String[] ret = null;

        grp = grp.replaceAll("\\n", "");
        grp = grp.replaceAll("\\r", "");
        System.out.println("Fetching contacts for group: " + grp + " Contacts found");
        if (grp.length() < 1) {
            return null;
        }
        try {
            String sql = "select distinct k.contactNumber from Tuseraddressbook k where k.userId='" + id + "' and k.groupName='" + grp + "' group by k.contactNumber";
            ret = this.getnumbervalueimpl(sql, grp);

        } catch (NumberFormatException ex1) {
            //ex1.printStackTrace();
            ret = null;
        }

        for (int i = 0; i < ret.length; i++) {
            System.out.println(ret[i]);
        }

        return ret;
    }

    public String[] getnumbervalueimpl(String sql2, String grp) {
        List lista = null;
        String[] contacts = null;
        String formatedString = null;
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista = session.createQuery(sql2).list();
            session.getTransaction().commit();
        } catch (HibernateException k) {

        } finally {
            session.close();
        }
        try {
            if (lista.isEmpty()) {
                System.out.println("The number doesnot exist in the database ");
                try {
                    grp = grp.replace('+', ' ');
                    grp = grp.trim();
                    Integer.parseInt(grp);
                    contacts = new String[]{grp};
                } catch (NumberFormatException ex1) {
                    //ex1.printStackTrace();
                    contacts = null;
                }
            } else {
                formatedString = lista.toString()
                        .replace(",", "") //remove the commas
                        .replace("[", "") //remove the right bracket
                        .replace("]", "") //remove the left bracket
                        .replace(" ", "\n")
                        .trim();
                contacts = formatedString.split("\\r?\\n");

            }
        } catch (Exception k) {
            System.out.println("Error occured while assigning group contacts under new group sms ");
        }

        return contacts;
    }

    private int send_sms(String line, int rule_id, String submitted, String dest, Date time) {
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
        message = this.formatMsg2(message);
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
            username = "MSPACE-Unknown";
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
        if (dest_addr.length() > 0 && dest_addr.matches("[0-9,+]+") ) {
            ///////////////////////////////////Get distribution info

            Session session4 = getSessionFactory().openSession();
            String sqlm = null;
            try {

                String sql = "from Tdist  k where substring(k.destAddr,1,5) = substring('" + dest_addr + "',1,5) or k.destAddr='%' and k.owner='admin' order by length(k.destAddr) desc";
                session4.beginTransaction();

                List employee1 = session4.createQuery(sql).list();
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
                    session4.getTransaction().commit();
                }
            } catch (Exception k) {
                session4.getTransaction().rollback();
            } finally {
                session4.close();
            }

//                        
            if (time.before(new Date()) || time.equals(new Date())) {
                Session session5 = getSessionFactory().openSession();
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
                    session5.beginTransaction();
                    session5.save(inse);
                    session5.getTransaction().commit();

                    sqlm = "insert into tSMSOUT (source_addr,destination_addr,message_payload,user_message_reference,status,sentby,submittedby,esm_class,user) values "
                            + "('" + source_addr + "','" + dest_addr + "','" + message + "',01,0,'"
                            + sentby + "','" + submitted + "'," + String.valueOf(smstype) + ",'"
                            + username + "')";
                    System.out.println("inserter executing tsmsout/new group ...\n " + sqlm);
                    research("groupsmssender", "gs", "Send SMS dump\nStatement " + sqlm + "\n");

                    stat = 1;

                } catch (Exception k) {
                    k.printStackTrace();
                    stat = 0;
                    String fileLoc = "";
                    String errorloglocation = "/shared/smsfiles/temp/";

                    fileLoc = errorloglocation + dformat.format(new java.util.Date()) + "_webv4_groupsmserror_smssend.err";
                    System.out.println("Error occured while inserting sms   file copied to  " + fileLoc);
                    try {
                        File outputFile = new File(fileLoc);
                        FileWriter out = new FileWriter(outputFile, true);
                        out.write(new java.util.Date() + "\t" + user + "\t" + id + "\t" + sqlm + "\n");
                        out.flush();
                        out.close();
                    } catch (Exception ex1) {
                        ex1.printStackTrace();
                    }
//         k.printStackTrace();
                } finally {
                    session5.close();
                }
            } else if (time.after(new Date())) {

                System.out.println("Scheduling new group SMS...");
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
         stat = 0;
        }

        return stat;
    }

    private String formatMsg2(String m) {//removes unwanted characters in the message string

        String acceptstr = "1234567890zxcvbnmlkjhgfdsaqwertyuiop,.:;/'?=+-()*&%#@!~[{]}|< > ";
        if ((m == null) || (m.length() == 0)) {
            return "";
        }
        m = m.replaceAll("\"", "'");
        String nuMsg = "";
        for (int i = 0; i < m.length(); i++) {
            if (acceptstr.indexOf(String.valueOf(m.charAt(i)).toLowerCase()) > -1) {
                nuMsg = nuMsg + String.valueOf(m.charAt(i));
            }
        }
        return nuMsg;

    }

    private void research(String name, String type, String info) {

        int ret = 0;
        Session session = getSessionFactory().openSession();
        try {
            String time1 = null;
            DateFormat inFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time1 = inFormat2.format(new Date());
            Tinfo km = new Tinfo();
            km.setTime(time1);
            km.setBind_name(name);
            km.setBind_type(type);
            km.setInfo(info);
            session.beginTransaction();
            session.save(km);
            session.getTransaction().commit();
            ret = 1;
        } catch (Exception k) {
            k.printStackTrace();
            ret = 0;
        } finally {
            session.close();
        }

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
                    String errorloglocation = "/shared/smsfiles/temp/";

                    fileLoc = errorloglocation + dformat.format(new java.util.Date()) + "_webv4_groupsmsupdaterror_smssend.err";
                    System.out.println("Error occured while updating  sms  bal file copied to  " + fileLoc);
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

                String errorloglocation = "/shared/smsfiles/temp/";

                fileLoc = errorloglocation + dformat.format(new java.util.Date()) + "_webv4_groupsmsupdaterror_smssend.err";
                System.out.println("Error occured while updating balance group sms  file copied to  " + fileLoc);

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

}
