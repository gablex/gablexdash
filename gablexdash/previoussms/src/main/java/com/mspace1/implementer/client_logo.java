/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.implementer;

import com.mspace1.model2.Tclient;
import static com.mspace1.util.HibernateUtil2.getSessionFactory;
import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mspace
 */
public class client_logo  implements Serializable{
private String logopath;
private Tclient tclient;

   public void init() {
        Tclient  k= new Tclient();
   }

    public Tclient getTclient() {
        return tclient;
    }

    public void setTclient(Tclient tclient) {
        this.tclient = tclient;
    }
   
    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }
    /**
     * Creates a new instance of logo_client
     */

    /**
     * Creates a new instance of logo_client
     * @return
     */
     
     
    public String clnt_logo(){ 
    
        Session session1 = null; 
        
        try{
            session1 = getSessionFactory().openSession();
            session1.beginTransaction();
             Query query;
             query = session1.createQuery("from Tclient ");
          tclient=(Tclient) query.list().get(0);
        session1.getTransaction().commit();
              logopath=tclient.getPicPath();
        }catch(Exception e){
            System.out.println("Check your logo path location or if the logo image do exist");
            logopath="files/config/MSpacelogo.png";
//        e.printStackTrace();
        }
        finally{
            if(session1!=null){
        session1.close();
      }else{
               System.out.println("database connection error /unable to get logo path");
                
            }} 


             
    return logopath;
    }
    
    
    
    
}
