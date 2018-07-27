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
public class PerStaff implements Serializable {
 
    private BarChartModel barModel;
    
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    
    
 


     public void storeUser() {
        // Create connection
        
     }
    private BarChartModel initBarModel() {
        
        BarChartModel model = new BarChartModel();
 
        ChartSeries series1 = new ChartSeries();
        try {
           Connection con = Database.getConnection();
                    
            String sql="SELECT COUNT(*), t.status,u.username from dbTASK.tTASKS t , dbSMS.tUSER u, dbTASK.tALLOCATIONS a WHERE t.status=3 AND DATE_SUB(CURDATE(),INTERVAL 1  MONTH) <= t.completionDueDate group by u.username order by u.username";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
           series1.setLabel("Pending");
        series1.set(rs.getString("u.username"),  rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
      
 
        ChartSeries series2 = new ChartSeries();
        try {
           Connection con = Database.getConnection();
                    
            String sql1="SELECT COUNT(*), t.status,u.username from dbTASK.tTASKS t , dbSMS.tUSER u, dbTASK.tALLOCATIONS a WHERE t.status=1 AND DATE_SUB(CURDATE(),INTERVAL 1  MONTH) <= t.completionDueDate group by u.username order by u.username";

           
            PreparedStatement pt=con.prepareStatement(sql1);    
            ResultSet rs1=pt.executeQuery();
            
            while(rs1.next())
            {
                  series2.setLabel("Completed");
        series2.set(rs1.getString("u.username"), rs1.getInt("COUNT"));
      
                
                             
            }
             
           con.close();
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
        model.addSeries(series1);
        model.addSeries(series2);
         //model.addSeries(series3);
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
       ;
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Per Staff"); 
        //barModel.setLegendPosition("ne");
         barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Staff");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Status");
        
    }
       
    
              
    
 
}