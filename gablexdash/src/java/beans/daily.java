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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

 
@ManagedBean
public class daily implements Serializable {
 
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
        try {
         Connection con = Database.getConnection();
            String sql="SELECT MAX(DAYNAME(a.dateAllocated)) as Day_Name,date(a.dateAllocated) as tarehe,avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a where (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and yearweek(a.dateAllocated)=  yearweek(curdate()) group by tarehe order by tarehe asc";
            

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            double Allocationaverage1=0.0;
    double Receivedaverage1=0.0;
   double Allocationaverage2=0.0;
    double Receivedaverage2=0.0;
   double Allocationaverage3=0.0;
     double Receivedaverage3=0.0;
    double Allocationaverage4=0.0;
    double Receivedaverage4=0.0;
   double Allocationaverage5=0.0;
     double Receivedaverage5=0.0;
     double Allocationaverage6=0.0;
     double Receivedaverage6=0.0;
     double Allocationaverage7=0.0;
    double Receivedaverage7=0.0;
           
            while(rs.next())
            {
           
               if(rs.getString("Day_Name").equals("Monday"))
               {
                  if(rs.getInt("AllocationNClosedTimeDiff_Hr")!=0){  
                     
                
                 Allocationaverage1=rs.getInt("AllocationNClosedTimeDiff_Hr");
               }
                  else{
                       Allocationaverage1=0;
                  }
                  if(rs.getInt("ReceiveNAllocationTimeDiff_Hr")!=0)
                  {
                 Receivedaverage1=rs.getInt("ReceiveNAllocationTimeDiff_Hr");
                  }else
                  {
                     Receivedaverage1= 0;
                     
                
                  }
               
                
                }
                if(rs.getString("Day_Name").equals("Tuesday"))
               {
                  if(rs.getInt("AllocationNClosedTimeDiff_Hr")!=0){  
                     
                
                 Allocationaverage2=rs.getInt("AllocationNClosedTimeDiff_Hr");
               }
                  else{
                       Allocationaverage2=0;
                  }
                  if(rs.getInt("ReceiveNAllocationTimeDiff_Hr")!=0)
                  {
                 Receivedaverage2=rs.getInt("ReceiveNAllocationTimeDiff_Hr");
                  }else
                  {
                     Receivedaverage2= 0;
                     
                
                  }
               
                
                }
                if(rs.getString("Day_Name").equals("Wednesday"))
               {
                  if(rs.getInt("AllocationNClosedTimeDiff_Hr")!=0){  
                     
                
                 Allocationaverage3=rs.getInt("AllocationNClosedTimeDiff_Hr");
               }
                  else{
                       Allocationaverage3=0;
                  }
                  if(rs.getInt("ReceiveNAllocationTimeDiff_Hr")!=0)
                  {
                 Receivedaverage3=rs.getInt("ReceiveNAllocationTimeDiff_Hr");
                  }else
                  {
                     Receivedaverage3= 0;
                     
                
                  }
               
                
                }
               if(rs.getString("Day_Name").equals("Thursday"))
               {
                  if(rs.getInt("AllocationNClosedTimeDiff_Hr")!=0){  
                     
                
                 Allocationaverage4=rs.getInt("AllocationNClosedTimeDiff_Hr");
               }
                  else{
                       Allocationaverage4=0;
                  }
                  if(rs.getInt("ReceiveNAllocationTimeDiff_Hr")!=0)
                  {
                 Receivedaverage4=rs.getInt("ReceiveNAllocationTimeDiff_Hr");
                  }else
                  {
                     Receivedaverage4= 0;
                     
                
                  }
               
                
                }
               if(rs.getString("Day_Name").equals("Friday"))
               {
                  if(rs.getInt("AllocationNClosedTimeDiff_Hr")!=0){  
                     
                
                 Allocationaverage5=rs.getInt("AllocationNClosedTimeDiff_Hr");
               }
                  else{
                       Allocationaverage5=0;
                  }
                  if(rs.getInt("ReceiveNAllocationTimeDiff_Hr")!=0)
                  {
                 Receivedaverage5=rs.getInt("ReceiveNAllocationTimeDiff_Hr");
                  }else
                  {
                     Receivedaverage5= 0;
                     
                
                  }
               
                
                }
               if(rs.getString("Day_Name").equals("Saturday"))
               {
                  if(rs.getInt("AllocationNClosedTimeDiff_Hr")!=0){  
                     
                
                 Allocationaverage6=rs.getInt("AllocationNClosedTimeDiff_Hr");
               }
                  else{
                       Allocationaverage6=0;
                  }
                  if(rs.getInt("ReceiveNAllocationTimeDiff_Hr")!=0)
                  {
                 Receivedaverage6=rs.getInt("ReceiveNAllocationTimeDiff_Hr");
                  }else
                  {
                     Receivedaverage6= 0;
                     
                
                  }
               
                
                }
               if(rs.getString("Day_Name").equals("Sunday"))
               {
                  if(rs.getInt("AllocationNClosedTimeDiff_Hr")!=0){  
                     
                
                 Allocationaverage7=rs.getInt("AllocationNClosedTimeDiff_Hr");
               }
                  else{
                       Allocationaverage7=0;
                  }
                  if(rs.getInt("ReceiveNAllocationTimeDiff_Hr")!=0)
                  {
                 Receivedaverage7=rs.getInt("ReceiveNAllocationTimeDiff_Hr");
                  }else
                  {
                     Receivedaverage7= 0;
                     
                
                  }
               
                
                }
               
               
               
               //////////////////////////////////////
              
           
              
                
          
            }
       
          series1.setLabel("Allocated Time Average");
          series1.set("Sunday", Allocationaverage7);
         series1.set("Monday", Allocationaverage1);
         series1.set("Tuesday", Allocationaverage2);
         series1.set("Wednesday", Allocationaverage3);
         series1.set("Thursday", Allocationaverage4);
         series1.set("Friday",Allocationaverage5);
         series1.set("Saturday",Allocationaverage6);
         series2.setLabel("Received Time Average");
         series2.set("Sunday", Receivedaverage7);
         series2.set("Monday",Receivedaverage1);
         series2.set("Tuesday", Receivedaverage2);
         series2.set("Wednesday", Receivedaverage3);
         series2.set("Thursday", Receivedaverage4);
         series2.set("Friday",Receivedaverage5);
         series2.set("Saturday",Receivedaverage6);
        
            
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
      
        
        
 
        model.addSeries(series1);
        model.addSeries(series2);
        
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();
        
       
    }
     
    private void createBarModel() {
         LocalDate today = LocalDate.now();

    // Go backward to get Monday
    LocalDate monday = today;
    while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
      monday = monday.minusDays(1);
    }

    // Go forward to get Sunday
    LocalDate sunday = today;
    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
      sunday = sunday.minusDays(1);
      
    }
     System.out.println("Sunday is   "+sunday);
        barModel = initBarModel();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMM d");
       String monday1=monday.format(formatter);
       String sunday1=sunday.format(formatter);
       String today1=today.format(formatter);
         
        barModel.setTitle("Current Week Turnaround (" +sunday1 +"-"+today1 +")");
        
        barModel.setLegendPosition("ne");
           //barModel.setExtender("chartExtender");
            barModel.setDatatipFormat("%2$d");
             barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Days Of The Week");
          xAxis.setTickAngle(-30);
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Turnaround time in hours");
     
    }
     
     public static void main(String[] x) {
        new daily().createBarModel();
    }

              
    
 
}
