
package beans;

/**
 *
 * @author HP
 */
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;
import java.sql.*;
import dao.Database;
 import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class DueToday implements Serializable {
    
 
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
            // Load driver
            Connection con = Database.getConnection();
                    
            String sql="SELECT COUNT(*), status from tTASKS WHERE status=0  ";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
              series1.setLabel("To Allocate");
        series1.set("Status", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
      
 
        ChartSeries series2 = new ChartSeries();
        try {
            // Load driver
           Connection con = Database.getConnection();
                    
            String sql="SELECT COUNT(*), status from tTASKS WHERE status=1 and DATE(completionDueDate) = DATE(NOW())";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series2.setLabel("Completed");
        series2.set("Status", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        ChartSeries series3 = new ChartSeries();
        try {
          Connection con = Database.getConnection();
            // Set autocommit to false to manage it by hand
                    
            String sql="SELECT COUNT(*), status from tTASKS WHERE status=3 and DATE(completionDueDate) = DATE(NOW())";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series3.setLabel("Pending");
        series3.set("Status", rs.getInt("COUNT(*)"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
        
 
        model.addSeries(series1);
        model.addSeries(series2);
        model.addSeries(series3);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
       
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("All Tasks Due Today");
        barModel.setLegendPosition("ne");
           //barModel.setExtender("chartExtender");
            barModel.setDatatipFormat("%2$d");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Status");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");
       
    }
     
    
              
    
 
}