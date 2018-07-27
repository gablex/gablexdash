/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author mspace-developer
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
public class daily1 implements Serializable {
 
    private BarChartModel barModel;
    public double average1;
    public double average2;
    public double average3;
    public double average4;
    public double average5;
    public double average6;
    public double average7;
    //public double average7=0.0;
    
 
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
            String sql="SELECT count(*) as count, date(a.dateAllocated) as tarehe,avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a where DAYNAME(a.dateAllocated)='Monday' and (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and (a.dateAllocated between  DATE_ADD(curdate(), INTERVAL '-6 0:0:0' DAY_SECOND) and now()) group by tarehe order by tarehe asc";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
             if (rs.isBeforeFirst()) {
            while(rs.next())
            {
                average1 =rs.getInt("AllocationNClosedTimeDiff_Hr")/rs.getInt("count");
         
            }
            
             } else
             {  average1=0.0;

             }
            } catch (Exception e) {
            e.printStackTrace();
       
           
        }
        
     
        try {
         Connection con = Database.getConnection();
            String sql="SELECT  "
                    + "count(*)as count,date(a.dateAllocated) as tarehe,"
                    + "avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, "
                    + "avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr "
                    + "FROM tTASKS t,  tALLOCATIONS a where DAYNAME(a.dateAllocated)='Tuesday' and (t.status = 1 or t.status = 2) and  "
                    + "a.ticketNo = t.ticketNo and (a.dateAllocated between  DATE_ADD(curdate(), INTERVAL '-6 0:0:0' DAY_SECOND) and "
                    + "now()) group by tarehe order by tarehe asc";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
           
             if (rs.isBeforeFirst()) {
            while(rs.next())
            {
                average2 =rs.getInt("AllocationNClosedTimeDiff_Hr")/rs.getInt("count");
                    }
            
             } else
             {
                   average2=0.0;

             }
            
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
        try {
         Connection con = Database.getConnection();
            String sql="SELECT count as count, date(a.dateAllocated) as tarehe,avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a where DAYNAME(a.dateAllocated)='Wednesday' and (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and (a.dateAllocated between  DATE_ADD(curdate(), INTERVAL '-6 0:0:0' DAY_SECOND) and now()) group by tarehe order by tarehe asc";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
         
             if (rs.isBeforeFirst()) {
            while(rs.next())
            {
                average3 =rs.getInt("AllocationNClosedTimeDiff_Hr")/rs.getInt("count");
                           
            }
            
             } else
             {
                 
               
                    average3=0.0;

                   
               
             }
            
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
        try {
         Connection con = Database.getConnection();
            String sql="SELECT count(*) as count,  date(a.dateAllocated) as tarehe,avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a where DAYNAME(a.dateAllocated)='Thursday' and (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and (a.dateAllocated between  DATE_ADD(curdate(), INTERVAL '-6 0:0:0' DAY_SECOND) and now()) group by tarehe order by tarehe asc";

            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
             if (rs.isBeforeFirst()) {
            while(rs.next())
            {
               
                average4 =rs.getInt("AllocationNClosedTimeDiff_Hr")/rs.getInt("count");
                             
            }
            
             } else
             {
                    average4=0.0;

                
             }
            
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
        
      
        try {
         Connection con = Database.getConnection();
            String sql="SELECT count(*) as count,  date(a.dateAllocated) as tarehe,avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a where DAYNAME(a.dateAllocated)='Friday' and (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and (a.dateAllocated between  DATE_ADD(curdate(), INTERVAL '-6 0:0:0' DAY_SECOND) and now()) group by tarehe order by tarehe asc";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
           
             if (rs.isBeforeFirst()) {
            while(rs.next())
            {
                 average5 =rs.getInt("AllocationNClosedTimeDiff_Hr")/rs.getInt("count");
               
            }
            
             } else
             {
                    average5=0.0;
  
             }
            
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
        try {
         Connection con = Database.getConnection();
            String sql="SELECT count(*) as count,  date(a.dateAllocated) as tarehe,avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a where DAYNAME(a.dateAllocated)='Saturday' and (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and (a.dateAllocated between  DATE_ADD(curdate(), INTERVAL '-6 0:0:0' DAY_SECOND) and now()) group by tarehe order by tarehe asc";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
             if (rs.isBeforeFirst()) {
            while(rs.next())
            {
         
                average6=rs.getInt("AllocationNClosedTimeDiff_Hr")/rs.getInt("count");
                 
            }
            
             } else
             {
                    average6=0;  
             }
            
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
        try {
         Connection con = Database.getConnection();
            String sql="SELECT count(*) as count,  date(a.dateAllocated) as tarehe,avg((UNIX_TIMESTAMP(a.dateAllocated)  - UNIX_TIMESTAMP(t.dateReceived)) )/3600 as ReceiveNAllocationTimeDiff_Hr, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a where DAYNAME(a.dateAllocated)='Sunday' and (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and (a.dateAllocated between  DATE_ADD(curdate(), INTERVAL '-6 0:0:0' DAY_SECOND) and now()) group by tarehe order by tarehe asc";

           
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
             if (rs.isBeforeFirst()) {
            while(rs.next())
            {
                 average7 =rs.getInt("AllocationNClosedTimeDiff_Hr")/rs.getInt("count");
      
            }
            
             } else
             {
                    average7=0.0;

             }
             
             con.close();
            
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
         series1.setLabel("Days Of The Week");
          series1.set("Sunday", average7);
         series1.set("Monday", average1);
         series1.set("Tuesday", average2);
         series1.set("Wednesday", average3);
         series1.set("Thursday", average4);
         series1.set("Friday",average5);
         series1.set("Saturday",average6);
        

        model.addSeries(series1);

         
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
     
        barModel = initBarModel();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMM d");
       String monday1=monday.format(formatter);
       String today1=today.format(formatter);
         
        barModel.setTitle("Current Week Turnaround ("+ monday1+"-"+today1+")" );
        
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

        new daily1().initBarModel();
    }

              
    
 
}
