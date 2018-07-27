/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        if(sessionFactory==null){
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory= metadata.getSessionFactoryBuilder().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("DB Connection error");
            return null;
        }
           
        }  
        return sessionFactory;
    }
    public static SessionFactory getSessionFactory() {
//       SessionFactory sessionFactory = buildSessionFactory();
        try {
            return sessionFactory;
        } catch (Exception k) {
            System.out.println("error2  while bulding session factory  object due to connnection error");

            return null;

        }
    }
    
    
  

  
    
    
}
