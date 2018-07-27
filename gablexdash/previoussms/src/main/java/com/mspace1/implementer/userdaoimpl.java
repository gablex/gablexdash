/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.interfac.usersinterfc;
import com.mspace1.model.users;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;


import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author mspace
 */
public class userdaoimpl implements usersinterfc{
@Override
public void saveuser(users user){
    Session session = getSessionFactory().openSession();
    try  {
        
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit(); 
    }catch(HibernateException k){
     }finally{
    session.close();
    }
        
    }

    
@Override
    public users getUser(String username, String password) {
      users u=new users();
      Session session = getSessionFactory().openSession();
       try{ 
           session.getTransaction().begin();
         String hql1="from users u where u.username= :username and u.password= :password";
       Query query=session.createQuery(hql1);           
       query.setParameter("username", username);
            query.setParameter("password", password);
       
       
       u=(users) query.list().get(0);
       session.getTransaction().commit();

       }catch(Exception k){
          try {
              FacesContext.getCurrentInstance().getExternalContext().redirect("home.jsf");
          } catch (IOException ex) {
              Logger.getLogger(userdaoimpl.class.getName()).log(Level.SEVERE, null, ex);
          }
         }finally{
      session.close();
       }
      return u;
    }

    @Override
    public void update(users user) {
        Session session = getSessionFactory().openSession();
    try  {
        
        session.beginTransaction();
     session.update(user);
        session.getTransaction().commit();
    }catch(HibernateException k){
     }finally{
    session.close();
    }
        
    }

    @Override
    public void remove(users user) {
         Session session = getSessionFactory().openSession();
    try  {
        
        session.beginTransaction();
     session.delete(user);
        session.getTransaction().commit();
    }catch(HibernateException k){
     }finally{
    session.close();
    }
    }

    @Override
    public List<users> list() {
        List lista = null;
         Session session = getSessionFactory().openSession();
    try  {
        
        session.beginTransaction();
 lista =session.createQuery("from users").list();
        session.getTransaction().commit();
    }catch(HibernateException k){
     }finally{
    session.close();
    }
    return lista;
   }

    @Override
    public users getbyid(long id) {
         Session session = getSessionFactory().openSession();
   
        
        session.beginTransaction();
     return(users)session.load(users.class,id);
    
        
    }
    
   
    }
    

