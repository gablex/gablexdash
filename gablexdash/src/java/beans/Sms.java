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
public class Sms implements Serializable {
 
    private BarChartModel barModel;
    
 
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    
    
 


    
    private BarChartModel initBarModel() {
        
        BarChartModel model = new BarChartModel();
 
        ChartSeries series1 = new ChartSeries();
        try {
          Connection con = Database.getConnection();
            String sql="SELECT COUNT(*) FROM tSMSOUT WHERE status=0 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
              series1.setLabel("Pending");
        series1.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
      
 
        ChartSeries series2 = new ChartSeries();
        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dbSMS";
            // Connect to the database
           String username = "mysql";
		String password = "mysql123";
            Connection con =  DriverManager.getConnection(url, username, password);
            // Set autocommit to false to manage it by hand
                    
            String sql="SELECT status,COUNT(*) FROM tSMSOUT WHERE status=1 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series2.setLabel("Pending");
        series2.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        ChartSeries series3 = new ChartSeries();
        try {
            Connection con = Database.getConnection();
                    
            String sql="SELECT status,COUNT(*) FROM tSMSOUT WHERE status=3 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series3.setLabel("Sent");
        series3.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        ChartSeries series4 = new ChartSeries();
        try {
            Connection con = Database.getConnection();
            String sql="SELECT status,COUNT(*) FROM tSMSOUT WHERE status=5 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series4.setLabel("Undelivered");
        series4.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        ChartSeries series6 = new ChartSeries();
        try {
           Connection con = Database.getConnection();
            String sql="SELECT status,COUNT(*) FROM tSMSOUT WHERE status=6 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series6.setLabel("Expired");
        series6.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
       ChartSeries series7 = new ChartSeries();
        try {
          Connection con = Database.getConnection();
                    
            String sql="SELECT status,COUNT(*) FROM tSMSOUT WHERE status=7 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series7.setLabel("Sunmited failed");
        series7.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
       ChartSeries series8 = new ChartSeries();
        try {
            Connection con = Database.getConnection();
            String sql="SELECT status,COUNT(*) FROM tSMSOUT WHERE status=8 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series8.setLabel("Network");
        series8.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
       ChartSeries series9 = new ChartSeries();
        try {
            Connection con = Database.getConnection();
                    
            String sql="SELECT status,COUNT(*) FROM tSMSOUT WHERE status=6 AND DATE(time_submitted) = DATE(NOW()) OR  DATE(time_processed) = DATE(NOW())";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series9.setLabel("Expired");
        series9.set("", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
       
 
        model.addSeries(series1);
        model.addSeries(series2);
        model.addSeries(series3);
         model.addSeries(series4);
         model.addSeries(series6);
         model.addSeries(series7);
         model.addSeries(series8);
         model.addSeries(series9);
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
       
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Sms");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Status");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");
      yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    
              
    
 
}
