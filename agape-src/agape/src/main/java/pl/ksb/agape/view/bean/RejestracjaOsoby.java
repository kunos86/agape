package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import pl.ksb.agape.domain.model.Osoba;
import pl.ksb.agape.service.UserManagement;



@ManagedBean(name = "rejestracja")
@ViewScoped

public class RejestracjaOsoby implements Serializable {

	private static final long serialVersionUID = 3073828016819449376L;

//
	@EJB
	private UserManagement userManagement;
	
	

	@Inject
	private FacesContext facesContext;
	
	
	private Osoba osoba = new Osoba();
	private String haslo2;
	
	private boolean regulamin;
	
	
	
	public boolean isRegulamin() {
		return regulamin;
	}

	public void setRegulamin(boolean regulamin) {
		this.regulamin = regulamin;
	}

	public String getHaslo2() {
		return haslo2;
	}

	public void setHaslo2(String haslo2) {
		this.haslo2 = haslo2;
	}

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public String zapisz() {
		String ret="";
		if (!regulamin){
			facesContext.addMessage(null, new FacesMessage( "Niezaakceptowales regulaminu. Aby sie zarejestrowac musisz zaakceptowac regulamin servisu."));
			return "";
		}
		
		try {
			userManagement.register(osoba);
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Rejestracja zakończona powodzeniem. Zaloguj się, aby przejść do servisu.",
					"Rejestracja zakończona"));
			
				ret =  "registered";
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd rejestracji osoby", "Wystąpił błąd podczas rejestracji nowej osoby. "));
		}
		return ret;
	}
}
