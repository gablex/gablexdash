/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.implementer.Excel;
import com.mspace1.implementer.messagesender;
import com.mspace1.implementer.updater;
import com.mspace1.model.TallowedAlphanumerics;
import com.mspace1.model.smsstatust;
import com.mspace1.navigationcontroller.FacePainter;
import com.mspace1.util.getsession;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 *
 * @author kevol geek
 */
@ManagedBean(name = "Exceluploader")
@ViewScoped
public class Exceluploader implements Serializable {

    @ManagedProperty(value = "#{facePainter}")

    public FacePainter facePainter;
    HttpSession sessionm = getsession.getSession();
    long id = (long) sessionm.getAttribute("id");
    String username = (String) sessionm.getAttribute("username");
    private UploadedFile uploadedFile;
    private String samplenumber;
    private String sheetname;
    private String getColumnName;
    FacesMessage messager;
    InputStream input = null;
    Workbook workbook = null;
    String fileName = null;
    private Date time;
    private String Message;
    private String To;
    FacesMessage LL;
    private String msg2;
    List<smsstatust> allstatus;
    String[][] Alldata;
    HttpSession session = getsession.getSession();
    updater checkbal = new updater();
    //value for preview message 
    senderaddressource m = new senderaddressource();
    List<String> names = new ArrayList<>();
    private List columnHeaders;
    int cols = 0;
    int rows = 0;
    private int rowsample;
    List<TallowedAlphanumerics> lista;
    private String fromaddress;
    private List<List> tableData;
    private List<smsstatust> filteredsms;
    Excel excel = new Excel();

    public Exceluploader() {
        getuserlist2();
    }

    public String getSamplenumber() {
        return samplenumber;
    }

    public void setSamplenumber(String samplenumber) {
        this.samplenumber = samplenumber;
    }

    public List<TallowedAlphanumerics> getLista() {
        return lista;
    }

    public void setLista(List<TallowedAlphanumerics> lista) {
        this.lista = lista;
    }

    private List<String> getalladdress = new ArrayList<>();

    public List<String> getGetalladdress() {
        return getalladdress;
    }

    public List<smsstatust> getFilteredsms() {
        return filteredsms;
    }

    public void setFilteredsms(List<smsstatust> filteredsms) {
        this.filteredsms = filteredsms;
    }

    public List<smsstatust> getAllstatus() {
        return allstatus;
    }

    public void setAllstatus(List<smsstatust> allstatus) {
        this.allstatus = allstatus;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }

    public int getRowsample() {
        return rowsample;
    }

    public void setRowsample(int rowsample) {
        this.rowsample = rowsample;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<List> getTableData() {
        return tableData;
    }

    public List getColumnHeaders() {
        return columnHeaders;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress;
    }

    public String getGetColumnName() {
        return getColumnName;
    }

    public void setGetColumnName(String getColumnName) {
        this.getColumnName = getColumnName;
    }

    public String getSheetname() {
        return sheetname;
    }

    public void setSheetname(String sheetname) {
        this.sheetname = sheetname;
    }

    public FacePainter getFacePainter() {
        return facePainter;
    }

    public void setFacePainter(FacePainter facePainter) {
        this.facePainter = facePainter;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void handleFileUpload(FileUploadEvent event) {

        if (event.getFile() != null) {
            uploadedFile = event.getFile();
            fileName = uploadedFile.getFileName();
            try {
                input = uploadedFile.getInputstream();
                //Create Workbook instance for xlsx/xls file input stream
                if (fileName.toLowerCase().endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(input);
                } else if (fileName.toLowerCase().endsWith("xls")) {
                    workbook = new HSSFWorkbook(input);
                }
            } catch (Exception kk) {
                kk.printStackTrace();
            }
            getsheetnames();

            try {
                System.out.println("Name of Excel uploaded is " + fileName);
                messager = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, messager);
                try {
                    excel.copyFile(fileName, uploadedFile.getInputstream());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception m) {
                m.printStackTrace();
                messager = new FacesMessage(" Not Succesful", " Check the columns of your Excel file.");
                FacesContext.getCurrentInstance().addMessage(null, messager);
            }

        } else {
            messager = new FacesMessage(" NOT Succesful", event.getFile().getFileName() + " is not  uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, messager);
        }

    }

    public void getsheetvalue() {
        tableData = new ArrayList();
         String[][] ret = null;
        try {

            //Get the nth sheet from the workbook
            Sheet sheet = workbook.getSheet(sheetname);
            System.out.println("Excel sheet selected : " + sheetname);
            getSheetCols();
            System.out.println("The Excel sheet has " + cols + " column " + " and " + rows + " rows");
             ret = new String[rows][cols];
            for (int d = 0; d < rows; d++) {
                Row row = sheet.getRow(d);
                String[] kevol = new String[cols];
                for (int j = 0; j < cols; j++) {             
                    
                    if (rows == 1 && cols == 1) {
                        kevol[j] = " ";
                        ret[d][j] = " ";
                        break;
                    }
                    try {
                        Cell cell2 = row.getCell(j);
                        //test code
//                        cell2.setCellType(1);
                        switch (cell2.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell2.equals("")) {
                                  ret[d][j] = " ";
                                    kevol[j] = " ";
                                } else {
//                                    System.out.println("Reading excel value "+cell2.getRichStringCellValue().getString());
                                    kevol[j] = cell2.getRichStringCellValue().getString();
                                    ret[d][j] = cell2.getRichStringCellValue().getString();
                                }

                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell2)) {
//                                     System.out.println("Reading excel of dated  "+cell2.getDateCellValue());
                                    kevol[j] = String.valueOf(cell2.getDateCellValue());
                                     ret[d][j] =String.valueOf(cell2.getDateCellValue());
                                }
                                
                                
                                
                                else {
                                     cell2.setCellType(Cell.CELL_TYPE_STRING);
                                      kevol[j]= cell2.getRichStringCellValue().getString();
                                     ret[d][j]= cell2.getRichStringCellValue().getString();
                                    
//                                    kevol[j] = String.valueOf(cell2.getNumericCellValue());
//                                     ret[d][j]=String.valueOf(cell2.getNumericCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                kevol[j] = String.valueOf(cell2.getBooleanCellValue());
                                ret[d][j] = String.valueOf(cell2.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                kevol[j] = (cell2.getCellFormula());
                                ret[d][j] =(cell2.getCellFormula());
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                kevol[j] = "";
                                 ret[d][j] ="";
                                     break;
                                     default:
                                         System.out.println();
                                          ret[d][j] = " ";
                                    kevol[j] = " ";
                        }

                    } catch (Exception ex) {
                        kevol[j] = "";
                         ret[d][j] ="";
                    }
 
                }
              Alldata=ret;
 tableData.add(Arrays.asList(kevol));
            }

            //end of sheets for loop
            //close file input stream
            columnHeaders = new ArrayList();
            for (int r = 0; r < cols; r++) {
                String hold = "col";
                String colnum = String.valueOf(r);
                String fullcol = hold.concat(colnum);
                System.out.println(fullcol);
                columnHeaders.add(fullcol);

//                columns.add(new ColumnModel(fullcol.toUpperCase(), fullcol));
            }

        } catch (Exception e) {
            messager = new FacesMessage(" Not Succesful", " Check the columns/ FORMAT of your Excel file.");
            FacesContext.getCurrentInstance().addMessage(null, messager);
        }

    }

    private void getsheetnames() {
        try {
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("Excel File has " + numberOfSheets + "Sheet");
            for (int i = 0; i < numberOfSheets; i++) {
                Sheet aSheet = workbook.getSheetAt(i);
                names.add(aSheet.getSheetName());
            }
        } catch (Exception kk) {
            kk.printStackTrace();
        }
        System.out.println("Names of Excel Sheet are " + names);
    }

    public String[] colsArray() {
        String[] columns = null;
        try {

            getSheetCols();

            columns = new String[cols];
            for (int r = 0; r < cols; r++) {
                String hold = "col";
                String colnum = String.valueOf(r);
                String fullcol = hold.concat(colnum);
                columns[r] = fullcol;
            }

            if (columns.length < 1) {
                System.out.println("the col names are:" + columns[1]);
            } else {

            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            messager = new FacesMessage(" Not Succesful", " Check the columns/ FORMAT of your Excel file/ the Excel is empty.");
            FacesContext.getCurrentInstance().addMessage(null, messager);
        }

        return columns;
    }

    private void getSheetCols() {
        Sheet sheet;
          int aa = 0;
        int bb = 0, bc = 0;
        try {
            sheet = workbook.getSheet(sheetname);
//            rows = sheet.getPhysicalNumberOfRows();

           
            Iterator rowIter = sheet.rowIterator();
            while (rowIter.hasNext()) {
                Row myRow = (Row) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                            while (cellIter.hasNext()) {
                Cell cell = (Cell) cellIter.next();
                bc = cell.getColumnIndex();
                if(bc > bb)
                bb = cell.getColumnIndex();
                aa = cell.getRowIndex();
            }
                
                  rows = aa + 1;
                 cols =  bb + 1;
                 
                  if (rows > 5) {
                rowsample = 4;
            } else if (rows > 1 && rows <= 5) {
                rowsample = rows - 1;
            } else {
                rowsample = 0;
            }
            }
        } catch (Exception m) {
            System.out.println("null Exception for workbook and sheet name");
        }

    }

    public List<String> shownamesheet() {
        return names;
    }

    public void selectcolumn() {
        getsheetvalue();
        m.getuserlist();
        facePainter.setMainContent("sms/selectcolumn.xhtml");
    }

    public void sendexcelsms() {
        getsavedmessage();
        facePainter.setMainContent("sms/sendexelsms.xhtml");
    }

    public List<String> getallAddrsvalue() {
        getalladdress.clear();
        String colname = getColumnName;
        int columnindex = 0;
        Sheet sheet = null;
        getSheetCols();
        try {
columnindex = Integer.parseInt(colname.substring(colname.lastIndexOf("l") + 1));
            sheet = workbook.getSheet(sheetname);
        } catch (Exception m) {
            System.out.println("null Exception");
        }
//every sheet has rows, iterate over them
        try {
            Iterator<Row> rowIterator;
            rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                //Get the row object
                Row row = rowIterator.next();
                //Every row has columns, get the column iterator and iterate over them
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    //Get the Cell object
                    Cell cell = cellIterator.next();
                    cell.setCellType(1);
                    if (cell.getColumnIndex() == columnindex) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                if (cell.getStringCellValue().equals("")) {
                                    getalladdress.add("");
                                } else {
                                    getalladdress.add(cell.getRichStringCellValue().getString());
                                }

                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                getalladdress.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                getalladdress.add(cell.getCellFormula());
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    getalladdress.add(String.valueOf(cell.getDateCellValue()));
                                } else {
                                    getalladdress.add(String.valueOf(cell.getNumericCellValue()));

                                }
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                getalladdress.add("");
                            default:

                        }
                    }

                }
            }//end of rows iterator
//            System.out.println(data +"hhhhhhhhh");

            //end of sheets for loop
            //close file input stream
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("null Exception");
            messager = new FacesMessage(" Number address failure ", " Check the columns/ FORMAT of your Address Column.");
            FacesContext.getCurrentInstance().addMessage(null, messager);
        }

        return getalladdress;
    }

    
    
    

    public void saveMessage() {
//        Alldata = this.getsheetvalue22();
        String colname = getColumnName;
        int columnindex = Integer.parseInt(colname.substring(colname.lastIndexOf("l") + 1));
        samplenumber = Alldata[rowsample][columnindex];

        int result;
        try {
            result = excel.saveMessage(Message, username, fromaddress);
            if (result > 0) {
                msg2 = excel.formatMsgDstv(Message);
                for (int j = 0; j < Alldata[rowsample].length; j++) {
                    try {
                        msg2 = msg2.replaceAll(";;;col" + j + ";;;", Alldata[rowsample][j]);

                    } catch (Exception e) {
                        messager = new FacesMessage("  Message Conversion Error ", " Message was not converted ");
                        FacesContext.getCurrentInstance().addMessage(null, messager);
                    }
                       
        if(msg2.length() >670){
          msg2 = msg2.substring(0,670);
    }
                }
            } else {
                messager = new FacesMessage(" Saving Message ", " Message was not Saved ");
                FacesContext.getCurrentInstance().addMessage(null, messager);

            }

        } catch (Exception m) {
            m.printStackTrace();

            messager = new FacesMessage(" Saving Message and Coversion Error  ", " Message was not Saved /converted ");
            FacesContext.getCurrentInstance().addMessage(null, messager);
        }

    }

    public void updateMessages() {
        String colname = getColumnName;

        if (Alldata != null) {

        } else {
            try {
//                Alldata = this.getsheetvalue22();
            } catch (Exception l) {
                System.out.println("null Exception for getAllData method");
            }
        }
        int columnindex = Integer.parseInt(colname.substring(colname.lastIndexOf("l") + 1));
        samplenumber = Alldata[rowsample][columnindex];
        try {
            msg2 = excel.formatMsgDstv(Message);
            for (int j = 0; j < Alldata[rowsample].length; j++) {
                try {
                    msg2 = msg2.replaceAll(";;;col" + j + ";;;", Alldata[rowsample][j]);

                } catch (Exception e) {
                    messager = new FacesMessage("  Message Conversion Error ", " Message was not converted ");
                    FacesContext.getCurrentInstance().addMessage(null, messager);
                }
            }
            
 
        } catch (Exception my) {
            my.printStackTrace();
        }
        
        if(msg2.length() >670){
          msg2 = msg2.substring(0,670);
    }
    }


    public void sendpreviewMessage() {

        String meso = msg2;
        Date timetosend = time;
        if (timetosend == null) {
            timetosend = new Date();
        }
        String from = fromaddress;
        String destination = To;
        System.out.println("Sending a preview message ...");
        allstatus = new messagesender().getsmsstatuslist(meso, from, destination, timetosend);
        if (allstatus.size() > 0) {
            LL = new FacesMessage(" Succesful", " Message sent successfully.");
            FacesContext.getCurrentInstance().addMessage(null, LL);
        } else {
            LL = new FacesMessage(" Not Succesful", " Message Not sent .");
            FacesContext.getCurrentInstance().addMessage(null, LL);
        }

    }
// updated

    public void getsavedmessage() {
        System.out.println("Excel column selected with address is " + getColumnName);
        String[] savedmessage = new String[2];
        savedmessage = excel.getMsg(username);

        if (Message != null && !Message.isEmpty()) {
            System.out.println("message noot null");
        } else {

            this.setMessage(savedmessage[0]);
            this.setFromaddress(savedmessage[1]);
        }
    }

    public void sendMySms() {
        if (allstatus == null || allstatus.isEmpty()) {


        }else{
               allstatus.clear();
        }


        if (time != null) {
        } else {
            time = new Date();
        }
        long remainigsms;
        remainigsms = checkbal.getSmsBalance();

//        Alldata = this.getsheetvalue22();

        if (getalladdress.size() > remainigsms) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('myDialogVar1').show();");

        } else {
            int checkcredit = excel.smsToSend(fileName, sheetname, getColumnName, Message, username, fromaddress, time);
            if (checkcredit > 0) {
                allstatus = excel.sendMySms20171024(uploadedFile, sheetname, getColumnName, Alldata, Message, fromaddress, time, username);

            }
            showsmsent();
        }
    }

    public void sendnewsms2() {
        if (allstatus == null || allstatus.isEmpty()) {
   

        }else{
               allstatus.clear();
        }
    
        if (time == null) {
            time = new Date();
        }
        long remainigsms;
        remainigsms = checkbal.getSmsBalance();

        if (remainigsms > 0) {
            int checkcredit = excel.smsToSend(fileName, sheetname, getColumnName, Message, username, fromaddress, time);
            if (checkcredit > 0) {
                allstatus = excel.sendMySms20171024(uploadedFile, sheetname, getColumnName, Alldata, Message, fromaddress, time, username);
                System.out.print("Number of sms sent  " + allstatus.size());
                showsmsent();
            } else {
                System.out.print("Sms Credit not Enough");
            }

        } else {
            System.out.print("No credit ");
            LL = new FacesMessage("Not  Succesful", " Kindly ask for credit Recharge ");
            FacesContext.getCurrentInstance().addMessage(null, LL);
        }

    }

    public List<smsstatust> sentsms() {
        return allstatus;

    }

    public void showsmsent() {
        facePainter.setMainContent("sms/Excelsmssent.xhtml");
    }

    public void resetall() {
        sheetname = null;
        getColumnName = null;
        uploadedFile = null;
        msg2 = null;
        if (names != null || !names.isEmpty()) {

            names.clear();
            Alldata = null;
//            System.out.println(names);
        } else {

        }
    }

    public void getuserlist2() {
        lista = m.getuserlist3();

    }

}
