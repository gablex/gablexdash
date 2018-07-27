/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.model.Groups_bean;
import com.mspace1.model.group_contact;
import com.mspace1.model.contactsbeanz;
import com.mspace1.implementer.groupsmssender;
import com.mspace1.implementer.messagesender;
import com.mspace1.implementer.tuseraddressbookimpl;
import com.mspace1.implementer.updater;
import com.mspace1.model.Tuseraddressbook;
import com.mspace1.model.smsstatust;
import com.mspace1.navigationcontroller.FacePainter;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.primefaces.context.RequestContext;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "tuseraddressbook")
@SessionScoped
public class tuseraddressbook implements Serializable {
    updater checkbal = new updater();
    private group_contact groupsbeannumbersz;
    private Groups_bean groups_bean;
    private contactsbeanz contactbnz;
    private Date todayDate;
    private Tuseraddressbook user;
    private List<smsstatust> filteredsms;
    private String selectedgrp2;
    private String listno;
    private String from;
    private String Message;
    private Date time;
    String[][] sent;
    private long columnCount;
       FacesMessage LL;   
    private int first;
    private String grpname;
    private String contactname;
    private String number;
    private int smsttl;
    private long noofsms;
    private String statusummary;
    

    List<smsstatust> allstatus;

    List<contactsbeanz> grp_contacss;
    List<group_contact> grp_numbercntnwsms;
    @ManagedProperty(value = "#{facePainter}")
    public FacePainter facePainter;
    tuseraddressbookimpl userDao = new tuseraddressbookimpl();
    List<Tuseraddressbook> listagroups;
    List<Groups_bean> grpcntlist;
    @ManagedProperty(value = "#{senderaddressource}")
    private senderaddressource k;

    @PostConstruct
    public void init() {
        getgroups2();
        time = todayDate;
        user = new Tuseraddressbook();
    }

    HttpSession session = getsession.getSession();

    public String getStatusummary() {
        return statusummary;
    }

    public void setStatusummary(String statusummary) {
        this.statusummary = statusummary;
    }
     
    
    
    public List<group_contact> getGrp_numbercntnwsms() {
        return grp_numbercntnwsms;
    }

    public void setGrp_numbercntnwsms(List<group_contact> grp_numbercntnwsms) {
        this.grp_numbercntnwsms = grp_numbercntnwsms;
    }

    public contactsbeanz getContactbnz() {
        return contactbnz;
    }

    public void setContactbnz(contactsbeanz contactbnz) {
        this.contactbnz = contactbnz;
    }

    public List<contactsbeanz> getGrp_contacss() {
        return grp_contacss;
    }

    public void setGrp_contacss(List<contactsbeanz> grp_contacss) {
        this.grp_contacss = grp_contacss;
    }

    public senderaddressource getK() {
        return k;
    }

    public void setK(senderaddressource k) {
        this.k = k;
    }

    public group_contact getGroupsbeannumbersz() {
        return groupsbeannumbersz;
    }

    public void setGroupsbeannumbersz(group_contact groupsbeannumbersz) {
        this.groupsbeannumbersz = groupsbeannumbersz;
    }

    public Groups_bean getGroups_bean() {
        return groups_bean;
    }

    public void setGroups_bean(Groups_bean groups_bean) {
        this.groups_bean = groups_bean;
    }

    public long getNoofsms() {
        return noofsms;
    }

    public void setNoofsms(long noofsms) {
        this.noofsms = noofsms;
    }

    public FacePainter getFacePainter() {
        return facePainter;
    }

    public void setFacePainter(FacePainter facePainter) {
        this.facePainter = facePainter;
    }

    public List<Tuseraddressbook> getListagroups() {
        return listagroups;
    }

    public void setListagroups(List<Tuseraddressbook> listagroups) {
        this.listagroups = listagroups;
    }

    public List<Groups_bean> getGrpcntlist() {
        return grpcntlist;
    }

    public void setGrpcntlist(List<Groups_bean> grpcntlist) {
        this.grpcntlist = grpcntlist;
    }

    public Date getTodayDate() {
        return todayDate;
    }

    public List<smsstatust> getFilteredsms() {
        return filteredsms;
    }

    public void setFilteredsms(List<smsstatust> filteredsms) {
        this.filteredsms = filteredsms;
    }

    public String getGrpname() {
        return grpname;
    }

    public void setGrpname(String grpname) {
        this.grpname = grpname;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getSmsttl() {
        return smsttl;
    }

    public void setSmsttl(int smsttl) {
        this.smsttl = smsttl;
    }
    HttpSession session1 = getsession.getSession();
    long id = (Long) session1.getAttribute("id");

    public Tuseraddressbook getUser() {
        return user;
    }

    public void setUser(Tuseraddressbook user) {
        this.user = user;
    }

    public String getListno() {
        return listno;
    }

    public void setListno(String listno) {
        this.listno = listno;
    }

    public long getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(long columnCount) {
        this.columnCount = columnCount;
    }

    public String getSelectedgrp2() {
        return selectedgrp2;
    }

    public void setSelectedgrp2(String selectedgrp2) {
        this.selectedgrp2 = selectedgrp2;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = 1;
    }

    public List<contactsbeanz> getgroups() {
        grp_contacss = new tuseraddressbookimpl().getgroupandcontact();
        return grp_contacss;
    }

    public void getgroups2() {
        listagroups = new tuseraddressbookimpl().getgroups2impl(id);
    }

    ///take theselected group of group sms


    public String selectedname(AjaxBehaviorEvent event) {
        return grpname; //return string of names selected
    }

    public void reset() {

        groups_bean = null;
        this.listno = "";
        this.Message = "";
        this.time = null;

        k.getuserlist();

    }

    public void resetfornewsms() {
        grp_contacss = null;
        groups_bean = null;
        this.Message = "";
        this.time = null;
           this.listno = "";
        k.getuserlist();
        getgroups();
        getallgroupand_contacts();
    }

    public void reset2() {
        groups_bean = null;
        this.listno = "";
        this.Message = "";
        this.time = null;
        getGroups_countz();
        k.getuserlist();
    }

    public void sendnewsms() {
        long remainigsms;
        remainigsms =checkbal.getSmsBalance();
        String[] destination;
        if (time == null) {
            time = new Date();
        }
        listno = listno.replaceAll(" ", "");
        destination = listno.split("\\r?\\n");
        noofsms = destination.length;
        if( remainigsms > 0){       
        
        if (noofsms >remainigsms) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('myDialogVar').show();");
        } else {
            System.out.println("Phone numbers selected are : \n "+listno);
            allstatus = new messagesender().getsmsstatuslist(Message, from, listno, time);
            showsmsent();
        }
        }else{
             System.out.println("No Enough credit to send  "+noofsms +"  Sms"); 
         LL = new FacesMessage("Not  Succesful", " Kindly ask for credit Recharge ");
            FacesContext.getCurrentInstance().addMessage(null, LL);
        }

    }
    ///; when confirmdialog

    public void sendnewsms2() {
       long    remainigsms ;
        if (time == null) {
            time = new Date();
        }
        System.out.println("Phone numbers selected are : \n "+listno);
      
        allstatus = new messagesender().getsmsstatuslist(Message, from, listno, time);
               showsmsent();
      
 
    }
   
    public void showsmsent() {
        facePainter.setMainContent("sms/addcontacts.xhtml");
    }

    public List<smsstatust> getsmsdetail() {

        return allstatus;
    }

    public Integer getttsms() {
        return allstatus.size();
    }

    public long getcount1() {
        columnCount = sent.length;
        return columnCount;
    }

    public void selectedaddr() {
        grpname = selectedgrp2;
    }

    public void sendgroupsms() {
         
         if (time == null) {
            time = new Date();
        }
        
        String[] destination;
        String[] contacts = null;
        destination = listno.split("\\r?\\n");
        if (destination == null) {
            System.out.println("destination null");
        } else {
            for (int d = 0; d < destination.length; d++) {

                contacts = this.getGrpContacts(destination[d]);
            }           
            
            long remainigsms = checkbal.getSmsBalance();
            noofsms = contacts.length;
            
            if(remainigsms > 0){
            if (noofsms > remainigsms) {

                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('myDialogVar').show();");

            } else {
                groupsmssender senderr = new groupsmssender();
                allstatus = senderr.getsmsstatuslist(Message, from, destination, time );
                  showsmsent();
            }

        
        }else{ 
                System.out.println("No Enough credit to send  "+noofsms +"  Sms"); 
         LL = new FacesMessage("Not  Succesful", " Kindly ask for credit Recharge ");
            FacesContext.getCurrentInstance().addMessage(null, LL);
                
                }}
    }

    public void sendgroupsms2() {

        String[] destination;
        destination = listno.split("\\r?\\n");
        if (destination == null) {
            System.out.println("destination null");
        } else {  
   
            groupsmssender senderr = new groupsmssender();          
          
            allstatus = senderr.getsmsstatuslist(Message, from, destination , time);
            showsmsentgrp();
      
        }
    }

    public void showsmsentgrp() {
        facePainter.setMainContent("sms/viewgroupsmsent.xhtml");
    }

    public void shoowaddressbygroup() {
        facePainter.setMainContent("sms/addressbook.xhtml");
    }

    public void deleteUser(Tuseraddressbook user) {

        userDao.delete(user);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void savecontact() {

        userDao.savee(this.getGrpname(), contactname, number);
        shoowaddressbygroup();
    }

    public void resetvalue() {
        contactname = "";
        number = "";
    }

    public void resetvalue2() {
        selectedgrp2 = "";
        grpname = "";
        contactname = "";
        number = "";
    }

    private String[] getGrpContacts(String grp) {
        String[] ret = null;
        grp = grp.replaceAll("\\n", "");
        grp = grp.replaceAll("\\r", "");
        if (grp.length() < 1) {
            return null;
        }
        try {
            String sql = "select distinct k.contactNumber from Tuseraddressbook k where k.userId='" + id + "' and k.groupName='" + grp + "' group by k.contactNumber";
            ret = this.getnumbervalueimpl(sql, grp);

        } catch (NumberFormatException ex1) {

            ret = null;
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
            if (lista.isEmpty()) {

                try {
                    grp = grp.replace('+', ' ');
                    grp = grp.trim();
                    Integer.parseInt(grp);
                    contacts = new String[]{grp};
                } catch (NumberFormatException ex1) {

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
        } finally {
            session.close();
        }

        return contacts;
    }

//get count  with group
    public void getGroups_countz() {

        grpcntlist = userDao.getgroupcnt();

        for (Iterator iterator = grpcntlist.iterator(); iterator.hasNext();) {
            Groups_bean employee = (Groups_bean) iterator.next();
            System.out.println(employee.getGrpnamez() + " :: " + employee.getGroup_count());
        }
    }

    public List<Groups_bean> returngrppcnt() {
        return grpcntlist;
    }
//update message



//test 
    //for grops with count
    public void getGroups_countz2() {
        grpcntlist = userDao.getgroupcnt();
        for (Iterator iterator = grpcntlist.iterator(); iterator.hasNext();) {
            Groups_bean employee = (Groups_bean) iterator.next();
            System.out.println(employee.getGrpnamez() + " :: " + employee.getGroup_count());
        }
    }

    public List<Groups_bean> getgroupcnt2() {
        List<Groups_bean> groupscnt = null;
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        groupscnt = session.createCriteria(Tuseraddressbook.class)
                .add(Restrictions.like("userId", id))
                .setProjection(Projections.projectionList()
                        .add(Projections.groupProperty("groupName"), "grpnamez")
                        .add(Projections.rowCount(), "group_count"))
                .setResultTransformer(Transformers.aliasToBean(Groups_bean.class))
                .list();
        session.getTransaction().commit();
        return groupscnt;
    }

    ////main
    public void getallgroupand_contacts() {
        grp_numbercntnwsms = userDao.getallgroupnumbez();

    }
  public String smsummary(){
      messagesender km = new messagesender();
statusummary= km.countlist();
     System.out.println(statusummary +"eeeeeeeeeeeeee");
      
    return statusummary;
}
}
