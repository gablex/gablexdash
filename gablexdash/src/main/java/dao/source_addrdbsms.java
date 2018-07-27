/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author mspace
 */

@ManagedBean(name = "source_addrdbsms")
@ViewScoped
public class source_addrdbsms implements Serializable {
 private BarChartModel animatedModel2;  
 
   //private static final String dbUserName = "dlr";
 private static final String dbUserName = "mysql";
    //private static final String dbPassword = "dlr123";
 private static final String dbPassword = "mysql123";
  //  private static final String dbServer = "jdbc:mysql://10.240.181.14:3306/dbSMS?useSSL=false";
     private static final String dbServer = "jdbc:mysql://192.168.1.51:3306/dbSMS?useSSL=false";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    Connection con = null;
    ResultSet result = null;
    Statement stmt = null;
    
    @PostConstruct
    public void source_addrdbsms() {
        try {
            createAnimatedModels();
        } catch (Exception k) {
        }
    }
    /**
     * Creates a new instance of source_addrdbsms
     */
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() throws ClassNotFoundException, SQLException {
        animatedModel2 = initBarModel();
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        animatedModel2.setShadow(true);
        animatedModel2.setZoom(true);
        animatedModel2.setShowDatatip(true);
        animatedModel2.setDatatipFormat("%2$d");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        xAxis.setTickAngle(-45);
    }

    private BarChartModel initBarModel() throws ClassNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd000000");
        Date date = new Date();
        String k = (dateFormat.format(date));

        String sql = "Select count(*) as net, source_addr from `tSMSOUT` where status >=0 and time_submitted > '" + k + "' group by source_addr";

        BarChartModel model = new BarChartModel();

        try {
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbServer, dbUserName, dbPassword);
            stmt = con.createStatement();
            result = stmt.executeQuery(sql);

            ChartSeries boys = new ChartSeries();
            boys.setLabel("Sorce addr");

            while (result.next()) {
                boys.set(result.getString("source_addr"), result.getInt("net"));
            }
            model.addSeries(boys);
        } catch (SQLException m) {
            m.printStackTrace();
        } finally {
            try {
                result.close();
                stmt.close();
                con.close();
            } catch (SQLException l) {
            }
        }

        return model;
    }

}
