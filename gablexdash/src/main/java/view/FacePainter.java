/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import javax.faces.bean.*;

/**
 *
 * @author mspace
 */
@ManagedBean(name="facePainter")
@SessionScoped
public class FacePainter {
    private String mainContent = "content/01name.xhtml";

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

}