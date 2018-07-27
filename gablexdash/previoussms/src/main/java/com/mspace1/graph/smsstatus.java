/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.graphs;

import com.mspace1.model.graphvalue;
import com.mspace1.model.smscondition;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author mspace
 */
@ManagedBean(name = "smsstatus")
@ApplicationScoped
public class smsstatus {
 private graphvalue grap;
    private BarChartModel animatedModel2;

   
   

   
   
    @PostConstruct
    public void ChartView() {
        try {
            createAnimatedModels();
        } catch (Exception k) {
        }
    }
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() throws ClassNotFoundException, SQLException {
        animatedModel2 = initBarModel();
        animatedModel2.setLegendPosition("ne");
        animatedModel2.setDatatipFormat("%2$d");
        animatedModel2.setAnimate(true);
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        xAxis.setTickAngle(-30);
    }

    private BarChartModel initBarModel() throws ClassNotFoundException {
        Session session = getSessionFactory().openSession();
        graphvalue cust = new graphvalue();
         ChartSeries boys = new ChartSeries();
        smscondition cust2 = new smscondition();
        try {
            session.getTransaction().begin();
           DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd000000");
            Date date = new Date();
            String k = (dateFormat.format(date));
            String sql4 = "select graphval.status ,COUNT(graphval.status) from graphvalue graphval where (graphval.status>0 and graphval.time_submitted > '" + k + "')or graphval.status=0 GROUP BY graphval.status";
           
            Query query = session.createQuery(sql4);
            for (Iterator it = query.iterate(); it.hasNext();) {
                Object[] row = (Object[]) it.next();
                if (((int) row[0])== 0) {
                    cust2.setTo_be_sent((int)row[1]);
                   
                }
                if (((int) row[0]) == 1) {
                    cust2.setSubmitted_to_network((int) row[1]);
                }
                if (((int) row[0])== 2) {
                    cust2.setSuccesfully_sent((int) row[1]);
                }
                if (((int) row[0])== 3) {
                    cust2.setDelivered((int) (row[1]));
                }
                if (((int) row[0])==  4) {
                    cust2.setNetwork((int) row[1]);
                }
                if (((int) row[0])== 5) {
                    cust2.setUndelivered((int) row[1]);
                }
                if (((int) row[0]) == 6) {
                    cust2.setExpired((int) row[1]);
                }
                if (((int) row[0]) == 7) {
                    cust2.setSubmit_failed((int) row[1]);
                }
                if (((int) row[0]) ==8) {
                    cust2.setNetwork2((int) row[1]);
                }
                if (((int) row[0]) == 11) {
                    cust2.setScheduled((int) row[1]);
                }
            }
            session.getTransaction().commit();
 } catch (Exception k) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("../user/home.jsf");
            } catch (IOException ex) {
                Logger.getLogger(smsstatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            session.close();
            
        }

        BarChartModel model = new BarChartModel();
      boys.setLabel("Sent SMS");
      
       boys.set("to be sent", cust2.getTo_be_sent());
        boys.set("submitted-network", cust2.getSubmitted_to_network());
        boys.set("successfully sent", cust2.getSuccesfully_sent());
        boys.set("deliverd", cust2.getDelivered());
        boys.set("Network", cust2.getNetwork() + cust2.getNetwork2());
        boys.set("undelivered", cust2.getUndelivered());
        boys.set("expired", cust2.getExpired());
        boys.set("submit failed", cust2.getSubmit_failed());
        boys.set("scheduled", cust2.getScheduled());
           model.addSeries(boys);
    

        return model;
    }

}
