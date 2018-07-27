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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import model.graphvalue;

/**
 *
 * @author kevol
 */
@ManagedBean(name = "ChartView")

public class ChartView implements Serializable {

    private BarChartModel animatedModel2;  

 //private static final String dbUserName = "dlr";
 private static final String dbUserName = "mysql";
    //private static final String dbPassword = "dlr123";
 private static final String dbPassword = "mysql123";
    //private static final String dbServer = "jdbc:mysql://10.240.181.14:3306/dbSMPPGateway?useSSL=false";
    private static final String dbServer = "jdbc:mysql://192.168.1.51:3306/dbSMPPGateway?useSSL=false";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    Connection con = null;
    ResultSet result = null;
    Statement stmt = null;

    @PostConstruct
    public void ChartView() {
        try {
            createAnimatedModels();
        } catch (Exception k) {
        }
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() throws ClassNotFoundException, SQLException {
        animatedModel2 = initBarModel();
        animatedModel2.setLegendPosition("ne");
animatedModel2.setDatatipFormat("%2$d");
   animatedModel2.setAnimate(true);
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        Axis xAxis = animatedModel2.getAxis(AxisType.X);
        xAxis.setTickAngle(-30);
    }

    private BarChartModel initBarModel() throws ClassNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd000000");
        Date date = new Date();
        String k = (dateFormat.format(date));

        String sql3 = "select count(*) as net, status from dbSMPPGateway.tSMSOUT where (status >0 and time_submitted > '" + k + "') or status = 0 group by status";
 BarChartModel model = new BarChartModel();
        try {
            Class.forName(dbDriver);
            con = DriverManager.getConnection(dbServer, dbUserName, dbPassword);
            stmt = con.createStatement();
            result = stmt.executeQuery(sql3);

            graphvalue cust = new graphvalue();
            while (result.next()) {
                switch (result.getInt("status")) {
                    case 0:
                        cust.setTo_be_sent(result.getInt("net"));
                        break;
                    case 1:
                        cust.setSubmitted_to_network(result.getInt("net"));
                        break;
                    case 2:
                        cust.setNetwork3(result.getInt("net"));
                        break;
                    case 3:
                        cust.setDelivered(result.getInt("net"));
                        break;
                    case 4:
                        cust.setNetwork(result.getInt("net"));
                        break;
                    case 5:
                        cust.setUndelivered(result.getInt("net"));
                        break;
                    case 6:
                        cust.setExpired(result.getInt("net"));
                        break;
                    case 7:
                        cust.setSubmit_failed(result.getInt("net"));
                        break;
                    case 8:
                        cust.setNetwork2(result.getInt("net"));
                        break;
                         case 9:
                        cust.setOptedOut(result.getInt("net"));
                        break;
                    case 11:
                        cust.setScheduled(result.getInt("net"));
                        break;
                    default:
                        break;
                }

            }

            ChartSeries boys = new ChartSeries();
            boys.setLabel("Sent SMS");
            boys.set("to be sent", cust.getTo_be_sent());
            boys.set("submitted-network", cust.getSubmitted_to_network());
      
            boys.set("deliverd", cust.getDelivered());
            boys.set("Network", cust.getNetwork() + cust.getNetwork2()+cust.getNetwork3());
            boys.set("undelivered", cust.getUndelivered());
            boys.set("expired", cust.getExpired());
            boys.set("submit failed", cust.getSubmit_failed());
            boys.set("scheduled", cust.getScheduled());
 boys.set("Opted out", cust.getOptedOut());
            model.addSeries(boys);
        } catch (SQLException m) {
        } finally {

            try {
                result.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return model;
    }

}
