/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.functions.davFunctions;
import com.mspace1.interfac.fromsender;
import com.mspace1.model.TallowedAlphanumerics;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author mspace
 */
public class getsender  extends davFunctions implements fromsender{

    @Override
    public List<TallowedAlphanumerics> getsendername() {
        List data = null;
          String hostname = this.executeShellCommand("hostname");
  if(hostname!= null && hostname.length() > 0 && !hostname.contains("72-167-52-99") && !hostname.equalsIgnoreCase("mspace.co.ke"))
      {    

      HttpSession session1 = getsession.getSession();
   
         Session session = getSessionFactory().openSession();
    try  {
        
        session.beginTransaction();
data=session.createQuery("select k.alphanumeric from Talphanumerics k").list();
        session.getTransaction().commit();
    }catch(HibernateException k){
     }finally{
    session.close();
    }
 }
  else if(hostname!= null && hostname.length() > 0 && (hostname.contains("72-167-52-99") || hostname.equalsIgnoreCase("mspace.co.ke")))
      {
           
          HttpSession session1 = getsession.getSession();
     String username=(String)session1.getAttribute("username");
         Session session = getSessionFactory().openSession();
    try  {
        
        session.beginTransaction();
data=session.createQuery("select k.alphanumeric from TallowedAlphanumerics k where  k.username=:username")
        .setParameter("username", username)
        .list();
        session.getTransaction().commit();
    }catch(HibernateException k){
     }finally{
    session.close();
    }
    
      }
      return data;
    }
    
}
