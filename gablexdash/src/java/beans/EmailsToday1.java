

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
public class EmailsToday1 implements Serializable {
    
 
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
        ChartSeries series2 = new ChartSeries();
        ChartSeries series3 = new ChartSeries();
        try {
            // Load driver
            Connection con = Database.getConnection();
                    
            String sql="select  MAX(t.status) as status, MAX(s.STATUS) as statusDesc,count(*) as net from tTASKS t  left join tTASKSTATUS s on t.status = s.id where (t.status > 0 and t.msgType = 2 and UNIX_TIMESTAMP(t.completionDueDate) = UNIX_TIMESTAMP(curdate())) or (t.status = 0 and t.msgType = 2)";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
             int count1=0;
                  int count2=0;
                  int count3=0;
              if(rs.isBeforeFirst())
              {
            while(rs.next())
            {
                
                    //rs.isBeforeFirst();
         if(rs.getInt("status")==1)
                {
                    count1=rs.getInt("net");
                      
                     //System.out.println("Completed"+count1);
                }
         
         
                if(rs.getInt("status")==3)
                {
                    count2=rs.getInt("net");
                    
                       //System.out.println("Pending"+count2);
                    
                }  
                 if(rs.getInt("status")==0)
                {
                    count3=rs.getInt("net");
                    
                }
                 
               
                             
            }
            
               series1.setLabel("Completed");
                      series1.set("Status",count1 );
                      System.out.println("Completed"+count1); 
                      series2.setLabel("Pending");
                      series2.set("Status",count2 );
                       System.out.println("Pending"+count2);
                       series3.setLabel("To Allocate");
                      series3.set("Status", count3);
                     System.out.println("To Allocate"+count3);
                     
            con.close();
              }else{
                   series1.setLabel("Status");
        series1.set("No Tasks ", 0);
        series2.setLabel("Status");
        series2.set("No Tasks ", 0);
        series3.setLabel("Status");
        series3.set("No Tasks ", 0);
       
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
           //barModel.setExtender("ext1");
           barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
              barModel.setShowDatatip(false);
            barModel.setMouseoverHighlight(false);
            barModel.setShowPointLabels(true);
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");
       
    }
}