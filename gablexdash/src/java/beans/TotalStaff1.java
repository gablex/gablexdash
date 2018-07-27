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
import org.primefaces.model.chart.ChartSeries;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@ManagedBean
public class TotalStaff1 implements Serializable {

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    
    private void createBarModels() {
        createBarModel();

    }

    private void createBarModel() {
        barModel = initBarModel2();

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
        new TotalStaff1().initBarModel2();
    }

    private BarChartModel initBarModel2() {

        BarChartModel model = new BarChartModel();
        ChartSeries series1 = new ChartSeries();
        ChartSeries series2 = new ChartSeries();

        try (Connection con = Database.getConnection()) {

             String sql = "select s.STATUS as statusDesc,t.status, COUNT(*) as net , concat(u.firstname,' ',u.surname) as allocatedTo from tTASKS t  left join tTASKSTATUS s on t.status = s.id left join tALLOCATIONS a on a.ticketNo = t.ticketNo left join dbSMS.tUSER u on u.id = a.allocatedTo  where t.status in (1,3) and DATE_FORMAT(t.completionDueDate ,'%Y%b')  = DATE_FORMAT(curdate(),'%Y%b') group by t.status,a.allocatedTo order by a.allocatedTo,s.STATUS asc";

            try (PreparedStatement pt = con.prepareStatement(sql)) {

                ResultSet rs = pt.executeQuery();

                if (rs.isBeforeFirst()) {

                    while (rs.next()) {
                        int count1 = 0;
                        int count2 = 0;
                        String AllocatedTo = "";
                        String AllocatedTo2 = "";

                        if (rs.getInt("t.status") == 1) {
                            count1 = rs.getInt("net");
                            AllocatedTo = rs.getString("allocatedTo");
                            series1.setLabel("Completed");
                            series1.set(AllocatedTo, count1);

                        }else{
                             series1.setLabel("Completed");
                            series1.set(AllocatedTo, 0);
                        }
                            
                       
                        
                        if (rs.getInt("t.status") == 3) {

                            AllocatedTo2 = rs.getString("allocatedTo");
                            count2 = rs.getInt("net");
                            series2.setLabel("Pending");
                            series2.set(AllocatedTo2, count2);

                        }else{
                             series2.setLabel("Pending");
                            series2.set(AllocatedTo2, 0);
                            
                        }
                        

                    }
                    
                    System.out.format("(%d). Completed --> %s\n", series1.getData().size(), series1.getData());
                    System.out.format("(%d). Pending --> %s\n", series2.getData().size(), series2.getData());
                    
                    //getting the sizes of the two series' 
                    int s1 = series1.getData().size();
                    int s2 = series2.getData().size();
                    while (s1 != s2) {
                        if (s1 > s2) {
                            for(Object key : series1.getData().keySet()) {
                                if (!series2.getData().containsKey(key)) {
                                    series2.set(key, 0);
                                    s1 = series1.getData().size();
                                    s2 = series2.getData().size();
                                }
                            }
                        }
                        if (s2 > s1) {
                            for (Object key : series2.getData().keySet()) {
                                if (!series1.getData().containsKey(key)) {
                                    series1.set(key, 0);
                                    s1 = series1.getData().size();
                                    s2 = series2.getData().size();
                                }
                            }
                        }
                    }
                    
                    System.out.format("(#%d). Completed --> %s\n", series1.getData().size(), series1.getData());
                    System.out.format("(#%d). Pending --> %s\n", series2.getData().size(), series2.getData());
                    
                    
                    TreeMap tm = new TreeMap(series1.getData()); 
                    TreeMap tm2 = new TreeMap(series2.getData());

                    
                    series1.setData(tm);
                    series2.setData(tm2);
                    

                    System.out.format("&Completed --> %s\n", series1.getData());
                    System.out.format("&Pending --> %s\n", series2.getData());

                   
                }
                else{
        series1.setLabel("Completed");
        series1.set("No Tasks", 0);
        series2.setLabel("Pending");
        series2.set("No Tasks ", 0);
                }
                    
            }
                   model.addSeries(series1);
                    model.addSeries(series2);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

}
