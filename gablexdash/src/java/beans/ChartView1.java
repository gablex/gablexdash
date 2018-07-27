/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author HP
 */
import dao.Database;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
import java.sql.*;
 import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class ChartView1 implements Serializable {
 
     private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        
        try{
            Connection con=Database.getConnection();
            String sql = "select t.status, s.STATUS as statusDesc, COUNT(*) as net , concat(u.firstname,' ',u.surname) as allocatedTo from tTASKS t  left join tTASKSTATUS s on t.status = s.id left join tALLOCATIONS a on a.ticketNo = t.ticketNo left join dbSMS.tUSER u on u.id = a.allocatedTo  where t.status=1  AND allocatedTo IS NOT NULL group by t.status,a.allocatedTo order by a.allocatedTo";
            PreparedStatement pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            if(rs.isBeforeFirst())
            {
             while (rs.next()) {
                 int count=10;

                 count=rs.getInt("net");
             
               
                 String AllocatedTo=rs.getString("allocatedTo");
                 boys.setLabel("Completed");
        boys.set(AllocatedTo, count);
             }
            }
        }catch(Exception e)
        {
            
        }
        
        
 
        ChartSeries girls = new ChartSeries();
           
        try{
            Connection con=Database.getConnection();
            String sql = "select t.status, s.STATUS as statusDesc, COUNT(*) as net , concat(u.firstname,' ',u.surname) as allocatedTo from tTASKS t  left join tTASKSTATUS s on t.status = s.id left join tALLOCATIONS a on a.ticketNo = t.ticketNo left join dbSMS.tUSER u on u.id = a.allocatedTo  where t.status=3  AND allocatedTo IS NOT NULL group by t.status,a.allocatedTo order by a.allocatedTo";
            PreparedStatement pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            rs.isBeforeFirst();
            {
             while (rs.next()) {
                    int count1=0;
             
                  if(rs.getInt("net")!=0)
                  {
                   
              
                 count1=rs.getInt("net");
                  }else
                  {
                      count1=0;
                  }
                 String AllocatedTo1=rs.getString("allocatedTo");
                 girls.setLabel("Pending");
        girls.set(AllocatedTo1, count1);
             }
            }
        }catch(Exception e)
        {
            
        }
        
      
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
         xAxis.setTickAngle(-30);
       
    }
     
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        ChartSeries boys = new ChartSeries();
        
 
        ChartSeries girls = new ChartSeries();
        
 
        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);
         
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
         
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");        
    }
 
}