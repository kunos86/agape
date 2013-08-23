package pl.ksb.agape.view.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import pl.ksb.agape.domain.model.User;
import pl.ksb.agape.service.UserManagement;



@ManagedBean(name = "registration")
@ViewScoped

public class UserRegistration implements Serializable {

	private static final long serialVersionUID = 3073828016819449376L;

//
	@EJB
	private UserManagement userManagement;
	
	

	@Inject
	private FacesContext facesContext;
	
	
	private User user = new User();
	private String password2;
	
	private boolean regulations;
	
	
	


	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public boolean isRegulations() {
		return regulations;
	}

	public void setRegulations(boolean regulations) {
		this.regulations = regulations;
	}





	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String save() {
		String ret="";
		if (!regulations){
			facesContext.addMessage(null, new FacesMessage( "Niezaakceptowales regulaminu. Aby sie zarejestrowac musisz zaakceptowac regulamin servisu."));
			return "";
		}
		
		try {
			userManagement.register(user);
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
