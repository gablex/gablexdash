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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Formatter;
 import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class AverageStaff implements Serializable {
 
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
            // Set autocommit to false to manage it by hand
                    
            String sql="SELECT  concat(u.firstname, ' ',u.surname) as name, avg((UNIX_TIMESTAMP(t.closedDate)  - UNIX_TIMESTAMP(a.dateAllocated))) /3600 as AllocationNClosedTimeDiff_Hr FROM tTASKS t,  tALLOCATIONS a, dbSMS.tUSER u where (t.status = 1 or t.status = 2) and  a.ticketNo = t.ticketNo and u.id = a.allocatedTo and yearweek(a.dateAllocated)=  yearweek(curdate())  group by u.username order by name asc";
            PreparedStatement pt=con.prepareStatement(sql);    
            ResultSet rs=pt.executeQuery();
            
             double avg=0.0;
             String FirstName="";
            if (rs.isBeforeFirst()) {
            while(rs.next())
            {
                FirstName=rs.getString("name");
              avg =rs.getInt("AllocationNClosedTimeDiff_Hr");
              series1.setLabel(rs.getString("name"));
        series1.set(rs.getString("name"), avg);
              System.out.println(FirstName+"average is "+avg);
            }
            }else
            {
                avg=0.0;

              series1.setLabel("Average per staff");
        series1.set("No Tasks ", avg);
      
        System.out.println("No data for "+"average is "+avg);
                
            }
            
           
             
        } catch (Exception e) {
            e.printStackTrace();
           
        }
        
      
 
     
      
        
        
        
 
        model.addSeries(series1);
        //model.addSeries(series2);
        //model.addSeries(series3);
         
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
        String sunday1=sunday.format(formatter);
       String today1=today.format(formatter);
       barModel.setExtender("ext1");
        barModel.setTitle("Turnaround per staff for the Current week ("+ sunday1+"-"+today1+")" );
       // System.out.println("datttettttttttttttt"+dateFormatter.format(monday));
          //barModel.setExtender("chartExtender1");
           barModel.setDatatipFormat("%2$d");
           barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Staff");
         xAxis.setTickAngle(-30);
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Turnaround time in hours");
       
        
        
           
    }
     
     public static void main(String[] x) {
         
        new AverageStaff().initBarModel();
    }

              
    
 
}