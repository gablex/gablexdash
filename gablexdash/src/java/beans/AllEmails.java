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
public class AllEmails implements Serializable {
    
 
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
                    
            String sql="SELECT t.status, s.STATUS as statusDesc, COUNT(*) as net FROM tTASKS t  left join tTASKSTATUS s on t.status = s.id WHERE t.status>=0 AND t.msgType=2 and t.status>0 group by t.status order by t.status asc";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
          
            while(rs.next())
            {
                
              series1.setLabel("status");
        series1.set(rs.getString("statusDesc"), rs.getInt("net"));
      
                
                             
            }
             
           
             
        } catch (Exception e) {
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
         
        barModel.setTitle("All Emails Tasks");
        barModel.setLegendPosition("ne");
        barModel.setSeriesColors("58BA27,FFCC33,F74A4A,F52F2F,A30303");
             barModel.setShowDatatip(false);
            barModel.setMouseoverHighlight(false);
            barModel.setShowPointLabels(true);
//         barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
//       barModel.setExtender("colors");
      
         barModel.setDatatipFormat("%2$d");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");
       
    }
     
    
              
    
 
}