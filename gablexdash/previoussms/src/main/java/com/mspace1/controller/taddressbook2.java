/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.model.Groups_bean;
import com.mspace1.implementer.tuseraddressbookimpl;
import com.mspace1.model.Tuseraddressbook;
import com.mspace1.navigationcontroller.FacePainter;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "taddressbook2")
@ViewScoped
public class taddressbook2 implements Serializable {

    fileuploader fb = new fileuploader();

    private List<Tuseraddressbook> usersList;
    private Tuseraddressbook user;
    private List<Tuseraddressbook> address;
    tuseraddressbookimpl userDao = new tuseraddressbookimpl();
    private List<Tuseraddressbook> filteredaddress;
    private List<Groups_bean> grpcntlist;
    private String groupnamex;
    @ManagedProperty(value = "#{facePainter}")
    public FacePainter facePainter;
   @ManagedProperty(value = "#{tuseraddressbook}")
    public tuseraddressbook tuseraddressbookz;
   
  
    public FacePainter getFacePainter() {
        return facePainter;
    }

    public void setFacePainter(FacePainter facePainter) {
        this.facePainter = facePainter;
    }

    public tuseraddressbook getTuseraddressbookz() {
        return tuseraddressbookz;
    }

    public void setTuseraddressbookz(tuseraddressbook tuseraddressbookz) {
        this.tuseraddressbookz = tuseraddressbookz;
    }
    
    
    

    public String getGroupnamex() {
        return groupnamex;
    }

    public void setGroupnamex(String groupnamex) {
        this.groupnamex = groupnamex;
    }

    public List<Groups_bean> getGrpcntlist() {
        return grpcntlist;
    }

    public void setGrpcntlist(List<Groups_bean> grpcntlist) {
        this.grpcntlist = grpcntlist;
    }

    public List<Tuseraddressbook> getFilteredaddress() {
        return filteredaddress;
    }

    public void setFilteredaddress(List<Tuseraddressbook> filteredaddress) {
        this.filteredaddress = filteredaddress;
    }

    public List<Tuseraddressbook> getAddress() {
        return address;
    }

    public void setAddress(List<Tuseraddressbook> address) {
        this.address = address;
    }

    public Tuseraddressbook getUser() {
        return user;
    }

    public void setUser(Tuseraddressbook user) {
        this.user = user;
    }

    public List<Tuseraddressbook> getcontacts() {

        if (this.getUsersList() == null || usersList.isEmpty()) {
            usersList = userDao.alluser(groupnamex);
            return usersList;
        } else {
            return usersList;
        }

    }

    public void onRowEdit(RowEditEvent event) {

        FacesMessage msg = new FacesMessage(" Edited Record No", ((Tuseraddressbook) event.getObject()).getContactNumber());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Tuseraddressbook editeduser = (Tuseraddressbook) event.getObject();
        userDao.update(editeduser);

    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void deleteUser(Tuseraddressbook user) {

        userDao.delete(user);
        usersList.remove(user);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");

        RequestContext.getCurrentInstance().showMessageInDialog(message);

    }

    public List<Tuseraddressbook> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Tuseraddressbook> usersList) {
        this.usersList = usersList;
    }

    public void setnull() {
tuseraddressbookz.resetvalue2();
        fb.reset();
        usersList = null;
    }
        public void setnull3() {                      
tuseraddressbookz.resetvalue2();
        fb.reset();
        usersList = null;
    
    }

    public void setnull2() {
//        fb.reset();
        usersList = null;
tuseraddressbookz.resetvalue();
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("address Selected", ((Tuseraddressbook) event.getObject()).getContactName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("address Unselected", ((Tuseraddressbook) event.getObject()).getContactNumber());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteselected() {

        for (Tuseraddressbook temp : address) {
            Session session = getSessionFactory().openSession();
            try {
                session.beginTransaction();
                Query q = session.createQuery("delete Tuseraddressbook k where k.id=:id");
                q.setParameter("id", temp.getId());
                q.executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
                session.close();
            }
        }

    }
    public List<Groups_bean>retngrpcntlist(){
        tuseraddressbookimpl userDao1 = new tuseraddressbookimpl();
     grpcntlist = userDao1.getgroupcnt();
        return grpcntlist;
    }


    //delete group
    public void deleteselectedgroup(Groups_bean user) {
        userDao.deleteaddrbygroup(user);
        grpcntlist.remove(user);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Group: " + user.getGrpnamez() + " numbers  deleted successfully");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void viewContactBygroup(Groups_bean user) {
        groupnamex = user.getGrpnamez();
        tuseraddressbookz.setGrpname(groupnamex);
        viewcontactbygroup();
    }

    public void viewcontactbygroup() {
        facePainter.setMainContent("sms/addressbycontact.xhtml");
    }

}
