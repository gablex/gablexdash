/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

public class User {

	
	private String source;
	private String dateReceived;
	private String status;
        private String allocatedTo;
        private String subject;
        private String message;

	public User() {
	}

	public User(String source, String dateReceived, 
			String status,String allocatedTo,String subject,String message) {
		
		this.source= source;
		this.dateReceived = dateReceived;
	
		this.status = status;
                this.subject=subject;
                this.allocatedTo=allocatedTo;
                this.message=message;
	}
	

	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
        
        public void setAllocateTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}
        public String getAllocatedTo() {
		return allocatedTo;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
        public String getSubject() {
		return subject;
	}


	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
        public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}