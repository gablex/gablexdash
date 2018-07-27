
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
public class EmailsToday implements Serializable {
    
 
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
                    
            String sql="select  t.status, s.STATUS as statusDesc,count(*) as net from tTASKS t  left join tTASKSTATUS s on t.status = s.id where (t.status =1 and t.msgType = 2 ) ";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
              series1.setLabel("Completed");
        series1.set("Status", rs.getInt("net"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
      
 
        ChartSeries series2 = new ChartSeries();
        try {
            // Load driver
           Connection con = Database.getConnection();
                    
            String sql="select  t.status, s.STATUS as statusDesc,count(*) as net from tTASKS t  left join tTASKSTATUS s on t.status = s.id where (t.status = 3 and t.msgType = 2 and UNIX_TIMESTAMP(t.completionDueDate) = UNIX_TIMESTAMP(curdate())) \n" +
"";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series2.setLabel("Pending");
        series2.set("Status", rs.getInt("net"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        ChartSeries series3 = new ChartSeries();
        try {
          Connection con = Database.getConnection();
            // Set autocommit to false to manage it by hand
                    
            String sql="select  t.status, s.STATUS as statusDesc,count(*) as net from tTASKS t  left join tTASKSTATUS s on t.status = s.id where (t.status = 0 and t.msgType = 2 and UNIX_TIMESTAMP(t.completionDueDate) = UNIX_TIMESTAMP(curdate())) \n" +
"";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
            while(rs.next())
            {
                  series3.setLabel("To Allocate");
        series3.set("Status", rs.getInt("net"));
      
                
                             
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
         
        barModel.setTitle("All Email Tasks Due Today");
        barModel.setLegendPosition("ne");
           //barModel.setExtender("chartExtender");
            barModel.setDatatipFormat("%2$d");
            barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");
       
    }
     
    
              
    
 
}