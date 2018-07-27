/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mspace1.controller;

import com.mspace1.implementer.Excel;
import com.mspace1.model.Tuseraddressbook;
import com.mspace1.navigationcontroller.FacePainter;
import static com.mspace1.util.HibernateUtil.getSessionFactory;
import com.mspace1.util.getsession;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author kevol geek
 */

@ManagedBean(name = "fileuploader")
@ViewScoped
public class fileuploader extends com.mspace1.functions.davFunctions implements Serializable{
 @ManagedProperty(value = "#{facePainter}")
    public FacePainter facePainter; 
    HttpSession sessionm = getsession.getSession();
    long id = (long) sessionm.getAttribute("id");
UploadedFile uploadedFile;
   private  List<Tuseraddressbook> list;
    FacesMessage message;

    List<Tuseraddressbook> countriesList = new ArrayList<Tuseraddressbook>() ;

    public fileuploader() {
    }

    public List<Tuseraddressbook> getList() {
        return list;
    }

    public void setList(List<Tuseraddressbook> list) {
        this.list = list;
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

    public List<Tuseraddressbook> handleFileUpload(FileUploadEvent event) {
  Excel excel= new Excel();
        if (event.getFile() != null) {
     
            uploadedFile = event.getFile();
              String   fileName = uploadedFile.getFileName();
            try{
            list = readExcelData();
             message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
                      try {
                    System.out.println("Excecuting file copy");
           excel.copyFile(fileName, uploadedFile.getInputstream()); 
        } catch (Exception e) {
            e.printStackTrace();
        }
          
            }catch(Exception m){ 
                m.printStackTrace();
                message = new FacesMessage(" Not Succesful", " Check the columns of your Excel file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
           
            return list;
        } else {
            message = new FacesMessage(" NOT Succesful", event.getFile().getFileName() + " is not  uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
    }

    public List<Tuseraddressbook> mgetuploaded() {
        return list;
    }

    public List<Tuseraddressbook> readExcelData() {
        String fileName = uploadedFile.getFileName();
        try {

            try (
                    InputStream input = uploadedFile.getInputstream()) {
                //Create Workbook instance for xlsx/xls file input stream
                Workbook workbook = null;
                if (fileName.toLowerCase().endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(input);
                } else if (fileName.toLowerCase().endsWith("xls")) {
                    workbook = new HSSFWorkbook(input);
                }

                //Get the number of sheets in the xlsx file
                int numberOfSheets = workbook.getNumberOfSheets();

                //loop through each of the sheets
                for (int i = 0; i < numberOfSheets; i++) {

                    //Get the nth sheet from the workbook
                    Sheet sheet = workbook.getSheetAt(i);

                    //every sheet has rows, iterate over them
                    Iterator<Row> rowIterator = sheet.iterator();
                    while (rowIterator.hasNext()) {

                        String groupName = "";
                        String contactName = "";
                        String contactNumber = "";
                        long userId = 0;

                        //Get the row object
                        Row row = rowIterator.next();

                        //Every row has columns, get the column iterator and iterate over them
                        Iterator<Cell> cellIterator = row.cellIterator();

                        while (cellIterator.hasNext()) {
                            //Get the Cell object
                            Cell cell = cellIterator.next();


                                    if (groupName.equalsIgnoreCase("")) {
                                        groupName = this.formatAddress(cell.getStringCellValue().trim());
                                        userId = id;

                                    } else if (contactName.equalsIgnoreCase("")) {
                                        //2nd column
                                        contactName =this.formatAddress(cell.getStringCellValue().trim());
                                      
                                    } else if (contactNumber.equalsIgnoreCase("")) {
                                        //3nd column
                                        String contactNumber2 = cell.getStringCellValue().trim();
                                        
                                       if (contactNumber2.matches("[0-9,+]+") && contactNumber2.length() > 2) {
                                           this.formatNumbersOnly(contactNumber2);
                                            contactNumber =this.formatMobileNo(contactNumber2);
                                                   }
                                        else{
                                           groupName=null;
                                           contactName=null;
                                            contactNumber=null;
                                  
                                         message = new FacesMessage(" Kindly ", " Check the columns of your Excel file That contain "
                                                 + "Mobile Number "+"It contain Invalid Mobile Number "+" "+contactNumber2);
                                        FacesContext.getCurrentInstance().addMessage(null, message);
                                        }
                                        
                                        
                                     
                  
                                    
                                    } else {
                                        //random data, leave it
                                        message = new FacesMessage(" Not Succesful", " Check the columns of your Excel file.");
                                        FacesContext.getCurrentInstance().addMessage(null, message);
                                    }
                    
                        } //end of cell iterator
                        if (contactNumber !=null){
                        Tuseraddressbook c = new Tuseraddressbook(userId, groupName, contactName, contactNumber);
                         countriesList.add(c);}
 
                    } //end of rows iterator

                } //end of sheets for loop

                //close file input stream
            }

        } catch (IOException e) {
//            e.printStackTrace();
 message = new FacesMessage(" Not Succesful", " Check the columns/ FORMAT of your Excel file.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        }

        return countriesList;

    }
    
        public String formatAddress(String msg)
  {
    String acceptstr = " 1234567890abcdefghijklmnopqrstuvwxyz";

        msg = msg.replaceAll("\"", "'");
        StringBuffer msg2 = new StringBuffer();
//        String nuMsg = "";
        for (int i = 0; i < msg.length(); i++) {
          if (acceptstr.indexOf(String.valueOf(msg.charAt(i)).toLowerCase()) >-1)
          {
//            nuMsg = nuMsg + String.valueOf(msg.charAt(i));
            msg2 = msg2.append(msg.charAt(i));
          }
        }

    return msg2.toString();
  }
        
  public void add_address() {
   Session session = getSessionFactory().openSession();
    try  {
        
        session.beginTransaction();
        for(Tuseraddressbook address : countriesList){
           session.save(address);
        }
        session.getTransaction().commit();
          message = new FacesMessage(" Address Updated Succesfully", "" );
        FacesContext.getCurrentInstance().addMessage(null, message);
    }catch(HibernateException k){
        k.printStackTrace();
     }finally{
    session.close();
    }
  System.out.println("address inserted");
  showsmsent();
  }  
     public void showsmsent() {
        facePainter.setMainContent("sms/addressbook.xhtml");
    }
 
 public void reset() {
     
      if (list == null || list.isEmpty()) {
           
    
        } else if ((list != null || !list.isEmpty())) {
                    list.clear();
//               System.out.println("nulled");
 }
        else{ 
        
        
        
        }


    }
}
