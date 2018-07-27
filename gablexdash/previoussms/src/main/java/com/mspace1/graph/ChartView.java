/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.graphs;
import com.mspace1.util.getsession;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
 
@ManagedBean
public class ChartView implements Serializable {
 HttpSession  sessionm = getsession.getSession(); ;
        
    private BarChartModel animatedModel2;
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
      
 
    }
 
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
 
    private void createAnimatedModels() {
      animatedModel2 = initBarModel();
        animatedModel2.setLegendPosition("ne");
         animatedModel2.setTitle("Sent Sms Report");
        animatedModel2.setDatatipFormat("%2$d");
        animatedModel2.setAnimate(true);
        
               Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        xAxis.setTickAngle(-30);
    }
     
    private BarChartModel initBarModel() {
       
        BarChartModel model = new BarChartModel();
  
        ChartSeries boys = new ChartSeries();
         
        
        boys.setLabel("Sent Sms");
          long maxtt=(long) sessionm.getAttribute("max_total");
        if(maxtt <0){
               maxtt=0;
        }
    
        
        
        boys.set("Sent  today",  (long) sessionm.getAttribute("counttoday"));
        boys.set("Sent this week", (long) sessionm.getAttribute("countweek"));
        boys.set("Sent  this month", (long) sessionm.getAttribute("countmonth"));
        boys.set("Total sms sent ", (long) sessionm.getAttribute("counttotoal"));
           boys.set("Current sms Balance ", maxtt);
        
          
        model.addSeries(boys);
   
         
        return model;
    }
     
    
    
}