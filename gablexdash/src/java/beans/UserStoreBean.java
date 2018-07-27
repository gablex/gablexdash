/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
import org.primefaces.model.UploadedFile;
 
@ManagedBean
@RequestScoped
public class UserStoreBean {
 
    private UploadedFile file;
    String itemname;
    Integer quantity;
    Double price;
     
    // Store file in the databas;
    
    public void storeUser() {
        // Create connection
        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dbSMS";
            // Connect to the database
           String username = "mysql";
		String password = "mysql";
            Connection connection =  DriverManager.getConnection(url, username, password);
            // Set autocommit to false to manage it by hand
            connection.setAutoCommit(false);
             
            // Create the statement object
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (firstname,lastname,uname) VALUES (?,?,?)");
             statement.setString (1, getFirstname());
             statement.setString (2, getLastname());
             statement.setString (3, getUname());
   
            statement.executeUpdate();
             
            // Commit & close
            connection.commit();    // when autocommit=false
            connection.close();
             
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "successfully inserted",getFirstname() + " is inserted.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
             
        } catch (Exception e) {
            e.printStackTrace();
             
            // Add error message
            FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert error", e.getMessage());  
            FacesContext.getCurrentInstance().addMessage(null, errorMsg);
        }
         
    }
 
    // Getter method
   
 
    // Setter method
      
   
 private String firstname;
	private String lastname;
	private String uname;

	public UserStoreBean() {
	}

	public UserStoreBean(String firstname, String lastname, Integer speed,
			String uname) {
		
		this.firstname= firstname;
		this.lastname = lastname;
	
		this.uname = uname;
	}

	

	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

}

