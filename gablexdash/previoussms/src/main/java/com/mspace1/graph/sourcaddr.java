/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.graphs;

import com.mspace1.model.graphvalue;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import java.io.IOException;
import java.io.Serializable;
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

@ManagedBean(name = "sourcaddr")
@ApplicationScoped
public class sourcaddr implements Serializable{

    private graphvalue grap;
    private BarChartModel animatedModel2;

    @PostConstruct
    public void sourcaddr() {
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
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        animatedModel2.setShadow(true);
        animatedModel2.setZoom(true);
        animatedModel2.setShowDatatip(true);
        animatedModel2.setDatatipFormat("%2$d");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        xAxis.setTickAngle(-45);

    }

    private BarChartModel initBarModel() throws ClassNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd000000");
        Date date = new Date();
        String k = (dateFormat.format(date));
        String sql4 = "select graphval.source_addr ,COUNT(graphval.source_addr) from graphvalue graphval where graphval.status>=0 and graphval.time_submitted > '" + k + "' GROUP BY graphval.source_addr";
   BarChartModel model = new BarChartModel();
        graphvalue val = new graphvalue();
        Session session = getSessionFactory().openSession();
        ChartSeries address = new ChartSeries();
        try {
            session.getTransaction().begin();
            
            Query query = session.createQuery(sql4);
            for (Iterator it = query.iterate(); it.hasNext();) {
                Object[] row = (Object[]) it.next();

                address.setLabel((String) (row[0]));
                address.set((String) (row[0]), (Number) row[1]);
            }
            session.getTransaction().commit();
            model.addSeries(address);
        } catch (Exception m) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/user/index.jsf");
            } catch (IOException ex) {
                Logger.getLogger(sourcaddr.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            session.close();
        }

        return model; 
    }

}
