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
import org.primefaces.model.chart.ChartSeries;
import java.sql.*;

@ManagedBean
public class TotalStaff implements Serializable {

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

//            String sql = "select  (COUNT(*))  as 'record_count' , coalesce(u.username, 'no_record'),\n"
//                    + "coalesce(t.status, 'no_record') from dbSMS.tUSER u \n"
//                    + "inner join dbTASK.tALLOCATIONS a on u.id = a.allocatedTo \n"
//                    + "inner join dbTASK.tTASKS t on a.ticketNo = t.ticketNo \n"
//                    + "where t.status = 3 GROUP BY u.username order by u.username";
            String sql = "select u.firstname,u.surname,s.STATUS as statusDesc, COUNT(*) as net , concat(u.firstname,' ',u.surname) as allocatedTo from tTASKS t  left join tTASKSTATUS s on t.status = s.id left join tALLOCATIONS a on a.ticketNo = t.ticketNo left join dbSMS.tUSER u on u.id = a.allocatedTo  where t.status=1 and DATE_FORMAT(t.completionDueDate ,'%Y%b')  = DATE_FORMAT(curdate(),'%Y%b') group by t.status,a.allocatedTo order by a.allocatedTo,s.STATUS asc";
            PreparedStatement pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();

            int count = 0;
            String firstname="";
            String surname="";
            String AllocatedTo;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    //if (rs.getInt("count")>=0) {
                    firstname=rs.getString("u.firstname");
                    surname=rs.getString("u.surname");
                   AllocatedTo= firstname.concat("  " +surname);
                    count = rs.getInt("net");

                    //}
                    //count=0;
                    System.out.format("  username for series one" + AllocatedTo + "  Count is   " + count + "\n");
                    series1.setLabel("Completed");
                    series1.set(AllocatedTo, count);
                }

            } else {
               
                  count=0;
                    System.out.format("  username series not found one"  + "  Count is   " + count + "\n");
                
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        ChartSeries series2 = new ChartSeries();
        try {
            // Load driver
            Connection con = Database.getConnection();
            // Set autocommit to false to manage it by hand

            String sql = "select u.firstname,u.surname,s.STATUS as statusDesc, COUNT(*) as net , concat(u.firstname,' ',u.surname) as allocatedTo from tTASKS t  left join tTASKSTATUS s on t.status = s.id left join tALLOCATIONS a on a.ticketNo = t.ticketNo left join dbSMS.tUSER u on u.id = a.allocatedTo  where t.status=3 and DATE_FORMAT(t.completionDueDate ,'%Y%b')  = DATE_FORMAT(curdate(),'%Y%b') group by t.status,a.allocatedTo order by a.allocatedTo,s.STATUS asc";

            PreparedStatement pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();

            int count = 0;
            String firstname="";
            String surname="";
            String AllocatedTo;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    
                   firstname=rs.getString("u.firstname");
                    surname=rs.getString("u.surname");
                        count = rs.getInt("net");
                        AllocatedTo= rs.getString("u.firstname");
                  

                    System.out.format("  username series 2" +AllocatedTo + "  COunt is   " + count + "\n");
                    series2.setLabel("Pending");
                    series2.set(AllocatedTo, count);
                }

            } else {
                
                  
count=0;
         
                    
                    System.out.format("  username series two not found" +"  Count is   " + count + "\n");
                
            }

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

    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Total Per staff in Current Month ");
        barModel.setLegendPosition("ne");
        barModel.setExtender("chartExtender");
        //barModel.setExtender("ext1");
        barModel.setDatatipFormat("%2$d");
        barModel.setSeriesColors("000099 ,CC0000,FFA500,F52F2F,A30303");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Status");
        xAxis.setTickAngle(-30);

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tasks");

    }

    public static void main(String[] x) {
        new TotalStaff().initBarModel();
        String firstName = "";
String lastName  ="";
String fullName  = firstName + " " + lastName;
System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm"+fullName);
    }

}
