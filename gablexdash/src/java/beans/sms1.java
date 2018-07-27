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
public class sms1 implements Serializable {
 
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
            Connection con = Database.getConnection1();
                    
            String sql="select t.desctiption as status, count(*) as net from tSMSOUT s left join tSMSSTATUS t on t.id = s.status  where (s.status > 0 and s.time_submitted > (DATE_ADD(curdate(), INTERVAL 0 SECOND) + 0)) or (s.status = 0) group by t.desctiption order by net desc";
           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            String statusName="";
            int count=0;
        if (rs.isBeforeFirst()) {
            while(rs.next())
            {
                count=rs.getInt("net");
                  series1.setLabel("SMS");
        series1.set(rs.getString("status"),count );
      
                
                             
            }
        }else
        {
            count=0;
             series1.setLabel("No Messages");
         series1.set("No messages",0 );
        }
       con.close();  
           
             
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            
           
        }
      model.addSeries(series1);
        
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
       
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("SMS Status Today");
        barModel.setLegendPosition("ne");
        //barModel.setExtender("chartExtender");
      barModel.setDatatipFormat("%2$d");
      barModel.setExtender("ext1");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Status");
        barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
             xAxis.setTickAngle(-30);
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");
       
    }
     
    
              
    
 
}