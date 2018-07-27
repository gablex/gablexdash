/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.model.Groups_bean;
import com.mspace1.model.contactsbeanz;
import com.mspace1.model.group_contact;
import com.mspace1.interfac.tuseraddressbook;
import com.mspace1.model.Tuseraddressbook;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author mspace
 */
public class tuseraddressbookimpl implements tuseraddressbook {

    HttpSession sessionm = getsession.getSession();
    long id = (long) sessionm.getAttribute("id");
    private List<Tuseraddressbook> lista;

    @Override
    public List<Tuseraddressbook> getgroupsimpl(String sql1) {
        List lista = null;
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            lista = session.createQuery(sql1).list();
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }

        return lista;
    }

    @Override
    public List<Tuseraddressbook> getgroups2impl(long id) {

        Session session = getSessionFactory().openSession();
        try {
            String sql1 = "select distinct k.groupName from Tuseraddressbook k where  k.userId=:id  order by k.groupName asc";
            session.beginTransaction();
            lista = session.createQuery(sql1).
                    setParameter("id", id).
                    list();
            session.getTransaction().commit();
        } catch (HibernateException k) {
        } finally {
            session.close();
        }
        return lista;
    }

    @Override
    public List<Tuseraddressbook> alluser(String groupnamex) {
        List<Tuseraddressbook> DaoAllUsers = null;
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
//  = session.createCriteria(Tuseraddressbook.class).list(); 
            DaoAllUsers = session.createQuery("from Tuseraddressbook k where  k.userId=:id and k.groupName='" + groupnamex + "'  order by k.id desc").
                    setParameter("id", id).
                    list();
            int count = DaoAllUsers.size();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return DaoAllUsers;
    }

    public void update(Tuseraddressbook user) {
        Session session = getSessionFactory().openSession();
        try {
            String no = user.getContactNumber();
            String grpname = user.getGroupName();
            String name = user.getContactName();
            session.beginTransaction();
            session.update(user);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
    }

    public void delete(Tuseraddressbook user) {
        Session session = getSessionFactory().openSession();
        try {

            long id = user.getId();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            session.close();
        }
    }

    public int savee(String grpname, String contactname, String number) {
        int stat = 0;
        if (grpname == null || grpname.length() == 0) {
            grpname = "Default";
        }
        HttpSession sessionm = getsession.getSession();
        if (number.substring(0, 1).equals("0")) {
            number = "+254" + number.substring(1);
        }

        long id = (long) sessionm.getAttribute("id");
        String user2 = (String) sessionm.getAttribute("username");
        Session session4 = getSessionFactory().openSession();
        try {

            Tuseraddressbook inse = new Tuseraddressbook();
            inse.setGroupName(grpname);
            inse.setContactName(contactname);
            inse.setContactNumber(number);
            inse.setUserId(id);

            session4.beginTransaction();
            session4.save(inse);
            session4.getTransaction().commit();
            stat = 1;
            String kl = "insert into tUSERADDRESSBOOK (group_name,contact_name,contact_number,user_id) values ('" + grpname + "','" + contactname + "','" + number + "','" + id + "')";
            java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyyMMdd");
            System.out.println(kl);
            String fileLoc = "";
            String os = System.getProperty("os.name");
            String catalina = System.getProperty("catalina.home");
            System.out.println("operating system: " + os);
            System.out.println("Catalinahome: " + catalina);
            fileLoc = catalina + "/logs/" + dformat.format(new java.util.Date()) + "_web_addressbk.log";
            try {
                File outputFile = new File(fileLoc);
                FileWriter out = new FileWriter(outputFile, true);
                out.write(new java.util.Date() + "\t" + user2 + "\t" + id + "\t"
                        + kl + "\n");
                out.flush();
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("inserter executing contact add ...");
        } catch (Exception k) {
            k.printStackTrace();
            stat = 0;
        } finally {
            session4.close();

        }

        return stat;
    }

    //group list with count 
    public List<Groups_bean> getgroupcnt() {
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

    //delete by group
    public void deleteaddrbygroup(Groups_bean user) {
        String grppname = user.getGrpnamez();
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("delete Tuseraddressbook k where k.groupName=:groupName");
            q.setParameter("groupName", grppname);

            q.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Group " + grppname + " in contact address deleted ! ");
        } catch (Exception e) {
            System.out.println("Error deleteng contacts of group" + grppname);
            session.getTransaction().rollback();
            session.close();
        }
    }

    //get contact name and value of  its contact no
    public List<contactsbeanz> getgroupandcontact() {
        List<contactsbeanz> groupcnt = null;
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        groupcnt = session.createCriteria(Tuseraddressbook.class)
                .add(Restrictions.like("userId", id))
                .setProjection(Projections.projectionList()
                        .add(Projections.property("contactName"), "contactname")
                        .add(Projections.property("contactNumber"), "contactNo"))
                .setResultTransformer(Transformers.aliasToBean(contactsbeanz.class))
                .list();
        session.getTransaction().commit();
        return groupcnt;
    }

    //newsms get group contacsts
    public List<group_contact> getallgroupnumbez() {
        List<group_contact> listaa = new ArrayList<>();

        String sqle = "select distinct k.groupName from Tuseraddressbook k where  k.userId=:id order by k.groupName asc";
        List listas = null;
        Session session = getSessionFactory().openSession();
        try {
            session.beginTransaction();
            listas = session.createQuery(sqle)
                    .setParameter("id", id)
                    .list();
            session.getTransaction().commit();
        } catch (HibernateException k) {
            k.printStackTrace();
        }

        try {
            if (listas != null) {
                int i;
                for (i = 0; i < listas.size(); i++) {
                    String lnah = "";
                    lnah = (String) listas.get(i);
                    String sqlp = "select distinct k.contactNumber from Tuseraddressbook k where k.userId=:id and k.groupName='" + lnah + "'";

                    List lista2 = null;
                    try {
                        session.beginTransaction();
                        lista2 = session.createQuery(sqlp)
                                .setParameter("id", id)
                                .list();
                        session.getTransaction().commit();
                    } catch (HibernateException k) {
                    }
                    long mk = 0l;
                    String listString = "";
                    for (Object s : lista2) {
                        listString = s + "\n" + listString;
                        mk++;
                    }

                    group_contact kevol = new group_contact();
                    kevol.setGroupname_count(mk);
                    kevol.setGroup_name(lnah);
                    kevol.setNumberz(listString);

                    listaa.add(kevol);

                }
            }
        } catch (Exception kn) {
            kn.printStackTrace();
        } finally {
            session.close();
        }
        return listaa;
    }

}
